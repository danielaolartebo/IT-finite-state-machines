package model.Mealy;

import java.util.ArrayList;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class MealyMachine {

    //Atributes

    private ArrayList<StateMealy> states;
    private ArrayList<TransitionMealy> transitions;

    //Constructor

    public MealyMachine(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    //Getters and setters

    public ArrayList<StateMealy> getStates() {
        return states;
    }

    public ArrayList<TransitionMealy> getTransitions() {
        return transitions;
    }
}
