package controller;

import java.util.ArrayList;

import module.Team;

public class CreateTeamFromScratchMenu implements ExecutesMenu {
	private ArrayList<Team> tempListOfTeamsAvailable;
	private Team newTeam;
	
	@Override
	public void executeMenuChoice() {
			retriveAvailableTeams();
			createNewTeam();
			displayNewTeam();
			saveAvailableTeams();
			modifyNewTeam();
		}

		private void displayNewTeam() {
			System.out.format("%n%s%n%s%n%n%s%n%n",
					"Here is the team you just created.",
					newTeam,
					"Modify it if necessary.");
	}

		private void modifyNewTeam() {
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModifyIndex(MainMenu.getListOfAvailableTeams().size()-1);
			modifyTeam.modifyTeam();
	}

		private void retriveAvailableTeams() {
			tempListOfTeamsAvailable = MainMenu.getListOfAvailableTeams();
		}

		private void createNewTeam() {
			newTeam = new Team("default");
			newTeam.createTeamFromTheConsole();
			tempListOfTeamsAvailable.add(newTeam);			
		}
		
		private void saveAvailableTeams() {
			MainMenu.setListOfAvailableTeams(tempListOfTeamsAvailable);
		}
}
