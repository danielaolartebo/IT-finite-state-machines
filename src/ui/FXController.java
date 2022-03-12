package ui;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A0036....
 *
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;

public class FXController {

    public FXController(){

    }
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
    @FXML

    //Atributos

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

}
