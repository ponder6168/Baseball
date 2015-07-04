package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.Input;
import module.Player;
import module.Team;

public class ModifyTeamBattingOrderMenu implements ExecutesMenu {
	private ArrayList<Team> copyOfListOfAvailableTeams;
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
		copyOfListOfAvailableTeams = MainMenu.getListOfAvailableTeams();
	}

	private void getTeamToModify() {
		teamToModify =  copyOfListOfAvailableTeams.get(teamToModifyIndex);
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
		ArrayList<Player> localCopyOfTeam = teamToModify.getTeam();
		if(indexOfWhereToMovePlayerTo>indexOfPlayerToMove){
			Collections.rotate(localCopyOfTeam.subList(indexOfPlayerToMove, indexOfWhereToMovePlayerTo+1), -1);
		}else{
			Collections.rotate(localCopyOfTeam.subList(indexOfWhereToMovePlayerTo, indexOfPlayerToMove+1), 1);
		}
		teamToModify.setTeam(localCopyOfTeam);
	}

	private boolean userWantsToMoveAnotherPlayer() {
		return Input.getYesOrNoFromTheUser("Do you want to move another player? (y/n)").equals("y");
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfAvailableTeams(copyOfListOfAvailableTeams);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_BATTING_ORDER");
	}

}
