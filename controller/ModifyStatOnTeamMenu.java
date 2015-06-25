package controller;

import module.Team;

public class ModifyStatOnTeamMenu implements ExecutesMenuWithParameter {

	private Team teamToModify;
	private int teamToModifyIndex;

	@Override
	public void executeMenuChoice() {
		System.out.format("%n%s%n%n","Modify Stat Stub");
	}

	private void getTeamToModify() {
		teamToModify =  MainMenu.getListOfAvailableTeams().get(teamToModifyIndex);
	}

	private void saveTeamToModify() {
		MainMenu.getListOfAvailableTeams().set(teamToModifyIndex,teamToModify);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_STAT");
	}

	@Override
	public void setTeamToBeModifiedIndex(int teamToModifyIndex) {
		this.teamToModifyIndex = teamToModifyIndex;
	}

}
