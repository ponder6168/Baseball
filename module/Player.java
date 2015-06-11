package module;

import java.io.Serializable;


public class Player implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -528563255318771579L;
	private int atBats;
	private int hits;
	private int singles;
	private int doubles;
	private int triples;
	private int homeRuns;
	private int walks;
	private int stolenBases;
	private int caughtStealing;
	
	private int plateAppearances;
	private double singleCutOff;
	private double doubleCutOff;
	private double tripleCutOff;
	private double homeRunCutOff;
	private double walkCutOff;
	private double percentageSuccessfullStolenBases;

	
	

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
		this.singles=hits-doubles-triples-homeRuns;
		
		 this.plateAppearances=atBats+walks;
		 this.singleCutOff=(double)this.singles/this.plateAppearances;
		 this.doubleCutOff=(double)this.doubles/this.plateAppearances+singleCutOff;
		 this.tripleCutOff=(double)this.triples/this.plateAppearances+doubleCutOff;
		 this.homeRunCutOff=(double)this.homeRuns/this.plateAppearances+tripleCutOff;
		 this.walkCutOff=(double)this.walks/this.plateAppearances+homeRunCutOff;
		 this.percentageSuccessfullStolenBases=(double)stolenBases/(stolenBases+caughtStealing);
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

	public int getPlateAppearances() {
		return plateAppearances;
	}

	public Player (String string) {
		//  returns a Player object with default values
		this.atBats = 550;
		this.hits = 151;
		this.doubles = 27;
		this.triples = 2;
		this.homeRuns = 15;
		this.walks = 50;
		this.stolenBases = 10;
		this.caughtStealing = 5;
		this.singles=hits-doubles-triples-homeRuns;
		
		 this.plateAppearances=atBats+walks;
		 this.singleCutOff=(double)this.singles/this.plateAppearances;
		 this.doubleCutOff=(double)this.doubles/this.plateAppearances+singleCutOff;
		 this.tripleCutOff=(double)this.triples/this.plateAppearances+doubleCutOff;
		 this.homeRunCutOff=(double)this.homeRuns/this.plateAppearances+tripleCutOff;
		 this.walkCutOff=(double)this.walks/this.plateAppearances+homeRunCutOff;
		 this.percentageSuccessfullStolenBases=(double)stolenBases/(stolenBases+caughtStealing);

	}


	
	public Player(Player source){
		atBats = source.atBats;
		hits=source.hits;
		singles=source.singles;
		doubles=source.doubles;
		triples=source.triples;
		homeRuns=source.homeRuns;
		walks=source.walks;
		stolenBases=source.stolenBases;
		caughtStealing=source.caughtStealing;
		
		plateAppearances=source.plateAppearances;
		singleCutOff=source.singleCutOff;
		doubleCutOff=source.doubleCutOff;
		tripleCutOff=source.tripleCutOff;
		homeRunCutOff=source.homeRunCutOff;
		walkCutOff=source.walkCutOff;
		percentageSuccessfullStolenBases=source.percentageSuccessfullStolenBases;
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
	
	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;

	}

	
	public int getAtBats() {
		return atBats;
	}

	public void setAtBats(int atBats) {
		this.atBats = atBats;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
}

	public int getSingles() {
		return singles;
	}

	public void setSingles(int singles) {
		this.singles = singles;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getDoubles() {
		return doubles;
	}

	public void setDoubles(int doubles) {
		this.doubles = doubles;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getTriples() {
		return triples;
	}

	public void setTriples(int triples) {
		this.triples = triples;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getHomeRuns() {
		return homeRuns;
	}

	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getWalks() {
		return walks;
	}

	public void setWalks(int walks) {
		this.walks = walks;
		this.singles=hits-this.doubles-this.triples-this.homeRuns;
	}

	public int getStolenBases() {
		return stolenBases;
	}

	public void setStolenBases(int stolenBases) {
		this.stolenBases = stolenBases;
	}

	public int getCaughtStealing() {
		return caughtStealing;
	}

	public void setCaughtStealing(int caughtStealing) {
		this.caughtStealing = caughtStealing;
	}

	public double getPercentageStolenBases() {
		return percentageSuccessfullStolenBases;
	}

	public void setPercentageStolenBases(){
		this.percentageSuccessfullStolenBases= (double) this.stolenBases/(this.stolenBases+this.caughtStealing);
	}
	
	public void setPercentageStolenBases(double percentageSuccessfullStolenBases){
		this.percentageSuccessfullStolenBases= percentageSuccessfullStolenBases;
	}
}
