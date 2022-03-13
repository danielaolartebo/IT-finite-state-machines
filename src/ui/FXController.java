package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Machine;

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

    private Machine mc;

    public FXController(){
        mc = new Machine();
    }


    @FXML
    public void onNewMachine(ActionEvent event) {
        if(isNotEmptyProperties()) {
            if (parseNumState(txtNumEstados.getText())) {
                addProperties();
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
}
