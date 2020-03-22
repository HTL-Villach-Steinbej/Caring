package bll;

public class Zone {
	private int id;
	private String bezeichnung;
	private int kostensatz;
	
	
	public Zone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Zone(int id, String bezeichnung, int kostensatz) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.kostensatz = kostensatz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getKostensatz() {
		return kostensatz;
	}

	public void setKostensatz(int kostensatz) {
		this.kostensatz = kostensatz;
	}
	
	
	
	
}
