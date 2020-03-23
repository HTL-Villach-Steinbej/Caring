package bll;

import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Car extends AppCompatActivity{
    private int id;
    private String bezeichnung;
    private String marke;
    private int laufleistung;
    private float distance;
    private Location carLocation;
    private FusedLocationProviderClient fusedLocationClient;


    public Car()

    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Car(int id, String bezeichnung, String marke, int laufleistung, Location point) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.marke = marke;
        this.laufleistung = laufleistung;
        this.carLocation = point;
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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        distance=location.distanceTo(carLocation);

                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });


        return (int)distance;
    }
}
