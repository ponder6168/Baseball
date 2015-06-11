package controller;


import module.CummulativeGameResults;
import module.SingleGameResults;
import module.Team;

public class Game {
	private static double iterations=100000;
	private int NUMBER_OF_INNINGS_IN_A_GAME = 9;
	private int currentInnning;
	public static double getIterations() {
		return iterations;
	}

	public static void setIterations(double iterations) {
		Game.iterations = iterations;
	}

	public CummulativeGameResults playMultipleGames(Team firstTeam, Team secondTeam){
		CummulativeGameResults runningTotalOfGameResults = new CummulativeGameResults();
		for(int i=0;i<iterations;i++){
			SingleGameResults singleGameResults = playSingleGame(firstTeam, secondTeam);
			runningTotalOfGameResults = addSingleGameToRunningTotal(runningTotalOfGameResults,singleGameResults);
		}
		return runningTotalOfGameResults;
	}

	private CummulativeGameResults addSingleGameToRunningTotal(CummulativeGameResults runningTotalOfGameResults,
																SingleGameResults singleGameResults) {
			runningTotalOfGameResults.setTotalFirstTeamRunsScored(singleGameResults.getFirstTeamRunsScored()+runningTotalOfGameResults.getTotalFirstTeamRunsScored());
			runningTotalOfGameResults.setTotalSecondTeamRunsScored(singleGameResults.getSecondTeamRunsScored()+runningTotalOfGameResults.getTotalSecondTeamRunsScored());
			updateWinTotals(runningTotalOfGameResults,singleGameResults);
			return runningTotalOfGameResults;
	}

	private void updateWinTotals(CummulativeGameResults runningTotalOfGameResults,
									SingleGameResults singleGameResults) {
		if(singleGameResults.getFirstTeamRunsScored()>singleGameResults.getSecondTeamRunsScored())
			runningTotalOfGameResults.incrementTimesFirstTeamWonTheGame();
		else
			runningTotalOfGameResults.incrementTimesSecondTeamWonTheGame();
	}

	private SingleGameResults playSingleGame(Team firstTeam, Team secondTeam) {
		SingleGameResults gameResult = new SingleGameResults();
		gameResult.setFirstTeam(firstTeam);
		gameResult.setSecondTeam(secondTeam);
		gameResult.setFirstTeamRunsScored(0);
		gameResult.setSecondTeamRunsScored(0);
		currentInnning=1;
		while(gameNotOver(gameResult)){
			gameResult.setFirstTeamRunsScored(firstTeam.runsFromTeamAtBat()+gameResult.getFirstTeamRunsScored());
			gameResult.setSecondTeamRunsScored(secondTeam.runsFromTeamAtBat()+gameResult.getSecondTeamRunsScored());
			currentInnning +=1;
		}
		return gameResult;
	}


	private boolean gameNotOver(SingleGameResults gameResult) {
		// TODO Auto-generated method stub
		return currentInnning<=NUMBER_OF_INNINGS_IN_A_GAME || gameResult.getFirstTeamRunsScored()==gameResult.getSecondTeamRunsScored();
	}


}
