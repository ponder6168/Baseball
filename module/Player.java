package module;

import java.io.Serializable;

import view.Input;


public class Player implements Serializable{
	
	public enum PlayerStats{
		AT_BATS (".  Player's At Bats"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setAtBats(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the At Bats for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setAtBats(statValue);
			}
		},
		HITS (".  Player's Hits"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setHits(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Hits for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setHits(statValue);
			}
		},
		DOUBLES (".  Player's Doubles"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setDoubles(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Doubles for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setDoubles(statValue);
			}
		},
		TRIPLES (".  Player's Triples"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setTriples(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Triples for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setTriples(statValue);
			}
		},
		HOME_RUNS (".  Player's Home Runs"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setHomeRuns(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Home Runs for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setHomeRuns(statValue);
			}
		},
		WALKS (".  Player's Walks"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setWalks(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Walks for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setWalks(statValue);
			}
		},
		STOLEN_BASES (".  Player's Stolen Bases"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setStolenBases(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the Stolen Bases for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setStolenBases(statValue);
			}
		},
		CAUGHT_STEALING (".  Player's times Caught Stealing"){
			@Override
			public void setPlayerStatWithPrompt(Player player){
				player.setCaughtStealing(Input.getIntegerFromMinToMax
						(0, Integer.MAX_VALUE, "Enter the times Caught Stealing for the player. "));
			}
			@Override
			public void setPlayerStat(Player player, int statValue){
				player.setCaughtStealing(statValue);
			}
		};

		private String description;
		
		private PlayerStats(String description){
			this.description = description;
		}
		
		@Override
		public String toString(){
			return this.description;
		}
		
		public abstract void setPlayerStatWithPrompt(Player player);
		public abstract void setPlayerStat(Player player, int statValue);
	}
	
	private static final long serialVersionUID = -528563233318771579L;
	private int atBats;
	private int hits;
	private int doubles;
	private int triples;
	private int homeRuns;
	private int walks;
	private int stolenBases;
	private int caughtStealing;
	
	private double singleCutOff;
	private double doubleCutOff;
	private double tripleCutOff;
	private double homeRunCutOff;
	private double walkCutOff;
	
	private String menuDisplay;

	public Player () {
		//  returns a Player object with default values
		this.atBats = 550;
		this.hits = 151;
		this.doubles = 27;
		this.triples = 2;
		this.homeRuns = 15;
		this.walks = 50;
		this.stolenBases = 10;
		this.caughtStealing = 5;
		setCutOffs();
		buildMenuDisplay();
	}

	private void setCutOffs() {
		this.singleCutOff=(double)getSingles()/getPlateAppearances();
		 this.doubleCutOff=(double)this.doubles/getPlateAppearances()+singleCutOff;
		 this.tripleCutOff=(double)this.triples/getPlateAppearances()+doubleCutOff;
		 this.homeRunCutOff=(double)this.homeRuns/getPlateAppearances()+tripleCutOff;
		 this.walkCutOff=(double)this.walks/getPlateAppearances()+homeRunCutOff;
	}

	public Player(int atBats, int hits, int doubles, int triples,
			int homeRuns, int walks, int stolenBases, int caughtStealing) {
		super();
		this.atBats = atBats;
		this.hits = hits;
		this.doubles = doubles;
		this.triples = triples;
		this.homeRuns = homeRuns;
		this.walks = walks;
		this.stolenBases = stolenBases;
		this.caughtStealing = caughtStealing;
		setCutOffs();
		buildMenuDisplay();
	}
	
	public Player(Player source){
		atBats = source.atBats;
		hits=source.hits;
		doubles=source.doubles;
		triples=source.triples;
		homeRuns=source.homeRuns;
		walks=source.walks;
		stolenBases=source.stolenBases;
		caughtStealing=source.caughtStealing;
		setCutOffs();
		buildMenuDisplay();
	}
	
	public AtBatResult resultOfPlayerAtBat(){
		double atBat=Math.random();
		if(atBat<singleCutOff){
			return AtBatResult.SINGLE;
		}else if (atBat<doubleCutOff){
			return AtBatResult.DOUBLE;
		}else if (atBat<tripleCutOff){
			return AtBatResult.TRIPLE;
		}else if (atBat<homeRunCutOff){
			return AtBatResult.HOMERUN;
		}else if (atBat<walkCutOff){
			return AtBatResult.WALK;
		}else{
			return AtBatResult.OUT;
		}
	}

