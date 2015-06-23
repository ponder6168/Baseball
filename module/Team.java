package module;

import java.io.Serializable;
import java.util.ArrayList;


public class Team implements Serializable{

	public static final int BASE_IS_OPEN=-1;
	private static final long serialVersionUID = 810767236843005054L;
	private ArrayList<Player> team = new ArrayList<Player>();
	private int currentBatter;
	private Boolean playersCanSteal;
	private String description;
	private int outsInTheTeamAtBat;
	private int maximumOutsInAnAtBat=3;
	private int numberOfPlayersOnATeam = 9;
	private int runsScoredInTheTeamAtBat;

	// baseStatus[0]=n means the nth batter is on first, baseStatus[0]=BASE_IS_OPEN=-1 means the base is open
	private int[] baseStatus =  new int[]{BASE_IS_OPEN,BASE_IS_OPEN,BASE_IS_OPEN} ; 

	// Creates a team where every player has the default values.
	public Team(String string){
		this.team=new ArrayList<Player>();
		for(int i=0; i<numberOfPlayersOnATeam ; i++){
			this.team.add(new Player("default"));
		}
		this.currentBatter=0; //The leadoff hitter has an index of 0.
		this.description="Default Team";
		this.playersCanSteal = true;
	}

	public Team(Team source){
		currentBatter=source.currentBatter;
		playersCanSteal=source.playersCanSteal;
		description = source.description;
		for(Player player: source.team){
			team.add(new Player(player));
		}
	}

	public int runsFromTeamAtBat( ){
		clearBases();
		outsInTheTeamAtBat=0;
		runsScoredInTheTeamAtBat=0;
		while(teamAtBatIsNotOver()){
			AtBatResult whatTheBatterDid = team.get(currentBatter).resultOfPlayerAtBat();
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
		return Math.random()<team.get(getWhoIsOnBase(Bases.FIRST)).getPercentageStolenBases();
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


	//The players are numbered starting at 1 for output, but 0 in the array.
		public void printTeamStandard(){
			System.out.println();
			System.out.println("Team Description: "+this.getDescription());
			System.out.println();
			System.out.println("Player At Bats  Hits  Singles  Doubles  Triples  Home Runs  Walks  Stolen Bases  Caught Stealing");
			for(int i=0;i<9;i++){
				System.out.format("%4d %8d %5d %8d %8d %8d %10d %6d %9d %16d%n",i+1,this.getTeam().get(i).getAtBats(),
						this.getTeam().get(i).getHits(),
						this.getTeam().get(i).getSingles(),
						this.getTeam().get(i).getDoubles(),
						this.getTeam().get(i).getTriples(),
						this.getTeam().get(i).getHomeRuns(),
						this.getTeam().get(i).getWalks(),
						this.getTeam().get(i).getStolenBases(),
						this.getTeam().get(i).getCaughtStealing());
			}
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
						,player+1,this.getTeam().get(player).getAtBats(),
						this.getTeam().get(player).getHits(),
						this.getTeam().get(player).getSingles(),
						this.getTeam().get(player).getDoubles(),
						this.getTeam().get(player).getTriples(),
						this.getTeam().get(player).getHomeRuns(),
						this.getTeam().get(player).getWalks(),
						this.getTeam().get(player).getStolenBases(),
						this.getTeam().get(player).getCaughtStealing());
			}
			return heading+firstColumnHeadings+secondColumnHeadings+playerValues;
		}

	//The players are numbered starting at 1 for output, but 0 in the array.
	public void printTeamPlayer(int player){
		System.out.println("Player At Bats  Hits  Singles  Doubles  Triples  Home Runs  Walks  Stolen Bases  Caught Stealing");
		System.out.format("%4d %8d %5d %8d %8d %8d %10d %6d %9d %16d%n",player+1,this.getTeam().get(player).getAtBats(),
				this.getTeam().get(player).getHits(),
				this.getTeam().get(player).getSingles(),
				this.getTeam().get(player).getDoubles(),
				this.getTeam().get(player).getTriples(),
				this.getTeam().get(player).getHomeRuns(),
				this.getTeam().get(player).getWalks(),
				this.getTeam().get(player).getStolenBases(),
				this.getTeam().get(player).getCaughtStealing());

	}


	public ArrayList<Player> getTeam() {
		ArrayList<Player> team = new ArrayList<>();
		int playerLocation=0;
		for(Player player: this.team){
			team.add(new Player(player));
		}
		return team;
	}


	public void setTeam(ArrayList<Player> team) {
		int playerLocation=0;
		for(Player player: team){
			this.team.set(playerLocation, new Player(player));
		}
	}

	public Team(ArrayList<Player> team) {
		super();
		this.team = team;
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
		this.team.set(positionOfPlayerInBattingOrder, player);
	}

	public Player getPlayer(int positionOfPlayerInBattingOrder){
		return this.team.get(positionOfPlayerInBattingOrder);
	}

}
