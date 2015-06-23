package controller;

import java.util.Scanner;

import module.Team;
import view.Input;


public class ModifyTeamDescriptionMenu implements ExecutesMenuWithParameter {
	private Team teamToModify;

	public ModifyTeamDescriptionMenu() {
	}

	@Override
	public void executeMenuChoice() {
		setDescriptionOfTeamToModify(getNewTeamDescription());
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

	@Override
	public void setTeamToBeModified(Team team) {
		this.teamToModify = team;
	}


	public boolean equals(Object o){
		return o.equals("MODIFY_DESCRIPTION");
	}
}
