package de.beuth.cg1.dogeraytracer.gui;

import de.beuth.cg1.dogeraytracer.camera.Camera;
import de.beuth.cg1.dogeraytracer.camera.OrthographicCamera;
import de.beuth.cg1.dogeraytracer.camera.PerspectiveCamera;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Plane;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by valentin on 05/05/16.
 * FXML Controller Class for the CanvasViewer.
 */
public class CanvasController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView view;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem save;

    private  Raytracer raytracer;

    private  World world;

    private Camera camera;

    /*
     * variable to store a bufferedImage Object
     */
    private BufferedImage bufferedImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
         * add event listeners for the width and height property of the Pane node
         */

        pane.widthProperty().addListener(e-> initImageView((int)pane.getWidth(), (int)pane.getHeight()));
        pane.heightProperty().addListener(e-> initImageView((int)pane.getWidth(), (int)pane.getHeight()));

        /*
         * init Layout
         */
        initMenu();
        initImageView(640, 480);

        /*
         set TestData for the RayTracer
         */
        initSetup();
    }

    private void initSetup() {

        Plane plane = new Plane(new Color(0,1,0),new Point3(0,-1,0),new Normal3(0,1,0));

        ArrayList<Geometry> objects = new ArrayList<>();
        objects.add(plane);
        Geometry[] geometry = (Geometry[]) objects.toArray();

        world = new World(geometry,new Color(0,0,0));

        camera = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0) ,Math.PI/4);

    }

    private void printWidth() {
        System.out.println(pane.getWidth());
    }

    /**
     * Building the Menu and adding an eventHandler to the "save" MenuItem
     */
    public void initMenu() {

        menu.getItems().add(new MenuItem("save"));
        menu.getItems().get(0).setOnAction(e-> save());
    }

    /**
     * This Method saves the Image File.
     */
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

    /**
     * Method for initiating the ImageView and creating the BufferedImage.
     * @param width the width of the shown Image
     * @param height the height of the shown Image
     */
    public void initImageView(int width, int height) {


        bufferedImage = new BufferedImage(width, height, TYPE_INT_RGB);
        final WritableRaster raster = bufferedImage.getRaster();

        raytracer = new Raytracer(raster, world, camera);

        raytracer.trace(bufferedImage.getColorModel());

        /*
         * Converting the BufferedImage to JavaFX Image.
         * WritableImage is direct SubClass of Image class in JavaFX and can be used as Image Object here.
         * Pixels are written with the setArgb method which stores pixel data for a color into the specefied coordinates
         * of the surface.
         */
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
        /**
         * Setting the image to the Image View
         */
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setImage(wr);

    }

}
