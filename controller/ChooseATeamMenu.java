package controller;

import java.util.ArrayList;
import java.util.Scanner;

import module.Team;
import view.Input;
import view.Menu;

public class ChooseATeamMenu implements Menu {
	private Team chosenTeam;
	private String menuChoices;
	private ArrayList<Team> listOfAvailableTeams;
	private int numberOfMenuChoices;


	public ChooseATeamMenu(ArrayList<Team> listOfAvailableTeams) {
		this.listOfAvailableTeams = listOfAvailableTeams;
		this.numberOfMenuChoices = listOfAvailableTeams.size();
		StringBuilder menuMessage = new StringBuilder("Enter the number of the team you want to choose.") ;
		menuMessage.append(System.lineSeparator()+System.lineSeparator());
		
		for(int i=0;i<listOfAvailableTeams.size();i++){
			menuMessage.append((i+1)+".  "+listOfAvailableTeams.get(i).getDescription()+System.lineSeparator());
		}
		menuChoices= menuMessage.toString();
	}

	@Override
	public void printMenuChoices() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfMenuChoices() {
		return this.numberOfMenuChoices;
	}

	@Override
	public void presentMenuToUser() {
		this.chosenTeam = getUserTeamChoice();
	}

	private Team getUserTeamChoice() {
		int userTeamChoiceFromMenu;
		do{
			userTeamChoiceFromMenu = new Input().menuChoice(this);
		}while(correctTeamHasNotBeenChosen(userTeamChoiceFromMenu));
		return this.listOfAvailableTeams.get(userTeamChoiceFromMenu);
	}
	
	private boolean correctTeamHasNotBeenChosen(int userTeamChoiceFromMenu) {
		String teamDescription = this.listOfAvailableTeams.get(userTeamChoiceFromMenu).toString();
		return isThisTheWrongTeam();
	}

	private boolean isThisTheWrongTeam() {
		System.out.format("%n%s%n", "Is this the correct team (Y/N). ");
		String userChoice = getUserInput();
		return userChoice=="n";
	}

	private String getUserInput() {
		Scanner scan = new Scanner(System.in);
		String userInput = scan.next();
		return userInput.toLowerCase();
	}

	public Team getChosenTeam(){
		return this.chosenTeam;
	}

}
