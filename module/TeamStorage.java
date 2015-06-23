package module;

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

public class TeamStorage {
	private static final String teamStorageFileName="TeamStorageFile";
	private ArrayList<Team> allTeams;
	
	public void storeTeamInFile(Team team){
		allTeams = retriveExistingTeams();
		allTeams.add(team);
		storeAllTeamsInFile(allTeams);
	}

	public void storeAllTeamsInFile(ArrayList<Team> listOfTeamsToStore) {
		try(ObjectOutputStream out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(teamStorageFileName)))){
			  for(Team team:listOfTeamsToStore ){
	  			  out.writeObject(team);
			  }
		}catch(IOException e){
	        System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	public ArrayList<Team> retriveExistingTeams() {
		ArrayList<Team> listOfStoredTeams = new ArrayList<>();
		  try {
			  insureStorageFileHasDefaultTeam();
				listOfStoredTeams = readAllTheTeams();
		    } catch (IOException e) {
		        System.err.println("Caught IOException: " + e.getMessage());
		    } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		  return listOfStoredTeams;
	}
	
	private void insureStorageFileHasDefaultTeam() throws IOException {
		File fileObjectForStorageFile = new File(teamStorageFileName);
		fileObjectForStorageFile.createNewFile();
		FileInputStream fileInputSteam = new FileInputStream(fileObjectForStorageFile);  
		if(thereAreNoTeamsStored(fileInputSteam)){
			storeDefaultTeamInFile();
		}
	}

	private boolean thereAreNoTeamsStored(FileInputStream fileInputSteam) throws IOException {
		int fileIsEmptyReadReturnValue = -1;
		int firstByteInFile = fileInputSteam.read();
		fileInputSteam.close();
		return firstByteInFile==fileIsEmptyReadReturnValue;
	}
	
	private void storeDefaultTeamInFile() throws IOException {
		try(ObjectOutputStream out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(teamStorageFileName)))){
			  out.writeObject(new Team("default"));
		}
	}

	private ArrayList<Team> readAllTheTeams() throws ClassNotFoundException, IOException {
		ArrayList<Team> listOfStoredTeams = new ArrayList<>();
		try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(teamStorageFileName)))){
			while(true){
				//Note the first team will always be the default team.
				listOfStoredTeams.add((Team) in.readObject());
			}
		} catch (EOFException e) {
			//Read teams until the end of the file Exception
	    }
		return listOfStoredTeams;
	}


	private void addNewTeamToExistingTeams(Team team) {
		allTeams.add(team);
	}


}
