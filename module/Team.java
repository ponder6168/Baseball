package module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import module.Player.PlayerStats;
import view.Input;


public class Team implements Serializable, Storable {

	public static final int BASE_IS_OPEN=-1;
	public static final int NUMBER_OF_PLAYERS_ON_TEAM = 9;
	private static final long serialVersionUID = 810767232243005054L;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentBatter;
	private Boolean playersCanSteal;
	private String description;
	private int outsInTheTeamAtBat;
	private int maximumOutsInAnAtBat=3;
	private int runsScoredInTheTeamAtBat;

	// baseStatus[0]=n means the nth batter is on first, baseStatus[0]=BASE_IS_OPEN=-1 means the base is open
	private int[] baseStatus =  new int[]{BASE_IS_OPEN,BASE_IS_OPEN,BASE_IS_OPEN} ; 

	// Creates a team where every player has the default values.
	public Team(){
		this.players=new ArrayList<Player>();
		for(int i=0; i<NUMBER_OF_PLAYERS_ON_TEAM ; i++){
			this.players.add(new Player());
		}
		this.currentBatter=0; //The leadoff hitter has an index of 0.
		this.description="Default Team";
		this.playersCanSteal = true;
	}

	public Team(Team source){
		currentBatter=source.currentBatter;
		playersCanSteal=source.playersCanSteal;
		description = source.description;
		for(Player player: source.getPlayers()){
			players.add(new Player(player));
		}
	}
	
	//Create team of test players for JUnit Testing
	public Team(TestPlayer player){
		this.players=new ArrayList<Player>();
		for(int i=0; i<NUMBER_OF_PLAYERS_ON_TEAM ; i++){
			this.players.add(player);
		}
		this.currentBatter=0; //The leadoff hitter has an index of 0.
		this.description="Default Team";
		this.playersCanSteal = true;
	}

	
	@Override
	public Storable deepCopy() {
		return new Team(this);
	}

	public Team(Storable team2) {
		this((Team) team2);
	}

	public int runsFromTeamAtBat( ){
		clearBases();
		outsInTheTeamAtBat=0;
		runsScoredInTheTeamAtBat=0;
		while(teamAtBatIsNotOver()){
			AtBatResult whatTheBatterDid = players.get(currentBatter).resultOfPlayerAtBat();
			updateRunsInTheInning(whatTheBatterDid);
			advanceRunners(whatTheBatterDid);
			updateOuts(whatTheBatterDid);
			updateBatter();
			performSteals();
		}
		return runsScoredInTheTeamAtBat;
	}

	private void clearBases() {
		baseStatus =  new int[]{BASE_IS_OPEN,BASE_IS_OPEN,BASE_IS_OPEN} ; 
	}

	private boolean teamAtBatIsNotOver() {
		return outsInTheTeamAtBat<maximumOutsInAnAtBat;
	}

	private void updateRunsInTheInning(AtBatResult whatTheBatterDid) {
		// All hits score runners in scoring position (2nd & 3rd base)
		// Triples & Home Runs score a runner on 1st
		// Home Runs score the batter
		// A walk scores a runner if the bases are loaded

		switch(whatTheBatterDid){
		case OUT: 	
			break;
		case WALK: 	if(basesAreLoaded())
			runsScoredInTheTeamAtBat++;
		break;
		case HOMERUN:	runsScoredInTheTeamAtBat++; // Batter scores on HomeRun
		case TRIPLE:	if(baseIsOccupied(Bases.FIRST)) //Runner on First scores on HomeRun and Triple
			runsScoredInTheTeamAtBat++;
		case DOUBLE: 	
		case SINGLE: 	runsScoredInTheTeamAtBat+=runnersInScoringPosition(); //Runners on 2nd & 3rd scoreon all hits
		break;
		default:		
		}	
		return;
	}

	private void advanceRunners(AtBatResult whatTheBatterDid) {
		switch(whatTheBatterDid){
		case OUT: 	
			break;
		case WALK: 	advanceRunnersForWalk();
		break;
		case SINGLE: 	advanceRunnersForSingle();
		break;
		case DOUBLE: 	advanceRunnersForDouble();
		break;
		case TRIPLE: 	advanceRunnersForTriple();
		break;
		case HOMERUN: 	advanceRunnersForHomeRun();
		break;

		default:		
		}
	}

	private void advanceRunnersForWalk(){
		if(baseIsOccupied(Bases.FIRST) && baseIsOccupied(Bases.SECOND)){
			setBase(Bases.THIRD, getWhoIsOnBase(Bases.SECOND));
		}
		if(baseIsOccupied(Bases.FIRST)){
			setBase(Bases.SECOND, getWhoIsOnBase(Bases.FIRST));
		}
		setBase(Bases.FIRST, currentBatter);
		return ;
	}

