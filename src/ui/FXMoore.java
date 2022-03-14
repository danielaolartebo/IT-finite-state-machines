package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Machine;
import model.Moore.StateMoore;
import model.Moore.TransitionMoore;

import java.io.IOException;

public class FXMoore {

    @FXML
    private Pane pStateR;

    @FXML
    private Button btnAddTransition;

    @FXML
    private Label lblInitialState;

    @FXML
    private Label lblEstimulo;

    @FXML
    private ComboBox<String> cbFinalState;

    @FXML
    private Button btnPartition;

    @FXML
    private Label lblState;

    @FXML
    private ComboBox<String> cbRequestState;

    @FXML
    private Button btnNextScreen;

    @FXML
    private Button btnAddStateR;

    private FXController fxGUI;
    private Machine mc;
    private int setStateActual = 0;
    int estimuloActual = 0;
    int stateActual = 0;

    public FXMoore(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
    }

    public void initalize(){
        fillCbState();
        lblState.setText(mc.getProperties().getStates().get(0));
    }

    public void fillCbState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getOutputAlphabet());
        cbRequestState.setItems(obs);
    }

    public void fillCbFinalState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getStates());
        cbFinalState.setItems(obs);
    }

    public void addSetState() throws IOException {
        int countState = mc.getProperties().getStates().size();
        if(setStateActual < countState){
            mc.addStateMoore(lblState.getText(), cbRequestState.getValue());
            setStateActual++;
            if(setStateActual < countState){
                lblState.setText(mc.getProperties().getStates().get(setStateActual));
            } else {
                btnAddStateR.setDisable(true);
                btnNextScreen.setDisable(false);
            }
        }
    }

    public void showScreenMoore() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/MooreMachine.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        pStateR.getChildren().clear();
        pStateR.getChildren().setAll(root);

    }

    @FXML
    public void onAddTransition(ActionEvent event) {
        if(cbFinalState.getValue() != null){
            addTransitionMoore();
            newSandE();
            cbFinalState.setValue(null);
        } else {
            fxGUI.newAlert(0, "Por favor selecciona un estado final");
        }
    }

    @FXML
    public void onAutomataConexo(ActionEvent event) {
        mc.deleteNoConexoMoore();
        for (int i = 0; i<mc.getMooremc().getStates().size(); i++){
            System.out.println(mc.getMooremc().getStates().get(i).getState()+ "Estado");
        }
        for (int i = 0; i<mc.getMooremc().getTransitions().size(); i++){
            System.out.println(mc.getMooremc().getTransitions().get(i).getInitialState()+ "Transition");
        }
    }

    @FXML
    public void onPartition(ActionEvent event) {

    }


    @FXML
    public void onAddStateR(ActionEvent event) throws IOException {
        if(cbRequestState.getValue() != null){
            addSetState();
        } else {
            fxGUI.newAlert(0, "Selecciona una respuesta");
        }

    }

    @FXML
    public void onNextScreen(ActionEvent event) throws IOException {
        showScreenMoore();
        fillCbFinalState();
        lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(0));
    }

    public void changeEstimulMoore(){
        int countE = mc.getProperties().getInputAlphabet().size();
        if(estimuloActual < countE){
            lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(estimuloActual));
        } else {
            lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(0));
            estimuloActual = 0;
            stateActual++;
            changeState();
        }
    }

    public void changeState(){
        int countS = mc.getProperties().getStates().size();
        if(countS == 1){
            btnAddTransition.setDisable(true);
        } else {
            if(stateActual < countS){
                lblInitialState.setText(mc.getProperties().getStates().get(stateActual));
            } else {
                btnAddTransition.setDisable(true);
                cbFinalState.setDisable(true);
            }
        }
    }

    public void newSandE(){
        estimuloActual++;
        changeEstimulMoore();
    }

    public void addTransitionMoore(){
        StateMoore initS = mc.searchStateMoore(lblInitialState.getText());
        String estimulo = lblEstimulo.getText();
        StateMoore finalS = mc.searchStateMoore(cbFinalState.getValue());
        TransitionMoore tm = new TransitionMoore(initS, estimulo, finalS);
        mc.addTransitionMoore(initS, estimulo, finalS);
    }
}
