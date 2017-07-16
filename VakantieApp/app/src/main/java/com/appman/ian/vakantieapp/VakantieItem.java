package com.appman.ian.vakantieapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ian on 31-5-2017.
 */

public class VakantieItem implements Serializable{
    public String naam;
    public boolean compulsarydates;
    public ArrayList<Tijdvak> tijdvlak;

    public VakantieItem(String naam, boolean compulsarydates) {
        this.naam = naam;
        this.compulsarydates = compulsarydates;
        tijdvlak = new ArrayList<>();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean isCompulsarydates() {
        return compulsarydates;
    }

    public void setCompulsarydates(boolean compulsarydates) {
        this.compulsarydates = compulsarydates;
    }

    public void addTijdVak(Tijdvak tijdvak) {
        tijdvlak.add(tijdvak);
    }

    public ArrayList<Tijdvak> getTijdvlak() {
        return tijdvlak;
    }
}
