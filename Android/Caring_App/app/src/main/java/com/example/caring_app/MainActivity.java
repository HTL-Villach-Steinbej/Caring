package com.example.caring_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

import bll.Car;
import bll.Fahrzeug;
import dal.Database;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    CarsAdapter adapter;
    ArrayList<Car> items;
    private final int PERMISSION_REQUEST_CODE = 200;
    ArrayList<Fahrzeug> TestList;
    Toolbar toolbar;
    Location carLocation;
    private  FusedLocationProviderClient fusedLocationClient;
    private int distance;
BackgroundLocationService gpsService;
    public boolean mTracking = false;
    ServiceConnection serviceConnection;
    private FirebaseAuth mAuth;
    private FirebaseFirestore Firestoredb;
    ListView listOfCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillItems();
        super.onCreate(savedInstanceState);

        final Intent intent = new Intent(this.getApplication(), BackgroundLocationService.class);
        this.getApplication().startService(intent);
        serviceConnection = new ServiceConnection() {


            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                gpsService = ((BackgroundLocationService.LocationServiceBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        this.getApplication().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        setContentView(R.layout.activity_main);
         listOfCars= (ListView) findViewById(R.id.listCars);
        toolbar=findViewById(R.id.toolbar);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        adapter = new CarsAdapter(MainActivity.this, items);
        listOfCars.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listOfCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser User = mAuth.getCurrentUser();
                Firestoredb = Firestoredb.getInstance();

                try {
                    DocumentReference df = Firestoredb.collection("users").document(User.getUid());
                    df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                   String s =String.valueOf(document.getString("paymethod"));
                                   System.out.println(s);
                                   if(s=="" || s == null)
                                   {
                                       Intent i = new Intent(MainActivity.this,Payment.class);
                                       startActivity(i);
                                   }
                                } else {
                                    System.out.println("No such document");
                                }
                            } else {
                                task.getException().printStackTrace();
                            }
                        }
                    });
                    Intent modify_intent = new Intent(MainActivity.this, RentCar.class);
                    Car b = items.get(position);
                    modify_intent.putExtra("car", b);
                    startActivity(modify_intent);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
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

    public void startTracking() {
        //check for permission
        if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsService.startTracking();
            mTracking = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTracking();
            }
        }
    }

    private void fillItems() {

        items=new ArrayList<Car>();

        /*
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
        */
        Database db = Database.newInstance();
        try {
            TestList = db.getAllCars();
            for (Fahrzeug fahrzeug:TestList) {
                Location locationnew = new Location("");
                locationnew.setLongitude(fahrzeug.getLocation().getX());
                locationnew.setLatitude(fahrzeug.getLocation().getY());
               items.add(new Car(fahrzeug.getId(),fahrzeug.getBezeichnung(),fahrzeug.getMarke(),fahrzeug.getLaufleistung(),locationnew));
            }
            Toast.makeText(getApplicationContext(),items.toString(),Toast.LENGTH_LONG).show();
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
fillItems();
        adapter = new CarsAdapter(MainActivity.this, items);
        listOfCars.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}



