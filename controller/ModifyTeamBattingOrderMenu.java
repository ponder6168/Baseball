package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import view.Input;
import module.Player;
import module.Storable;
import module.StorageObject;
import module.Team;

public class ModifyTeamBattingOrderMenu implements ExecutesMenu {
	private List<Storable> copyOfListOfAvailableTeams;
	private int teamToModifyIndex;
	private Team teamToModify;
	
	public ModifyTeamBattingOrderMenu(int indexOfTeamToModify) {
		this.teamToModifyIndex = indexOfTeamToModify;
	}

	@Override
	public void executeMenuChoice() {
		retrieveAvailableTeams();
		getTeamToModify();
		modifyBattingOrder();
		saveAvailableTeams();
	}
	
	private void retrieveAvailableTeams() {
		copyOfListOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void getTeamToModify() {
		teamToModify =  (Team) copyOfListOfAvailableTeams.get(teamToModifyIndex);
	}

	private void modifyBattingOrder() {
		teamToModify.displayTeamWithMessage("Here is the current batting order.");
		do{
			int indexOfPlayerToMove = getPlayerToMove();
			int indexOfWhereToMovePlayerTo = getPlayersNewLocation();
			movePlayerToNewLocation(indexOfPlayerToMove,indexOfWhereToMovePlayerTo);
			teamToModify.displayTeamWithMessage("Here is the updated batting order.");
		}while(userWantsToMoveAnotherPlayer());
	}

	private int getPlayerToMove() {
		return Input.getIntegerFromMinToMax(1, Team.NUMBER_OF_PLAYERS_ON_TEAM,
				"Enter the player you want to move.")-1; // adjust for 0 based index
	}

	private int getPlayersNewLocation() {
		return Input.getIntegerFromMinToMax(1, Team.NUMBER_OF_PLAYERS_ON_TEAM,
				"Enter where you want to move the player to.")-1; // adjust for 0 based index
	}

	private void movePlayerToNewLocation(int indexOfPlayerToMove, int indexOfWhereToMovePlayerTo) {
		ArrayList<Player> localCopyOfTeamPlayers = teamToModify.getPlayers();
		if(indexOfWhereToMovePlayerTo>indexOfPlayerToMove){
			Collections.rotate(localCopyOfTeamPlayers.subList(indexOfPlayerToMove, indexOfWhereToMovePlayerTo+1), -1);
		}else{
			Collections.rotate(localCopyOfTeamPlayers.subList(indexOfWhereToMovePlayerTo, indexOfPlayerToMove+1), 1);
		}
		teamToModify.setPlayers(localCopyOfTeamPlayers);
	}

	private boolean userWantsToMoveAnotherPlayer() {
		return Input.getYesOrNoFromTheUser("Do you want to move another player? (y/n)").equals("y");
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, copyOfListOfAvailableTeams);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_BATTING_ORDER");
	}

}
