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

import module.Team;

public class TestTempClass {

	public TestTempClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		 String filename ="trash1234";
		
		        ObjectOutputStream out = null;
		        try {
	    			File newFile = new File(filename);
	    			newFile.createNewFile();
	    			FileInputStream fis = new FileInputStream(new File(filename));  
	    			int b = fis.read();
	    			fis.close();
	    			if (b == -1)  
	    			{  
	    			  out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
	    			  out.writeObject(new TempClass(1));
	    			  out.close();
	    			} 	
	    			
	    /*
	    			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
		    		TempClass temp = new TempClass();
		    		System.out.println(temp.getValue());
		            out.writeObject(temp);
		    		temp.setValue(10);
		    		System.out.println(temp.getValue());
		    		TempClass temp2 = new TempClass(25);
		            out.writeObject(temp2);
		            out.reset();
		            out.writeObject(temp);
*/

		        } finally {
		        }
		        

		        ObjectInputStream in = null;
		        TempClass inputTemp=new TempClass(100);
			    ArrayList<TempClass> tempList=new ArrayList<TempClass>(); //Create an ArrayList to hold all of the previous teams
		        try {
		            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));  
		        	while(true){

		        		tempList.add((TempClass) in.readObject());
		        	}
	            } catch (EOFException e) {}
		         finally {
		            in.close();
		        }
		        inputTemp.setValue(tempList.get(tempList.size()-1).getValue()+1);
		        tempList.add(inputTemp);
		        for(int i=0;i<tempList.size();i++){
		        	System.out.println(tempList.get(i).getValue());
		        }
  			  out= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
  			  for(int i=0;i<tempList.size();i++){
  				  out.writeObject(tempList.get(i));
  			  }
  			  out.close();

	}

}
