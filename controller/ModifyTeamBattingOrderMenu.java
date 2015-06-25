package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.Input;
import module.Player;
import module.Team;
import module.TeamTest;

public class ModifyTeamBattingOrderMenu implements ExecutesMenuWithParameter {
	private int teamToModifyIndex;
	private Team teamToModify;
	
	@Override
	public void executeMenuChoice() {
		getTeamToModify();
		modifyBattingOrder();
		saveTeamToModify();
	}
	
	private void getTeamToModify() {
		teamToModify =  MainMenu.getListOfAvailableTeams().get(teamToModifyIndex);
	}

	private void saveTeamToModify() {
		MainMenu.getListOfAvailableTeams().set(teamToModifyIndex,teamToModify);
	}

	private void modifyBattingOrder() {
		displayTeamToModify();
		do{
			int indexOfPlayerToMove = getPlayerToMove();
			int indexOfWhereToMovePlayerTo = getPlayersNewLocation();
			movePlayerToNewLocation(indexOfPlayerToMove,indexOfWhereToMovePlayerTo);
			displayUpdatedTeamToModify();
		}while(userWantsToMoveAnotherPlayer());
	}

	private void displayTeamToModify() {
		System.out.format("%n%s%n%n", "Here is the current batting order.");
		System.out.print(teamToModify);
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

	private void displayUpdatedTeamToModify() {
		System.out.format("%n%s%n%n", "Here is the updated batting order.");
		System.out.print(teamToModify);
	}

	private boolean userWantsToMoveAnotherPlayer() {
		return Input.getYesOrNoFromTheUser("Do you want to move another player? (y/n)").equals("y");
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_BATTING_ORDER");
	}

	@Override
	public void setTeamToBeModifiedIndex(int teamToModifyIndex) {
		this.teamToModifyIndex = teamToModifyIndex;
		
	}
}
