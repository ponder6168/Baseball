package controller;

import module.Team;

public class QuitModifyMenu implements TeamModifier {

	@Override
	public Team getModifiedTeam(Team team) {
		return null;
	}

	public boolean equals(Object o){
		return o.equals("QUIT");
	}

}
