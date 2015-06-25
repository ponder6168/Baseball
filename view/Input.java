package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ExecutesMenu;


public class Input {
	static final Scanner scan = new Scanner(System.in);

	public static int getInteger(String promptMessage) {
		System.out.print(promptMessage);
			while(!scan.hasNextInt()){
				scan.next();
				System.out.println("The value you entered is not an integer.  Please try again.");
				System.out.print(promptMessage);
			}
			 return scan.nextInt();
		}
	
	public static int getIntegerFromMinToMax(int minimumNumberAccepted, int maximumNumberAccepted, String promptMessage){
		int inputFromUser = getInteger(promptMessage);
		while(inputFromUser<minimumNumberAccepted || maximumNumberAccepted<inputFromUser){
			System.out.println("The value you entered is not from "+
								minimumNumberAccepted+" to "+
								maximumNumberAccepted+".  Please try again.");
			inputFromUser = getInteger(promptMessage);
		}
		return inputFromUser;
		
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
	
	private static String getLowerCaseUserInput() {
		String userInput = scan.next();
		return userInput.toLowerCase();
	}
	
	private static boolean userEnteredInvalidChoice(String userInput) {
		return !(userInput.equals("y") || userInput.equals("n"));
	}
}



