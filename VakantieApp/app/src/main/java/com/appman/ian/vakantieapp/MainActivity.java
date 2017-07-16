package com.appman.ian.vakantieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private ArrayList<VakantieItem> vakanties;
    private ListView vakantieListView;
    private VakantieListViewAdapter adapter;
    private android.os.Handler handler = new android.os.Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gegevensOphalen();
        }
    };

    private void gegevensOphalen() {
        new SchoolVakantieTask(new SchoolVakantieListener() {
            @Override
            public void onInfoAvailable(ArrayList<VakantieItem> vakantieItems) {
                vakanties.clear();
                vakanties.addAll(vakantieItems);
                adapter.notifyDataSetChanged();
            }
        }).execute("https://opendata.rijksoverheid.nl/v1/sources/rijksoverheid/infotypes/schoolholidays/schoolyear/2016-2017?output=json");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vakantieListView = (ListView) findViewById(R.id.vakantieListView);
        vakanties = new ArrayList<>();
        fillDummyData();
        adapter = new VakantieListViewAdapter(this, vakanties);
        vakantieListView.setOnItemClickListener(adapter);

        vakantieListView.setAdapter(adapter);
        handler.post(runnable);
    }

    private void fillDummyData() {
        for(int i = 0; i < 5; i++) {
            VakantieItem item = new VakantieItem("Test vakantie", false);
            item.addTijdVak(new Tijdvak("Noord", new Date(2011, 5, 6), new Date(2011, 6, 7)));
            vakanties.add(item);
        }
    }
}
