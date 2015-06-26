package controller;

import view.Input;

public class TwoTeamMenu implements ExecutesMenu {

	enum TwoTeamMenuChoices {
		NO_CHANGES (". Play two teams with no changes.",
								new PlayTwoTeamsOneRoundMenu()),
		INCREMENT_ONE_PLAYERS_STAT	(". Play two teams and increment one player's stat.",
									new PlayTwoTeamsIncrementPlayerStatMenu()),
		INCREMENT_ONE_TEAM_STAT	(". Play two teams and increment one stat for the team.",
											new PlayTwoTeamsIncrementTeamStat()),
		ROTATE_PLAYER_IN_BATTING_ORDER	(". Play two teams and rotate one player through the batting order.",
													new PlayTwoTeamsRotatePlayerInBattingOrder()),
		QUIT	(". Return to Main Menu.",
					new QuitMenu());


		private String promptMessage;
		private ExecutesMenu nextMenu;

		private TwoTeamMenuChoices(String promptMessage,
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
	
	public TwoTeamMenu() {
		this.numberOfMenuChoices = TwoTeamMenuChoices.values().length;
		this.menuDisplay = buildMenuDisplay();
	}

	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder("Enter the number of your choice.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(TwoTeamMenuChoices menuChoice: TwoTeamMenuChoices.values()){
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
		return TwoTeamMenuChoices.values()[menuChoice-1].nextMenu; 
	}


	public boolean equals(Object o){
		return o.equals("TWO_TEAM_SENARIO");
	}
}