	private void buildMenuDisplay() {
		StringBuilder menuDisplay = 
				new StringBuilder("Enter the number of the stat you wish to change ")
								.append("or chose Quit if you are done.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(PlayerStats menuChoice: PlayerStats.values()){
			menuDisplay.append(lineNumber++).append(menuChoice.toString())
			.append(System.lineSeparator());
		}
		menuDisplay.append(lineNumber++).append(".  Quit.")
		.append(System.lineSeparator());

		this.menuDisplay= menuDisplay.toString();
	}
	
	
	public String getMenuDisplay(){
		return this.menuDisplay;
	}

	public String chooseStatDisplay(String prompt) {
		StringBuilder menuDisplay = new StringBuilder(prompt);
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(PlayerStats menuChoice: PlayerStats.values()){
			menuDisplay.append(lineNumber++).append(menuChoice.toString())
			.append(System.lineSeparator());
		}
		return menuDisplay.toString();
	}
	

	@Override
	public String toString( ){
		String firstColumnHeadings = String.format("%s%n", "  At                                                      Stolen    Caught ");
		String secondColumnHeadings = String.format("%s%n", " Bats  Hits  Singles  Doubles  Triples  Home Runs  Walks   Bases   Stealing");
		String playerValues = String.format("%4d %5d %7d %7d %8d %9d %8d %7d %8d%n",this.getAtBats(),
																	this.getHits(),
																	this.getSingles(),
																	this.getDoubles(),
																	this.getTriples(),
																	this.getHomeRuns(),
																	this.getWalks(),
																	this.getStolenBases(),
																	this.getCaughtStealing());
		return firstColumnHeadings+secondColumnHeadings+playerValues;
	
	}
	
	public void setAllPlayerStatsFromConsole(){
		for(PlayerStats stat: PlayerStats.values()){
			stat.setPlayerStatWithPrompt(this);
		}
	}
	
	public void setSelectedPlayerStatFromConsole(){
		int usersChoice;
		do{
			 System.out.format("%n%s%n", this.toString());
			 usersChoice = getIndexOfUsersChoice();
			 setStatOfUsersChoice(usersChoice);
		}while(userWantsToChangeAnotherStat(usersChoice));
	}
	
	private int getIndexOfUsersChoice() {
		return 	Input.getIntegerFromMinToMax(1, PlayerStats.values().length+1, menuDisplay)-1;
	}

	private void setStatOfUsersChoice(int usersChoice) {
		if(usersChoice<PlayerStats.values().length){
			PlayerStats.values()[usersChoice].setPlayerStatWithPrompt(this);
		}
		setCutOffs();
	}

	private boolean userWantsToChangeAnotherStat(int usersChoice) {
		return usersChoice < PlayerStats.values().length;
	}
	
	public void setStatWithValue(PlayerStats statToChange, int statValue) {
			statToChange.setPlayerStat(this,statValue);
			setCutOffs();
		}

	public int getHits() {
		return hits;
	}

	private void setHits(int hits) {
		this.hits = hits;
	}
	
	public int getAtBats() {
		return atBats;
	}

	private void setAtBats(int atBats) {
		this.atBats = atBats;
}

	public int getSingles() {
		return 	hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getDoubles() {
		return doubles;
	}

	private void setDoubles(int doubles) {
		this.doubles = doubles;
	}

	public int getTriples() {
		return triples;
	}

	private void setTriples(int triples) {
		this.triples = triples;
	}

	public int getHomeRuns() {
		return homeRuns;
	}

	private void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}

	public int getWalks() {
		return walks;
	}

	private void setWalks(int walks) {
		this.walks = walks;
	}

	public int getStolenBases() {
		return stolenBases;
	}

	private void setStolenBases(int stolenBases) {
		this.stolenBases = stolenBases;
	}

	public int getCaughtStealing() {
		return caughtStealing;
	}

	private void setCaughtStealing(int caughtStealing) {
		this.caughtStealing = caughtStealing;
	}

	public double getPercentageStolenBases() {
		return (double) this.stolenBases/(this.stolenBases+this.caughtStealing);
	}

	public void setPercentageStolenBases(int percentageSuccessfullStolenBases){
		this.stolenBases = percentageSuccessfullStolenBases;
		this.caughtStealing = 100-percentageSuccessfullStolenBases;
	}
	
	public int getPlateAppearances() {
		return atBats + walks;
	}

}
