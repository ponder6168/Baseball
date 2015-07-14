package controller;

import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;

public class CreateTeamFromScratchMenu implements ExecutesMenu {
	private List<Storable> teamsAvailable;
	private Team newTeam;
	
	@Override
	public void executeMenuChoice() {
			retriveAvailableTeams();
			createNewTeam();
			displayNewTeam();
			modifyNewTeam();
			saveNewTeam();
			saveAvailableTeams();
		}

		private void retriveAvailableTeams() {
			teamsAvailable = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
		}

		private void createNewTeam() {
			newTeam = new Team();
			newTeam.createTeamFromTheConsole();
		}
		
		private void displayNewTeam() {
			System.out.format("%n%s%n%s%n%n%s%n%n",
					"Here is the team you just created.",
					newTeam,
					"Modify it if necessary.");
	}

		private void modifyNewTeam() {
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModify(newTeam);
			modifyTeam.modifyTeam();
			newTeam = modifyTeam.getModifiedTeam();
	}

			private void saveNewTeam() {
				teamsAvailable.add(newTeam);			
		}

		private void saveAvailableTeams() {
			MainMenu.setListOfStorableObjects(StorageObject.TEAM, teamsAvailable);
		}
}
