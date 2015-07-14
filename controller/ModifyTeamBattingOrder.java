package controller;

import module.Team;

public class ModifyTeamBattingOrder implements TeamModifier {
	
	@Override
	public Team getModifiedTeam(Team team) {
		team.modifyBattingOrder();
		return team;
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_BATTING_ORDER");
	}


}
