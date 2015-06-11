package controller;

import java.util.ArrayList;
import java.util.Iterator;

public class TestCopyObject {

	public TestCopyObject() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ArrayList<TempClass> junk = new ArrayList<TempClass>();
		TempClass test = new TempClass(1);
		for (int i=0; i<3;i++){
			test.addTempList(i);
		}
		test.printTempList();
		TempClass newTest = new TempClass(test);
		for (int i=5; i<8;i++){
			newTest.addTempList(i);
		}
		System.out.println("test");
		test.printTempList();
		System.out.println("new test");
		newTest.printTempList();
		
		/*
		junk.add(test);
		System.out.println(junk.get(0).getValue());
		TempClass temp = test;
		temp.setValue(2);
		System.out.println(junk.get(0).getValue());

		junk.add(test);
		Iterator<TempClass> values = junk.iterator();
		while(values.hasNext()){
			System.out.println(values.next().getValue());
		}
		System.exit(0);
		*/
	}

}
