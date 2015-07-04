package controller;

import java.util.ArrayList;

import module.Team;

public class CreateTeamFromExistingTeamMenu implements ExecutesMenu {

	private int teamToModifyIndex;
	private ArrayList<Team> tempListOfTeamsAvailable;
	
	@Override
	public void executeMenuChoice() {
		retriveAvailableTeams();
		createNewTeam();
		saveAvailableTeams();
		modifyNewTeam();
	}

	private void retriveAvailableTeams() {
		tempListOfTeamsAvailable = MainMenu.getListOfAvailableTeams();
	}

	private void createNewTeam() {
		teamToModifyIndex = new ChooseATeamMenu().getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			Team teamToStartWith = tempListOfTeamsAvailable.get(teamToModifyIndex);
			tempListOfTeamsAvailable.add(new Team(teamToStartWith));			
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToModifyIndex < tempListOfTeamsAvailable.size();
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfAvailableTeams(tempListOfTeamsAvailable);
	}

	private void modifyNewTeam() {
		if(userDidNotChooseQuit()){
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModifyIndex(teamToModifyIndex);
			modifyTeam.modifyTeam();
		}
	}
}
