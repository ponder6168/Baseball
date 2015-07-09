package module;

import java.io.Serializable;

public class TwoTeamSimulationResults implements Storable, Serializable {
	private static final long serialVersionUID = -3031172480224187242L;
	
	private int firstTeamRunsScored;
	private int secondTeamRunsScored;
	private Team wonTheGame;
	private Team firstTeam;
	private Team secondTeam;
	
	public TwoTeamSimulationResults() {
		super();
	}
	
	public TwoTeamSimulationResults(TwoTeamSimulationResults simulation) {
		this.firstTeamRunsScored = simulation.getFirstTeamRunsScored();
		this.secondTeamRunsScored = simulation.getSecondTeamRunsScored();
		this.wonTheGame = simulation.getWonTheGame();
		this.firstTeam = simulation.getFirstTeam();
		this.secondTeam = simulation.getSecondTeam();
	}

	@Override
	public Storable deepCopy() {
		return new TwoTeamSimulationResults(this);
	}
	
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
		return new Team(wonTheGame);
	}

	public void setWonTheGame(Team wonTheGame) {
		this.wonTheGame = new Team(wonTheGame);
	}

	public Team getFirstTeam() {
		return new Team(firstTeam);
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = new Team(firstTeam);
	}

	public Team getSecondTeam() {
		return new Team(secondTeam);
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = new Team(secondTeam);
	}



}
