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

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Machine {

    //Atributes

    private Properties properties;
    private MealyMachine mealymc;
    private MooreMachine mooremc;
    private PartitionMoore mooreP;

    //Constructor

    public Machine(){
        properties = new Properties();
        mealymc = new MealyMachine();
        mooremc = new MooreMachine();
        mooreP = new PartitionMoore();
    }

    //Getter and setters

    public Properties getProperties(){
        return properties;
    }

    public MealyMachine getMealymc(){
        return mealymc;
    }

    public MooreMachine getMooremc(){
        return mooremc;
    }

    /**
     * Adds the state to state-machine (Moore or Mealy)
     * @param numStates number of states
     */

    public void addState(int numStates){
        for (int i = 0; i < numStates; i++){
            String newState = "q"+i;
            properties.getStates().add(newState);
        }
    }

    /**
     * Adds input alphabet (example: a, b, c, d,...,n) to Moore or Mealy machine
     * @param alphabet of the state-machine
     */

    public void addInputAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            if(!s.equals(" ")){
                properties.getInputAlphabet().add(s);
            }
        }
        deletedRepeated(properties.getInputAlphabet());
    }

    /**
     * Adds the output alphabet (example: 0, 1) to Moore or Mealy machine
     * @param alphabet of the state-machine
     */

    public void addOutputAlphabet(String alphabet){
        String[] newAlphabet = alphabet.split(" ");
        for (String s : newAlphabet) {
            if(!s.equals(" ")){
                properties.getOutputAlphabet().add(s);
            }
        }
        deletedRepeated(properties.getOutputAlphabet());
    }

    /**
     * Deletes repeated alphabet
     * @param array of Strings
     */

    public void deletedRepeated(ArrayList<String> array){
        HashSet hs = new HashSet();
        hs.addAll(array);
        array.clear();
        array.addAll(hs);
    }

    /**
     * Adds states to Mealy machine
     */

    public void addStatesMealyMachine(){
        for(int i=0; i<properties.getStates().size(); i++){
            StateMealy state = new StateMealy(properties.getStates().get(i));
            mealymc.getStates().add(state);
        }
    }

    /**
     * Adds transitions to Mealy machine
     * @param initialState of the machine
     * @param estimulo of the machine
     * @param request of the machine
     * @param finalState of the machine
     */

    public void addTransitionMealyM(StateMealy initialState, String estimulo, String request, StateMealy finalState){
        TransitionMealy newTransition = new TransitionMealy(initialState, estimulo, request, finalState);
        mealymc.getTransitions().add(newTransition);
    }

    /**
     * Searchs state of the Mealy machine
     * @param state of the machine
     * @return StateMealy
     */

    public StateMealy searchStateMealy(String state){
        for(int i = 0; i<mealymc.getStates().size(); i++){
            if(mealymc.getStates().get(i).getState().equals(state)){
                return mealymc.getStates().get(i);
            }
        }
        return null;
    }

    /**
     * Adds deletes no conexo Mealy automata
     */

    public void deleteNoConexoMealy(){
        changeConnexionInitialState();
        changeAllconexxion();
        deleteInStateMealy();
    }

    /**
     * Changes connexion of initial state
     */

    public void changeConnexionInitialState(){
        mealymc.getStates().get(0).setConexo(true);
    }

    /**
     * Searches final state of the Mealy machine
     * @param stateInitial of the machine
     */

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

    /**
     * Changes connexion of the Mealy machine
     * @param stateFinal of the machine
     */

    public void changeConnexion(StateMealy stateFinal){
        for (int i = 0; i<mealymc.getStates().size(); i++){
            if(mealymc.getStates().get(i).getState().equals(stateFinal.getState())){
                mealymc.getStates().get(i).setConexo(true);
            }
        }
    }

    /**
     * Changes connexion of the machine
     */

    public void changeAllconexxion(){
        for(int i = 0; i<mealymc.getStates().size(); i++){
            searchingStateFinal(mealymc.getStates().get(i));
        }
    }

    /**
     * Deletes in state of Mealy machine
     */

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

    /**
     * Deletes in transition of the Mealy machine
     * @param stateDeleted of the machine
     */

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

    /**
     * Adds state to the Moore machine
     * @param state of the Moore machine
     * @param request of the machine
     */

    public void addStateMoore(String state, String request){
        StateMoore newState = new StateMoore(state, request);
        mooremc.getStates().add(newState);
    }

    /**
     * Adds transition to Moore machine
     * @param initial of the machine
     * @param estimulo of the machine
     * @param finalS of the machine
     */

    public void addTransitionMoore(StateMoore initial, String estimulo, StateMoore finalS){
        TransitionMoore newT = new TransitionMoore(initial, estimulo, finalS);
        mooremc.getTransitions().add(newT);
    }

    /**
     * Searches the state of the Moore machine
     * @param state of the Moore machine
     */

    public StateMoore searchStateMoore(String state){
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(mooremc.getStates().get(i).getState().equals(state)){
                return mooremc.getStates().get(i);
            }
        }
        return null;
    }

    /**
     * Deletes the no conexo Moore automata
     */

    public void deleteNoConexoMoore(){
        changeConnexionInitialStateMoore();
        changeAllconexxionMoore();
        deleteInStateMoore();
    }

    /**
     * Changes connexion of the initial state of Moore machine
     */

    public void changeConnexionInitialStateMoore(){
        mooremc.getStates().get(0).setConexo(true);
    }

    /**
     * Searches final state of Moore machine
     * @param stateInitial of the machine
     */

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

    /**
     * Changes connexion of the Moore machine
     * @param stateFinal of the machine
     */

    public void changeConnexionMoore(StateMoore stateFinal){
        for (int i = 0; i<mooremc.getStates().size(); i++){
            if(mooremc.getStates().get(i).getState().equals(stateFinal.getState())){
                mooremc.getStates().get(i).setConexo(true);
            }
        }
    }

    /**
     * Changes all connexion of the Moore machine
     */

    public void changeAllconexxionMoore(){
        for(int i = 0; i<mooremc.getStates().size(); i++){
            searchingStateFinalMoore(mooremc.getStates().get(i));
        }
    }

    /**
     * Deletes in state Moore machine
     */

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

    /**
     * Deletes in transition of the Moore machine
     * @param stateDeleted of the machine
     */

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

    /**
     * Shows tests
     */

    public void showTeset(){
        for (int i = 0; i<mooreP.getMooreP().size(); i++){
            for (int j = 0; j<mooreP.getMooreP().get(i).size(); j++){
                System.out.println(mooreP.getMooreP().get(i).get(j).getState());
            }
        }
    }
}
