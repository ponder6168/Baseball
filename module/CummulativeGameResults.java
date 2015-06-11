package module;



public class CummulativeGameResults {
	
	private int totalFirstTeamRunsScored;
	private int totalSecondTeamRunsScored;
	private int timesFirstTeamWonTheGame;
	private int timesSecondTeamWonTheGame;
	private Team firstTeam;
	private Team secondTeam;
	
	public int getTotalFirstTeamRunsScored() {
		return totalFirstTeamRunsScored;
	}

	public void setTotalFirstTeamRunsScored(int totalFirstTeamRunsScored) {
		this.totalFirstTeamRunsScored = totalFirstTeamRunsScored;
	}

	public int getTotalSecondTeamRunsScored() {
		return totalSecondTeamRunsScored;
	}

	public void setTotalSecondTeamRunsScored(int totalSecondTeamRunsScored) {
		this.totalSecondTeamRunsScored = totalSecondTeamRunsScored;
	}

	public int getTimesFirstTeamWonTheGame() {
		return timesFirstTeamWonTheGame;
	}
	public void incrementTimesFirstTeamWonTheGame() {
		 this.timesFirstTeamWonTheGame +=1;
	}

	public void setTimesFirstTeamWonTheGame(int timesFirstTeamWonTheGame) {
		this.timesFirstTeamWonTheGame = timesFirstTeamWonTheGame;
	}

	public int getTimesSecondTeamWonTheGame() {
		return timesSecondTeamWonTheGame;
	}

	public void incrementTimesSecondTeamWonTheGame() {
		this.timesSecondTeamWonTheGame += 1;
	}
	public void setTimesSecondTeamWonTheGame(int timesSecondTeamWonTheGame) {
		this.timesSecondTeamWonTheGame = timesSecondTeamWonTheGame;
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}

	public CummulativeGameResults() {
		// TODO Auto-generated constructor stub
	}

}
