package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Main extends Application {

	private FXController fsmc;

	public Main() {
		fsmc = new FXController();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start( Stage primaryStage) throws Exception {
		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("GUI/InitPane.fxml"));
		fxmll.setController(fsmc);
		Parent root = fxmll.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Finite State Machine");
		primaryStage.show();
	}
}