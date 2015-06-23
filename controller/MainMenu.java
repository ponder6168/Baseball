package controller;

import view.Input;

public class MainMenu implements ExecutesMenu {

	enum MainMenuChoices  { SINGLE_TEAM_SENARIO("  1. Run a Single Team Senario.", new SingleTeamMenu()),
		TWO_TEAM_SENARIO("  2. Run a Two Team Senario.", new TwoTeamMenu()), 
		MODIFY_TEAM("  3. Modify an Existing Team.", new ModifyTeamMenu()), 
		CREATE_TEAM("  4. Create a new Team.", new CreateTeamMenu()), 
		QUIT("  5. Exit the program.", new QuitMenu());

	private String promptMessage;
	private ExecutesMenu nextMenu;

		private MainMenuChoices(String promptMessage, ExecutesMenu nextMenu) {
			this.promptMessage=promptMessage;
			this.nextMenu = nextMenu;
		}

		public void printMenu(){
			System.out.format("%n%s%n%n", "Enter the number of your choice.");
			for(MainMenuChoices menuChoice: MainMenuChoices.values()){
				System.out.format("%s%n", menuChoice.promptMessage);
			}
		}
	}

	private int menuChoice;
	private int numberOfMenuChoices;
	
	public MainMenu() {
		this.numberOfMenuChoices=MainMenuChoices.values().length;
	}
	
	@Override
	public void executeMenuChoice() {
		ExecutesMenu newMenu;
		do{
			newMenu = getMenuChoice();
			newMenu.executeMenuChoice();			
		}while(!newMenu.equals("QUIT"));
	}

	public ExecutesMenu getMenuChoice(){
		MainMenuChoices sampleMainMenuChoice = MainMenuChoices.CREATE_TEAM;
		sampleMainMenuChoice.printMenu();
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices);
		return MainMenuChoices.values()[menuChoice-1].nextMenu; //Subtract 1 to convert user input to zero based index
	}

}

