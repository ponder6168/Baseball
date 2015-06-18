package controller;

import view.Input;
import view.Menu;

public class MainMenu implements Menu {
	
	private String menuChoices;
	private static final int numberOfMenuChoices=5;
	
	enum MainMenuChoices  {SINGLE_TEAM_SENARIO, TWO_TEAM_SENARIO, MODIFY_TEAM, CREATE_TEAM, QUIT}
	
	public MainMenu() {
		menuChoices= String.format("%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
				"Enter the number of your choice.",
				"  1. Run a Single Team Senario.",
				"  2. Run a Two Team Senario.",
				"  3. Modify an Existing Team.",
				"  4. Create a new Team.",
				"  5. Exit the program.");

	}
		
	 public void presentMenuToUser() {
		 MainMenuChoices mainMenuChoice;
			do{
				mainMenuChoice = getMainMenuUserChoice();
				Menu usersChoice = getNewMenu(mainMenuChoice);
				usersChoice.presentMenuToUser();
			}while(userHasNotEnteredQuit(mainMenuChoice));
	}
	 
		private MainMenuChoices getMainMenuUserChoice( ) {
			int mainMenuUserChoice = new Input().menuChoice(this);
			return MainMenuChoices.values()[mainMenuUserChoice];
		}

	 private Menu getNewMenu(MainMenuChoices mainMenuChoice) {
		 Menu newMenu;
		 switch(mainMenuChoice){
		 case SINGLE_TEAM_SENARIO: 
			 newMenu = new SingleTeamMenu();
			 break;
		 case TWO_TEAM_SENARIO: 
			 newMenu = new TwoTeamMenu();
			 break;
		 case MODIFY_TEAM: 
			 newMenu = new ModifyTeamMenu();
			 break;
		 case CREATE_TEAM: 
			 newMenu = new CreateTeamMenu();
			 break;
		 default:
			 newMenu = new QuitMenu();
		 }	
		 return newMenu;
	 }

		private boolean userHasNotEnteredQuit(MainMenuChoices mainMenuChoice) {
		return mainMenuChoice!=MainMenuChoices.QUIT;
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
