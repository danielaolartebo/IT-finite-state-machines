package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Machine;
import javafx.fxml.FXML;
import model.Mealy.StateMealy;
import model.Mealy.TransitionMealy;
import java.util.List;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */


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

    @FXML
    private Button btnPartition;

    @FXML
    private TableView<TransitionMealy> tblMealyTransition;

    @FXML
    private TableColumn<TransitionMealy, String> tblcInitialState;

    @FXML
    private TableColumn<TransitionMealy, String> tblcEstimulo;

    @FXML
    private TableColumn<TransitionMealy, String> tblcRequest;

    @FXML
    private TableColumn<TransitionMealy, String> tblcStateFinal;

    //Atributes

    private FXController fxGUI;
    private Machine mc;
    int estimuloActual = 0;
    int stateActual = 0;

    //Constructor

    public FXMealy(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
    }

    /**
     * Initialize JavaFX method
     */

    public void initialize(){
        fillCbRequest();
        fillCbFinalState();
        lblEstimulo.setText(mc.getProperties().getInputAlphabet().get(0));
    }

    /**
     * Fills Cb request
     */

    private void fillCbRequest(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getOutputAlphabet());
        cbRequest.setItems(obs);
    }

    /**
     * Fills cb final state
     */

    private void fillCbFinalState(){
        ObservableList<String> obs;
        obs = FXCollections.observableArrayList(mc.getProperties().getStates());
        cbFinalState.setItems(obs);
    }

    /**
     * Adds mealy states
     */

    public void addStatesMealyM(){
        mc.addStatesMealyMachine();
    }

    /**
     * Adds mealy transition
     */

    private void addTransitionMealy(){
        StateMealy initState = mc.searchStateMealy(lblInitialState.getText());
        StateMealy finalState = mc.searchStateMealy(cbFinalState.getValue());
        String estimulo = lblEstimulo.getText();
        String request = cbRequest.getValue();
        mc.addTransitionMealyM(initState, estimulo, request, finalState);
    }

    /**
     * Changes estimulo
     */

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

    /**
     * Changes state
     */

    public void changeState(){
        int countState = mc.getProperties().getStates().size();
        if(countState == 1){
            btnAddTransition.setDisable(true);
        } else {
            if(stateActual < countState){
                lblInitialState.setText(mc.getProperties().getStates().get(stateActual));
            } else {
                btnAddTransition.setDisable(true);
                cbFinalState.setDisable(true);
                cbRequest.setDisable(true);
            }
        }
    }

    /**
     * New states and estimulos
     */

    public void newStatesandEstimulos(){
        estimuloActual++;
        changeEstimulo();
    }

    /**
     * Adds transition
     * @param event of JavaFX
     */

    @FXML
    public void onAddTransition(ActionEvent event) {
        if(cbFinalState.getValue() != null && cbRequest.getValue() != null){
            addTransitionMealy();
            newStatesandEstimulos();
            cbRequest.setValue(null);
            cbFinalState.setValue(null);
            onTableTransition();
        } else {
            fxGUI.newAlert(0, "Seleccione todos los campos");
        }
    }

    /**
     * Automata conexo
     * @param event of JavaFX
     */

    @FXML
    public void onAutomataConexo(ActionEvent event) {
        mc.deleteNoConexoMealy();
        for(int i = 0; i<mc.getMealymc().getStates().size(); i++){
            System.out.println(mc.getMealymc().getStates().get(i).isConexo());
        }
        onTableTransition();
        btnPartition.setDisable(false);
    }

    /**
     * Partitions
     * @param event of JavaFX
     */

    @FXML
    public void onPartition(ActionEvent event) {
        fxGUI.newAlert(0, "Sorry, nos rendimos en esta parte, please PIEDAD");
    }

    /**
     * On table transition
     */

    public void onTableTransition(){
        List<TransitionMealy> transitions = mc.getMealymc().getTransitions();
        ObservableList<TransitionMealy> newTableTransitions;
        newTableTransitions = FXCollections.observableArrayList(transitions);

        tblMealyTransition.setItems(newTableTransitions);
        tblcEstimulo.setCellValueFactory(new PropertyValueFactory<>("estimulo"));
        tblcRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
        tblcStateFinal.setCellValueFactory(new PropertyValueFactory<>("nameF"));
        tblcInitialState.setCellValueFactory(new PropertyValueFactory<>("nameI"));
        tblMealyTransition.refresh();
    }
}
