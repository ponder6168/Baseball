package controller;

import java.util.ArrayList;
import module.Team;
import view.Input;

public class ChooseATeamMenu implements ExecutesMenu {
	private Team chosenTeam;
	private String menuChoices;
	private ArrayList<Team> listOfAvailableTeams;
	private int numberOfMenuChoices;


	public ChooseATeamMenu(ArrayList<Team> listOfAvailableTeams) {
		this.listOfAvailableTeams = listOfAvailableTeams;
		this.numberOfMenuChoices = listOfAvailableTeams.size()+1; //Add one for the QUIT option
		StringBuilder menuMessage = new StringBuilder(System.lineSeparator()) ;
		menuMessage.append("Enter the number of the team you want to choose.") ;
		menuMessage.append(System.lineSeparator()).append(System.lineSeparator());
		
		int lineNumberOfCurrentMenuOption=1;
		for(Team team: listOfAvailableTeams){
			menuMessage.append(lineNumberOfCurrentMenuOption++).append(".  ").append(team.getDescription()).append(System.lineSeparator());
		}
		menuMessage.append(lineNumberOfCurrentMenuOption).append(".  Return to Main Menu.").append(System.lineSeparator());
		menuChoices= menuMessage.toString();
	}


	@Override
	public void executeMenuChoice() {
		this.chosenTeam = getUserTeamChoice();
	}

	private Team getUserTeamChoice() {
		int userTeamChoiceFromMenu;
		do {
			System.out.print(menuChoices);
			userTeamChoiceFromMenu = Input.getIntegerFromMinToMax(1,
					numberOfMenuChoices) - 1;// Adjust user input for zero based index
			if (userChoseToQuit(userTeamChoiceFromMenu)) {
				return null;
			}
		} while (correctTeamHasNotBeenChosen(userTeamChoiceFromMenu));
		return this.listOfAvailableTeams.get(userTeamChoiceFromMenu);
	}
	
	private boolean correctTeamHasNotBeenChosen(int userTeamChoiceFromMenu) {
		String chosenTeam= this.listOfAvailableTeams.get(userTeamChoiceFromMenu).toString();
		System.out.print(chosenTeam);
		return isThisTheWrongTeam();
	}

	private boolean userChoseToQuit(int userTeamChoiceFromMenu) {
		return userTeamChoiceFromMenu==numberOfMenuChoices-1;
	}


	private boolean isThisTheWrongTeam() {
		return Input.getYesOrNoFromTheUser("Is this the correct team (Y/N). ").equals("n");
	}

	public Team getChosenTeam(){
		return this.chosenTeam;
	}

}
