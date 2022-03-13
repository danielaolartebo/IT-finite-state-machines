package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Machine;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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

    private FXController fxGUI;
    private Machine mc;

    public FXMealy(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
    }

    public void initialize(){
        fillCbRequest();
        fillCbFinalState();
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

    @FXML
    public void onAddTransition(ActionEvent event) {

    }

    @FXML
    public void onAutomataConexo(ActionEvent event) {

    }

    @FXML
    public void onPartition(ActionEvent event) {

    }
}
