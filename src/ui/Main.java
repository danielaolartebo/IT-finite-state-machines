package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Machine;

import javax.crypto.Mac;
import java.io.IOException;

/**
 *
 * @author Daniela Olarte Borja A00368359
 * @author Gabriel Suarez Baron A00368589
 *
 */

public class Main extends Application {

    FXController fxGUI;

    public Main() throws IOException {
        fxGUI = new FXController();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI/initPane.fxml"));
        fxmlLoader.setController(fxGUI);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Finite State Machine");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}