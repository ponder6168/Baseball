package controller;

import java.util.ArrayList;
import module.Team;
import view.Input;

public class ModifyTeamDescriptionMenu implements ExecutesMenu {
	private ArrayList<Team> copyOfListOfAvailableTeams;
	private Team teamToModify;
	private int teamToModifyIndex;
	
	public ModifyTeamDescriptionMenu(int teamToModifyIndex) {
		this.teamToModifyIndex = teamToModifyIndex;
	}

	@Override
	public void executeMenuChoice() {
		retrieveAvailableTeams();
		getTeamToModify();
		setDescriptionOfTeamToModify(getNewTeamDescription());
		saveAvailableTeams();
	}

	private void retrieveAvailableTeams() {
		copyOfListOfAvailableTeams = MainMenu.getListOfAvailableTeams();
	}

	private void getTeamToModify() {
		teamToModify =  copyOfListOfAvailableTeams.get(teamToModifyIndex);
	}

	private void setDescriptionOfTeamToModify(String newTeamDescription) {
		this.teamToModify.setDescription(newTeamDescription);
	}

	private String getNewTeamDescription() {
		String userInput = Input.getLineOfUserInput("Type the new description and press Enter."+
													 System.lineSeparator());
		while (userEnteredIncorrectDescription(userInput)){
			userInput = Input.getLineOfUserInput("Type the new description and press Enter."+
					 System.lineSeparator());
		}
		return userInput;
	}

	private boolean userEnteredIncorrectDescription(String userInput) {
		System.out.format("%n%s%s%s%n", "You entered: \"",userInput,"\"");
		return Input.getYesOrNoFromTheUser("Is this the description you want? (Y/N)").equals("n");
	}

	private void saveAvailableTeams() {
		MainMenu.setListOfAvailableTeams(copyOfListOfAvailableTeams);
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_DESCRIPTION");
	}
}
