package controller;

import view.Menu;

public class ModifyTeamBattingOrderMenu implements Menu {
	private String menuChoices;
	private static final int numberOfMenuChoices=9;
	
	enum ModifyTeamMenuChoices  {MODIFY_DESCRIPTON, MODIFY_BATTING_ORDER, MODIFY_PLAYER, MODIFY_STAT, QUIT}
	
	public ModifyTeamBattingOrderMenu() {
		menuChoices= String.format("%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
				"Enter the number of your choice.",
				"  1. Modify the team description.",
				"  2. Modify the team batting order.",
				"  3. Modify a player on the team.",
				"  4. Modify the team statistic.",
				"  5. Return to Main Menu.");
	}

	@Override
	public void printMenuChoices() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfMenuChoices() {
		return numberOfMenuChoices;
	}

	@Override
	public void presentMenuToUser() {
		System.out.println("Modify Batting Order Stub");

	}

}
