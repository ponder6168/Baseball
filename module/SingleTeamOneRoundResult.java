package module;

import java.io.Serializable;

public class SingleTeamOneRoundResult implements Serializable, Storable{
	private static final long serialVersionUID = -9170709552774432402L;

	public static final int  MAX_RUNS_PER_INNING_RECORDED = 12;
	private String simulationDescription="Default descripton.";
	private Team team;
	
	private int totalRunsScored;
	private double runsPerGame;
	private int [] runsPerGameDistribution = new int[MAX_RUNS_PER_INNING_RECORDED+1];
	
	private double distributionDisplayMaxWidth = 100;
	private double distributionDisplayInterval = SingleTeamSimulation.getIterations()/
													distributionDisplayMaxWidth;
	
	public SingleTeamOneRoundResult(){
		super();
	}
	
	public SingleTeamOneRoundResult(SingleTeamOneRoundResult simulationResults){
		this.simulationDescription = simulationResults.getDescription();
		this.team = simulationResults.getTeam();
		this.totalRunsScored = simulationResults.getTotalRunsScored();
		this.runsPerGame = simulationResults.getRunsPerGame();
		this.runsPerGameDistribution = simulationResults.getRunsPerGameDistribution();
	}
	
	@Override
	public Storable deepCopy() {
		return new SingleTeamOneRoundResult(this);
	}

	
	public String buildHistogram(){
		StringBuilder histogram= new StringBuilder("Runs Scored Per Game Histogram")
										.append(System.lineSeparator())
										.append(System.lineSeparator());
		int runsScored=0;
		for(int timesScored:runsPerGameDistribution){
			histogram.append(String.format("%3d%s", runsScored++,".  Runs Scored  "));
			for(float i=0.5f;i<timesScored/distributionDisplayInterval;i++){
				histogram.append("X");
			}
			histogram.append(System.lineSeparator());
		}
		
		return histogram.toString();
	}
	
	@Override
	public String toString(){
		StringBuilder string = new StringBuilder(simulationDescription)
									.append(System.lineSeparator())
									.append(System.lineSeparator());
		string.append("The runs per game for this simulation was ")
				.append(runsPerGame).append(System.lineSeparator())
									.append(System.lineSeparator());
		string.append(System.lineSeparator())
				.append(buildHistogram());
		return string.toString();
	}
	
	public void displayRunsPerGameDistribution(){
		StringBuilder string = new StringBuilder("Runs Scored in a Game Distribution")
									.append(System.lineSeparator())
									.append(System.lineSeparator());
		string.append(String.format("%15s%20s", "Runs", "Times Runs"))
				.append(System.lineSeparator());
		string.append(String.format("%15s%20s", "Scored", "Scored"))
				.append(System.lineSeparator());
		int runsScored=0;
		for(int timesScored:runsPerGameDistribution){
			string.append(String.format("%15d%20d", runsScored++,timesScored))
			.append(System.lineSeparator());
		}
	}

	@Override
	public String getDescription() {
		return simulationDescription;
	}

	public void setDescription(String simulationDescription) {
		this.simulationDescription = simulationDescription;
	}

	public int getTotalRunsScored() {
		return totalRunsScored;
	}
	
	public void setTotalRunsScored(int totalRunsScored) {
		this.totalRunsScored = totalRunsScored;
	}
	
	public double getRunsPerGame() {
		return runsPerGame;
	}
	
	public void setRunsPerGame(double runsPerGame) {
		this.runsPerGame = runsPerGame;
	}
	
	public int[] getRunsPerGameDistribution() {
		int[] copyRunsPerGameDistribution = new int[MAX_RUNS_PER_INNING_RECORDED+1];
		for(int i=0 ; i<= MAX_RUNS_PER_INNING_RECORDED; i++)
			copyRunsPerGameDistribution[i]=runsPerGameDistribution[i];
		return copyRunsPerGameDistribution;
	}
	public void setRunsPerGameDistribution(int[] runsPerGameDistribution) {
		for(int i=0 ; i<=MAX_RUNS_PER_INNING_RECORDED ; i++)
			this.runsPerGameDistribution[i] = runsPerGameDistribution[i];
	}
	
	public Team getTeam() {
		return new Team(team);
	}

	public void setTeam(Team team) {
		this.team = new Team(team);
	}


}
