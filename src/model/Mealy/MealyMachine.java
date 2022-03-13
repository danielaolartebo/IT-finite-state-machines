package model.Mealy;

import java.util.ArrayList;

public class MealyMachine {

    private ArrayList<StateMealy> states;
    private ArrayList<TransitionMealy> transitions;

    public MealyMachine(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public ArrayList<StateMealy> getStates() {
        return states;
    }

    public ArrayList<TransitionMealy> getTransitions() {
        return transitions;
    }
}
