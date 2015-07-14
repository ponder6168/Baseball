package controller;

import java.util.List;

import view.Input;
import module.SingleTeamSimulation;
import module.SingleTeamSimulationResults;
import module.Storable;
import module.StorageObject;
import module.Team;

public class SingleNoChangeSimulation implements Simulatable {
	private List<Storable> singleTeamSimulationResults;
	Team teamToSimulate;
	SingleTeamSimulationResults simulationResult;
	
	@Override
	public void runSimulation(Team team) {
		teamToSimulate = team;
		loadPreviousSimulations();
		runSingleTeamSimulation();
		displaySingleTeamSimulationResults();
		saveSingleTeamSimulationResult();
	}

	private void loadPreviousSimulations() {
		singleTeamSimulationResults = 
				MainMenu.getListOfStorableObjects(
						StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND);
	}

	private void runSingleTeamSimulation() {
		simulationResult = new SingleTeamSimulation().playMultipleGames(teamToSimulate);
		simulationResult.setTeam(teamToSimulate);
	}

	private void displaySingleTeamSimulationResults() {
		System.out.print(simulationResult);
	}

	private void saveSingleTeamSimulationResult() {
		if(userWantsToSaveResults()){
			setSimulationDescription();
			singleTeamSimulationResults.add(simulationResult);
			MainMenu.setListOfStorableObjects(StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND, 
					singleTeamSimulationResults);
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
