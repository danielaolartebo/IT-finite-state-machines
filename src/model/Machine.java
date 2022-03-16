package model;

import model.Mealy.MealyMachine;
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
            if(!s.equals("")){
                properties.getInputAlphabet().add(s);
            }
        }
        deletedRepeated(properties.getInputAlphabet());
    }

    public void addOutputAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            if(!s.equals("")){
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

    public void partitionMainMoore(){
        for (int i = 0; i < mooremc.getStates().size(); i++) {
            if(mooreP.getMooreP().isEmpty()){
                ArrayList<StateMoore> newPart = new ArrayList<>();
                newPart.add(mooremc.getStates().get(i));
                mooreP.getMooreP().add(newPart);
            } else {
                boolean out = false;
                for (int j = 0; j < mooreP.getMooreP().size() && !out; j++) {
                    if(mooreP.getMooreP().get(j).get(0).getRequest().equals(mooremc.getStates().get(i).getRequest())){
                        out = true;
                        mooreP.getMooreP().get(j).add(mooremc.getStates().get(i));
                    }
                }
                if(!out){
                    ArrayList<StateMoore> newPart = new ArrayList<>();
                    newPart.add(mooremc.getStates().get(i));
                    mooreP.getMooreP().add(newPart);
                }
            }
        }
    }

    public void addingFinalStatesInStateMoore(){
        for (int i = 0; i < mooremc.getStates().size(); i++) {
            for (int j = 0; j < mooremc.getTransitions().size(); j++) {
                if(mooremc.getStates().get(i).getState().equals(mooremc.getTransitions().get(j).getInitialState().getState())){
                    mooremc.getStates().get(i).getFinStates().add(
                            mooremc.getTransitions().get(j).getFinalState()
                    );
                }
            }
        }
    }

    public void partitionMooore(){
        ArrayList<StateMoore> newPart = new ArrayList<>();
        boolean contain = true;
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> actual = new ArrayList<>();
        for (int i = 0; i < mooreP.getMooreP().size(); i++) {
            for (int j = 1; j < mooreP.getMooreP().get(i).size(); j++) {
                for (int k = 0; k < mooreP.getMooreP().get(i).get(j).getFinStates().size() && contain; k++) {
                    contain = stateTogetherMoore(mooreP.getMooreP().get(i).get(0).getFinStates().get(k),
                            mooreP.getMooreP().get(i).get(j).getFinStates().get(k));
                    if(!contain){
                        newPart.add(mooreP.getMooreP().get(i).get(j));
                        pos.add(j);
                        actual.add(i);
                    }
                    contain = true;
                }
            }
        }
        if(!newPart.isEmpty()){
            mooreP.getMooreP().add(newPart);
            int numDel = 0;
            for (int i = 0; i < deleteRepeatinMooreActual(actual).size(); i++) {
                for (int j = 0; j < deleteRepeatinMoorePos(pos).size(); j++) {
                    int del = deleteRepeatinMoorePos(pos).get(j);
                    mooreP.getMooreP().get(deleteRepeatinMooreActual(actual).get(i)).remove(del-numDel);
                    numDel++;
                }
            }

        }
    }

    public ArrayList<Integer> deleteRepeatinMoorePos(ArrayList<Integer> pos){
        HashSet<Integer> hs = new HashSet<>();
        hs.addAll(pos);
        pos.clear();
        pos.addAll(hs);
        return pos;
    }

    public ArrayList<Integer> deleteRepeatinMooreActual(ArrayList<Integer> actual){
        HashSet<Integer> hs = new HashSet<>();
        hs.addAll(actual);
        actual.clear();
        actual.addAll(hs);
        return actual;
    }

    public boolean stateTogetherMoore(StateMoore s1, StateMoore s2){
        boolean out = false;
        for (int i = 0; i < mooreP.getMooreP().size() && !out; i++) {
            if (mooreP.getMooreP().get(i).contains(s1) && mooreP.getMooreP().get(i).contains(s2)) {
                out = true;
            }
        }
        return out;
    }

    public void testDeParticion(){
        System.out.println("Test final");
        System.out.println(mooreP.getMooreP().size());
        for (int i = 0; i < mooreP.getMooreP().size(); i++) {
            System.out.println("Particion"+(i+1));
            for (int j = 0; j < mooreP.getMooreP().get(i).size(); j++) {
                System.out.print(mooreP.getMooreP().get(i).get(j).getState()+"  ");
            }
            System.out.println(" ");
        }
    }

    public String showAutomataParticionado(){
        String msg = "{";
        for (int i = 0; i < mooreP.getMooreP().size(); i++) {
            msg = msg+"{";
            for (int j = 0; j < mooreP.getMooreP().get(i).size(); j++) {
                msg = msg+mooreP.getMooreP().get(i).get(j).getState()+", ";
            }
            msg = msg+"}";
        }
        msg = msg+"}";
        return msg;
    }
}
