package model;

import java.util.ArrayList;

public class Properties {

    private ArrayList<String> states;
    private ArrayList<String> alphabet;

    public Properties(){
        states = new ArrayList<>();
        alphabet = new ArrayList<>();
    }

    public ArrayList<String> getStates(){
        return states;
    }

    public ArrayList<String> getAlphabet(){
        return alphabet;
    }
}
