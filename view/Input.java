package view;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Input {

	private int INVALID_MENU_CHOICE = -1;
	private int menuChoice;
	
	public int menuChoice(Menu menu){
		do{
			System.out.print(menu.getMenuChoices());
			menuChoice = getInteger();
		}while(menuChoiceIsInvalid(menu));
		return menuChoice-1; //Adjust for zero index of array
	}

	private boolean menuChoiceIsInvalid(Menu menu) {
		boolean inValid =!(0<menuChoice && menuChoice<=menu.getNumberOfMenuChoices());
		if (inValid)
			System.out.println("You did not enter an integer from 1 to "+menu.getNumberOfMenuChoices()+".");
		return inValid;
	}

	private int getInteger() {
		int userChoice;
		try{
			Scanner scan = new Scanner(System.in);
			userChoice = scan.nextInt();
		}catch(InputMismatchException e){
			userChoice= INVALID_MENU_CHOICE;
		}
		return userChoice;
	}
}