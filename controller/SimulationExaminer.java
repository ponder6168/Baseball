package controller;

import java.util.ArrayList;
import java.util.List;

import view.Input;
import module.Storable;
import module.StorageAndRetrieval;
import module.StorageObject;

public class SimulationExaminer {
	
	private StorageObject storageLocation;
	List <Storable> allSimulations = new ArrayList<>();
	int indexOfChosenSimulation;

	public SimulationExaminer(StorageObject storageObject){
		this.storageLocation = storageObject;
	}
	
	public void examineSimulations() {
		getSimulations();
		do{
			chooseSimulation();
			displayChoosenSimulation();
		}while(userDidNotChooseQuit());
	}

	private void getSimulations() {
		allSimulations = 
				new StorageAndRetrieval(storageLocation).retrieveExistingObjects();
	}

	private void chooseSimulation() {
		StringBuilder promptMessage = 
				new StringBuilder("Enter the number of your choice.")
						.append(System.lineSeparator())
						.append(System.lineSeparator())
						.append(buildMenuDisplay());
		indexOfChosenSimulation = Input.getIntegerFromMinToMax(
										1, allSimulations.size()+1,
										promptMessage.toString())-1;
	}

	private StringBuilder buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder();
		int location = 1;
		for(Storable simulation:allSimulations){
			menuDisplay.append(location++).append(".  ")
						.append(simulation.getDescription())
						.append(System.lineSeparator());
		}
		menuDisplay.append(location).append(".  Quit");
		return menuDisplay;
	}

	private void displayChoosenSimulation() {
		if(userDidNotChooseQuit()){
			Storable simulationResult =
					 allSimulations.get(indexOfChosenSimulation);
			System.out.print(simulationResult);			
		}
	}

	private boolean userDidNotChooseQuit() {
		return indexOfChosenSimulation < allSimulations.size();
	}

}
