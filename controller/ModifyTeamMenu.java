package controller;

import java.util.ArrayList;

import module.Team;
import module.TeamStorage;
import view.Input;
import view.Menu;

public class ModifyTeamMenu implements Menu {
	
	private String menuChoices;
	private static final int numberOfMenuChoices=5;
	
	enum ModifyTeamMenuChoices  {MODIFY_DESCRIPTON, MODIFY_BATTING_ORDER, MODIFY_PLAYER, MODIFY_STAT, QUIT}
	
	public ModifyTeamMenu() {
		menuChoices= String.format("%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
				"Enter the number of your choice.",
				"  1. Modify the team description.",
				"  2. Modify the team batting order.",
				"  3. Modify a player on the team.",
				"  4. Modify the team statistic.",
				"  5. Return to Main Menu.");
	}

	@Override
	public void presentMenuToUser() {
		ModifyTeamMenuChoices modifyTeamMenuChoice;
		do{
			modifyTeamMenuChoice = getModifyTeamMenuUserChoice();
			Menu usersChoice = getNewMenu(modifyTeamMenuChoice);
			usersChoice.presentMenuToUser();
		}while(userHasNotEnteredQuit(modifyTeamMenuChoice));
	}

	private ModifyTeamMenuChoices getModifyTeamMenuUserChoice() {
		int modifyTeamMenuUserChoice = new Input().menuChoice(this);
		return ModifyTeamMenuChoices.values()[modifyTeamMenuUserChoice];
	}

	private Menu getNewMenu(ModifyTeamMenuChoices modifyTeamMenuChoice) {
		Team teamToModify = getTheTeamToModify();
		Menu newMenu;
		switch(modifyTeamMenuChoice){
		case MODIFY_DESCRIPTON: 
			newMenu = new ModifyTeamDescriptionMenu();
			break;
		case MODIFY_BATTING_ORDER: 
			newMenu = new ModifyTeamBattingOrderMenu();
			break;
		case MODIFY_PLAYER: 
			newMenu = new ModifyPlayerOnTeamMenu();
			break;
		case MODIFY_STAT: 
			newMenu = new ModifyStatOnTeamMenu();
			break;
		default:
			newMenu = new QuitMenu();
		}	
		return newMenu;
	}

	private Team getTheTeamToModify() {
		ArrayList<Team> listOfAvailableTeams = new TeamStorage().retriveExistingTeams();
		return new ChooseATeamMenu(listOfAvailableTeams).getUserTeamChoice();
	}

	private boolean userHasNotEnteredQuit(ModifyTeamMenuChoices modifyTeamMenuChoice) {
		return modifyTeamMenuChoice!=ModifyTeamMenuChoices.QUIT;
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
