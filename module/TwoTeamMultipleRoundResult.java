package module;
import java.io.Serializable;
import java.util.*;

public class TwoTeamMultipleRoundResult implements Storable, Serializable {
	private static final long serialVersionUID = -1775961734838385448L;
	
	private String description = "Simulation Results";
	private List <TwoTeamOneRoundResult> results = new ArrayList<>();
	private Team firstTeam;
	private Team secondTeam;
	
	public  TwoTeamMultipleRoundResult() {
		super();
	}
	
	public TwoTeamMultipleRoundResult(TwoTeamMultipleRoundResult result){
		this.description = result.description;
		this.results = result.getResults();
	}
	
	@Override
	public String toString(){
		StringBuilder string = new StringBuilder(description)
									.append(System.lineSeparator())
									.append(System.lineSeparator())
									.append("The first team description ")
									.append(firstTeam.getDescription())
									.append(System.lineSeparator())
									.append(System.lineSeparator())
									.append("The second team description ")
									.append(secondTeam.getDescription())
									.append(System.lineSeparator());
		
		for(TwoTeamOneRoundResult result:results){
			string.append(result);
		}
		return string.toString();
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TwoTeamOneRoundResult> getResults() {
		List<TwoTeamOneRoundResult> list = new ArrayList<>();
		for(TwoTeamOneRoundResult result:results){
			list.add(new TwoTeamOneRoundResult(result));
		}
		return list;
	}
	public void setResults(List<TwoTeamOneRoundResult> results) {
		List<TwoTeamOneRoundResult> list = new ArrayList<>();
		for(TwoTeamOneRoundResult result:results){
			list.add(new TwoTeamOneRoundResult(result));
		}
		this.results = list;
	}

	@Override
	public Storable deepCopy() {
		return new TwoTeamMultipleRoundResult(this);
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}
	
	

}