	private void advanceRunnersForSingle() {
		setBase(Bases.THIRD, BASE_IS_OPEN);
		setBase(Bases.SECOND, getWhoIsOnBase(Bases.FIRST));
		setBase(Bases.FIRST, currentBatter);
	}

	private void advanceRunnersForDouble() {
		setBase(Bases.THIRD, getWhoIsOnBase(Bases.FIRST));
		setBase(Bases.FIRST, BASE_IS_OPEN);
		setBase(Bases.SECOND, currentBatter);
	}

	private void advanceRunnersForTriple() {
		setBase(Bases.SECOND, BASE_IS_OPEN);
		setBase(Bases.FIRST, BASE_IS_OPEN);
		setBase(Bases.THIRD, currentBatter);
	}

	private void advanceRunnersForHomeRun() {
		setBase(Bases.THIRD, BASE_IS_OPEN);
		setBase(Bases.SECOND, BASE_IS_OPEN);
		setBase(Bases.FIRST, BASE_IS_OPEN);
	}

	private boolean baseIsOccupied(Bases base) {
		if(baseStatus[base.base()] != BASE_IS_OPEN)
			return true;
		else 
			return false;
	}

	private int getWhoIsOnBase(Bases base) {
		return baseStatus[base.base()];
	}

	private void setBase(Bases base, int whoIsOnBase) {
		baseStatus[base.base()]=whoIsOnBase;
	}

	private void updateOuts(AtBatResult whatTheBatterDid) {
		if(whatTheBatterDid==AtBatResult.OUT){
			outsInTheTeamAtBat++;
		}
		return;
	}

	private void updateBatter() {
		if(++currentBatter>8){
			currentBatter=0;
		}
	}

	private void performSteals() {
		if(!playersCanSteal || outsInTheTeamAtBat==3 || !runnerOnFirstAndSecondIsOpen()){
			return;
		}
		if(stealIsSuccessful()){
			setBase(Bases.SECOND, getWhoIsOnBase(Bases.FIRST));
		}
		else{
			outsInTheTeamAtBat++;
		}
		setBase(Bases.FIRST, BASE_IS_OPEN);
	}

	private boolean stealIsSuccessful() {
		//If the random number is below the successful steal percentage of the runner on first, the steal is successful
		return Math.random()<players.get(getWhoIsOnBase(Bases.FIRST)).getPercentageStolenBases();
	}

	private boolean runnerOnFirstAndSecondIsOpen() {
		return getWhoIsOnBase(Bases.SECOND)==BASE_IS_OPEN && getWhoIsOnBase(Bases.FIRST)!=BASE_IS_OPEN;
	}

	private boolean basesAreLoaded() {
		return baseIsOccupied(Bases.FIRST) && baseIsOccupied(Bases.SECOND) && baseIsOccupied(Bases.THIRD);
	}

	private int runnersInScoringPosition() {
		if(baseIsOccupied(Bases.SECOND) && baseIsOccupied(Bases.THIRD))
			return 2;
		else if(baseIsOccupied(Bases.SECOND) || baseIsOccupied(Bases.THIRD))
			return 1;
		return 0;
	}

		@Override
		//The players are numbered starting at 1 for output, but 0 in the array.
		public String toString(){
				String heading = String.format("%n%n%s%n%n", this.description);
				String firstColumnHeadings = String.format("%s%n", "          At                                                      Stolen    Caught ");
				String secondColumnHeadings = String.format("%s%n", "Player   Bats  Hits  Singles  Doubles  Triples  Home Runs  Walks  Bases    Stealing");
				String playerValues = "";
			for(int player=0;player<9;player++){
					playerValues +=  String.format("%4d %7d %5d %7d %7d %8d %9d %8d %7d %8d%n"
						,player+1,this.players.get(player).getAtBats(),
						this.players.get(player).getHits(),
						this.players.get(player).getSingles(),
						this.players.get(player).getDoubles(),
						this.players.get(player).getTriples(),
						this.players.get(player).getHomeRuns(),
						this.players.get(player).getWalks(),
						this.players.get(player).getStolenBases(),
						this.players.get(player).getCaughtStealing());
			}
			return heading+firstColumnHeadings+secondColumnHeadings+playerValues;
		}

