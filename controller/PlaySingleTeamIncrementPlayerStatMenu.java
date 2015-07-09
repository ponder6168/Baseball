package controller;

import java.util.ArrayList;
import java.util.List;

import module.Player.PlayerStats;
import view.Input;
import module.Player;
import module.SingleTeamIncrementPlayerStatResults;
import module.SingleTeamSimulation;
import module.SingleTeamSimulationResults;
import module.Storable;
import module.StorageObject;
import module.Team;

public class PlaySingleTeamIncrementPlayerStatMenu implements ExecutesMenu {
	private List<Storable> copyOfAvailableTeams;
	private List<Storable> copyOfSingleTeamIncrementPlayerResults;
	private List<SingleTeamSimulationResults> listOfSimulationResults = new ArrayList<>();
	int teamToPlayIndex;
	private Team teamToPlay;
	private SingleTeamIncrementPlayerStatResults simulationResults 
							= new SingleTeamIncrementPlayerStatResults();
	private Player playerToIncrement;
	private PlayerStats statToModify;
	private int initialStatValue;
	private int statIncrement;
	private int numberOfIncrements;
	
	
	@Override
	public void executeMenuChoice() {
		loadAllAvailableTeams();
		loadPreviousSimulations();
		getTeamToPlay();
		while(userDidNotChooseQuit()){
			modifyTeamToPlay();
			runSimulationsWithIncrements();
			getTeamToPlay();
		};
		saveAllAvailableTeams();
	}


	private void runSimulationsWithIncrements() {
		do{
			initializeSimulationValues();
			runSimulations();
			displaySimulationResults();
			saveSingleTeamIncrementPlayerStatResults();
		}while(userWantsToRunAnotherSimulation());
	}

	private void initializeSimulationValues() {
		playerToIncrement = teamToPlay.getPlayerFromConsole(
				"Choose the player whose stat you want to increment.");
		getPlayerStatToModify();
		setInitialStatValue();
		statIncrement = Input.getIntegerFromMinToMax(
								1, Integer.MAX_VALUE,
								"Enter the amount you want to increment the stat by. ");
		numberOfIncrements = Input.getIntegerFromMinToMax(
									2, 20,
									"Enter the number of times to increment the stat (2-20) ");
	}

	private void getPlayerStatToModify() {
		String prompt =
				playerToIncrement.chooseStatDisplay("Choose the stat you want to increment.");
		int choice = Input.getIntegerFromMinToMax(1, PlayerStats.values().length, prompt);
		statToModify = PlayerStats.values()[choice-1];
	}

	private void setInitialStatValue() {
		StringBuilder prompt = new StringBuilder("Enter the initial value of the ");
		prompt.append(statToModify.toString().replace('.', ' ').trim());
		initialStatValue = Input.getIntegerFromMinToMax(
								0, Integer.MAX_VALUE, prompt.toString());
		playerToIncrement.setStatWithValue(statToModify, initialStatValue);
	}

	private void runSimulations() {
		int nextStatValue = initialStatValue;
		for(int i=0 ; i<numberOfIncrements ; i++){
			SingleTeamSimulationResults result = 
					new SingleTeamSimulation().playMultipleGames(teamToPlay);
			result.setTeam(teamToPlay);
			listOfSimulationResults.add(result);
			nextStatValue += statIncrement;
			playerToIncrement.setStatWithValue(statToModify, nextStatValue);
		}
		playerToIncrement.setStatWithValue(statToModify, initialStatValue);
		simulationResults.setIndividualSimulationResults(listOfSimulationResults);
	}


	private boolean userWantsToRunAnotherSimulation() {
		return Input.getYesOrNoFromTheUser(
				"Do you want to run another simulation with this team? (y/n)").equals("y");
	}

	private void loadAllAvailableTeams() {
		copyOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void loadPreviousSimulations() {
		copyOfSingleTeamIncrementPlayerResults =
				MainMenu.getListOfStorableObjects(StorageObject.SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS);
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
		if(userWantsToModifyTeam()){
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


	private void displaySimulationResults() {
		System.out.print(simulationResults);
	}

	private void saveSingleTeamIncrementPlayerStatResults() {
		if(userWantsToSaveResults()){
			setSimulationDescription();
			copyOfSingleTeamIncrementPlayerResults.add(simulationResults);
			MainMenu.setListOfStorableObjects(
					StorageObject.SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS, 
					copyOfSingleTeamIncrementPlayerResults);
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
