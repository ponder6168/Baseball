package module;

	public enum StorageObject{
		TEAM("TeamStorageFile"),
		SINGLE_TEAM_SIMULATION_ONE_ROUND("SingleTeamSimulationOneRoundStorageFile"),
		SINGLE_TEAM_SIMULATION_MULTIPLE_ROUNDS("SingleTeamSimulationMultipleRoundsStorageFile"),
		TWO_TEAM_SIMULATION_ONE_ROUND("TwoTeamSimulationOneRoundStorageFile"),
		TWO_TEAM_SIMULATION_MULTIPLE_ROUNDS("TwoTeamSimulationMultipleRoundsStorageFile");
		
		private String objectStorageFileName;
		
		private StorageObject(String storageFileName){
			this.objectStorageFileName=storageFileName; 
		}
		
		@Override
		public String toString(){
			return objectStorageFileName;
		}
	}
	