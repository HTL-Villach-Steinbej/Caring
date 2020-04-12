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
        tvLocation.setText(String.valueOf(item.getDistance()));


        return convertView;
    }
}
