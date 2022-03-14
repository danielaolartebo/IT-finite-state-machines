package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import model.Machine;

import java.io.IOException;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A0036....
 *
 */

public class FXController {

    @FXML
    private TextField txtNumEstados;

    @FXML
    private TextField txtInputAlfabeto;

    @FXML
    private TextField txtOutputAlfabeto;

    @FXML
    private RadioButton rbMeely;

    @FXML
    private ToggleGroup tgTypeMachine;

    @FXML
    private RadioButton rbMoore;

    @FXML
    private BorderPane bpMain;

    private Machine mc;
    private FXMealy xMealy;
    private FXMoore xMoore;

    public FXController(){
        mc = new Machine();
        xMealy = new FXMealy(mc, this);
        xMoore = new FXMoore(mc, this);
    }


    @FXML
    public void onNewMachine(ActionEvent event) throws IOException {
        if(isNotEmptyProperties()) {
            if(rbMoore.isSelected()){
                if (parseNumState(txtNumEstados.getText())) {
                    addProperties();
                    showMoore();
                }
            } else if (rbMeely.isSelected()){
                if (parseNumState(txtNumEstados.getText())) {
                    addProperties();
                    showMealy();
                }
            } else {
                newAlert(0, "Selecciona un tipo de maquina");
            }
        } else {
            newAlert(0, "Llena todos los campos");
        }
    }

    public boolean isNotEmptyProperties(){
        return !txtInputAlfabeto.getText().isEmpty() &&
                !txtOutputAlfabeto.getText().isEmpty() && !txtNumEstados.getText().isEmpty();
    }

    public void addProperties(){
        mc.addInputAlphabet(txtInputAlfabeto.getText());
        mc.addOutputAlphabet(txtOutputAlfabeto.getText());
        newAlert(1, "Valores correctos");
        System.out.println(mc.getProperties().getInputAlphabet().toString());
        System.out.println(mc.getProperties().getStates().toString());
        System.out.println(mc.getProperties().getOutputAlphabet().toString());
    }

    public boolean parseNumState(String states){
        try {
            int numStates = Integer.parseInt(states);
            mc.addState(numStates);
            return true;
        } catch (NumberFormatException e){
            newAlert(0, "Ingresa un numero en la cantidad de estados");
            txtNumEstados.clear();
            return false;
        }
    }

    public void newAlert(int type, String message){
        Alert alert;
        if(type == 0){
            alert = new Alert(Alert.AlertType.WARNING);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setHeaderText(null);
        alert.setTitle("Alert");
        alert.setContentText(message);
        alert.show();
    }

    public void showMealy() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/MealyMachine.fxml"));
        fxmlLoader.setController(xMealy);
        Parent root = fxmlLoader.load();
        bpMain.getChildren().clear();
        bpMain.getChildren().setAll(root);
        xMealy.initialize();
        xMealy.addStatesMealyM();
    }

    public void showMoore() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/SetStateMoore.fxml"));
        fxmlLoader.setController(xMoore);
        Parent root = fxmlLoader.load();
        bpMain.getChildren().clear();
        bpMain.getChildren().setAll(root);
        xMoore.fillCbState();
    }
}
