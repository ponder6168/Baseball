package controller;

import java.util.ArrayList;

import module.Team;
import module.TeamStorage;
import view.Input;

public class MainMenu implements ExecutesMenu {

	enum MainMenuChoices  { 
		SINGLE_TEAM_SENARIO(".  Run a Single Team Senario.", new SingleTeamMenu()),
		TWO_TEAM_SENARIO(".  Run a Two Team Senario.", new TwoTeamMenu()), 
		MODIFY_TEAM(".  Modify an Existing Team.", new ModifyTeamMenu()), 
		CREATE_TEAM(".  Create a new Team.", new CreateTeamMenu()), 
		QUIT(".  Exit the program.", new QuitMenu());

		private String promptMessage;
		private ExecutesMenu nextMenu;

		private MainMenuChoices(String promptMessage, ExecutesMenu nextMenu) {
			this.promptMessage=promptMessage;
			this.nextMenu = nextMenu;
		}

		@Override
		public  String toString(){		
			return this.promptMessage;
		}
	}

	private static ArrayList<Team> listOfAvailableTeams;
	private int menuChoice;
	private int numberOfMenuChoices;
	private String menuDisplay;

	public MainMenu() {
		this.numberOfMenuChoices=MainMenuChoices.values().length;
		this.menuDisplay = buildMenuDisplay();
	}

	private String buildMenuDisplay() {
		StringBuilder menuDisplay = new StringBuilder("Enter the number of your choice.");
		menuDisplay.append(System.lineSeparator()).append(System.lineSeparator());
		int lineNumber = 1;
		for(MainMenuChoices menuChoice: MainMenuChoices.values()){
			menuDisplay.append(lineNumber++).append(menuChoice.toString())
											.append(System.lineSeparator());
		}
		return menuDisplay.toString();
	}

	@Override
	public  String toString(){		
		return this.menuDisplay;
	}

	@Override
	public void executeMenuChoice() {
		loadAllAvailableTeams();
		ExecutesMenu newMenu;
		do{
			newMenu = getMenuChoice();
			newMenu.executeMenuChoice();			
		}while(!newMenu.equals("QUIT"));
		storeAllAvailableTeams();
	}

	private void loadAllAvailableTeams() {
		listOfAvailableTeams = new TeamStorage().retriveExistingTeams();
	}

	private ExecutesMenu getMenuChoice(){
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,this.menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return MainMenuChoices.values()[menuChoice-1].nextMenu; 
	}

	private void storeAllAvailableTeams() {
		new TeamStorage().storeAllTeamsInFile(listOfAvailableTeams);
	}

	public static ArrayList<Team> getListOfAvailableTeams() {
		ArrayList<Team> copyOfListOfAvailableTeams = new ArrayList<>();
		for(Team team: MainMenu.listOfAvailableTeams){
			copyOfListOfAvailableTeams.add(team);
		}
		return copyOfListOfAvailableTeams;
	}

	public static void setListOfAvailableTeams(ArrayList<Team> listOfAvailableTeams) {
		MainMenu.listOfAvailableTeams.clear();
		for(Team team:listOfAvailableTeams){
			MainMenu.listOfAvailableTeams.add(team);
		}
	}
}

