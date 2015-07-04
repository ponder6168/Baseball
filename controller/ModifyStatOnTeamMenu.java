package controller;

import java.util.ArrayList;

import module.Team;

public class ModifyStatOnTeamMenu implements ExecutesMenu {
	private ArrayList<Team> copyOfListOfAvailableTeams;
	private Team teamToModify;
	private int teamToModifyIndex;

	public ModifyStatOnTeamMenu(int indexOfTeamToModify) {
		this.teamToModifyIndex = indexOfTeamToModify;
	}

	@Override
	public void executeMenuChoice() {
		retrieveAvailableTeams();
		getTeamToModify();
		modifyStat();
		saveAvailableTeams();
	}

	private void modifyStat() {
		teamToModify.setSelectedStatForTeam();
	}

	private void retrieveAvailableTeams() {
		copyOfListOfAvailableTeams = MainMenu.getListOfAvailableTeams();
	}

	private void getTeamToModify() {
		teamToModify =  copyOfListOfAvailableTeams.get(teamToModifyIndex);
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfAvailableTeams(copyOfListOfAvailableTeams);
	}


	public boolean equals(Object o){
		return o.equals("MODIFY_STAT");
	}
}
