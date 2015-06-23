package controller;

import module.Team;

public interface ExecutesMenuWithParameter extends ExecutesMenu {
	void setTeamToBeModified(Team team);

}
