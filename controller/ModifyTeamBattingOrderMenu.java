package controller;

import java.util.Collections;

import view.Input;
import module.Team;
import module.TeamTest;

public class ModifyTeamBattingOrderMenu implements ExecutesMenuWithParameter {
	private Team teamToModify;
	
	@Override
	public void executeMenuChoice() {
		modifyBattingOrder();
	}
	
	private void modifyBattingOrder() {
		displayTeamToModify();
		do{
			int indexOfPlayerToMove = getPlayerToMove();
			int indexOfWhereToMovePlayerTo = getPlayersNewLocation()
			movePlayerToNewLocation(indexOfPlayerToMove,indexOfWhereToMovePlayerTo);
			displayUpdatedTeamToModify();
		}while(userWantsToMoveAnotherPlayer());
	}



	private void movePlayerToNewLocation(int indexOfPlayerToMove, int indexOfWhereToMovePlayerTo) {
		if(indexOfWhereToMovePlayerTo>indexOfPlayerToMove){
			Collections.rotate(teamToModify.getTeam().subList(indexOfPlayerToMove, indexOfWhereToMovePlayerTo+1), -1);
		}else{
			Collections.rotate(teamToModify.getTeam().subList(indexOfWhereToMovePlayerTo, indexOfPlayerToMove+1), 1);
		}
		
	}

	private void displayUpdatedTeamToModify() {
		System.out.format("%n%s%n%n", "Here is the updated batting order.");
		System.out.print(teamToModify);
	}

	private boolean userWantsToMoveAnotherPlayer() {
		return Input.getYesOrNoFromTheUser("Do you want to move another player? (y/n)").equals("y");
	}

	private void displayTeamToModify() {
		System.out.format("%n%s%n%n", "Here is the current batting order.");
		System.out.print(teamToModify);
		
	}

	@Override
	public void setTeamToBeModified(Team team) {
		this.teamToModify = team;
		
	}
	
	public boolean equals(Object o){
		return o.equals("MODIFY_BATTING_ORDER");
	}


}
