package module;

public enum Bases {
	FIRST (0), SECOND(1), THIRD(2);
	
	private final int base;
	
	private Bases(int base){
		this.base=base;
	}
	
	public int base() {
		return base;
	}
}
