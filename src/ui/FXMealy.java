package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.Machine;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Mealy.MealyMachine;
import model.Mealy.StateMealy;

import java.util.Observable;


public class FXMealy {

    @FXML
    private Label lblInitialState;

    @FXML
    private Label lblEstimulo;

    @FXML
    private ComboBox<String> cbRequest;

    @FXML
    private ComboBox<String> cbFinalState;

    @FXML
    private Button btnAddTransition;

    private FXController fxGUI;
    private Machine mc;
    int estimuloActual = 0;
    int stateActual = 0;


    public FXMealy(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
    }

    public void initialize(){
        fillCbRequest();
        fillCbFinalState();
        addStateMealy();
    }

    private void fillCbRequest(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getOutputAlphabet());
        cbRequest.setItems(obs);
    }

    private void fillCbFinalState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getStates());
        cbFinalState.setItems(obs);
    }


    private void addStateMealy(){
        mc.addStatesMealyM();
    }

    private void addTransitionMealy(){
        StateMealy initState = mc.searchStateMealy(lblInitialState.getText());
        StateMealy finalState = mc.searchStateMealy(cbFinalState.getValue());
        String estimulo = lblEstimulo.getText();
        String request = cbRequest.getValue();
        mc.addTransitionMealyM(initState, estimulo, request, finalState);
    }


    public void changeEstimulo(){
        int countEstimulo = mc.getProperties().getInputAlphabet().size();
        if(estimuloActual < countEstimulo){
            lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(estimuloActual));
        } else {
            lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(0));
            estimuloActual = 0;
            stateActual++;
            changeState();
        }
    }

    public void changeState(){
        int countState = mc.getProperties().getStates().size();
        if(countState == 1){
            btnAddTransition.setDisable(true);
        } else {
            if(stateActual < countState){
                lblInitialState.setText(mc.getProperties().getStates().get(stateActual));
            } else {
                btnAddTransition.setDisable(true);
            }
        }
    }

    public void newStatesandEstimulos(){
        estimuloActual++;
        changeEstimulo();
    }

    @FXML
    public void onAddTransition(ActionEvent event) {
        newStatesandEstimulos();
        addTransitionMealy();
    }

    @FXML
    public void onAutomataConexo(ActionEvent event) {

    }

    @FXML
    public void onPartition(ActionEvent event) {

    }
}
