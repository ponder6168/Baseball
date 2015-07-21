package controller;

import view.Input;

public class TwoTeamMenu implements ExecutesMenu {

	enum TwoTeamMenuChoices {
		NO_CHANGES 
			(". Play two teams with no changes."){
			@Override
			public TwoTeamSimulatable getSimulator(){
				return new TwoNoChangesSimulation();
			}
		},
		INCREMENT_ONE_PLAYERS_STAT	
			(". Play two teams and increment one player's stat."){
			@Override
			public TwoTeamSimulatable getSimulator(){
				return new TwoIncrementPlayerSimulation();
			}
		},
		INCREMENT_ONE_TEAM_STAT	
			(". Play two teams and increment one stat for the team."){
			@Override
			public TwoTeamSimulatable getSimulator(){
				return new TwoIncrementTeamSimulation();
			}
		},
		ROTATE_PLAYER_IN_BATTING_ORDER	
			(". Play two teams and rotate one player through the batting order."){
			@Override
			public TwoTeamSimulatable getSimulator(){
				return new TwoRotatePlayerInBattingOrderSimulation();
			}
		},
		QUIT
			(". Return to Main Menu."){
			@Override
			public TwoTeamSimulatable getSimulator(){
				return new QuitTwoTeamSimulator();
			}
		};

		private String promptMessage;

		private TwoTeamMenuChoices(String promptMessage) {
			this.promptMessage=promptMessage;
		}
		
		abstract TwoTeamSimulatable getSimulator();

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
		TwoTeamSimulatable simulator;
		do{
			simulator = getMenuChoice();
			runSimulation(simulator);			
		}while(userDidNotChooseQuit(simulator));

	}
	
	private void runSimulation(TwoTeamSimulatable simulator) {
		if(userDidNotChooseQuit(simulator)){
			TwoTeamSimulator twoTeamSimulator = new TwoTeamSimulator(simulator);
			twoTeamSimulator.executeMenuChoice();
		}
	}

	private boolean userDidNotChooseQuit(TwoTeamSimulatable simulator) {
		return !simulator.equals("QUIT");
	}

	private TwoTeamSimulatable getMenuChoice(){
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,this.menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return TwoTeamMenuChoices.values()[menuChoice-1].getSimulator(); 
	}


	public boolean equals(Object o){
		return o.equals("TWO_TEAM_SENARIO");
	}
}