	public ArrayList<Player> getPlayers() {
		ArrayList<Player> tempPlayerList = new ArrayList<>();
		for(Player player:this.players){
			tempPlayerList.add(new Player(player));
		}
		return tempPlayerList;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players.clear();
		for(Player player:players){
			this.players.add(player);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCanSteal() {
		return playersCanSteal;
	}

	public void setCanSteal(Boolean playersCanSteal) {
		this.playersCanSteal = playersCanSteal;
	}

	public int getCurrentBatter() {
		return currentBatter;
	}

	public void setCurrentBatter(int currentBatter) {
		this.currentBatter = currentBatter;
	}

	// First position in the batting order has an index of 0.
	public void setPlayer(Player player, int positionOfPlayerInBattingOrder){
		this.players.set(positionOfPlayerInBattingOrder, new Player(player));
	}

	public Player getPlayer(int positionOfPlayerInBattingOrder){
		return  this.players.get(positionOfPlayerInBattingOrder);
	}
	
	
	public Player getPlayerFromConsole(String prompt) {
		Player player;
		do{
			System.out.print(this);
			int chosenPlayer = Input.getIntegerFromMinToMax
									(1, Team.NUMBER_OF_PLAYERS_ON_TEAM,prompt);
			player = this.getPlayer(chosenPlayer-1);
		}while(!correctPlayerIsChosen(player));
		return  player;
	}

	private boolean correctPlayerIsChosen(Player player) {
		System.out.print(player);
		return Input.getYesOrNoFromTheUser("Is this the correct player? (y/n)").equals("y");
	}

	public void createTeamFromTheConsole() {
		int positionOfPlayerInBattingOrder=1;
		for(Player player:this.players){
			System.out.format("%n%n%s%s%s%n%n%n","     Enter the stats for player number ",
					   					positionOfPlayerInBattingOrder++,
					   					" in the batting order.");
			player.setAllPlayerStatsFromConsole();
		}
	}

	public void displayTeamWithMessage(String prompt) {
		System.out.format("%n%n%n%s", prompt);
		System.out.print(this);
	}
	
	public void setSelectedStatForTeam(){
		int usersChoice;
		do{
			displayTeamWithMessage("Here is the current team");
			usersChoice = getIndexOfUsersChoice();
			setStatOfUsersChoice(usersChoice);
		}while(userWantsToChangeAnotherStat(usersChoice));
	}
	
	private int getIndexOfUsersChoice() {
		String promptMessage = new Player().getMenuDisplay();
		return Input.getIntegerFromMinToMax(1, PlayerStats.values().length+1, promptMessage)-1;
	}

	private void setStatOfUsersChoice(int usersChoice) {
		if(userWantsToChangeAnotherStat(usersChoice)){
			int statValue = Input.getIntegerFromMinToMax(0, Integer.MAX_VALUE, "Enter the new value.");
			PlayerStats statToChange = getPlayerStatToChange(usersChoice); 
			setTeamStat(statToChange, statValue);
		}
	}
	
	public void setTeamStat(PlayerStats statToChange, int statValue){
		for(Player player:players){
			player.setStatWithValue(statToChange, statValue);
		}
	}

	private boolean userWantsToChangeAnotherStat(int usersChoice) {
		return usersChoice < PlayerStats.values().length;
	}

	private PlayerStats getPlayerStatToChange(int usersChoice) {
		return PlayerStats.values()[usersChoice];
	}

	public void modifyBattingOrder() {
		displayTeamWithMessage("Here is the current batting order.");
		do{
			// Subtract 1 to adjust for 0 based index
			int indexOfPlayerToMove = 
					Input.getIntegerFromMinToMax(1, 
							Team.NUMBER_OF_PLAYERS_ON_TEAM,
							"Enter the player you want to move.")-1; 
			int indexOfWhereToMovePlayerTo = 
					Input.getIntegerFromMinToMax(1, 
							Team.NUMBER_OF_PLAYERS_ON_TEAM,
							"Enter where you want to move the player to.")-1;
			movePlayerToNewLocation(indexOfPlayerToMove,indexOfWhereToMovePlayerTo);
			displayTeamWithMessage("Here is the updated batting order.");
		}while(userWantsToMoveAnotherPlayer());
	}

	private void movePlayerToNewLocation(int indexOfPlayerToMove, int indexOfWhereToMovePlayerTo) {
		if(indexOfWhereToMovePlayerTo>indexOfPlayerToMove){
			Collections.rotate(players.subList(indexOfPlayerToMove, indexOfWhereToMovePlayerTo+1), -1);
		}else{
			Collections.rotate(players.subList(indexOfWhereToMovePlayerTo, indexOfPlayerToMove+1), 1);
		}
	}

	private boolean userWantsToMoveAnotherPlayer() {
		return Input.getYesOrNoFromTheUser("Do you want to move another player? (y/n)").equals("y");
	}


}
