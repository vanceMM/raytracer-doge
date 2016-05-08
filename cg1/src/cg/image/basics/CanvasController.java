package cg.image.basics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by valentin on 05/05/16.
 */
public class CanvasController implements Initializable {


    @FXML
    private ImageView view;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu;

    @FXML
            private MenuItem save;

    BufferedImage bufferedImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



       // initImageView();
        initMenu();
        initImageView();
    }

    public void initMenu() {

        menu.getItems().add(new MenuItem("save"));
        menu.getItems().get(0).setOnAction(e-> save());
    }

    private void save() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Image");
        File selection = chooser.showSaveDialog(null);
        try {
            ImageIO.write(bufferedImage, "jpeg", selection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initImageView() {

        bufferedImage = new BufferedImage(640, 480, TYPE_INT_RGB);
        final WritableRaster raster = bufferedImage.getRaster();

        for (int i=0; i<bufferedImage.getWidth(); i++) {
            for (int j=0; j<bufferedImage.getHeight(); j++) {
                bufferedImage.setRGB(i,j,0x000000);
            }
        }

        WritableImage wr = null;
        if (bufferedImage != null) {
            wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pw.setArgb(x,y, bufferedImage.getRGB(x, y));
                }
            }
        }

        view.setPreserveRatio(true);
        view.setImage(wr);
    }

}
