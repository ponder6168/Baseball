package controller;

import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;

public class ModifyStatOnTeamMenu implements ExecutesMenu {
	private List<Storable> copyOfListOfAvailableTeams;
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
		copyOfListOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void getTeamToModify() {
		teamToModify =  (Team) copyOfListOfAvailableTeams.get(teamToModifyIndex);
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, copyOfListOfAvailableTeams);
	}


	public boolean equals(Object o){
		return o.equals("MODIFY_STAT");
	}
}
