package controller;

import module.StorageObject;
import view.Input;

public class PreviousSimulationsMenu implements ExecutesMenu {

	public enum SimulationChoices{
			SINGLE_TEAM_ONE_ROUND (
					". Examine Single Team One Round Simulations.",
					StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND),
			SINGLE_TEAM_MULTIPLE_ROUNDS(
					". Examine Single Team Multiple Round Simulations",
					StorageObject.SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS),
			TWO_TEAM_ONE_ROUND (
					". Examine Two Team One Round Simulations.",
					StorageObject.TWO_TEAM_SIMULATION_ONE_ROUND),
			TWO_TEAM_MULTIPLE_ROUNDS(
					". Examine Two Team Multiple Round Simulations.",
					StorageObject.TWO_TEAM_SIMULATION_MULTIPLE_ROUNDS),
			QUIT(". Quit",StorageObject.SINGLE_TEAM_SIMULATION_ONE_ROUND);
			
			private String promptMessage;
			private StorageObject storageLocation;
			
			private SimulationChoices(String promptMessage, StorageObject object){
				this.promptMessage = promptMessage;
				this.storageLocation = object;
			}
			
			@Override
			public String toString(){
				return this.promptMessage;
			}
			
			public StorageObject getStorageLocation(){
				return this.storageLocation;
			}
			
	}
	
	@Override
	public String toString(){
		StringBuilder string = new StringBuilder();
		for(SimulationChoices choice:SimulationChoices.values()){
			string.append((choice.ordinal()+1)+choice.toString())
					.append(System.lineSeparator());
		}
		return string.toString();
	}
	
	@Override
	public void executeMenuChoice() {
		SimulationChoices simulationChoice;
		simulationChoice = getSimulationChoice();
		do{
			SimulationExaminer examiner = 
					new SimulationExaminer(simulationChoice.getStorageLocation());
			examiner.examineSimulations();
			simulationChoice = getSimulationChoice();
		}while(userDidNotChooseQuit(simulationChoice));
	}
	
	private boolean userDidNotChooseQuit(SimulationChoices simulationChoice) {
		return !simulationChoice.equals(SimulationChoices.QUIT);
	}

	private SimulationChoices getSimulationChoice() {
		StringBuilder promptMessage =new StringBuilder("Enter the number of your choice.")
										.append(System.lineSeparator())
										.append(System.lineSeparator())
										.append(new PreviousSimulationsMenu());
									
		int index = Input.getIntegerFromMinToMax(1, 
							SimulationChoices.values().length, 
							promptMessage.toString());
		return SimulationChoices.values()[index-1];
	}

}
