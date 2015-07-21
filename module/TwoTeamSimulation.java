package module;

public class TwoTeamSimulation {
	
	private Team firstTeam;
	private Team secondTeam;
	private TwoTeamOneRoundResult results = new TwoTeamOneRoundResult();
	
	private final int inningsInGame=9;
	private int inningsPlayed;
	private double iterations;
	private double gamesPlayed;
	private double gamesWonByFirstTeam;
	private double gamesWonBySecondTeam;
	private int runsScoredByFirstTeam;
	private int runsScoredBySecondTeam;
	
	public TwoTeamSimulation(Team firstTeam, Team secondTeam){
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
	}

	public TwoTeamOneRoundResult getSimulationResults(){
		initializeGameVariables();
		while(haveNotPlayedIterationNumberOfGames()){
			playOneGame();
			updateCummulativeResults();
			updateGameVariables();
		}
		calculateFinalResults();
		return results;
	}

	private void initializeGameVariables() {
		inningsPlayed = 0;
		iterations = SingleTeamSimulation.getIterations();
		gamesPlayed = 0;
		gamesWonByFirstTeam = 0;
		gamesWonBySecondTeam = 0;
		runsScoredByFirstTeam = 0;
		runsScoredBySecondTeam = 0;
	}

	private boolean haveNotPlayedIterationNumberOfGames() {
		return gamesPlayed<iterations;
	}

	private void playOneGame() {
		while(gameIsNotOver()){
			runsScoredByFirstTeam += firstTeam.runsFromTeamAtBat();
			runsScoredBySecondTeam += secondTeam.runsFromTeamAtBat();
			inningsPlayed++;
		}
	}

	private boolean gameIsNotOver() {
		return (inningsPlayed < inningsInGame) || 
				runsScoredByFirstTeam == runsScoredBySecondTeam;
	}

	private void updateCummulativeResults() {
		if(runsScoredByFirstTeam > runsScoredBySecondTeam){
			gamesWonByFirstTeam++;
		}else{
			gamesWonBySecondTeam++;
		}
	}

	private void updateGameVariables() {
		inningsPlayed =0;
		gamesPlayed++;
		runsScoredByFirstTeam=0;
		runsScoredBySecondTeam=0;
	}

	private void calculateFinalResults() {
		results.setFirstTeam(firstTeam);
		results.setSecondTeam(secondTeam);
		results.setPercentageFirstTeamWon(
				(double) gamesWonByFirstTeam/iterations*100.0);
		results.setPercentageSecondTeamWon(
				(double) gamesWonBySecondTeam/iterations*100);
	}
}
