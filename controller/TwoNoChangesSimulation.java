package controller;

import java.util.ArrayList;
import java.util.List;

import view.Input;
import module.Storable;
import module.StorageAndRetrieval;
import module.StorageObject;
import module.Team;
import module.TwoTeamOneRoundResult;
import module.TwoTeamSimulation;

public class TwoNoChangesSimulation implements TwoTeamSimulatable {
	
	TwoTeamOneRoundResult result = new TwoTeamOneRoundResult();
	List <Storable> previousSimulations = new ArrayList<>();
	StorageAndRetrieval storageAndRetrival = 
			new StorageAndRetrieval(
					StorageObject.TWO_TEAM_SIMULATION_ONE_ROUND);
	
	@Override
	public void runSimulation(Team firstTeam, Team secondTeam) {
		result = new TwoTeamSimulation(firstTeam, secondTeam).getSimulationResults();
		displayResults();
		saveResults();
	}

	private void displayResults() {
		System.out.print(result);
	}

	private void saveResults() {
		if(userWantsToSaveTheResults()){
			loadPreviousSimulationResults();
			setDescription();
			addNewResultsToPreviousResults();
			storeSimulationResults();
		}
	}

	private boolean userWantsToSaveTheResults() {
		return 	Input.getYesOrNoFromTheUser(
				"Do you want to save the results? (y/n)").equals("y");
	}

	private void loadPreviousSimulationResults() {
		previousSimulations = storageAndRetrival.retrieveExistingObjects();
	}

	private void setDescription() {
		String description;
		do{
			description =
					Input.getLineOfUserInput(
							"Enter the description of the simulation.");
			System.out.format("%n%s%n%n%s%n","You entered:", description);
		}while(!descriptionIsCorrect());
		result.setDescription(description);
	}

	private boolean descriptionIsCorrect() {
		return Input.getYesOrNoFromTheUser(
						"Is the description correct? (y/n)").endsWith("y");
	}

	private void addNewResultsToPreviousResults() {
		previousSimulations.add(result);
	}

	private void storeSimulationResults() {
		storageAndRetrival.storeObjectsInFile(previousSimulations);
		
	}

}
