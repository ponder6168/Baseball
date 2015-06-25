package controller;


public class TwoTeamMenu implements ExecutesMenu {

	@Override
	public void executeMenuChoice() {
		System.out.format("%n%s%n%n","Two Team Stub");
		
	}

	public boolean equals(Object o){
		return o.equals("TWO_TEAM_SENARIO");
	}
}
