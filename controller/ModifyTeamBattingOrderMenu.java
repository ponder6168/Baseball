package controller;

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
