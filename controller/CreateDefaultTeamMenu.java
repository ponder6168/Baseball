package controller;

import java.util.ArrayList;

import module.Team;

public class CreateDefaultTeamMenu implements ExecutesMenu {

	private ArrayList<Team> tempListOfAvailableTeams;
	@Override
	public void executeMenuChoice() {
		tempListOfAvailableTeams = MainMenu.getListOfAvailableTeams();
		tempListOfAvailableTeams.add(new Team("default"));
		MainMenu.setListOfAvailableTeams(tempListOfAvailableTeams);
		displayCurrentTeams();
	}
	private void displayCurrentTeams() {
		int lineNumber = 1;
		System.out.format("%n%s%n%n", "Here is the updated list of teams.");
		for(Team team:tempListOfAvailableTeams){
			System.out.format("%s%s%s%n", lineNumber++,".  ",team.getDescription());
		}
		System.out.println();
	}

}
