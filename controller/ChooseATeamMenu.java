package controller;

import module.Team;
import view.Input;

public class ChooseATeamMenu  {
	private String menuDisplay;
	private int numberOfMenuChoices;


	public ChooseATeamMenu() {
		//Add one for the QUIT option
		this.numberOfMenuChoices = MainMenu.getListOfAvailableTeams().size()+1; 
		StringBuilder menuMessage = new StringBuilder(System.lineSeparator()) ;
		menuMessage.append("Enter the number of the team you want to choose.") ;
		menuMessage.append(System.lineSeparator()).append(System.lineSeparator());

		int lineNumberOfCurrentMenuOption=1;
		for(Team team: MainMenu.getListOfAvailableTeams()){
			menuMessage.append(lineNumberOfCurrentMenuOption++).append(".  ")
						.append(team.getDescription()).append(System.lineSeparator());
		}
		menuMessage.append(lineNumberOfCurrentMenuOption).append(".  Return to Previous Menu.")
					.append(System.lineSeparator());
		menuDisplay= menuMessage.toString();
	}

	public int getIndexOfChosenTeam(){
		int userTeamChoiceFromMenu;
		do {
			// Subtract 1 to adjust user input for zero based index
			userTeamChoiceFromMenu = 
					Input.getIntegerFromMinToMax(1,	numberOfMenuChoices,menuDisplay) - 1;
		} while (correctTeamHasNotBeenChosen(userTeamChoiceFromMenu));
		return userTeamChoiceFromMenu;
	}

	private boolean correctTeamHasNotBeenChosen(int userTeamChoiceFromMenu) {
		if(userChoseToQuit(userTeamChoiceFromMenu)){
			return false;
		}
		String chosenTeam= MainMenu.getListOfAvailableTeams().get(userTeamChoiceFromMenu).toString();
		System.out.print(chosenTeam);
		return isThisTheWrongTeam();
	}

	private boolean userChoseToQuit(int userTeamChoiceFromMenu) {
		return userTeamChoiceFromMenu == numberOfMenuChoices-1;
	}

	private boolean isThisTheWrongTeam() {
		return Input.getYesOrNoFromTheUser("Is this the correct team (Y/N). ").equals("n");
	}


}
