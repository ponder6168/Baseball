package controller;

import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;

public class CreateTeamFromExistingTeamMenu implements ExecutesMenu {

	private int teamToModifyIndex;
	private List<Storable> tempListOfTeamsAvailable;
	
	@Override
	public void executeMenuChoice() {
		retriveAvailableTeams();
		createNewTeam();
		saveAvailableTeams();
		modifyNewTeam();
	}

	private void retriveAvailableTeams() {
		tempListOfTeamsAvailable = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void createNewTeam() {
		teamToModifyIndex = new ChooseATeamMenu(
				"Choose the team you wish to use to copy and modify.")
					.getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			Team teamToStartWith = (Team) tempListOfTeamsAvailable.get(teamToModifyIndex);
			tempListOfTeamsAvailable.add(new Team(teamToStartWith));			
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToModifyIndex < tempListOfTeamsAvailable.size();
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, tempListOfTeamsAvailable);
	}

	private void modifyNewTeam() {
		if(userDidNotChooseQuit()){
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModifyIndex(tempListOfTeamsAvailable.size()-1);
			modifyTeam.modifyTeam();
		}
	}
}
