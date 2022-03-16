package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Machine;
import model.Moore.StateMoore;
import model.Moore.TransitionMoore;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

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

    @FXML
    private TableView<TransitionMoore> tblMooreTransition;

    @FXML
    private TableColumn<TransitionMoore, String> tblcStateInitial;

    @FXML
    private TableColumn<TransitionMoore, String> tblcRequesStateI;

    @FXML
    private TableColumn<TransitionMoore, String> tblcEstimulo;

    @FXML
    private TableColumn<TransitionMoore, String> tblcStateFinal;

    @FXML
    private Button btnConexAuto;

<<<<<<< HEAD
    @FXML
    private Label lblAutomataParticionado;
=======
    //Atributes
>>>>>>> d17d3fa3d76717b9c113787ee3775809b90b6df9

    private FXController fxGUI;
    private Machine mc;
    private int setStateActual = 0;
    int estimuloActual = 0;
    int stateActual = 0;

    //Constructor

    public FXMoore(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
    }

    /**
     * Initialize JavaFX method
     */

    public void initalize(){
        fillCbState();
        lblState.setText(mc.getProperties().getStates().get(0));
    }

    /**
     * Fills Cb state
     */

    public void fillCbState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getOutputAlphabet());
        cbRequestState.setItems(obs);
    }

    /**
     * Fills Cb final state
     */

    public void fillCbFinalState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getStates());
        cbFinalState.setItems(obs);
    }

    /**
     * Adds set state
     */

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

    /**
     * Shows moore screen
     */

    public void showScreenMoore() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/MooreMachine.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        pStateR.getChildren().clear();
        pStateR.getChildren().setAll(root);
    }

    /**
     *Adds transition
     */

    @FXML
    public void onAddTransition(ActionEvent event) {
        if(cbFinalState.getValue() != null){
            addTransitionMoore();
            newSandE();
            cbFinalState.setValue(null);
            onTableTransition();
        } else {
            fxGUI.newAlert(0, "Por favor selecciona un estado final");
        }
    }

    /**
     * Automata conexo method
     */

    @FXML
    public void onAutomataConexo(ActionEvent event) {
        System.out.println("Conexo?");
        mc.deleteNoConexoMoore();
        onTableTransition();
        btnConexAuto.setDisable(true);
        btnPartition.setDisable(false);
<<<<<<< HEAD
        mc.addingFinalStatesInStateMoore();
        mc.partitionMainMoore();
        mc.testDeParticion();
        lblAutomataParticionado.setText(mc.showAutomataParticionado());

=======
>>>>>>> d17d3fa3d76717b9c113787ee3775809b90b6df9
    }

    /**
     * Partition method
     */

    @FXML
    public void onPartition(ActionEvent event) {
        mc.partitionMooore();
        mc.testDeParticion();
        lblAutomataParticionado.setText(mc.showAutomataParticionado());
    }

    /**
     * Adds states
     * @param event of JavaFX
     */

    @FXML
    public void onAddStateR(ActionEvent event) throws IOException {
        if(cbRequestState.getValue() != null){
            addSetState();
        } else {
            fxGUI.newAlert(0, "Selecciona una respuesta");
        }
    }

    /**
     * Next screen
     * @param event of JavaFX
     */

    @FXML
    public void onNextScreen(ActionEvent event) throws IOException {
        showScreenMoore();
        fillCbFinalState();
        lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(0));
    }

    /**
     * Changes moore estimulo
     */

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

    /**
     * Changes state
     */

    public void changeState(){
        int countS = mc.getProperties().getStates().size();
        if(countS == 1){
            btnAddTransition.setDisable(true);
        } else {
            if(stateActual < countS){
                lblInitialState.setText(mc.getProperties().getStates().get(stateActual));
            } else {
                btnAddTransition.setDisable(true);
                btnConexAuto.setDisable(false);
                cbFinalState.setDisable(true);
            }
        }
    }

    /**
     * New sand E
     */

    public void newSandE(){
        estimuloActual++;
        changeEstimulMoore();
    }

    /**
     * Adds more transition
     */

    public void addTransitionMoore(){
        StateMoore initS = mc.searchStateMoore(lblInitialState.getText());
        String estimulo = lblEstimulo.getText();
        StateMoore finalS = mc.searchStateMoore(cbFinalState.getValue());
        mc.addTransitionMoore(initS, estimulo, finalS);
    }

    /**
     * On table transition
     */

    public void onTableTransition(){
        List<TransitionMoore> transitions = mc.getMooremc().getTransitions();
        ObservableList<TransitionMoore> newTableTransitions;
        newTableTransitions = FXCollections.observableArrayList(transitions);

        tblMooreTransition.setItems(newTableTransitions);
        tblcStateInitial.setCellValueFactory(new PropertyValueFactory<>("nameI"));
        tblcEstimulo.setCellValueFactory(new PropertyValueFactory<>("estimulo"));
        tblcStateFinal.setCellValueFactory(new PropertyValueFactory<>("nameF"));
        tblcRequesStateI.setCellValueFactory(new PropertyValueFactory<>("requestMooreState"));

        tblMooreTransition.refresh();
    }
}
