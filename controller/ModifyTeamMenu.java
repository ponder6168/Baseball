package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MainMenu.MainMenuChoices;
import module.Team;
import module.TeamStorage;
import view.Input;

public class ModifyTeamMenu implements ExecutesMenu {
	
	enum ModifyTeamMenuChoices {
		MODIFY_DESCRIPTON("  1. Modify the team description.",new ModifyTeamDescriptionMenu()),
		MODIFY_BATTING_ORDER	("  2. Modify the team batting order.",new ModifyTeamBattingOrderMenu()),
		MODIFY_PLAYER	("  3. Modify a player on the team.",new ModifyPlayerOnTeamMenu()),
		MODIFY_STAT	("  4. Modify the team statistic.",new ModifyStatOnTeamMenu()),
		QUIT	("  5. Return to Main Menu.",new QuitMenuWithParameter());

		
		private String promptMessage;
		private ExecutesMenuWithParameter nextMenu;

			private ModifyTeamMenuChoices(String promptMessage, ExecutesMenuWithParameter nextMenu) {
				this.promptMessage=promptMessage;
				this.nextMenu = nextMenu;
			}

			public void printMenu(){
				System.out.format("%n%s%n%n", "Enter the number of your choice.");
				for(ModifyTeamMenuChoices menuChoice: ModifyTeamMenuChoices.values()){
					System.out.format("%s%n", menuChoice.promptMessage);
				}
			}
		}
	
		private int menuChoice;
		private int numberOfMenuChoices;
		ArrayList<Team> listOfAvailableTeams;
		private Team teamToModify;

		public ModifyTeamMenu() {
			this.numberOfMenuChoices=ModifyTeamMenuChoices.values().length;
		}
		
		@Override
		public void executeMenuChoice() {
			ExecutesMenuWithParameter newMenu;
			listOfAvailableTeams = new TeamStorage().retriveExistingTeams();
			teamToModify = getTheTeamToModify();
			do{
				newMenu = getMenuChoice();
				newMenu.setTeamToBeModified(teamToModify);
				newMenu.executeMenuChoice();
			}while(!newMenu.equals("QUIT"));
			new TeamStorage().storeAllTeamsInFile(listOfAvailableTeams);
		}

		public ExecutesMenuWithParameter getMenuChoice(){
			if(noTeamWasChoosenToModify()){
				return ModifyTeamMenuChoices.values()[numberOfMenuChoices-1].nextMenu; //Return QUIT Menu
			}
			ModifyTeamMenuChoices sampleModifyTeamMenuChoice = ModifyTeamMenuChoices.MODIFY_BATTING_ORDER;
			sampleModifyTeamMenuChoice.printMenu();
			menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices);
			return ModifyTeamMenuChoices.values()[menuChoice-1].nextMenu; //Subtract 1 to convert user input to zero based index
		}

		private boolean noTeamWasChoosenToModify() {
			return teamToModify==null;
		}

		private Team getTheTeamToModify() {
			ChooseATeamMenu chooseATeamToModify = new ChooseATeamMenu(listOfAvailableTeams);
			chooseATeamToModify.executeMenuChoice();
			return  chooseATeamToModify.getChosenTeam();
		}
		
		public boolean equals(Object o){
			return o.equals("MODIFY_TEAM");
		}
	}



	



