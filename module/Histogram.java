package module;

import java.io.Serializable;
import java.util.ArrayList;

public class Histogram implements Serializable{
	
}	
	
	/**

	private static final long serialVersionUID = 810767236843005054L;
	private static final int HISTOGRAMLENGTH=30;
	private double [] runHistogramWithSteals=new double[HISTOGRAMLENGTH];
	private double [] runHistogramWithoutSteals= new double[HISTOGRAMLENGTH];
	private double runsPerGameWithSteals;
	private double runsPerGameWithoutSteals;
	private String description;
	
	public Histogram() {
		// TODO Auto-generated constructor stub
	}

	public Histogram(String string){
		this.team=new ArrayList<Player>();
		for(int i=0; i<9 ; i++){
			this.team.add(new Player("default"));
		}
		this.currentBatter=0;
		this.description="Default Team";
	}

	public Histogram(Histogram source){
		currentBatter=source.currentBatter;
		playersCanSteal=source.playersCanSteal;
		runsPerGameWithSteals = source.runsPerGameWithSteals;
		runsPerGameWithoutSteals = source.runsPerGameWithoutSteals;
		description = source.description;
		for(int i=0;i<source.runHistogramWithSteals.length;i++){
			runHistogramWithSteals[i]=source.runHistogramWithSteals[i];
		}
		for(int i=0;i<source.runHistogramWithoutSteals.length;i++){
			runHistogramWithoutSteals[i]=source.runHistogramWithoutSteals[i];
		}
		for(Player player: source.team){
			team.add(new Player(player));
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRunsPerGameWithoutSteals() {
		return runsPerGameWithoutSteals;
	}

	public void setRunsPerGameWithoutSteals(double runsPerGameWithoutSteals) {
		this.runsPerGameWithoutSteals = runsPerGameWithoutSteals;
	}

	public double getRunsPerGameWithSteals() {
		return runsPerGameWithSteals;
	}

	public void setRunsPerGameWithSteals(double runsPerInningWithSteals) {
		this.runsPerGameWithSteals = runsPerInningWithSteals;
	}



	public double[] getRunHistogramWithSteals() {
		return runHistogramWithSteals;
	}

	public void setRunHistogramWithSteals(double[] runHistogramWithSteals) {
		for(int i=0;i<HISTOGRAMLENGTH;i++){
			this.runHistogramWithSteals[i] = runHistogramWithSteals[i];
		}
	}

	public double[] getRunHistogramWithoutSteals() {
		return runHistogramWithoutSteals;
	}

	public void setRunHistogramWithoutSteals(double[] runHistogramWithoutSteals) {
		for(int i=0;i<HISTOGRAMLENGTH;i++){
			this.runHistogramWithoutSteals[i] = runHistogramWithoutSteals[i];
		}
	}
	
	public void printPerGameResults(Histogram team){
			System.out.println("These results were generated with the following team.");
			System.out.println();
			printTeamStandard();
			System.out.println();
			printTeamHistogram(team.runHistogramWithSteals, team.runHistogramWithoutSteals);
			System.out.println();
			System.out.format("This team produced %4.2f runs per game with steals.%n",team.getRunsPerGameWithSteals());
			System.out.format("This team produced %4.2f runs per game without steals.%n",team.getRunsPerGameWithoutSteals());
	
	}
	
	public void printTeamHistogram(double [] histogramWithSteals, double [] histogramWithoutSteals){
		System.out.format("%20s%25s%20s%25s%n","Number    ","Probability of Scoring","Number     ","Probability of Scoring");
		System.out.format("%20s%25s%20s%25s%n","of        ","Number of Runs     ","of       ","Number of Runs     ");
		System.out.format("%20s%25s%20s%25s%n","Runs      ","in a Game        ","Runs      ","in a Game        ");
		System.out.format("%20s%25s%20s%25s%n","with      ","with          ","without      ","without        ");
		System.out.format("%20s%25s%20s%25s%n","Steals    ","Steals           ","Steals      ","Steals        ");
		System.out.format("%20s%25s%20s%25s%n","------     ","---------------------","------     ","---------------------");
		for(int i=0;i<9;i++){
			 System.out.format("%13d%7s%15.2f%10s%13d%7s%15.2f%n",i," ",histogramWithSteals[i]," ",i," ",histogramWithoutSteals[i]);
		}
		double sumWithSteals=0;
		double sumWithoutSteals=0;
		for(int i=9;i<HISTOGRAMLENGTH;i++){
			sumWithSteals+=histogramWithSteals[i];
			sumWithoutSteals+=histogramWithoutSteals[i];
		}
		 System.out.format("%21s%14.2f%31s%14.2f%n","9 or more",sumWithSteals, "9 or more",sumWithoutSteals);
	}

	public Histogram(ArrayList<Player> team) {
		super();
		this.team = team;
	}

}
*/
