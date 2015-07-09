package module;



import module.SingleTeamSimulationResults;
import module.Team;


public class SingleTeamSimulation {

	private static double iterations=100000;
	private int NUMBER_OF_INNINGS_IN_A_GAME = 9;
	private int currentInnning;
	
	public static double getIterations() {
		return iterations;
	}

	public static void setIterations(double iterations) {
		SingleTeamSimulation.iterations = iterations;
	}

	public SingleTeamSimulationResults playMultipleGames(Team team){
		SingleTeamSimulationResults runningTotalOfGameResults = new SingleTeamSimulationResults();
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
	

	private SingleTeamSimulationResults addSingleGameToRunningTotal
		(SingleTeamSimulationResults runningTotalOfGameResults,
			int runsFromSingleGame) {
		int runningTotalOfRunsScored = runningTotalOfGameResults.getTotalRunsScored()+runsFromSingleGame;
		runningTotalOfGameResults.setTotalRunsScored(runningTotalOfRunsScored);
		int [] runningRunsPerGameDistribution = runningTotalOfGameResults.getRunsPerGameDistribution();
		runningRunsPerGameDistribution = 
				updateRunningRunsPerGameDistribution(runningRunsPerGameDistribution,runsFromSingleGame);
		runningTotalOfGameResults.setRunsPerGameDistribution(runningRunsPerGameDistribution);
		return runningTotalOfGameResults;
	}


	private int[] updateRunningRunsPerGameDistribution(
			int[] runningRunsPerGameDistribution, int runsFromSingleGame) {
			if (runsFromSingleGame>=SingleTeamSimulationResults.MAX_RUNS_PER_INNING_RECORDED)
				runningRunsPerGameDistribution[SingleTeamSimulationResults.MAX_RUNS_PER_INNING_RECORDED]++;
			else
				runningRunsPerGameDistribution[runsFromSingleGame]++;
		return runningRunsPerGameDistribution;
	}



}
