package controller;

import module.Team;

public class QuitSimulation implements Simulatable {

	@Override
	public void runSimulation(Team team) {
		// Dummy override to quit

	}

	public boolean equals(Object o){
		return o.equals("QUIT");
	}

}
