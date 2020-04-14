package bll;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.caring_app.MainActivity;
import com.example.caring_app.User;
import com.example.caring_app.UserLocationFinder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;

public class Car implements Parcelable {
    private int id;
    private String bezeichnung;
    private String marke;
    private int laufleistung;
    private float distance;
    private Location carLocation=new Location("");
    private MainActivity ma=new MainActivity();


    public Car()
    {
    }

    public Car(int id, String bezeichnung, String marke, int laufleistung, Location point) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.marke = marke;
        this.laufleistung = laufleistung;
        this.carLocation = point;
    }

    public Car(Parcel p){
        this.id=p.readInt();
        this.bezeichnung=p.readString();
        this.marke=p.readString();
        this.laufleistung=p.readInt();
        this.carLocation=p.readParcelable(Location.class.getClassLoader());
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

    public Location getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(Location carLocation) {
        this.carLocation = carLocation;
    }

    public void setLaufleistung(int laufleistung) {
        this.laufleistung = laufleistung;
    }

    public int getDistance() {
        UserLocationFinder finder=new UserLocationFinder();
        Location l=new Location("");
        l.setLongitude(13.848);
        l.setLatitude(46.6353);

        return (int)carLocation.distanceTo(l);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(bezeichnung);
        parcel.writeString(marke);
        parcel.writeInt(laufleistung);
        parcel.writeParcelable(carLocation,i);

    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
