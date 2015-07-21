package controller;

import module.Team;

public class QuitTwoTeamSimulator implements TwoTeamSimulatable {

	@Override
	public void runSimulation(Team team1, Team team2) {
		// dummy method for quit class

	}

	@Override
	public boolean equals(Object o){
		return o.equals("QUIT");
	}
}
