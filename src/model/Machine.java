package model;

import model.Mealy.MealyMachine;
import model.Mealy.PartitionMealy;
import model.Mealy.StateMealy;
import model.Mealy.TransitionMealy;
import model.Moore.MooreMachine;
import model.Moore.PartitionMoore;
import model.Moore.StateMoore;
import model.Moore.TransitionMoore;

import java.util.ArrayList;
import java.util.HashSet;

public class Machine {

    private Properties properties;
    private MealyMachine mealymc;
    private MooreMachine mooremc;
    private PartitionMoore mooreP;

    public Machine(){
        properties = new Properties();
        mealymc = new MealyMachine();
        mooremc = new MooreMachine();
        mooreP = new PartitionMoore();
    }

    public Properties getProperties(){
        return properties;
    }

    public MealyMachine getMealymc(){
        return mealymc;
    }

    public MooreMachine getMooremc(){
        return mooremc;
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

    public void addStatesMealyMachine(){
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

    public void deleteNoConexoMealy(){
        changeConnexionInitialState();
        changeAllconexxion();
        deleteInStateMealy();

    }

    public void changeConnexionInitialState(){
        mealymc.getStates().get(0).setConexo(true);

    }

    public void searchingStateFinal(StateMealy stateInitial){
        if(stateInitial.isConexo()){
            for(int i = 0; i<mealymc.getTransitions().size(); i++){
                if(mealymc.getTransitions().get(i).getInitialState().getState().equals(stateInitial.getState())){
                    StateMealy stateFinal = mealymc.getTransitions().get(i).getFinalState();
                    changeConnexion(stateFinal);
                }
            }
        }
    }

    public void changeConnexion(StateMealy stateFinal){
        for (int i = 0; i<mealymc.getStates().size(); i++){
            if(mealymc.getStates().get(i).getState().equals(stateFinal.getState())){
                mealymc.getStates().get(i).setConexo(true);
            }
        }
    }

    public void changeAllconexxion(){
        for(int i = 0; i<mealymc.getStates().size(); i++){
            searchingStateFinal(mealymc.getStates().get(i));
        }
    }

    public void deleteInStateMealy(){
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i<mealymc.getStates().size(); i++){
            if(!mealymc.getStates().get(i).isConexo()){
                index.add(i);
            }
        }
        for (int deleted : index) {
            deleteInTranstitionMealy(mealymc.getStates().get(deleted));
            mealymc.getStates().remove(deleted);
        }
    }

    public void deleteInTranstitionMealy(StateMealy stateDeleted){
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i<mealymc.getTransitions().size(); i++){
            if(mealymc.getTransitions().get(i).getInitialState().getState().equals(stateDeleted.getState())){
                index.add(i);
            }
        }
        for (int deleted : index) {
            mealymc.getTransitions().remove(deleted);
        }
    }

    public void addStateMoore(String state, String request){
        StateMoore newState = new StateMoore(state, request);
        mooremc.getStates().add(newState);
    }

    public void addTransitionMoore(StateMoore initial, String estimulo, StateMoore finalS){
        TransitionMoore newT = new TransitionMoore(initial, estimulo, finalS);
        mooremc.getTransitions().add(newT);
    }

    public StateMoore searchStateMoore(String state){
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(mooremc.getStates().get(i).getState().equals(state)){
                return mooremc.getStates().get(i);
            }
        }
        return null;
    }


    public void deleteNoConexoMoore(){
        changeConnexionInitialStateMoore();
        changeAllconexxionMoore();
        deleteInStateMoore();

    }

    public void changeConnexionInitialStateMoore(){
        mooremc.getStates().get(0).setConexo(true);

    }

    public void searchingStateFinalMoore(StateMoore stateInitial){
        if(stateInitial.isConexo()){
            for(int i = 0; i<mooremc.getTransitions().size(); i++){
                if(mooremc.getTransitions().get(i).getInitialState().getState().equals(stateInitial.getState())){
                    StateMoore stateFinal = mooremc.getTransitions().get(i).getFinalState();
                    changeConnexionMoore(stateFinal);
                }
            }
        }
    }

    public void changeConnexionMoore(StateMoore stateFinal){
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(mooremc.getStates().get(i).getState().equals(stateFinal.getState())){
                mooremc.getStates().get(i).setConexo(true);
            }
        }
    }

    public void changeAllconexxionMoore(){
        for(int i = 0; i<mooremc.getStates().size(); i++){
            searchingStateFinalMoore(mooremc.getStates().get(i));
        }
    }

    public void deleteInStateMoore(){
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(!mooremc.getStates().get(i).isConexo()){
                index.add(i);
            }
        }
        for (int deleted : index) {
            deleteInTranstitionMoore(mooremc.getStates().get(deleted));
            mooremc.getStates().remove(deleted);
        }
    }

    public void deleteInTranstitionMoore(StateMoore stateDeleted){
        ArrayList<Integer> indexS = new ArrayList<>();
        for (int i = 0; i<mooremc.getTransitions().size(); i++){
            if(mooremc.getTransitions().get(i).getInitialState().getState().equals(stateDeleted.getState())){
                indexS.add(i);
            }
        }
        for (int deleted : indexS) {
            mooremc.getTransitions().remove(deleted);
        }
    }

    public void showTeset(){
        for (int i = 0; i<mooreP.getMooreP().size(); i++){
            for (int j = 0; j<mooreP.getMooreP().get(i).size(); j++){
                System.out.println(mooreP.getMooreP().get(i).get(j).getState());
            }
        }
    }
}
