package controller;


public class SingleTeamMenu implements ExecutesMenu {

	@Override
	public void executeMenuChoice() {
		System.out.println("Single Team Stub");
	}

	public boolean equals(Object o){
		return o.equals("SINGLE_TEAM_SENARIO");
	}
}
