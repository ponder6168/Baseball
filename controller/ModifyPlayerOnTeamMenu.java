package controller;

import java.util.List;

import view.Input;
import module.Player;
import module.Storable;
import module.StorageObject;
import module.Team;

public class ModifyPlayerOnTeamMenu implements ExecutesMenu {
	private List<Storable> copyOfListOfAvailableTeams;
	private Team teamToModify;
	private int teamToModifyIndex;
	private Player playerToModify;


	public ModifyPlayerOnTeamMenu(int indexOfTeamToModify) {
		this.teamToModifyIndex = indexOfTeamToModify;
	}

	@Override
	public void executeMenuChoice() {
		retrieveAvailableTeams();
		getTeamToModify();
		getPlayerToModify();
		modifyPlayer();
		saveAvailableTeams();
	}

	private void retrieveAvailableTeams() {
		copyOfListOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void getTeamToModify() {
		teamToModify =  (Team) copyOfListOfAvailableTeams.get(teamToModifyIndex);
	}

	private void getPlayerToModify() {
		do{
			System.out.print(teamToModify);
			int chosenPlayer = Input.getIntegerFromMinToMax
					(1, Team.NUMBER_OF_PLAYERS_ON_TEAM,"Choose the player you want to modify.");
			playerToModify = teamToModify.getPlayer(chosenPlayer-1);
		}while(!correctPlayerIsChosen());
	}

	private boolean correctPlayerIsChosen() {
		System.out.print(playerToModify);
		return Input.getYesOrNoFromTheUser("Is this the correct player? (y/n)").equals("y");
	}

	private void modifyPlayer() {
		playerToModify.setSelectedPlayerStatFromConsole();
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, copyOfListOfAvailableTeams);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_PLAYER");
	}
}
