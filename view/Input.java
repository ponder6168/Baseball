package view;

import java.util.Scanner;


public class Input {
	public static final int mainMenuItemCount=4;
	public static final int stealsChoiceMenuItemCount=2;
	public static final int runsPerGameMenuItemCount=2;
	public static final int teamCompetitionMenuItemCount=6;
	public static final int battingOrderMenuItemCount=2;
	public static final int modifyTeamMenuItemCount=5;
	public static final int modifyStatisticMenuItemCount=8;
	public static final int modifyStatisticChoiceMenuItemCount=3;
	public static final int modifyPlayerMenuItemCount=2;
	public static final int modifyPlayerDataMenuItemCount=8;
	public static final int modifyPlayerChoiceMenuItemCount=9;
	

	public Input() {
		// TODO Auto-generated constructor stub
	}


	public static void mainMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Run a Runs per Game Senario.");
		System.out.println("  2. Run a Team competition Senario.");
		System.out.println("  3. Investigate Batting orders.");
		System.out.println("  4. Exit the program.");
	}
	
	

	public static void teamCompetitionMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Use an existing team for team 1.");
		System.out.println("  2. Modify an existing team for team 1.");
		System.out.println("  3. Enter a new team for team 1.");
		System.out.println("  4. Use an existing team for team 2.");
		System.out.println("  5. Modify an existing team for team 2.");
		System.out.println("  6. Enter a new team for team 2.");
	}

	public static void battingOrderMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Rotate a player through the batting order.");
		System.out.println("  2. Return to previous menu.");
	}

	public static void modifyTeamMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Modify the team description");
		System.out.println("  2. Modify a player.");
		System.out.println("  3. Modify a statistic.");
		System.out.println("  4. Modify the batting order.");
		System.out.println("  5. Exit modification.");
	}

	public static void modifyPlayerMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Enter all new data for the player.");
		System.out.println("  2. Change some of the player data.");
	
	}
	
	public static void modifyStatisticMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Modify At Bats");
		System.out.println("  2. Modify Hits");
		System.out.println("  3. Modify Doubles");
		System.out.println("  4. Modify Triples");
		System.out.println("  5. Modify Home Runs");
		System.out.println("  6. Modify Walks");
		System.out.println("  7. Modify Stolen Bases");
		System.out.println("  8. Modify Caught Stealing");
	}
	
	public static void modifyStatisticChoiceMenu(){
		System.out.println();
		System.out.println("Enter the number of your choice.");
		System.out.println();
		System.out.println("  1. Enter the same value for all of the players.");
		System.out.println("  2. Enter a seperate value for each player.");
		System.out.println("  3. Exit Modifying a stat.");

	}
	
	public static void modifyPlayerDataMenu(){
		System.out.println();
		System.out.println("Enter the number of the stat you want to change.");
		System.out.println();
		System.out.println("  1. At Bats");
		System.out.println("  2. Hits");
		System.out.println("  3. Doubles");
		System.out.println("  4. Triples");
		System.out.println("  5. Home Runs");
		System.out.println("  6. Walks");
		System.out.println("  7. Stolen Bases");
		System.out.println("  8. Caught Stealing");

	}




	public static int menuChoice(int menuItemCount){
		Boolean inputAccepted=false;
		Scanner scan = new Scanner(System.in);
		int choice=0;
		while(!inputAccepted){
			if(scan.hasNextInt()){
				choice=scan.nextInt();
				scan.nextLine();
				if(0<choice && choice<=menuItemCount){
					inputAccepted=true;
				}else{
					System.out.println("Please enter a number from 1 to "+menuItemCount+".");
				}
			}else{
				System.out.println("Please enter a number from 1 to "+menuItemCount+".");
			}
		}
		return choice;
	}
}
