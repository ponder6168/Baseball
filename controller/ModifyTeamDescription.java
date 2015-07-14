package controller;

import module.Team;
import view.Input;

public class ModifyTeamDescription implements TeamModifier {
	private Team teamToModify;
	
	@Override
	public Team getModifiedTeam(Team teamToModify) {
		this.teamToModify = teamToModify;
		setDescriptionOfTeamToModify(getNewTeamDescription());
		return this.teamToModify;
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

	public boolean equals(Object o){
		return o.equals("MODIFY_DESCRIPTION");
	}

}
