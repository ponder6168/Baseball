package controller;


public class TwoTeamMenu implements ExecutesMenu {

	@Override
	public void executeMenuChoice() {
		System.out.println("Two Team menu stub");
		
	}

	public boolean equals(Object o){
		return o.equals("TWO_TEAM_SENARIO");
	}
}
