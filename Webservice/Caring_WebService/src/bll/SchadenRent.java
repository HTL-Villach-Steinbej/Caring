package bll;

public class SchadenRent {
	private int id;
	private int lid;
	private int kosten; 
	private String bezeichnung;
	
	public SchadenRent() {
		super();
	}
	
	public SchadenRent(int lid,int id,int kosten,String bezeichnung) {
		super();
		this.id = id;
		this.lid=lid;
		this.kosten = kosten; 
		this.bezeichnung = bezeichnung;
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

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	
	
}
