package controller;

import module.Team;

public class ModifyPlayerOnTeamMenu implements ExecutesMenuWithParameter {
	
	Team teamToModify;

	public ModifyPlayerOnTeamMenu() {
	}

	@Override
	public void executeMenuChoice() {
		System.out.println("Modify Player Stub");
	}

	@Override
	public void setTeamToBeModified(Team team) {
		this.teamToModify = teamToModify;
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_PLAYER");
	}
}
