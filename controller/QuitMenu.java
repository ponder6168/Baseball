package controller;


public class QuitMenu implements ExecutesMenu {

	@Override
	public void executeMenuChoice() {
		//QuitMenu is returned when the user decides to leave a menu and does not do anything.
	}
	

	public boolean equals(Object o){
		return o.equals("QUIT");
	}

}
