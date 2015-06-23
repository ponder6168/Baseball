package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu implements ExecutesMenu{
	
	public static final int INVALID_MENU_CHOICE = -1;
	public int menuChoice;
	public int numberOfMenuChoices;

	public int getInteger() {
		int userChoice;
		try{
			Scanner scan = new Scanner(System.in);
			userChoice = scan.nextInt();
		}catch(InputMismatchException e){
			userChoice= INVALID_MENU_CHOICE;
		}
		return userChoice;
	}
	
	public boolean menuChoiceIsInvalid() {
		boolean inValid = !(0<menuChoice && menuChoice<=numberOfMenuChoices);
		if (inValid){
			System.out.format("%s%s%s%n","You did not enter an integer from 1 to ",numberOfMenuChoices,".");
		}
		return inValid;
	}
	
	public static String getYesOrNoFromTheUser(String promptMessage){
		System.out.format("%n%s%n", promptMessage);
		String userInput = getLowerCaseUserInput();
		if(userEnteredInvalidChoice(userInput)){
			System.out.format("%n%s%n", "You must enter Y, y, N or n.");
			userInput = getYesOrNoFromTheUser(promptMessage);
		}
		return userInput;
	}
	
	private static boolean userEnteredInvalidChoice(String userInput) {
		return !(userInput.equals("y") || userInput.equals("n"));
	}

	private static String getLowerCaseUserInput() {
		Scanner scan = new Scanner(System.in);
		String userInput = scan.next();
		return userInput.toLowerCase();
	}
}
