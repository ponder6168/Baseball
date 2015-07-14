package controller;

import view.Input;

public class SingleTeamMenu implements ExecutesMenu {

	enum SingleTeamMenuChoices {
		NO_CHANGES (". Play a team with no changes."){
			public SingleTeamSimulator getSimulator(){
				return new SingleTeamSimulator(new SingleNoChangeSimulation());
			}
		},
		INCREMENT_ONE_PLAYERS_STAT	(". Play a team and increment one player's stat."){
			public SingleTeamSimulator getSimulator(){
				return new SingleTeamSimulator(new SingleIncrementPlayerSimulation());
			}
		},
		INCREMENT_ONE_TEAM_STAT	(". Play a team and increment one stat for the team."){
			public SingleTeamSimulator getSimulator(){
				return new SingleTeamSimulator(new SingleIncrementTeamSimulation());
			}
		},
		ROTATE_PLAYER_IN_BATTING_ORDER	(". Play a team and rotate one player through the batting order."){
			public SingleTeamSimulator getSimulator(){
				return new SingleTeamSimulator(new SingleRotatePlayerInBattingOrderSimulation());
			}
		},
		QUIT	(". Return to Main Menu."){
			public SingleTeamSimulator getSimulator(){
				return new SingleTeamSimulator(new QuitSimulation());
			}
		};


		private String promptMessage;

		private SingleTeamMenuChoices(String promptMessage) {
			this.promptMessage=promptMessage;
		}

		@Override
		public String toString(){
			return this.promptMessage;
		}
		
		abstract public SingleTeamSimulator getSimulator();
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
			executeIfUserDidNotChooseQuit(newMenu);
		}while(!newMenu.equals("QUIT"));

	}
	
	private void executeIfUserDidNotChooseQuit(ExecutesMenu menu) {
		if(!menu.equals("QUIT")){
			menu.executeMenuChoice();			
		}
		
	}

	private ExecutesMenu getMenuChoice(){
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,this.menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return SingleTeamMenuChoices.values()[menuChoice-1].getSimulator(); 
	}

	public boolean equals(Object o){
		return o.equals("SINGLE_TEAM_SENARIO");
	}
}
