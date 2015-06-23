package controller;

import module.Team;

public class ModifyStatOnTeamMenu implements ExecutesMenuWithParameter {

	private Team teamToModify;

	public ModifyStatOnTeamMenu() {
	}

	@Override
	public void executeMenuChoice() {
		System.out.println("Modify Stat Stub");
	}

	@Override
	public void setTeamToBeModified(Team team) {
		this.teamToModify = teamToModify;
	}


	public boolean equals(Object o){
		return o.equals("MODIFY_STAT");
	}

}
