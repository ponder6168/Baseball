package controller;

import java.util.List;
import module.Storable;
import module.StorageObject;
import module.Team;

public class CreateDefaultTeamMenu implements ExecutesMenu {

	private List<Storable> tempListOfAvailableTeams;
	@Override
	public void executeMenuChoice() {
		tempListOfAvailableTeams = MainMenu.getListOfStorableObjects(StorageObject.TEAM);
		tempListOfAvailableTeams.add(new Team());
		MainMenu.setListOfStorableObjects(StorageObject.TEAM, tempListOfAvailableTeams);
		displayCurrentTeams();
	}
	private void displayCurrentTeams() {
		int lineNumber = 1;
		System.out.format("%n%s%n%n", "Here is the updated list of teams.");
		for(Storable team:tempListOfAvailableTeams){
			System.out.format("%s%s%s%n", lineNumber++,".  ",((Team) team).getDescription());
		}
		System.out.println();
	}

}
