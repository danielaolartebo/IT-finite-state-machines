package model;

import model.Mealy.MealyMachine;
import model.Mealy.PartitionMealy;
import model.Mealy.StateMealy;
import model.Mealy.TransitionMealy;
import model.Moore.MooreMachine;
import model.Moore.StateMoore;
import model.Moore.TransitionMoore;

import java.util.ArrayList;
import java.util.HashSet;

public class Machine {

    private Properties properties;
    private MealyMachine mealymc;
    private MooreMachine mooremc;

    public Machine(){
        properties = new Properties();
        mealymc = new MealyMachine();
        mooremc = new MooreMachine();
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
    /*

    public void addingFirstP() {
        ArrayList<StateMealy> P11 = new ArrayList<>();
        pMealy.getHsPartition().add(P11);
        pMealy.getHsPartition().get(0).add(mealymc.getStates().get(0));
        for(int i = 0; i<mealymc.getStates().size(); i++){
            if(i != 0){
                if(comparingTransition(mealymc.getStates().get(0), mealymc.getStates().get(i))){
                    pMealy.getHsPartition().get(0).add(mealymc.getStates().get(i));
                }
            }
        }
        System.out.println(pMealy.getHsPartition().get(0).size()+"Mird");
        for(int i = 0; i<pMealy.getHsPartition().size(); i++){
            for (int j = 0; j<pMealy.getHsPartition().get(i).size(); j++){
                System.out.println(pMealy.getHsPartition().get(i).get(j).getState()+"Pucha");
            }
        }
    }

    public boolean comparingTransition(StateMealy init, StateMealy fin){
        ArrayList<TransitionMealy> s1 = new ArrayList<>();
        ArrayList<TransitionMealy> s2 = new ArrayList<>();
        for (int i = 0; i<mealymc.getTransitions().size(); i++){
            if(getMealymc().getTransitions().get(i).getInitialState().getState().equals(init.getState())){
                s1.add(mealymc.getTransitions().get(i));
            }
            if(getMealymc().getTransitions().get(i).getInitialState().getState().equals(fin.getState())){
                s2.add(mealymc.getTransitions().get(i));
            }
        }
        return isEquals(s1, s2);
    }

    public boolean isEquals(ArrayList<TransitionMealy> s1, ArrayList<TransitionMealy> s2){
        int countTotal = s1.size();
        int count = 0;
        for (int i = 0; i<s1.size(); i++){
            if(s1.get(i).getEstimulo().equals(s2.get(i).getEstimulo())){
                if(s1.get(i).getRequest().equals(s2.get(i).getRequest())){
                    count++;
                }
            }
        }
        return count == countTotal;
    }

     */


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

    /*

    public void deleteNoConexoMoore(){
        changeConnexionInitialStateMoore();
        changeAllconexxionMoore();
        deleteInStateMoore();
    }

    private void changeConnexionInitialStateMoore() {
        mooremc.getStates().get(0).setConexo(true);
    }

    public void searchingStateFinalMoore(StateMoore stateInitial){
        if (stateInitial.isConexo()){
            for (int i = 0; i<mooremc.getTransitions().size(); i++){
                if(mooremc.getTransitions().get(i).getInitialState().getState().equals(stateInitial.getState())){
                    StateMoore stateFinal = mooremc.getTransitions().get(i).getFinalState();
                    changeConexionMoore(stateFinal);
                }
            }
        }
    }

    public void changeConexionMoore(StateMoore stateFinal){
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(mooremc.getStates().get(i).getState().equals(stateFinal.getState())){
                mooremc.getStates().get(i).setConexo(true);
            }
        }
    }

    private void changeAllconexxionMoore() {
        for (int i = 0; i<mooremc.getStates().size(); i++){
            searchingStateFinalMoore(mooremc.getStates().get(i));
        }
    }

    private void deleteInStateMoore() {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(!mooremc.getStates().get(i).isConexo()){
                index.add(i);
            }
        }
        for(int deleted : index){
            deleteInTranstitionMoore(mooremc.getStates().get(deleted));
            mooremc.getStates().remove(deleted);
        }
    }

    private void deleteInTranstitionMoore(StateMoore stateMoore) {
        ArrayList<Integer> indexMoore = new ArrayList<>();
        for (int i = 0; i<mooremc.getTransitions().size(); i++){
            if(mooremc.getTransitions().get(i).getInitialState().getState().equals(stateMoore.getState())){
                indexMoore.add(i);
            }
        }
        for(int deleted : indexMoore){
            mooremc.getTransitions().remove(deleted);
        }
    }
    */
}
