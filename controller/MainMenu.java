package controller;

import view.Input;
import view.Menu;

public class MainMenu implements Menu {
	
	private String menuChoices;
	private int numberOfMenuChoices;
	
	enum MainMenuChoices  {SINGLE_TEAM_SENARIO, TWO_TEAM_SENARIO, MODIFY_TEAM, CREATE_TEAM, QUIT}
	
	public MainMenu() {
		menuChoices= String.format("%n%s%n%n%s%n%s%n%s%n%s%n%s%n",
				"Enter the number of your choice.",
				"  1. Run a Single Team Senario.",
				"  2. Run a Two Team Senario.",
				"  3. Modify an Existing Team.",
				"  4. Create a new Team.",
				"  5. Exit the program.");
		numberOfMenuChoices=5;

	}
		
	 public void runMainProgram() {
		 Menu mainMenu = new MainMenu();
		 MainMenuChoices mainMenuChoice;
			do{
				mainMenuChoice = getMainMenuUserChoice(mainMenu);
				runUsersChoice(mainMenuChoice);
			}while(userHasNotEnteredQuit(mainMenuChoice));
	}

		private boolean userHasNotEnteredQuit(MainMenuChoices mainMenuChoice) {
		return !(mainMenuChoice==MainMenuChoices.QUIT);
	}

		private MainMenuChoices getMainMenuUserChoice(Menu mainMenu) {
			Input userChoice = new Input();
			int mainMenuUserChoice = userChoice.menuChoice(mainMenu);
			return MainMenuChoices.values()[mainMenuUserChoice];
		}

	private void runUsersChoice(MainMenuChoices mainMenuChoice) {
			switch(mainMenuChoice){
				case SINGLE_TEAM_SENARIO: 
					SingleTeam singleTeam = new SingleTeam();
					singleTeam.runSingleTeamMenu();
					break;
				case TWO_TEAM_SENARIO: 
					TwoTeam twoTeam = new TwoTeam();
					twoTeam.runTwoTeamMenu();
					break;
				case MODIFY_TEAM: 
					TeamModifier teamModifier = new TeamModifier();
					teamModifier.runModifyTeamMenu();
					break;
				case CREATE_TEAM: 
					TeamCreator teamCreator = new TeamCreator();
					teamCreator.runTeamCreatorMenu();
					break;
				case QUIT:
					break;
				default:
			}	
		}			

	@Override
	public String getMenuChoices() {
		return menuChoices;
	}


	@Override
	public int getNumberOfMenuChoices() {
		// TODO Auto-generated method stub
		return numberOfMenuChoices;
	}
}