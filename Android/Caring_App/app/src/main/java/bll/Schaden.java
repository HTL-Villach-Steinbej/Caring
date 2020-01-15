package bll;

public class Schaden {
    private int id;
    private String bezeichnung;
    private String beschreibung;

    public Schaden() {
        super();
    }

    public Schaden(int id, String bezeichnung,String beschreibung) {
        super();
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
