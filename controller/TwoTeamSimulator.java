package controller;

import java.util.ArrayList;
import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;

public class TwoTeamSimulator implements ExecutesMenu{

	private List<Storable> availableTeams = new ArrayList<>();
	private int indexOfTeam1;
	private int indexOfTeam2;
	private Team team1;
	private Team team2;
	private TwoTeamSimulatable simulator;
	
	public TwoTeamSimulator(TwoTeamSimulatable simulator) {
		this.simulator = simulator;
	}
	
	@Override
	public void executeMenuChoice() {
		availableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
		chooseTeams();
		while(userDidNotChooseQuit()){
			runSimulations();
			chooseTeams();
		}
	}


	private void runSimulations() {
		simulator.runSimulation(team1,team2);
	}

	private void chooseTeams() {
		indexOfTeam1 = new ChooseATeamMenu("Choose the first team.")
							.getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			team1 = (Team) availableTeams.get(indexOfTeam1);
		}else{
			return;
		}

		indexOfTeam2 = new ChooseATeamMenu("Choose the second team.")
							.getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			team2 = (Team) availableTeams.get(indexOfTeam2);
		}
	}

	private boolean userDidNotChooseQuit() {
		return (indexOfTeam1 < availableTeams.size())&&
				(indexOfTeam2 < availableTeams.size());
	}

}
