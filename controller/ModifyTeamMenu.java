package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MainMenu.MainMenuChoices;
import module.Team;
import module.TeamStorage;
import view.Input;

public class ModifyTeamMenu implements ExecutesMenu {

	enum ModifyTeamMenuChoices {
		MODIFY_DESCRIPTON(". Modify the team description.",
								new ModifyTeamDescriptionMenu()),
		MODIFY_BATTING_ORDER	(". Modify the team batting order.",
									new ModifyTeamBattingOrderMenu()),
		MODIFY_PLAYER	(". Modify a player on the team.",
							new ModifyPlayerOnTeamMenu()),
		MODIFY_STAT	(". Modify the team statistic.",
						new ModifyStatOnTeamMenu()),
		QUIT	(". Return to Main Menu.",
					new QuitMenuWithParameter());


		private String promptMessage;
		private ExecutesMenuWithParameter nextMenu;

		private ModifyTeamMenuChoices(String promptMessage,
				ExecutesMenuWithParameter nextMenu) {
			this.promptMessage=promptMessage;
			this.nextMenu = nextMenu;
		}

		@Override
		public String toString(){
			return this.promptMessage;
		}
	}

	private int menuChoice;
	private int numberOfMenuChoices;
	private int teamToModifyIndex;
	private String menuDisplay;

	public ModifyTeamMenu() {
		this.numberOfMenuChoices=ModifyTeamMenuChoices.values().length;
		this.menuDisplay = buildMenuDisplay();
	}

	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder("Enter the number of your choice.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(ModifyTeamMenuChoices menuChoice: ModifyTeamMenuChoices.values()){
			menuDisplay.append(lineNumber++).append(menuChoice.toString())
			.append(System.lineSeparator());
		}
		return menuDisplay.toString();
	}

	@Override
	public  String toString(){		
		return this.menuDisplay;
	}

	@Override
	public void executeMenuChoice() {
		ExecutesMenuWithParameter newMenu;
		teamToModifyIndex = getIndexOfTeamToModify();
		do{
			newMenu = getMenuChoice();
			newMenu.setTeamToBeModifiedIndex(teamToModifyIndex);
			newMenu.executeMenuChoice();
		}while(!newMenu.equals("QUIT"));
	}

	private int getIndexOfTeamToModify() {
		ChooseATeamMenu chooseATeamToModify = new ChooseATeamMenu();
		chooseATeamToModify.executeMenuChoice();
		return  chooseATeamToModify.getChosenTeam();
	}


	public ExecutesMenuWithParameter getMenuChoice(){
		if(noTeamWasChoosenToModify()){
			return new QuitMenuWithParameter();
		}
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return ModifyTeamMenuChoices.values()[menuChoice-1].nextMenu; 
	}

	private boolean noTeamWasChoosenToModify() {
		return teamToModifyIndex==MainMenu.getListOfAvailableTeams().size();
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_TEAM");
	}
}







