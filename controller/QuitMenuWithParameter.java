package controller;

import module.Team;
//The QuitMenuWithParameter class does not do anything.  It is called when
// the user leaves the menu

public class QuitMenuWithParameter implements ExecutesMenuWithParameter {

	@Override
	public void executeMenuChoice() {
		//empty
	}

	@Override
	public void setTeamToBeModified(Team team) {
		//empty
	}

	public boolean equals(Object o){
		return o.equals("QUIT");
	}

}
