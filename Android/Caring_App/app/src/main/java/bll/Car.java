package bll;

public class Car {
    private int id;
    private String bezeichnung;
    private String marke;
    private int laufleistung;
    private Point location;



    public Car() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Car(int id, String bezeichnung, String marke, int laufleistung, Point location) {
        super();
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.marke = marke;
        this.laufleistung = laufleistung;
        this.location = location;
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
