package module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SingleTeamIncrementPlayerStatResults implements Storable, Serializable{
	private static final long serialVersionUID = 7513682510915355356L;

	private List<SingleTeamSimulationResults> individualSimulationResults =
												new ArrayList<>();
	private String simulationDescription = "Default Description";

	public  SingleTeamIncrementPlayerStatResults() {
		super();
	}
	
	public SingleTeamIncrementPlayerStatResults(SingleTeamIncrementPlayerStatResults results){
		this.simulationDescription = results.getDescription();
		this.individualSimulationResults = results.getIndividualSimulationResults();
	}
	
	@Override
	public Storable deepCopy() {
		return new SingleTeamIncrementPlayerStatResults(this);
	}

	@Override
	public String toString(){
		StringBuilder string = new StringBuilder(getDescription())
									.append(System.lineSeparator())
									.append(System.lineSeparator());
		
		for(SingleTeamSimulationResults results:individualSimulationResults){
			string.append(results.toString());
		}
		
		return string.toString();
	}
	
	public List<SingleTeamSimulationResults> getIndividualSimulationResults() {
		List<SingleTeamSimulationResults> tempResultList = new ArrayList<>();
		for(SingleTeamSimulationResults result:individualSimulationResults){
			tempResultList.add(new SingleTeamSimulationResults(result));
		}
		return tempResultList;
	}

	public void setIndividualSimulationResults(
			List<SingleTeamSimulationResults> individualSimulationResults) {
		List<SingleTeamSimulationResults> tempResultList = new ArrayList<>();
		for(SingleTeamSimulationResults results:individualSimulationResults){
			tempResultList.add(new SingleTeamSimulationResults(results));
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
