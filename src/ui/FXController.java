package ui;
/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.GenericArray;
import model.Analyzers.Analyzer;
import model.Analyzers.MealyAnalyzer;
import model.*;
import model.mealyMachine.MealyMachine;
import model.mealyMachine.MealyState;

public class FXController {

	@FXML
	private Pane mainPane;
	@FXML
	private TextField statesAmount;
	@FXML
	private TextField symbolInput;
	@FXML
	private TextField symbolOutput;
	@FXML
	private RadioButton rbMeely;
	@FXML
	private ToggleGroup tgTypeMachine;
	@FXML
	private RadioButton rbMoore;
	@FXML
	private Button createMealyStatesButton;
	@FXML
	private Button symbolOutputButton;
	@FXML
	private Button loadSymbolsButton;
	@FXML
	private Label alphInLabel;
	@FXML
	private Label alphOutLabel;
	@FXML
	private Label labelInfoLink;
	@FXML
	private Pane linkPaneMealy;
	@FXML
	private TextField currentInSymbolToLink;
	@FXML
	private TextField currentOutSymbolToLink;
	@FXML
	private TextField indexDestinyState;
	@FXML
	private TextField indexCurrentStateToLink;
	@FXML
	private Label partitionOne;


	//************************************************************************************************************************************************

	private MealyMachine<String, String> a;
	private ArrayList<String> allOutputs;
	private String[] symbolsInput;
	private String setStates;
	public int counterSymbols;
	public int counterStates;
	private MealyAnalyzer mealyAnalizer, mooreAnalyzer;
	private String accessibleStatesOfTheMyMealyMachine;

	String[] symbolsOutput;
	GenericArray<MealyState<String, String>> mealyS;
	ArrayList<ArrayList<MealyState<String, String>>> arrayListdeArraylists;
	ArrayList<MealyState<String, String>> accessibleMealyStatesLis;

	//************************************************************************************************************************************************

	@FXML
	void createMealyStates(ActionEvent event) {
		mealyS = new GenericArray<MealyState<String, String>>(Integer.parseInt(statesAmount.getText()));
		loadSymbolsButton.setDisable(false);
		createMealyStatesButton.setDisable(true);
		symbolInput.setEditable(true);
	}

	@FXML
	void loadSymbols(ActionEvent event) {
		symbolsInput = symbolInput.getText().split(",");
		setStates = "";

		String setinSymbols = "";
		for (String in : symbolsInput) {

			if (in.equals(symbolsInput[symbolsInput.length - 1])) {
				setinSymbols += " " + in;
			} else {
				setinSymbols += " " + in + ",";
			}
		}

		alphInLabel.setText(alphInLabel.getText() + setinSymbols + "}");

		for (int i = 0; i < mealyS.length; i++) {
			mealyS.set(i, new MealyState<String, String>("q" + i, symbolsInput.length));
			if (i < mealyS.length - 1) {
				setStates += " q" + i + ",";
			} else {
				setStates += " q" + i;
			}

		}
		setStates += "}";
		alphInLabel.setVisible(true);

		symbolOutput.setEditable(true);
		symbolOutputButton.setDisable(false);
	}

	@FXML
	void symbolOutputButton(ActionEvent event) {
		symbolsOutput = symbolOutput.getText().split(",");

		linkPaneMealy.setBackground(
				new Background(new BackgroundFill(Color.rgb(255, 127, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		linkPaneMealy.setVisible(true);

		labelInfoLink.setText(labelInfoLink.getText() + setStates);

		currentInSymbolToLink.setText(symbolsInput[0]);
		setStates = "";

		String setoutSymbols = "";
		for (String out : symbolsOutput) {

			if (out.equals(symbolsInput[symbolsInput.length - 1])) {
				setoutSymbols += " " + out;
			} else {
				setoutSymbols += " " + out + ", ";
			}

		}

		alphOutLabel.setText(alphOutLabel.getText() + setoutSymbols + "}");
		currentInSymbolToLink.setText(symbolsInput[0]);
		alphOutLabel.setVisible(true);
	}

	@FXML
	void link(ActionEvent event) throws IOException {

		if (currentOutSymbolToLink.getText().equals("") || indexDestinyState.getText().equals("")) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Advertencia");
			alert.setHeaderText("Informacion incompleta");

		}
		if (counterStates + 1 == mealyS.length && counterSymbols + 1 == symbolsInput.length) {

			mealyS.get(counterStates).link(mealyS.get(Integer.parseInt(indexDestinyState.getText())),
					currentInSymbolToLink.getText(), currentOutSymbolToLink.getText());

			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Información");
			alert.setHeaderText("Transiciones completadas");
			alert.setContentText(
					"Transiciones guardadas. Continue para hallar el autómata conexo y el autómata mínimo equivalente");
			alert.showAndWait();

			GenericArray<String> myInputAlphabet = new GenericArray<String>(symbolsInput.length);
			for (int i = 0; i < symbolsInput.length; i++) {
				myInputAlphabet.set(i, symbolsInput[i]);
			}

			GenericArray<String> myOutputAlphabet = new GenericArray<String>(symbolsOutput.length);
			for (int i = 0; i < symbolsOutput.length; i++) {
				myOutputAlphabet.set(i, symbolsOutput[i]);
			}
			accessibleStatesOfTheMyMealyMachine = mealyS.get(0).setAccessible();
			a = new MealyMachine<>(mealyS.get(0), "Mealy Machine", myInputAlphabet, myOutputAlphabet);

			accessibleMealyStatesLis = new ArrayList<MealyState<String, String>>();

			for (int i = 0; i < mealyS.length; i++) {
				if (mealyS.get(i).isAccessible()) {
					accessibleMealyStatesLis.add(mealyS.get(i));
				}
			}

			allOutputs = new ArrayList<String>();

			for (int i = 0; i < accessibleMealyStatesLis.size(); i++) {

				System.out.println(accessibleMealyStatesLis.get(i).getMyOutputs().toString());
			}
			System.out.println(a.getInitialState().getMyOutputsArray()[0]);
			System.out.println(a.getInitialState().getMyOutputsArray()[1]);

			mealyAnalizer = new MealyAnalyzer(a, accessibleMealyStatesLis);

		} else {

			mealyS.get(counterStates).link(mealyS.get(Integer.parseInt(indexDestinyState.getText())),
					currentInSymbolToLink.getText(), currentOutSymbolToLink.getText());

			counterSymbols++;
			if (!(counterSymbols == symbolsInput.length)) {
				currentInSymbolToLink.setText(symbolsInput[counterSymbols]);
				currentOutSymbolToLink.setText("");
				indexDestinyState.setText("");
			}
			if (counterSymbols == symbolsInput.length) {
				counterStates++;
				indexCurrentStateToLink.setText("" + counterStates);
				counterSymbols = 0;
				currentInSymbolToLink.setText(symbolsInput[0]);
				currentOutSymbolToLink.setText("");
				indexDestinyState.setText("");

			}
		}
	}
}
