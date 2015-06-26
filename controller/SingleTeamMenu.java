package controller;

import view.Input;

public class SingleTeamMenu implements ExecutesMenu {

	enum SingleTeamMenuChoices {
		NO_CHANGES (". Play a team with no changes.",
								new PlaySingleTeamOneRoundMenu()),
		INCREMENT_ONE_PLAYERS_STAT	(". Play a team and increment one player's stat.",
									new PlaySingleTeamIncrementPlayerStatMenu()),
		INCREMENT_ONE_TEAM_STAT	(". Play a team and increment one stat for the team.",
											new PlaySingleTeamIncrementTeamStat()),
		ROTATE_PLAYER_IN_BATTING_ORDER	(". Play a team and rotate one player through the batting order.",
													new PlaySingleTeamRotatePlayerInBattingOrder()),
		QUIT	(". Return to Main Menu.",
					new QuitMenu());


		private String promptMessage;
		private ExecutesMenu nextMenu;

		private SingleTeamMenuChoices(String promptMessage,
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
	
	public SingleTeamMenu() {
		this.numberOfMenuChoices = SingleTeamMenuChoices.values().length;
		this.menuDisplay = buildMenuDisplay();
	}

	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder("Enter the number of your choice.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(SingleTeamMenuChoices menuChoice: SingleTeamMenuChoices.values()){
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
		return SingleTeamMenuChoices.values()[menuChoice-1].nextMenu; 
	}

	public boolean equals(Object o){
		return o.equals("SINGLE_TEAM_SENARIO");
	}
}
