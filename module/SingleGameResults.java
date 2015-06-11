package module;

public class SingleGameResults {
	private int firstTeamRunsScored;
	private int secondTeamRunsScored;
	private Team wonTheGame;
	private Team firstTeam;
	private Team secondTeam;
	
	public int getFirstTeamRunsScored() {
		return firstTeamRunsScored;
	}

	public void setFirstTeamRunsScored(int firstTeamRunsScored) {
		this.firstTeamRunsScored = firstTeamRunsScored;
	}

	public int getSecondTeamRunsScored() {
		return secondTeamRunsScored;
	}

	public void setSecondTeamRunsScored(int secondTeamRunsScored) {
		this.secondTeamRunsScored = secondTeamRunsScored;
	}

	public Team getWonTheGame() {
		return wonTheGame;
	}

	public void setWonTheGame(Team wonTheGame) {
		this.wonTheGame = wonTheGame;
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

	public SingleGameResults() {
		// TODO Auto-generated constructor stub
	}

}
