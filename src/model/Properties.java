package model;

import java.util.ArrayList;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Properties {

    //Atributes

    private ArrayList<String> states;
    private ArrayList<String> inputAlphabet;
    private ArrayList<String> outputAlphabet;

    //Constructor

    public Properties(){
        states = new ArrayList<>();
        inputAlphabet = new ArrayList<>();
        outputAlphabet = new ArrayList<>();
    }

    //Getters and setters

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
