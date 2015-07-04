package view;

import java.util.Scanner;

public class Input {
	private static final Scanner scan = new Scanner(System.in);

	public static int getInteger(String promptMessage) {
		System.out.format("%n%s%n", promptMessage);
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
		String userInput = scan.next();
		while(userEnteredInvalidChoice(userInput)){
			System.out.format("%n%s%n", "You must enter Y, y, N or n.");
			System.out.format("%n%s%n", promptMessage);
			userInput = scan.next();
		}
		return userInput.toLowerCase();
	}
	
	private static boolean userEnteredInvalidChoice(String userInput) {
		return !(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n"));
	}
	
	public static String getLineOfUserInput(String prompt) {
		System.out.print(prompt);
		String line;
		do{
			line = scan.nextLine();
		} while(line.trim().isEmpty());
		return  line;
	}

}



