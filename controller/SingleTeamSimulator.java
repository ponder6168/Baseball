package controller;

import java.util.List;

import view.Input;
import module.Storable;
import module.StorageObject;
import module.Team;

public class SingleTeamSimulator implements ExecutesMenu {
	Simulatable simulator;
	private List<Storable> availableTeams;
	private int teamToPlayIndex;
	private Team teamToPlay;
	
	public SingleTeamSimulator(Simulatable simulator) {
		this.simulator = simulator;
	}
	
	public SingleTeamSimulator() {
		super();
	}
	
	@Override
	public void executeMenuChoice() {
		loadAllAvailableTeams();
		getTeamToPlay();
		while(userDidNotChooseQuit()){
			modifyTeamToPlay();
			simulator.runSimulation(teamToPlay);
			getTeamToPlay();
		};
	}

	private void loadAllAvailableTeams() {
		availableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void getTeamToPlay() {
		teamToPlayIndex = new ChooseATeamMenu(
								"Choose the team you wish to use for the simulation"+
								" or Return to Previous Menu.").getIndexOfChosenTeam();
		if(userDidNotChooseQuit()){
			teamToPlay = (Team) availableTeams.get(teamToPlayIndex);
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToPlayIndex < availableTeams.size();
	}

	private void modifyTeamToPlay() {
		if(userWantsToModifyTeam()){
			ModifyTeamMenu modifyTeam = new ModifyTeamMenu();
			modifyTeam.setTeamToModify(teamToPlay);
			modifyTeam.modifyTeam();
			teamToPlay = modifyTeam.getModifiedTeam();
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
	
	public boolean equals(Object o){
		return simulator.equals("QUIT");
	}

}
