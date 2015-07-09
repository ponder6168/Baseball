package controller;

import module.StorageObject;
import module.Team;
import view.Input;

public class ModifyTeamMenu implements ExecutesMenu {

	enum ModifyTeamMenuChoices {
		MODIFY_DESCRIPTON(". Modify the team description."){
			@Override
			public ExecutesMenu getModifyMenu(int indexOfTeamToModify){
				return 	new ModifyTeamDescriptionMenu(indexOfTeamToModify);
			}
		},
		MODIFY_BATTING_ORDER	(". Modify the team batting order."){
			@Override
			public ExecutesMenu getModifyMenu(int indexOfTeamToModify){
				return 	new ModifyTeamBattingOrderMenu(indexOfTeamToModify);
			}
		},
		MODIFY_PLAYER	(". Modify a player on the team."){
			@Override
			public ExecutesMenu getModifyMenu(int indexOfTeamToModify){
				return 	new ModifyPlayerOnTeamMenu(indexOfTeamToModify);
			}
		},
		MODIFY_STAT	(". Modify the team statistic."){
			@Override
			public ExecutesMenu getModifyMenu(int indexOfTeamToModify){
				return 	new ModifyStatOnTeamMenu(indexOfTeamToModify);
			}
		},
		QUIT	(". Return to Previous Menu."){
			@Override
			public ExecutesMenu getModifyMenu(int indexOfTeamToModify){
				return 	new QuitMenu();
			}
		};


		private String promptMessage;

		private ModifyTeamMenuChoices(String promptMessage) {
			this.promptMessage=promptMessage;
		}
		
		public abstract ExecutesMenu getModifyMenu(int indexOfTeamToModify);

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
		setTeamToModifyIndexConsole();
		modifyTeam();
	}

	private void setTeamToModifyIndexConsole() {
		teamToModifyIndex = new ChooseATeamMenu(
				"Choose the team you wish to modify.").getIndexOfChosenTeam();
	}

	public void modifyTeam() {
		ExecutesMenu newMenu;
		do{
			displayTeamToModify();
			newMenu = getMenuChoice();
			newMenu.executeMenuChoice();
		}while(!newMenu.equals("QUIT"));
	}

	private void displayTeamToModify() {
		if(userDidNotChooseQuit()){
			((Team) MainMenu.getListOfStorableObjects(StorageObject.TEAM).get(teamToModifyIndex)).
	         displayTeamWithMessage("Here is the team you are modifying");
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToModifyIndex < MainMenu.getListOfStorableObjects(StorageObject.TEAM).size();
	}

	public ExecutesMenu getMenuChoice(){
		if(userDidNotChooseQuit()){
			menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,menuDisplay);
			//Subtract 1 to convert user input to zero based index
			return ModifyTeamMenuChoices.values()[menuChoice-1].getModifyMenu(teamToModifyIndex); 
		}
		return ModifyTeamMenuChoices.QUIT.getModifyMenu(teamToModifyIndex);
	}
	
	public void setTeamToModifyIndex(int teamToModifyIndex){
		this.teamToModifyIndex = teamToModifyIndex;
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_TEAM");
	}
}







