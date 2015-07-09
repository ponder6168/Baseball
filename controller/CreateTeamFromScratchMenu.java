package controller;

import java.util.List;
import module.Storable;
import module.StorageObject;
import module.Team;

public class CreateTeamFromScratchMenu implements ExecutesMenu {
	private List<Storable> tempListOfTeamsAvailable;
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
			modifyTeam.setTeamToModifyIndex(
					MainMenu.getListOfStorableObjects(StorageObject.TEAM).size()-1);
			modifyTeam.modifyTeam();
	}

		private void retriveAvailableTeams() {
			tempListOfTeamsAvailable = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
		}

		private void createNewTeam() {
			newTeam = new Team();
			newTeam.createTeamFromTheConsole();
			tempListOfTeamsAvailable.add(newTeam);			
		}
		
		private void saveAvailableTeams() {
			MainMenu.setListOfStorableObjects(StorageObject.TEAM, tempListOfTeamsAvailable);
		}
}
