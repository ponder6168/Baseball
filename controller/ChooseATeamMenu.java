package controller;

import module.Storable;
import module.StorageObject;
import module.Team;
import view.Input;

public class ChooseATeamMenu  {
	private String menuDisplay;
	private int numberOfMenuChoices;


	public ChooseATeamMenu(String prompt) {
		//Add one for the QUIT option
		this.numberOfMenuChoices = MainMenu.getListOfStorableObjects(StorageObject.TEAM).size()+1; 
		StringBuilder menuMessage = new StringBuilder(System.lineSeparator()) ;
		menuMessage.append(prompt) ;
		menuMessage.append(System.lineSeparator()).append(System.lineSeparator());

		int lineNumberOfCurrentMenuOption=1;
		for(Storable team:  MainMenu.getListOfStorableObjects(StorageObject.TEAM)){
			menuMessage.append(lineNumberOfCurrentMenuOption++).append(".  ")
						.append(((Team) team).getDescription()).append(System.lineSeparator());
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
		String chosenTeam= MainMenu.getListOfStorableObjects(
								StorageObject.TEAM).get(userTeamChoiceFromMenu).toString();
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
