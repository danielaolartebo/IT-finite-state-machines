package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Machine;

public class FXMoore {

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
    private Button btnAddStateR;

    private FXController fxGUI;
    private Machine mc;

    public FXMoore(Machine mc, FXController fxGUI){
        this.mc = mc;
        this.fxGUI = fxGUI;
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


    @FXML
    public void onAddStateR(ActionEvent event) {

    }
}
