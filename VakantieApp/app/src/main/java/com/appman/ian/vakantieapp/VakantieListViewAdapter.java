package com.appman.ian.vakantieapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ian on 31-5-2017.
 */

public class VakantieListViewAdapter extends ArrayAdapter<VakantieItem> implements AdapterView.OnItemClickListener {

    public VakantieListViewAdapter(Context context, ArrayList<VakantieItem> vakanties) {
        super(context, 0, vakanties);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VakantieItem vakantieItem = getItem(position);
        boolean isColored = position % 2 == 0;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_vakantielayout, parent, false);
        }
        TextView vakantieNaam = (TextView) convertView.findViewById(R.id.PeriodeNaam);
        TextView vakantieAantalRegios = (TextView) convertView.findViewById(R.id.AantalRegios);
        TextView vakantieRegioText = (TextView) convertView.findViewById(R.id.regioTekst);

        vakantieNaam.setText(vakantieItem.getNaam());
        if(isColored) {
            vakantieNaam.setTextColor(Color.BLUE);
        }

        if(vakantieItem.getTijdvlak().size() == 1) {
            vakantieRegioText.setText("Regio");
        }
        else {
            vakantieRegioText.setText("Regio's");
        }
        vakantieAantalRegios.setText(String.valueOf(vakantieItem.getTijdvlak().size()));

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), Vakantie_info.class);
        intent.putExtra("Vakantie", getItem(position));
        view.getContext().startActivity(intent);
    }
}