package controller;

import view.Menu;

public class ModifyTeamMenu implements Menu {
	
	private String menuChoices;
	private int numberOfMenuChoices;
	
	enum ModifyTeamMenuChoices  {MODIFY_DESCRIPTON, MODIFY_BATTING_ORDER, MODIFY_PLAYER, MODIFY_STAT, QUIT}
	
	public ModifyTeamMenu() {
		menuChoices= String.format("%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
				"Enter the number of your choice.",
				"  1. Modify the team description.",
				"  2. Modify the team batting order.",
				"  3. Modify a player on the team.",
				"  4. Modify the team statistic.",
				"  5. Exit the program.");
		numberOfMenuChoices=5;

	}


	@Override
	public void printMenuChoices() {
		System.out.print(menuChoices);
	}

	@Override
	public int getNumberOfMenuChoices() {
		return numberOfMenuChoices;
	}

}
