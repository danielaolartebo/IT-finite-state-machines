package model;

import model.Mealy.MealyMachine;
import model.Mealy.StateMealy;
import model.Mealy.TransitionMealy;
import java.util.ArrayList;
import java.util.HashSet;

public class Machine {

    private Properties properties;
    private MealyMachine mealymc;

    public Machine(){
        properties = new Properties();
        mealymc = new MealyMachine();
    }

    public Properties getProperties(){
        return properties;
    }

    public MealyMachine getMealymc(){
        return mealymc;
    }

    public void addState(int numStates){
        for (int i = 0; i < numStates; i++){
            String newState = "q"+i;
            properties.getStates().add(newState);
        }
    }

    public void addInputAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            if(!s.equals(" ")){
                properties.getInputAlphabet().add(s);
            }
        }
        deletedRepeated(properties.getInputAlphabet());
    }

    public void addOutputAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            if(!s.equals(" ")){
                properties.getOutputAlphabet().add(s);
            }
        }
        deletedRepeated(properties.getOutputAlphabet());
    }

    public void deletedRepeated(ArrayList<String> array){
        HashSet hs = new HashSet();
        hs.addAll(array);
        array.clear();
        array.addAll(hs);
    }

    public void addStatesMealyM(){
        for(int i=0; i<properties.getStates().size(); i++){
            StateMealy state = new StateMealy(properties.getStates().get(i));
            mealymc.getStates().add(state);
        }
    }

    public void addTransitionMealyM(StateMealy initialState, String estimulo, String request, StateMealy finalState){
        TransitionMealy newTransition = new TransitionMealy(initialState, estimulo, request, finalState);
        mealymc.getTransitions().add(newTransition);
    }

    public StateMealy searchStateMealy(String state){
        for(int i = 0; i<mealymc.getStates().size(); i++){
            if(mealymc.getStates().get(i).getState().equals(state)){
                return mealymc.getStates().get(i);
            }
        }
        return null;
    }
}
