package cg.image.basics;

/**
 * Created by valentin on 03/05/16.
 */
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer extends Application {

    @Override
    public void start(Stage primaryStage) {

        /*
         * Opening a fileChooser Dialog
         */
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        // Create Image and ImageView objects
        BufferedImage buffereImage = null;
        try {
            buffereImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView();
        // Utility Method for converting data type Image from Swing/AWT to Image in JavaFX Format
        Image image = SwingFXUtils.toFXImage(buffereImage, null);
        imageView.setImage(image);

        // Display image on screen
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Image Read Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}