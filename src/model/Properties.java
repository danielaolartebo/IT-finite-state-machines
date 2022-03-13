package model;

import java.util.ArrayList;

public class Properties {

    private ArrayList<String> states;
    private ArrayList<String> inputAlphabet;
    private ArrayList<String> outputAlphabet;

    public Properties(){
        states = new ArrayList<>();
        inputAlphabet = new ArrayList<>();
        outputAlphabet = new ArrayList<>();
    }

    public ArrayList<String> getStates(){
        return states;
    }

    public ArrayList<String> getInputAlphabet(){
        return inputAlphabet;
    }

    public ArrayList<String> getOutputAlphabet(){
        return outputAlphabet;
    }
}
