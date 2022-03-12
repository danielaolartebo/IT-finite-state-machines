package ui;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Machine;
import model.Properties;

import javax.crypto.Mac;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FXController {

    private Machine mc;

    public FXController(){
        mc = new Machine();
    }

    @FXML
    private TextField txtNumEstados;

    @FXML
    private TextField txtAlfabeto;

    @FXML
    private RadioButton rbMeely;

    @FXML
    private ToggleGroup tgTypeMachine;

    @FXML
    private RadioButton rbMoore;

    @FXML
    public void onNewMachine(ActionEvent event) {
        if(parseNumState(txtNumEstados.getText())){
            mc.addAlphabet(txtAlfabeto.getText());
            newAlert(1, "Valores correctos");
        }
        System.out.println(mc.getProperties().getAlphabet().toString());
        System.out.println(mc.getProperties().getStates().toString());
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



    /*
    //Atributos
        @FXML
    private TextField txtEstados;
    @FXML
    private TextField txtAlfabeto;
    @FXML
    private RadioButton tgMealy;
    @FXML
    private ToggleGroup machine;
    @FXML
    private RadioButton tgMoore;
    @FXML
    private Button btCrearTabla;
    @FXML
    private Button btMin;
    @FXML
    private ScrollPane scrollPane1;
    private ScrollPane minimizeScroll;
    private GridPane gridPanel1;
    private GridPane gridP1;
    private GridPane gridP2;
    private TextField[][] textfield;
    private int filas;
    private int columnas;

    public void initialize() {

    }

    @FXML
    public void crearTabla() {
        if(!verificarCampoTexto()) {
            return;
        }
    }

    public boolean verificarCampoTexto() {
        String alfabeto = txtAlfabeto.getText();
        String estados = txtEstados.getText();

        if (alfabeto.isEmpty() || estados.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Alerta");
            alert.setContentText("Todos los campos de texto deben ser llenados");
            alert.show();
            return false;
        }
        return true;
    }
     */

}
