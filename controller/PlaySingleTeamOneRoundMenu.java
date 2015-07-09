package controller;

import java.util.List;

import view.Input;
import module.SingleTeamSimulation;
import module.SingleTeamSimulationResults;
import module.Storable;
import module.StorageObject;
import module.Team;

public class PlaySingleTeamOneRoundMenu implements ExecutesMenu {
	private List<Storable> copyOfAvailableTeams;
	private List<Storable> copyOfListOfSingleTeamSimulationResults;
	int teamToPlayIndex;
	Team teamToPlay;
	SingleTeamSimulationResults simulationResults;
	
	@Override
	public void executeMenuChoice() {
		loadAllAvailableTeams();
		loadPreviousSimulations();
		getTeamToPlay();
		while(userDidNotChooseQuit()){
			modifyTeamToPlay();
			runSingleTeamSimulation();
			displaySingleTeamSimulationResults();
			saveSingleTeamSimulationResults();
			getTeamToPlay();
		};
		saveAllAvailableTeams();
	}

	private void loadAllAvailableTeams() {
		copyOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void loadPreviousSimulations() {
		copyOfListOfSingleTeamSimulationResults = 
				MainMenu.getListOfStorableObjects(
						StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND);
	}

	private void getTeamToPlay() {
		teamToPlayIndex = new ChooseATeamMenu(
								"Choose the team you wish to use for the simulation"+
								" or Return to Previous Menu.").getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			teamToPlay = (Team) copyOfAvailableTeams.get(teamToPlayIndex);
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToPlayIndex < copyOfAvailableTeams.size();
	}

	private void modifyTeamToPlay() {
		if(userWantsToModifyTeam()  && userDidNotChooseQuit()){
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModifyIndex(teamToPlayIndex);
			modifyTeam.modifyTeam();
		}
		setStealsOnOrOff();
	}

	private boolean userWantsToModifyTeam() {
		return Input.getYesOrNoFromTheUser(
					"Do you want to modify the team before you play? (y/n)").equals("y");
	}

	private void setStealsOnOrOff() {
		if(userChoosesToAllowSteals()){
			teamToPlay.setCanSteal(true);
		}else{
			teamToPlay.setCanSteal(false);
		}
	}

	private boolean userChoosesToAllowSteals() {
		return 	Input.getYesOrNoFromTheUser(
					"Do you wish to allow steals? (y/n)").equals("y");
	}

	private void runSingleTeamSimulation() {
		simulationResults = new SingleTeamSimulation().playMultipleGames(teamToPlay);
		simulationResults.setTeam(teamToPlay);
	}

	private void displaySingleTeamSimulationResults() {
		System.out.print(simulationResults);
	}

	private void saveSingleTeamSimulationResults() {
		if(userWantsToSaveResults()){
			setSimulationDescription();
			copyOfListOfSingleTeamSimulationResults.add(simulationResults);
			MainMenu.setListOfStorableObjects(StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND, 
					copyOfListOfSingleTeamSimulationResults);
		}
	}

	private boolean userWantsToSaveResults() {
		return Input.getYesOrNoFromTheUser(
				"Do you want to save the results of this simulation? (y/n) ").equals("y");
	}

	private void setSimulationDescription() {
		simulationResults.setSimulationDescription(
				Input.getLineOfUserInput("Enter your description of the simulation. "));
	}

	private void saveAllAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, copyOfAvailableTeams);
	}

}
