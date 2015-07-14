package controller;

import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;

public class CreateTeamFromExistingTeamMenu implements ExecutesMenu {

	private int teamToModifyIndex;
	private List<Storable> teamsAvailable;
	private Team teamToStartWith;
	
	@Override
	public void executeMenuChoice() {
		retriveAvailableTeams();
		createNewTeam();
		modifyNewTeam();
		saveNewTeam();
		saveAvailableTeams();
	}

	private void retriveAvailableTeams() {
		teamsAvailable = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void createNewTeam() {
		teamToModifyIndex = new ChooseATeamMenu(
									"Choose the team you wish to use to copy and modify.")
									.getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			teamToStartWith = (Team) teamsAvailable.get(teamToModifyIndex);
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToModifyIndex < teamsAvailable.size();
	}

	private void modifyNewTeam() {
		if(userDidNotChooseQuit()){
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModify(teamToStartWith);
			modifyTeam.modifyTeam();
			teamToStartWith = modifyTeam.getModifiedTeam();
		}
	}
	
	private void saveNewTeam() {
		teamsAvailable.add(new Team(teamToStartWith));			
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, teamsAvailable);
	}
}
