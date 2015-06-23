package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import module.Player;
import module.Team;
import view.Input;

public class SingleTeamPerformance {
}
/*
	private static double iterations=100000;
	private static String runsPerGameFile="runsPerGame";
	public static double getIterations() {
		return iterations;
	}

	public static void setIterations(double iterations) {
		SingleTeamPerformance.iterations = iterations;
	}

	public SingleTeamPerformance() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)  throws IOException, ClassNotFoundException {
		iterations=100000;
		runsPerGameFile="runsPerGame";
		boolean quit=false;
		while(quit==false){
			Input.mainMenu();
			int choice=Input.menuChoice(Input.mainMenuItemCount);

			switch(choice){
				case 1: runsPerGame();
					break;
				case 2: teamCompetition();
					break;
				case 3: battingOrder();
					break;
				case 4: quit=true;
					break;
				default:
			}	
		}

	}
	
	 public static void runsPerGame() throws IOException{
		    ArrayList<Team> availableTeams=loadTeams(runsPerGameFile); //Create an ArrayList to hold all of the previous teams
            //Choose a team to play or modify
	    	chooseATeam(availableTeams);
			int choice=Input.menuChoice(availableTeams.size()+1);
			Team team = new Team(availableTeams.get(choice-1));
			//Modify the chosen team
			modifyTeam(team);
			
			//Play the team with and without steals
			team.setCanSteal(true);
			generateRunsPerGameHistogram(team);
			team.setCanSteal(false);
			generateRunsPerGameHistogram(team);
			team.printPerGameResults(team);
			
			if(yesOrNo("Do you wish to save these results? (y/n)","y")){
			    ObjectOutputStream out = null;

				out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(runsPerGameFile)));
  			  availableTeams.add(team);
  			  for(int i=0;i<availableTeams.size();i++ ){
  	  			  out.writeObject(availableTeams.get(i));
  			  }
  			  out.close();
			}

	 }

	
	 private static ArrayList<Team> loadTeams(String fileName) throws IOException{
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    ArrayList<Team> availableTeams=new ArrayList<Team>(); //Create an ArrayList to hold all of the previous teams
    try {
    	//runsPerGameFile defined in main method of Game
		File newFile = new File(fileName);
		newFile.createNewFile();
		FileInputStream fis = new FileInputStream(new File(fileName));  
		int b = fis.read();
		fis.close();
		if (b == -1)  
		{  
		  out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		  out.writeObject(new Team("default"));
		  out.close();
		}  
        in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
    	while(true){
            //Note the first team will always be the default team.
		    availableTeams.add((Team) in.readObject());
    	}
	} catch (EOFException e) {
		//Read teams until the end of the file
	} catch (FileNotFoundException e) {
        System.err.println("Created the file "+fileName);
    } catch (IOException e) {
        System.err.println("Caught IOException: " + e.getMessage());
    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
        in.close();
    }
	return availableTeams;
}
	 
	public static void chooseATeam(ArrayList<Team> availableTeams){
        System.out.println("Choose a team to play or modify.");
        Iterator<Team> teams = availableTeams.iterator();
        int count=1;
        while(teams.hasNext()){
        	System.out.println(count+++".  "+teams.next().getDescription());
        }
	}
        


	public static void generateRunsPerGameHistogram(Team team ){
		

		int [] gameRuns = new int[30];
		double [] histogramGameRuns = new double[30];
		for(int i=0; i<30; i++){
			gameRuns[i]=0;
		}
		

		Boolean canSteal=team.getCanSteal();
		team.setCurrentBatter(0);
		

		for(int j=0; j<iterations ;j++){
			int totalRuns=0;
			for(int i=0; i<9 ; i++){
				int runs = team.playInning(team); // returns the number of runs scored in the inning.
				totalRuns+=runs;  
			}

			if(totalRuns<30){
				gameRuns[totalRuns]++;
			}else{
				gameRuns[29]++;
			}
			
		}
		int SumOfGameRuns=0;
		for (int i=0 ; i<30; i++){
			histogramGameRuns[i]=gameRuns[i]/iterations;
			SumOfGameRuns+=gameRuns[i]*i;
		}
		double runsPerGame=SumOfGameRuns/iterations;
		if (canSteal==true){
			team.setRunsPerGameWithSteals(runsPerGame);
			team.setRunHistogramWithSteals(histogramGameRuns);
		}else{
			team.setRunsPerGameWithoutSteals(runsPerGame);
			team.setRunHistogramWithoutSteals(histogramGameRuns);
	
		}
	}
	


	public static Team chooseAnExistingUnplayedTeam(){
		Team team = new Team();
		System.out.println("chooseAnExistingUnplayedTeam stub");
		return team;
	}
	
	public static void modifyTeam(Team team){
		boolean change = true;
		while(change==true){
			team.printTeamStandard();
			Input.modifyTeamMenu();
			int choice=Input.menuChoice(Input.modifyTeamMenuItemCount);
			switch(choice){
			case 1: modifyTeamDescription(team);
				break;
			case 2: modifyPlayer(team);
				break;
			case 3: modifyStatistic(team);
				break;
			case 4: modifyBattingOrder(team);
				break;
			case 5: change = false;
				break;
				default:
			}
		}
	}
	
	private static void modifyBattingOrder(Team team) {
		int player1=0;
		int player2=0;
		boolean orderCorrect = false;
		while(orderCorrect==false){
			boolean correctPlayer=false;
			while(correctPlayer==false){
				team.printTeamStandard();
				//Pass the list position of the player which is one less than the printed player number
				System.out.println();
				System.out.println("Enter the number of the first player you wish to move.");
				System.out.println();
				player1=Input.menuChoice(Input.modifyPlayerChoiceMenuItemCount);
				team.printTeamPlayer(player1-1);
				if(yesOrNo("Is this the correct player? (y/n)","y")){
					correctPlayer=true;
				}
			}
			correctPlayer=false;
			while(correctPlayer==false){
				team.printTeamStandard();
				//Pass the list position of the player which is one less than the printed player number
				System.out.println();
				System.out.println("Enter the number of the second player you wish to move.");
				System.out.println();
				player2=Input.menuChoice(Input.modifyPlayerChoiceMenuItemCount);
				team.printTeamPlayer(player2-1);
				if(yesOrNo("Is this the correct player? (y/n)","y")){
					correctPlayer=true;
				}
			}
			switchPlayers(team, player1-1, player2-1);
			System.out.println();
			System.out.println("Here is the new batting order");
			System.out.println();
			team.printTeamStandard();
			System.out.println();
			if(yesOrNo("Do you wish to change the batting order again? (y/n)","n")){
				orderCorrect=true;
			}

		}
	}

	private static void modifyTeamDescription(Team team){
		Boolean inputAccepted=false;
		Scanner scan = new Scanner(System.in);
		while(!inputAccepted){
			System.out.println("");
			System.out.println("Enter your new team description.");
			team.setDescription(scan.nextLine());
			System.out.println("You entered:");
			System.out.println("");
			System.out.println(team.getDescription());
			System.out.println("");
			if(yesOrNo("Is this description correct? (y/n)","y")){
				inputAccepted=true;
			}
		}
	}
	
	private static boolean yesOrNo(String message, String yOrNisTrue){
		Scanner scan = new Scanner(System.in);
		String answer = new String();
		while(true){
			System.out.println();
			System.out.println(message);
			System.out.println();
			answer=scan.next();
			scan.nextLine();
			if(answer.equalsIgnoreCase(yOrNisTrue)){
				return true;
			}else if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")){
				return false;
			}else{
				System.out.println("Please enter a y or an n.");
			}
		}
	}

	private static void modifyPlayer(Team team){
		boolean change = true;
		
		int player=0;
		while(change==true){
			boolean correctPlayer=false;
			while(correctPlayer==false){
				team.printTeamStandard();
				//Pass the list position of the player which is one less than the printed player number
				System.out.println();
				System.out.println("Enter the number of the player you wish to modify.");
				System.out.println();
				player=Input.menuChoice(Input.modifyPlayerChoiceMenuItemCount);
				team.printTeamPlayer(player-1);
				if(yesOrNo("Is this the correct player? (y/n)","y")){
					correctPlayer=true;
				}
			}
			Input.modifyPlayerMenu();
			int choice=Input.menuChoice(Input.modifyPlayerMenuItemCount);
			switch(choice){
			case 1: allNewPlayerData(team.getTeam().get(player-1));
				break;
			case 2: modifyPlayerData(team.getTeam().get(player-1));
				break;
				default:
			}
			if(yesOrNo("Do you wish to modify another player? (y/n)","n")){
				change=false;
			}
		}
	}
	
	private static void modifyPlayerData(Player player) {
		boolean done = false;
		while(done==false){
			Input.modifyPlayerDataMenu();
			int choice=Input.menuChoice(Input.modifyPlayerDataMenuItemCount);
			switch(choice){
			case 1: 
				System.out.println("Enter the new At Bats value.");
				player.setAtBats(getValue());
				break;
			case 2:  				
				System.out.println("Enter the new Hits value.");
				player.setHits(getValue());
				break;
			case 3:  
				System.out.println("Enter the new Doubles value.");
				player.setDoubles(getValue());
				break;
			case 4:  
				System.out.println("Enter the new Triples value.");
				player.setTriples(getValue());
				break;
			case 5:  
				System.out.println("Enter the new Home Runs value.");
				player.setHomeRuns(getValue());
				break;
			case 6:  
				System.out.println("Enter the new Walks value.");
				player.setWalks(getValue());
				break;
			case 7:  
				System.out.println("Enter the new Stolen Bases value.");
				player.setStolenBases(getValue());
				break;
			case 8:  
				System.out.println("Enter the new Caught Stealing value.");
				player.setCaughtStealing(getValue());
				break;
			default:
			}
			player.printPlayer();
			System.out.println();
			if(yesOrNo("Do you wish to modify another stat? (y/n)","n")){
				done=true;
			}
		}
	}

	private static void allNewPlayerData(Player player) {
		boolean done = false;
		Scanner scan = new Scanner(System.in);
		String yesNo= new String();
		while(done==false){
			System.out.println("Enter the At Bats");
			player.setAtBats(getValue());
			System.out.println("Enter the Hits");
			player.setHits(getValue());
			System.out.println("Enter the Doubles");
			player.setDoubles(getValue());
			System.out.println("Enter the Triples");
			player.setTriples(getValue());
			System.out.println("Enter the Home Runs");
			player.setHomeRuns(getValue());
			System.out.println("Enter the Walks");
			player.setWalks(getValue());
			System.out.println("Enter the Stolen Bases");
			player.setStolenBases(getValue());
			System.out.println("Enter the Caught Stealing");
			player.setCaughtStealing(getValue());

			player.printPlayer();
			System.out.println();
			if(yesOrNo("Do you want to reenter this player's data? (y/n)","n")){
				done=true;
			}
		}
	}
	
	private static int getValue(){
		int value=0;
		Scanner scan= new Scanner(System.in);
		boolean done=false;
		while(done==false){
			if(scan.hasNextInt()){
				value=scan.nextInt();
				done=true;
			}else{
				System.out.println("You must enter a number with no decimal point.");
			}
			scan.nextLine();
		}
		return value;
	}

	private static void modifyStatistic(Team team){
		boolean done = false;
		while(done==false){
			Input.modifyStatisticChoiceMenu();
			int choice=Input.menuChoice(Input.modifyStatisticChoiceMenuItemCount);
			switch(choice){
			case 1: 
				modifyAllValuesSame(team);
				break;
			case 2:  
				modifyAllValuesIndividually(team);
				break;
			case 3:  
				done=true;
				break;
			default:
			}	
		}	
	}
	
	private static void modifyAllValuesIndividually(Team team) {

		boolean done = false;
		int value;
		while(done==false){
			Input.modifyStatisticMenu();
			int choice=Input.menuChoice(Input.modifyStatisticMenuItemCount);
			switch(choice){
			case 1: 
				for(int i=1;i<10;i++){
					System.out.println("Enter the new At Bats value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setAtBats(value);
				}
				break;
			case 2:  				
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Hits value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setHits(value);
				}
				break;
			case 3:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Doubles value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setDoubles(value);
				}
				break;
			case 4:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Triples value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setTriples(value);
				}
				break;
			case 5:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Home Runs value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setHomeRuns(value);
				}
				break;
			case 6:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Walks value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setWalks(value);
				}
				break;
			case 7:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Stolen Bases value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setStolenBases(value);
				}
				break;
			case 8:  
				for(int i=1;i<10;i++){
					System.out.println("Enter the new Caught Stealing value for player "+i+".");
					value=getValue();
					team.getTeam().get(i-1).setCaughtStealing(value);
				}
				break;
			default:
			}	
			team.printTeamStandard();
			System.out.println();

			if(yesOrNo("Do you wish to modify another stat? (y/n)","n")){
				done=true;
			}	
		}
	}

	private static void modifyAllValuesSame(Team team) {
		boolean done = false;
		int value;
		while(done==false){
			Input.modifyStatisticMenu();
			int choice=Input.menuChoice(Input.modifyStatisticMenuItemCount);
			switch(choice){
			case 1: 
				System.out.println("Enter the new At Bats value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setAtBats(value);
				}
				break;
			case 2:  				
				System.out.println("Enter the new Hits value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setHits(value);
				}
				break;
			case 3:  
				System.out.println("Enter the new Doubles value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setDoubles(value);
				}
				break;
			case 4:  
				System.out.println("Enter the new Triples value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setTriples(value);
				}
				break;
			case 5:  
				System.out.println("Enter the new Home Runs value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setHomeRuns(value);
				}
				break;
			case 6:  
				System.out.println("Enter the new Walks value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setWalks(value);
				}
				break;
			case 7:  
				System.out.println("Enter the new Stolen Bases value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setStolenBases(value);
				}
				break;
			case 8:  
				System.out.println("Enter the new Caught Stealing value.");
				value=getValue();
				for(int i=0;i<9;i++){
					team.getTeam().get(i).setCaughtStealing(value);
				}
				break;
			default:
			}	
			team.printTeamStandard();
			System.out.println();

			if(yesOrNo("Do you wish to modify another stat? (y/n)","n")){
				done=true;
			}	
		}
	}

	public static Team formANewTeam(){
		Team team = new Team("default");
		return team;
	}
	
	
	 private static void teamCompetition(){
		Team team = new Team();
		Input.teamCompetitionMenu();
		int choice=Input.menuChoice(Input.teamCompetitionMenuItemCount);
		switch(choice){
		case 1: team =chooseAnExistingUnplayedTeam();
			break;
		case 2: team=chooseAnExistingUnplayedTeam();
				modifyTeam(team);
			break;
		case 3: formANewTeam();
			break;
			default:
		}
	 }
	 
	 private static void battingOrder() throws IOException{
		ArrayList<Team> availableTeams=loadTeams(runsPerGameFile); //Create an ArrayList to hold all of the previous teams
        //Choose a team to play or modify
	    chooseATeam(availableTeams);
		int choice=Input.menuChoice(availableTeams.size()+1);
		Team team = new Team(availableTeams.get(choice-1));
		if(yesOrNo("Do you wish to modify the team? (y/n)","y")){
			modifyTeam(team);
		}

		Input.battingOrderMenu();
			choice=Input.menuChoice(Input.battingOrderMenuItemCount);
			switch(choice){
			case 1:  rotateBattingOrder(team);
				break;
			case 2: 
				break;
			default:
			}
		 	 }
	 

	 
	private static void rotateBattingOrder(Team team) {
		boolean correctPlayer=false;
		int player=0;
		double [] rotationResults = new double[9];
		while(correctPlayer==false){
			team.printTeamStandard();
			//Pass the list position of the player which is one less than the printed player number
			System.out.println();
			System.out.println("Enter the number of the player you wish to rotate.");
			System.out.println();
			player=Input.menuChoice(Input.modifyPlayerChoiceMenuItemCount);
			team.printTeamPlayer(player-1);
			if(yesOrNo("Is this the correct player? (y/n)","y")){
				correctPlayer=true;
			}
		}
		switchPlayers(team,0,player-1);
		if(yesOrNo("Do you want to allow steals? (y/n)","y")){
			team.setCanSteal(true);
		} else{
			team.setCanSteal(false);
		}
		for(int i=0;i<9;i++){
			generateRunsPerGameHistogram(team);
			if(team.getCanSteal()==true){
				rotationResults[i]=team.getRunsPerGameWithSteals();
			}else{
				rotationResults[i]=team.getRunsPerGameWithoutSteals();
			}
			if(i<8){
				switchPlayers(team,i,i+1);
			}
		}
		switchPlayers(team,0,8);
		team.printTeamStandard();
		System.out.println("");
		System.out.println("The result of rotating player number one of the above team is:");
		System.out.println();
		player=1;
		for(Double runs:rotationResults){
			System.out.println("The runs per game scored with the player batting "+player+++" is "+runs+".");
		}
		
	}
	
	private static void switchPlayers(Team team, int position1, int position2){
		Player temp = team.getTeam().get(position1);
		team.getTeam().set(position1, team.getTeam().get(position2));
		team.getTeam().set(position2, temp);
	}

	private static Team examineTeamCompetition() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/
