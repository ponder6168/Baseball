package module;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StorageAndRetrieval{

	private  String storageFileName="";

	public StorageAndRetrieval(StorageObject storageObject){
		this.storageFileName = storageObject.toString();
	}
	
	public void storeObjectsInFile(List<Storable> listOfObjectsToStore) {
		try(ObjectOutputStream out= new ObjectOutputStream(
									new BufferedOutputStream(
									new FileOutputStream(storageFileName)))){
			  for(Storable object:listOfObjectsToStore ){
	  			  out.writeObject(object);
			  }
		}catch(IOException e){
	        System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	public List<Storable> retrieveExistingObjects() {
		List<Storable> listOfStoredObjects = new ArrayList<>();
		try {
			createStorageFileIfNecessary();
			listOfStoredObjects = readAllTheObjects();
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return listOfStoredObjects;
	}
	
	private void createStorageFileIfNecessary() throws IOException {
		File fileObjectForStorageFile = new File(storageFileName);
		fileObjectForStorageFile.createNewFile();
	}

	private List<Storable> readAllTheObjects() throws ClassNotFoundException, IOException {
		List<Storable> listOfStoredObjects = new ArrayList<>();
		try(ObjectInputStream in = 	new ObjectInputStream(
									new BufferedInputStream(
									new FileInputStream(storageFileName)))){
			while(true){
				listOfStoredObjects.add((Storable) in.readObject());
			}
		} catch (EOFException e) {
			//Read objects until the end of the file Exception
	    }
		return listOfStoredObjects;
	}
}
