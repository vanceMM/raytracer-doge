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


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
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

        initMenu();
    }

    public void initMenu() {

        menuBar.getMenus().addAll(menu);
        menu.getItems().addAll(save);
    }

}
