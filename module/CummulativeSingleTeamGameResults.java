package module;

public class CummulativeSingleTeamGameResults {
	public static final int  MAX_RUNS_PER_INNING_RECORDED = 12;
	private int totalRunsScored;
	private double runsPerGame;
	private int [] runsPerGameDistribution = new int[MAX_RUNS_PER_INNING_RECORDED+1];

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

}
