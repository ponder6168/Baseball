package module;

import java.io.Serializable;

public class TwoTeamOneRoundResult implements Storable, Serializable {
	private static final long serialVersionUID = -3031172480224187242L;
	
	private double percentageFirstTeamWon;
	private double percentageSecondTeamWon;
	private Team firstTeam;
	private Team secondTeam;
	private String simulationDescription;
	
	public TwoTeamOneRoundResult() {
		super();
	}
	
	public TwoTeamOneRoundResult(TwoTeamOneRoundResult simulation) {
		this.percentageFirstTeamWon = simulation.getPercentageFirstTeamWon();
		this.percentageSecondTeamWon = simulation.getPercentageSecondTeamWon();
		this.firstTeam = simulation.getFirstTeam();
		this.secondTeam = simulation.getSecondTeam();
		this.simulationDescription = simulation.getDescription();
	}

	@Override
	public Storable deepCopy() {
		return new TwoTeamOneRoundResult(this);
	}
	
	@Override
	public String getDescription() {
		return this.simulationDescription;
	}
	
	@Override
	public String toString(){
		StringBuilder string = new StringBuilder(firstTeam.toString());
		string.append(secondTeam.toString());
		string.append(String.format("%n%n%s%3.0f%n%n","First team winning percentage: ",
				this.percentageFirstTeamWon));		
		string.append(String.format("%s%3.0f%n%n","Second team winning percentage: ",
				this.percentageSecondTeamWon));		
		return string.toString();
	}
	
	public void setDescription(String description){
		this.simulationDescription = description;
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

	public double getPercentageFirstTeamWon() {
		return percentageFirstTeamWon;
	}

	public void setPercentageFirstTeamWon(double percentageFirstTeamWon) {
		this.percentageFirstTeamWon = percentageFirstTeamWon;
	}

	public double getPercentageSecondTeamWon() {
		return percentageSecondTeamWon;
	}

	public void setPercentageSecondTeamWon(double percentageSecondTeamWon) {
		this.percentageSecondTeamWon = percentageSecondTeamWon;
	}



}
