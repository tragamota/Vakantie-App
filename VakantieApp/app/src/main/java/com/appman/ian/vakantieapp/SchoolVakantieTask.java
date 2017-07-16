package com.appman.ian.vakantieapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ian on 31-5-2017.
 */

public class SchoolVakantieTask extends AsyncTask<String, Void, String> {
    private SchoolVakantieListener listener;

    public SchoolVakantieTask(SchoolVakantieListener listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";
        String response = "";

        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            response = reader.readLine().toString();
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
        } catch (MalformedURLException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (Exception e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("TAG", e.getLocalizedMessage());
                    return null;
                }
            }
        }

        return response;
    }

    protected void onPostExecute(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray vakanties = jsonObject.getJSONArray("content").getJSONObject(0).getJSONArray("vacations");
            System.out.println(vakanties);

            ArrayList<VakantieItem> vakantieItemsToAdd = new ArrayList<>();
            SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            for(int i = 0; i < vakanties.length(); i++) {
                JSONObject vakantie = vakanties.getJSONObject(i);

                String vakantieNaam = vakantie.getString("type");
                boolean compulsarydate = vakantie.getBoolean("compulsorydates");
                VakantieItem vakantieItem = new VakantieItem(vakantieNaam, compulsarydate);

                JSONArray vakantieData = vakantie.getJSONArray("regions");
                for(int j = 0; j < vakantieData.length(); j++) {
                    JSONObject vakantieDatum = vakantieData.getJSONObject(j);
                    String gebied = vakantieDatum.getString("region");
                    String startDate = vakantieDatum.getString("startdate");
                    String endDate = vakantieDatum.getString("enddate");

                    try {
                        Date startDatum = timeFormater.parse(startDate);
                        Date endDatum = timeFormater.parse(endDate);
                        vakantieItem.addTijdVak(new Tijdvak(gebied, startDatum, endDatum));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                vakantieItemsToAdd.add(vakantieItem);
            }
            System.out.println("");
            listener.onInfoAvailable(vakantieItemsToAdd);

        } catch (JSONException e) {
            Log.e("TAG", e.getLocalizedMessage());
        }
    }
}
