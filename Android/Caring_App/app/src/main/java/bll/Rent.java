package bll;


import java.sql.Date;

public class Rent {
    private int id;
    private int fid;
    private String uid;
    private int zid;
    private Date von;
    private Date bis;

    public Rent(int id, int fid, String uid, int zid, Date von, Date bis) {
        super();
        this.id = id;
        this.fid = fid;
        this.uid = uid;
        this.zid = zid;
        this.von = von;
        this.bis = bis;
    }

    public Rent(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public Date getVon() {
        return von;
    }

    public void setVon(Date von) {
        this.von = von;
    }

    public Date getBis() {
        return bis;
    }

    public void setBis(Date bis) {
        this.bis = bis;
    }


}
