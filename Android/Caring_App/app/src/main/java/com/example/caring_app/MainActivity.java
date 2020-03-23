package com.example.caring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;

import bll.Car;

public class MainActivity extends AppCompatActivity {
    CarsAdapter adapter;
    ArrayList<Car> items;
    Button addCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listOfCars= (ListView) findViewById(R.id.listCars);
        adapter = new CarsAdapter(MainActivity.this, items);
        listOfCars.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        addCar=findViewById(R.id.btnAdd);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newTodo = new Intent(MainActivity.this, AddCar.class);
                startActivity(newTodo);
            }
        });


        listOfCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Intent modify_intent = new Intent(MainActivity.this, RentCar.class);
                Car b=items.get(position);
                modify_intent.putExtra("key",(Serializable) b);
                startActivity(modify_intent);
            }
        });


    }

    private void fillItems() {
        Location location=new Location("");
        location.setLatitude(46.63534558545709);
        location.setLongitude(13.848026296368516);
        items.add(new Car(1,"530d","BMW",10000,location));
        location.setLatitude(46.60672966890297);
        location.setLongitude(13.83002907003356);
        items.add(new Car(2,"320d","BMW",10000,location));
        location.setLatitude(46.602056383042424);
        location.setLongitude(13.877747146269485);
        items.add(new Car(3,"A8","Audi",10000,location));


    }
}

