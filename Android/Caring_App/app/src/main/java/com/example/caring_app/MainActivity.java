package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;

import bll.Car;
import bll.Fahrzeug;
import dal.Database;

public class MainActivity extends AppCompatActivity {
    CarsAdapter adapter;
    ArrayList<Car> items;
    ArrayList<Fahrzeug> TestList;
    Toolbar toolbar;
    Location carLocation;
    private  FusedLocationProviderClient fusedLocationClient;
    private int distance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listOfCars= (ListView) findViewById(R.id.listCars);
        toolbar=findViewById(R.id.toolbar);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        adapter = new CarsAdapter(MainActivity.this, items);
        listOfCars.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listOfCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Intent modify_intent = new Intent(MainActivity.this, RentCar.class);
                Car b=items.get(position);
                modify_intent.putExtra("car", b);
                startActivity(modify_intent);
            }
        });

        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)

    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menue_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.payment:
                Intent i = new Intent(MainActivity.this,Payment.class);
                startActivity(i);
                return true;
            case R.id.addCar:
                Intent s = new Intent(MainActivity.this,AddCar.class);
                startActivity(s);
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
                MainActivity.this.finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillItems() {

        items=new ArrayList<Car>();

        Location location=new Location("");
        location.setLatitude(20.63534558545709);
        location.setLongitude(33.848026296368516);
        items.add(new Car(1,"530d","BMW",10000,location));
        location.setLatitude(46.60672966890297);
        location.setLongitude(13.83002907003356);
        items.add(new Car(2,"320d","BMW",10000,location));
        location.setLatitude(46.602056383042424);
        location.setLongitude(13.877747146269485);
        items.add(new Car(3,"A8","Audi",10000,location));
/*
        Database db = Database.newInstance();
        try {
            TestList = db.getAllCars();
            for (Fahrzeug fahrzeug:TestList) {
                Location location = new Location("");
                location.setLatitude(fahrzeug.getLocation().getX());
                location.setLongitude(fahrzeug.getLocation().getY());
               items.add(new Car(fahrzeug.getId(),fahrzeug.getBezeichnung(),fahrzeug.getMarke(),fahrzeug.getLaufleistung(),location));
            }
            Toast.makeText(getApplicationContext(),items.toString(),Toast.LENGTH_LONG).show();
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
*/

    }

    public int getLOcation(final Location carLocation){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            distance= (int)location.distanceTo(carLocation);

                        }
                    }
                });



        return distance;
    }
}

