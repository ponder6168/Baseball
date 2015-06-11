package controller;

public class TestInnerClass {
	public TestInnerClass(int testValue) {
		super();
		this.testValue = testValue;
	}

	private int testValue;

	public TestInnerClass() {
		// TODO Auto-generated constructor stub
	}
	
	public TestInnerClass( TestInnerClass value){
		testValue = value.testValue;
	}

	public int getTestValue() {
		return testValue;
	}

	public void setTestValue(int testValue) {
		this.testValue = testValue;
	}

}
