package cg.image.basics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by valentin on 05/05/16.
 */
public class CanvasController implements Initializable {

    @FXML
    private Canvas imageCanvas;

    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MenuItem save = new MenuItem("Save");
        MenuItem close = new MenuItem("close");
        Menu file = new Menu("File");

        menuBar.getMenus().addAll(file);
        file.getItems().addAll(save, close);

        imageCanvas = new ImageCanvas(640, 480);
        imageCanvas.paint();


    }
}
