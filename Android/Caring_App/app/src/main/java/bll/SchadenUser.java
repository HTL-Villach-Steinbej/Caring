package bll;

import java.sql.Date;

public class SchadenUser {
    private int sid;
    private String uid;
    private int fid;
    private String beschreibung;
    private String bezeichnung;
    private Date datum;

    public SchadenUser(int sid ,String uid, int fid, String beschreibung, String bezeichnung, Date datum) {
        super();
        this.sid = sid;
        this.uid = uid;
        this.fid = fid;
        this.beschreibung = beschreibung;
        this.bezeichnung = bezeichnung;
        this.datum = datum;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getUd() {
        return uid;
    }

    public void setUd(String ud) {
        this.uid = ud;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
