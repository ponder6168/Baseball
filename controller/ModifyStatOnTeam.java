package controller;

import module.Team;

public class ModifyStatOnTeam implements TeamModifier {
	
	@Override
	public Team getModifiedTeam(Team team) {
		team.setSelectedStatForTeam();
		return team;
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_STAT");
	}

}
