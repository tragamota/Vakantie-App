package com.appman.ian.vakantieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Vakantie_info extends AppCompatActivity {
    private VakantieItem vakantieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakantie_info);
        vakantieItem = (VakantieItem) getIntent().getExtras().getSerializable("Vakantie");

        TextView vakantieNaam = (TextView) findViewById(R.id.vakantieNaam);
        vakantieNaam.setText(vakantieItem.getNaam());

        TextView noordBeginDatum = (TextView) findViewById(R.id.beginDatumNoord_vakantie_info);
        TextView noordEindDatum = (TextView) findViewById(R.id.noordeindDatum);
        TextView middenBeginDatum = (TextView) findViewById(R.id.middenBegindatum);
        TextView middenEindDatum = (TextView) findViewById(R.id.middenEindDatum);
        TextView zuidBeginDatum = (TextView) findViewById(R.id.zuidBeginDatum);
        TextView zuidEindDatum = (TextView) findViewById(R.id.zuidEindDatum);

        if(vakantieItem.getTijdvlak().size() == 1) {
            String begintijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(0).getStartdate());
            String eindtijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(0).getEnddate());
            noordBeginDatum.setText(begintijd);
            middenBeginDatum.setText(begintijd);
            zuidBeginDatum.setText(begintijd);
            noordEindDatum.setText(eindtijd);
            middenEindDatum.setText(eindtijd);
            zuidEindDatum.setText(eindtijd);
        }
        else {
            String noordbegintijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(0).getStartdate());
            String noordeindtijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(0).getEnddate());
            String middenbegintijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(1).getStartdate());
            String middeneindtijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(1).getEnddate());
            String zuidbegintijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(2).getStartdate());
            String zuideindtijd = Tijdvak.dateString(vakantieItem.getTijdvlak().get(2).getEnddate());

            noordBeginDatum.setText(noordbegintijd);
            middenBeginDatum.setText(middenbegintijd);
            zuidBeginDatum.setText(zuidbegintijd);
            noordEindDatum.setText(noordeindtijd);
            middenEindDatum.setText(middeneindtijd);
            zuidEindDatum.setText(zuideindtijd);
        }
    }
}