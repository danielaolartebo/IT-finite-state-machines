package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @SuppressWarnings("FieldMayBeFinal")
    FXController fxGUI;

    private static final int COUNT_LIMIT = 1000;

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
        primaryStage.setTitle("Renting Car");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}