package module;



import module.CummulativeSingleTeamGameResults;
import module.Team;


public class SingleTeamRunProduction {

	private static double iterations=100000;
	private int NUMBER_OF_INNINGS_IN_A_GAME = 9;
	private int currentInnning;
	public static double getIterations() {
		return iterations;
	}

	public static void setIterations(double iterations) {
		SingleTeamRunProduction.iterations = iterations;
	}

	public CummulativeSingleTeamGameResults playMultipleGames(Team team){
		CummulativeSingleTeamGameResults runningTotalOfGameResults = new CummulativeSingleTeamGameResults();
		for(int i=0;i<iterations;i++){
			int runsFromSingleGame = playSingleTeamSingleGame(team);
			runningTotalOfGameResults = addSingleGameToRunningTotal(runningTotalOfGameResults,runsFromSingleGame);
		}
		runningTotalOfGameResults.setRunsPerGame(runningTotalOfGameResults.getTotalRunsScored()/iterations);
		return runningTotalOfGameResults;
	}

	private int playSingleTeamSingleGame(Team team) {
		int totalRunsScored=0;
		currentInnning=1;
		while(gameNotOver()){
			totalRunsScored += team.runsFromTeamAtBat();
		}
		return totalRunsScored;
	}

	private boolean gameNotOver( ) {
		return currentInnning++<=NUMBER_OF_INNINGS_IN_A_GAME ;
	}
	

	private CummulativeSingleTeamGameResults addSingleGameToRunningTotal(CummulativeSingleTeamGameResults runningTotalOfGameResults,
			int runsFromSingleGame) {
		int runningTotalOfRunsScored = runningTotalOfGameResults.getTotalRunsScored()+runsFromSingleGame;
		runningTotalOfGameResults.setTotalRunsScored(runningTotalOfRunsScored);
		int [] runningRunsPerGameDistribution = runningTotalOfGameResults.getRunsPerGameDistribution();
		runningRunsPerGameDistribution = updateRunningRunsPerGameDistribution(runningRunsPerGameDistribution,runsFromSingleGame);
		runningTotalOfGameResults.setRunsPerGameDistribution(runningRunsPerGameDistribution);
		return runningTotalOfGameResults;
	}


	private int[] updateRunningRunsPerGameDistribution(
			int[] runningRunsPerGameDistribution, int runsFromSingleGame) {
			if (runsFromSingleGame>=CummulativeSingleTeamGameResults.MAX_RUNS_PER_INNING_RECORDED)
				runningRunsPerGameDistribution[CummulativeSingleTeamGameResults.MAX_RUNS_PER_INNING_RECORDED]++;
			else
				runningRunsPerGameDistribution[runsFromSingleGame]++;
		return runningRunsPerGameDistribution;
	}



}
