package controller;

import module.Player;
import module.Player.PlayerStats;
import module.Storable;
import module.StorageAndRetrieval;
import module.StorageObject;
import module.Team;
import module.TwoTeamMultipleRoundResult;
import module.TwoTeamOneRoundResult;
import module.TwoTeamSimulation;

import java.util.*;

import view.Input;

public class TwoIncrementPlayerSimulation implements TwoTeamSimulatable {

	private Team firstTeam;
	private Team secondTeam;
	private Team teamToModify;
	private Player playerToModify;
	private PlayerStats statToModify;
	private String statName;
	private int initialStatValue;
	private int incrementStatValue;
	private int numberOfIncrements;
	private TwoTeamMultipleRoundResult multipleRoundResult;	
	private List <Storable> previousSimulations;
	private StorageAndRetrieval storage = 
			new StorageAndRetrieval(
					StorageObject.TWO_TEAM_SIMULATION_MULTIPLE_ROUNDS);

	
	@Override
	public void runSimulation(Team firstTeam, Team secondTeam) {
		this.firstTeam = new Team(firstTeam);
		this.secondTeam = new Team(secondTeam);
		executeSimulations();
	}

	private void executeSimulations() {
		do{
			setTeamToModify();
			setPlayerToModify();
			initializeSimulationValues();
			runMultipleRoundSimulation();
			displayResults();
			saveResults();
		}while(userWantsToContinue());
	}


	private void setTeamToModify() {
		int indexOfTeamToModify;
		do{
			indexOfTeamToModify = 
					Input.getIntegerFromMinToMax(
							1, 2, buildTeamMenu());
			if(indexOfTeamToModify == 1){
				teamToModify = firstTeam;
			}else{
				teamToModify = secondTeam;
			}
			System.out.format("%s%n%n%s", "You picked the following team."
										,teamToModify);
		}while(!teamToModifyIsCorrect());
	}

	private String buildTeamMenu() {
		StringBuilder menu = new StringBuilder("Team Choices.")
								.append(System.lineSeparator())
								.append(System.lineSeparator())
								.append("1.  ")
								.append(firstTeam.getDescription())
								.append(System.lineSeparator())
								.append("2.  ")
								.append(secondTeam.getDescription())
								.append(System.lineSeparator())
								.append(System.lineSeparator())
								.append("Choose the team you want to modify.")
								.append(System.lineSeparator())
								.append(System.lineSeparator());		
		return menu.toString();
	}

	private boolean teamToModifyIsCorrect() {
		return Input.getYesOrNoFromTheUser(
				"Is this the team you want to modify? (y/n)").equals("y");
	}

	private void setPlayerToModify() {
		playerToModify = teamToModify.getPlayerFromConsole(
				"Choose the player whose stat you want to increment.");
	}

	private void initializeSimulationValues() {
		multipleRoundResult = new TwoTeamMultipleRoundResult();
		chooseStatToModify();
		setInitialValueOfStatToModify();
		setStatIncrement();
		setStatNumberOfTimesToIncrement();
	}

	private void chooseStatToModify() {
		String prompt =playerToModify.chooseStatDisplay(
				"Enter the number of the stat you wish to increment");
		int indexOfStatToModify = Input.getIntegerFromMinToMax(
									1, PlayerStats.values().length, prompt)-1;
		statToModify = PlayerStats.values()[indexOfStatToModify];
		statName = statToModify.toString().replace('.', ' ').trim();

	}

	private void setInitialValueOfStatToModify() {
		StringBuilder prompt = 
				new StringBuilder("Enter the initial value of " )
							.append(statName).append(".");
		initialStatValue = Input.getIntegerFromMinToMax(0, Integer.MAX_VALUE, prompt.toString());
	}

	private void setStatIncrement() {
		StringBuilder prompt = 
				new StringBuilder("Enter how much you want to increment " )
							.append(statName).append(" by.");
		incrementStatValue = Input.getIntegerFromMinToMax(1, Integer.MAX_VALUE, prompt.toString());
	}

	private void setStatNumberOfTimesToIncrement() {
		StringBuilder prompt = 
				new StringBuilder("Enter the number of times to increment the " )
							.append(statName).append(".")
							.append("(From 1 to 20 inclusive)");
		numberOfIncrements = Input.getIntegerFromMinToMax(1, 20, prompt.toString());
	}

	private void runMultipleRoundSimulation() {
		List <TwoTeamOneRoundResult> currentResults = new ArrayList<>();
		TwoTeamOneRoundResult oneRoundResult = new TwoTeamOneRoundResult();
		int nextStatValue = initialStatValue;
		playerToModify.setStatWithValue(statToModify, nextStatValue);
		for(int i=0 ; i<numberOfIncrements ; i++){
			oneRoundResult = new TwoTeamSimulation(firstTeam, secondTeam)
													.getSimulationResults();
			currentResults.add(oneRoundResult);
			nextStatValue += incrementStatValue;
			playerToModify.setStatWithValue(statToModify, nextStatValue);
		}
		multipleRoundResult.setResults(currentResults);
		playerToModify.setStatWithValue(statToModify, initialStatValue);
	}

	private void displayResults() {
		setSimulationTeams();
		System.out.print(multipleRoundResult);
	}
	
	private void setSimulationTeams() {
		multipleRoundResult.setFirstTeam(new Team(firstTeam));
		multipleRoundResult.setSecondTeam(new Team(secondTeam));
	}

	private void saveResults() {
		loadPreviousSimulations();
		previousSimulations.add(multipleRoundResult);
		saveUpdatedSimulations();
	}

	private void loadPreviousSimulations() {
		previousSimulations = storage.retrieveExistingObjects();
	}

	private boolean descriptionIsCorrect() {
		return Input.getYesOrNoFromTheUser(
				"Is the description correct? (y/n)").equals("y");
	}

	private void saveUpdatedSimulations() {
		if(userWantsToSaveResult()){
			setSimulationDescription();
			storage.storeObjectsInFile(previousSimulations);
		}
	}

	private void setSimulationDescription() {
		String description;
		do{
			description = Input.getLineOfUserInput(
					"Enter the description for the Simulation.");
			System.out.format("%n%s%n%s%n%n", "You entered: ", description);
		}while(!descriptionIsCorrect());
		multipleRoundResult.setDescription(description);
	}


	private boolean userWantsToSaveResult() {
		return 	 Input.getYesOrNoFromTheUser(
				"Do you want to save the result? (y/n)")
				.equals("y");
	}

	private boolean userWantsToContinue() {
		return Input.getYesOrNoFromTheUser(
				"Do you want to perform more Simulations? (y/n)")
					.equals("y");
	}

}
