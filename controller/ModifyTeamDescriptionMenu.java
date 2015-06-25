package controller;

import java.util.Scanner;

import module.Team;
import view.Input;


public class ModifyTeamDescriptionMenu implements ExecutesMenuWithParameter {
	private Team teamToModify;
	private int teamToModifyIndex;

	@Override
	public void executeMenuChoice() {
		getTeamToModify();
		setDescriptionOfTeamToModify(getNewTeamDescription());
		saveTeamToModify();
	}

	private void getTeamToModify() {
		teamToModify =  MainMenu.getListOfAvailableTeams().get(teamToModifyIndex);
	}

	private void saveTeamToModify() {
		MainMenu.getListOfAvailableTeams().set(teamToModifyIndex,teamToModify);
	}

	private void setDescriptionOfTeamToModify(String newTeamDescription) {
		this.teamToModify.setDescription(newTeamDescription);
	}

	private String getNewTeamDescription() {
		System.out.format("%n%s%n", "Type the new description and press Enter.");
		String userInput = getUserInput();
		if(userEnteredIncorrectDescription(userInput)){
			userInput = getNewTeamDescription();
		}
		return userInput;
	}

	private boolean userEnteredIncorrectDescription(String userInput) {
		System.out.format("%n%s%s%n", "You entered ",userInput);
		return Input.getYesOrNoFromTheUser("Is this the description you want? (Y/N)").equals("n");
	}

	private String getUserInput() {
		Scanner scan = new Scanner(System.in);
		return  scan.nextLine();
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_DESCRIPTION");
	}

	@Override
	public void setTeamToBeModifiedIndex(int teamToModifyIndex) {
		this.teamToModifyIndex = teamToModifyIndex;
	}
}
