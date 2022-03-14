package model.Moore;

import java.util.ArrayList;

public class MooreMachine {

    private ArrayList<StateMoore> states;
    private ArrayList<TransitionMoore> transitions;

    public MooreMachine(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public ArrayList<StateMoore> getStates() {
        return states;
    }

    public void setStates(ArrayList<StateMoore> states) {
        this.states = states;
    }

    public ArrayList<TransitionMoore> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<TransitionMoore> transitions) {
        this.transitions = transitions;
    }
}
