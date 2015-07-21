package controller;

import java.util.ArrayList;
import java.util.List;
import module.Storable;
import module.StorageAndRetrieval;
import module.StorageObject;
import view.Input;

public class MainMenu implements ExecutesMenu {

	enum MainMenuChoices  { 
		SINGLE_TEAM_SENARIO(".  Run a Single Team Senario.", new SingleTeamMenu()),
		TWO_TEAM_SENARIO(".  Run a Two Team Senario.", new TwoTeamMenu()), 
		MODIFY_TEAM(".  Modify an Existing Team.", new ModifyTeamMenu()), 
		CREATE_TEAM(".  Create a new Team.", new CreateTeamMenu()), 
		EXAMINE_PREVIOUS_SIMULATIONS(".  Examine previous simulatons.", new PreviousSimulationsMenu()), 
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

	private static List<List<Storable>> listOfStorableLists=new ArrayList<>();

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
		loadStoredData();
		ExecutesMenu newMenu;
		do{
			newMenu = getMenuChoice();
			newMenu.executeMenuChoice();			
		}while(!newMenu.equals("QUIT"));
		saveAllStorableLists();
	}

	private void loadStoredData() {
		for(StorageObject storageObject: StorageObject.values()){
			listOfStorableLists
				.add(new StorageAndRetrieval(storageObject).retrieveExistingObjects());
		}
	}

	private ExecutesMenu getMenuChoice(){
		menuChoice = Input.getIntegerFromMinToMax(1, numberOfMenuChoices,this.menuDisplay);
		//Subtract 1 to convert user input to zero based index
		return MainMenuChoices.values()[menuChoice-1].nextMenu; 
	}

	private void saveAllStorableLists() {
		int index=0;
		for(StorageObject storageObject: StorageObject.values()){
			new StorageAndRetrieval(
					storageObject).storeObjectsInFile(listOfStorableLists.get(index++));
		}
	}

	public static ArrayList<Storable> getListOfStorableObjects(StorageObject storableObject) {
		ArrayList<Storable> copyOfListOfStorableObjects = new ArrayList<>();
		for(Storable storable: listOfStorableLists.get(storableObject.ordinal())){
			copyOfListOfStorableObjects.add(storable.deepCopy());
		}
		return copyOfListOfStorableObjects;
	}

	public static void setListOfStorableObjects(StorageObject storableObject,
										List<Storable> listOfStorableObjects) {
		listOfStorableLists.get(storableObject.ordinal()).clear();
		for(Storable storable:listOfStorableObjects){
			listOfStorableLists.get(storableObject.ordinal()).add(storable.deepCopy());
		}
	}
}

