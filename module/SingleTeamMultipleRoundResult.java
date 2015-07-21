package module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SingleTeamMultipleRoundResult implements Storable, Serializable{
	private static final long serialVersionUID = 2587005942749201595L;

	private List<SingleTeamOneRoundResult> individualSimulationResults =
												new ArrayList<>();
	private String simulationDescription = "Default Description";

	public  SingleTeamMultipleRoundResult() {
		super();
	}
	
	public SingleTeamMultipleRoundResult(SingleTeamMultipleRoundResult results){
		this.simulationDescription = results.getDescription();
		this.individualSimulationResults = results.getIndividualSimulationResults();
	}
	
	@Override
	public Storable deepCopy() {
		return new SingleTeamMultipleRoundResult(this);
	}

	@Override
	public String toString(){
		StringBuilder string = new StringBuilder(getDescription())
									.append(System.lineSeparator())
									.append(System.lineSeparator());
		
		for(SingleTeamOneRoundResult results:individualSimulationResults){
			string.append(results.toString());
		}
		
		return string.toString();
	}
	
	public List<SingleTeamOneRoundResult> getIndividualSimulationResults() {
		List<SingleTeamOneRoundResult> tempResultList = new ArrayList<>();
		for(SingleTeamOneRoundResult result:individualSimulationResults){
			tempResultList.add(new SingleTeamOneRoundResult(result));
		}
		return tempResultList;
	}

	public void setIndividualSimulationResults(
					List<SingleTeamOneRoundResult> individualSimulationResults) {
		List<SingleTeamOneRoundResult> tempResultList = new ArrayList<>();
		for(SingleTeamOneRoundResult results:individualSimulationResults){
			tempResultList.add(new SingleTeamOneRoundResult(results));
		}
		this.individualSimulationResults = tempResultList;
	}
	
	public String getDescription() {
		return simulationDescription;
	}

	public void setSimulationDescription(String description) {
		this.simulationDescription = description;
	}
}
