package controller;

import java.util.ArrayList;
import java.util.List;

import module.Player.PlayerStats;
import view.Input;
import module.Player;
import module.SingleTeamMultipleRoundResult;
import module.SingleTeamSimulation;
import module.SingleTeamOneRoundResult;
import module.Storable;
import module.StorageObject;
import module.Team;

public class SingleIncrementPlayerSimulation  implements Simulatable {

	private List<Storable> singleTeamMultipleRoundSimulationResults;
	private Team teamToSimulate;
	private SingleTeamMultipleRoundResult simulationResult 
							= new SingleTeamMultipleRoundResult();
	private Player playerToIncrement;
	private PlayerStats statToModify;
	private int initialStatValue;
	private int statIncrement;
	private int numberOfIncrements;
	
	@Override
	public void runSimulation (Team team) {
		teamToSimulate = new Team(team);
		loadPreviousSimulations();
		runSimulationsWithIncrements();
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
		playerToIncrement = teamToSimulate.getPlayerFromConsole(
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
		List<SingleTeamOneRoundResult> listOfSimulationResults = new ArrayList<>();
		int nextStatValue = initialStatValue;
		for(int i=0 ; i<numberOfIncrements ; i++){
			SingleTeamOneRoundResult result = 
					new SingleTeamSimulation().playMultipleGames(teamToSimulate);
			result.setTeam(teamToSimulate);
			listOfSimulationResults.add(result);
			nextStatValue += statIncrement;
			playerToIncrement.setStatWithValue(statToModify, nextStatValue);
		}
		playerToIncrement.setStatWithValue(statToModify, initialStatValue);
		simulationResult.setIndividualSimulationResults(listOfSimulationResults);
	}

	private boolean userWantsToRunAnotherSimulation() {
		return Input.getYesOrNoFromTheUser(
				"Do you want to run another simulation with this team? (y/n)").equals("y");
	}

	private void loadPreviousSimulations() {
		singleTeamMultipleRoundSimulationResults =
				MainMenu.getListOfStorableObjects(StorageObject.SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS);
	}

	private void displaySimulationResults() {
		System.out.print(simulationResult);
	}

	private void saveSingleTeamIncrementPlayerStatResults() {
		if(userWantsToSaveResults()){
			setSimulationDescription();
			singleTeamMultipleRoundSimulationResults.add(simulationResult);
			MainMenu.setListOfStorableObjects(
					StorageObject.SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS, 
					singleTeamMultipleRoundSimulationResults);
		}
	}

	private boolean userWantsToSaveResults() {
		return Input.getYesOrNoFromTheUser(
				"Do you want to save the results of this simulation? (y/n) ").equals("y");
	}

	private void setSimulationDescription() {
		simulationResult.setSimulationDescription(
				Input.getLineOfUserInput("Enter your description of the simulation. "));
	}
}
