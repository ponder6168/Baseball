package controller;

import module.Player;
import module.Team;

public class ModifyPlayerOnTeam implements TeamModifier {
	private Team teamToModify;
	private Player playerToModify;

	@Override
	public Team getModifiedTeam(Team team) {
		this.teamToModify = team;
		getPlayerToModify();
		modifyPlayer();
		return teamToModify;
	}

	private void getPlayerToModify() {
		playerToModify = teamToModify.getPlayerFromConsole(
							"Choose the player you want to modify.");
	}

	private void modifyPlayer() {
		playerToModify.setSelectedPlayerStatFromConsole();
	}

	public boolean equals(Object o){
		return o.equals("MODIFY_PLAYER");
	}

}
