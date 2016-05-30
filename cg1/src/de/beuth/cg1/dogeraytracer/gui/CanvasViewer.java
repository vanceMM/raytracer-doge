package de.beuth.cg1.dogeraytracer.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by valentin on 05/05/16.
 * Starting Class for the CanvasViewer.
 */
public class CanvasViewer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("canvasViewer.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
