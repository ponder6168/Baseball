package controller;


public class CreateTeamMenu implements ExecutesMenu {

	@Override
	public void executeMenuChoice() {
		System.out.println("Create Team Stub");
	}

	public boolean equals(Object o){
		return o.equals("CREATE_TEAM");
	}
}
