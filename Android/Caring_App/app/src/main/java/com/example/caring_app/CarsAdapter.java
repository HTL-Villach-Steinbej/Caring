package com.example.caring_app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bll.Car;

public class CarsAdapter extends ArrayAdapter<Car> {
    public CarsAdapter(Context context, List<Car> object) {
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.caritem_view, parent, false);
        }

        TextView tvMarke = convertView.findViewById(R.id.tvMarke);
        TextView tvBezeichnung = convertView.findViewById(R.id.tvBezeichnung);
        TextView tvLocation = convertView.findViewById(R.id.tvLocation);



        Car item = getItem(position);

        tvMarke.setText(item.getMarke());
        tvBezeichnung.setText(item.getBezeichnung());
        double distance=item.getDistance();
        if(distance>1000){
            distance=distance/1000;

            tvLocation.setText("Dist: "+String.valueOf( String.format("%.2f", distance))+"km");
        }
        else{
            tvLocation.setText("Dist: "+String.valueOf(item.getDistance())+"m");

        }


        return convertView;
    }
}
