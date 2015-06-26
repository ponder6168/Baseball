package controller;

import view.Input;
import controller.MainMenu.MainMenuChoices;

public class CreateTeamMenu implements ExecutesMenu {
	
	enum CreateTeamMenuChoices {
		CREATE_DEFAULT_TEAM (". Create a team of default players.",
								new CreateDefaultTeamMenu()),
		CREATE_NEW_TEAM_FROM_EXISTING_TEAM	(". Modify an existing team to create a new team.",
									new CreateTeamFromExistingTeamMenu()),
		CREATE_TEAM_FROM_SCRATCH	(". Create a team by entering each player individually.",
							new CreateTeamFromScratchMenu()),
		QUIT	(". Return to Main Menu.",
					new QuitMenu());


		private String promptMessage;
		private ExecutesMenu nextMenu;

		private CreateTeamMenuChoices(String promptMessage,
										ExecutesMenu nextMenu) {
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
	private String menuDisplay;
	
	public CreateTeamMenu() {
		this.numberOfMenuChoices = CreateTeamMenuChoices.values().length;
		this.menuDisplay = buildMenuDisplay();
	}

	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder("Enter the number of your choice.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(CreateTeamMenuChoices menuChoice: CreateTeamMenuChoices.values()){
			menuDisplay.append(lineNumber++).append(menuChoice.toString())
											.append(System.lineSeparator());
		}
		return menuDisplay.toString();
	}
	
	@Override
	public String toString(){
		return this.menuDisplay;
	}

	@Override
	public void executeMenuChoice() {
		ExecutesMenu newMenu;
		do{
			newMenu = getMenuChoice();
			newMenu.executeMenuChoice();			
		}while(!newMenu.equals("QUIT"));

	}
	
	private ExecutesMenu getMenuChoice(){
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,this.menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return CreateTeamMenuChoices.values()[menuChoice-1].nextMenu; 
	}


	public boolean equals(Object o){
		return o.equals("CREATE_TEAM");
	}
}
