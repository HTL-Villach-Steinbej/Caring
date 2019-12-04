package bll;

public class Car {
    private int id;
    private String bezeichnung;
    private String marke;
    private int laufleistung;
    private int xCord;
    private int yCord;

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

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public int getLaufleistung() {
        return laufleistung;
    }

    public void setLaufleistung(int laufleistung) {
        this.laufleistung = laufleistung;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public Car(int id, String bezeichnung, String marke, int laufleistung, int xCord, int yCord) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.marke = marke;
        this.laufleistung = laufleistung;
        this.xCord = xCord;
        this.yCord = yCord;
    }
}
