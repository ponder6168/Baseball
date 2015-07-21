package controller;

import java.util.List;

import module.Storable;
import module.StorageObject;
import module.Team;
import view.Input;

public class ModifyTeamMenu implements ExecutesMenu {

	enum ModifyTeamMenuChoices {
		MODIFY_DESCRIPTON(". Modify the team description.",	
				new ModifyTeamDescription()),
		MODIFY_BATTING_ORDER(". Modify the team batting order.",
				new ModifyTeamBattingOrder()),
		MODIFY_PLAYER	(". Modify a player on the team.",
				new ModifyPlayerOnTeam()),
		MODIFY_STAT	(". Modify the team statistic.",
				new ModifyStatOnTeam()),
		QUIT	(". Return to Previous Menu.",
				new QuitModifyMenu());

		private String promptMessage;
		private TeamModifier teamModifier;

		private ModifyTeamMenuChoices(String promptMessage, TeamModifier teamModifier) {
			this.promptMessage=promptMessage;
			this.teamModifier = teamModifier;
		}


		@Override
		public String toString(){
			return this.promptMessage;
		}
	}

	private List<Storable> availableTeams;
	private int menuChoice;
	private int numberOfMenuChoices;
	int teamToModifyIndex;
	private Team teamToModify;
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
		retrieveAvailableTeams();
		initializeTeamToModify();
		getTeamToModifyIndex();
		getTeamToModify();
		modifyTeam();
		saveModifiedTeam();
		saveAvailableTeams();
	}

	private void retrieveAvailableTeams() {
		availableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
	}

	private void initializeTeamToModify() {
		teamToModifyIndex = 0;
		teamToModify = (Team) availableTeams.get(teamToModifyIndex);
	}

	private void getTeamToModifyIndex() {
		teamToModifyIndex= new ChooseATeamMenu(
				"Choose the team you wish to modify.")
				.getIndexOfChosenTeam();
	}

	private void getTeamToModify() {
		if(userDidNotChooseQuit()){
			teamToModify =  (Team) availableTeams.get(teamToModifyIndex);
		}
	}

	private boolean userDidNotChooseQuit() {
		return teamToModifyIndex < MainMenu.getListOfStorableObjects(StorageObject.TEAM).size();
	}

	public void modifyTeam() {
		TeamModifier newModifier;
		do{
			displayTeamToModify();
			newModifier = getMenuChoice();
			executeChosenModification(newModifier);
		}while(!newModifier.equals("QUIT"));
	}

	private void displayTeamToModify() {
		if(userDidNotChooseQuit()){
			teamToModify.displayTeamWithMessage("Here is the team you are modifying");
		}
	}

	private TeamModifier getMenuChoice(){
		if(userDidNotChooseQuit()){
			menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,menuDisplay);
			//Subtract 1 to convert user input to zero based index
			return ModifyTeamMenuChoices.values()[menuChoice-1].teamModifier; 
		}
		return ModifyTeamMenuChoices.QUIT.teamModifier;
	}

	private void executeChosenModification(TeamModifier newModifier) {
		if(!newModifier.equals("QUIT")){
			teamToModify = newModifier.getModifiedTeam(teamToModify);
		}
	}

	public Team getModifiedTeam(){
		return new Team(teamToModify);
	}
	
	public void setTeamToModify(Team team) {
		this.teamToModify = new Team(team);
	}

	private void saveModifiedTeam() {
		if(userDidNotChooseQuit()){
			availableTeams.set(teamToModifyIndex, teamToModify);
		}
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, availableTeams);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_TEAM");
	}
}







