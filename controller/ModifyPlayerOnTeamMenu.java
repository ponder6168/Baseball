package controller;

import module.Team;

public class ModifyPlayerOnTeamMenu implements ExecutesMenuWithParameter {
	
	private Team teamToModify;
	private int teamToModifyIndex;

	public ModifyPlayerOnTeamMenu() {
	}

	@Override
	public void executeMenuChoice() {
		System.out.format("%n%s%n%n","Modify Player Stub");
	}

	private void getTeamToModify() {
		teamToModify =  MainMenu.getListOfAvailableTeams().get(teamToModifyIndex);
	}

	private void saveTeamToModify() {
		MainMenu.getListOfAvailableTeams().set(teamToModifyIndex,teamToModify);
	}


	public boolean equals(Object o){
		return o.equals("MODIFY_PLAYER");
	}

	@Override
	public void setTeamToBeModifiedIndex(int teamToModifyIndex) {
		this.teamToModifyIndex = teamToModifyIndex;
	}
}
