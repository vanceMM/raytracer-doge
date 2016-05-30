package de.beuth.cg1.dogeraytracer.gui;

import de.beuth.cg1.dogeraytracer.camera.Camera;
import de.beuth.cg1.dogeraytracer.camera.OrthographicCamera;
import de.beuth.cg1.dogeraytracer.camera.PerspectiveCamera;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.*;
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

    private Camera perspective;

    private Camera orthographic;

    /*
     * variable to store a bufferedImage Object
     */
    private BufferedImage bufferedImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
         * add event listeners for the width and height property of the Pane node
         */

       /* pane.widthProperty().addListener(e-> initImageView((int)pane.getWidth(), (int)pane.getHeight()));
        pane.heightProperty().addListener(e-> initImageView((int)pane.getWidth(), (int)pane.getHeight()));*/

        /*
         * init Layout
         */
        initMenu();
        initSetup();
        initImageView(640, 480);

        /*
         set TestData for the RayTracer
         */
    }

    private void initSetup() {

        //-------------------- Tests -------------------//

        //      Abb. 5
//        Plane plane = new Plane(new Color(0,1,0),new Point3(0,-1,0),new Normal3(0,1,0));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        world = new World(objects,new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0) ,Math.PI/4);


        //      Abb. 6
//        Sphere sphere = new Sphere(new Color(1,0,0), new Point3(0,0,-3), 0.5);
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(sphere);
//        world = new World(objects,new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0) ,Math.PI/4);
//        //orthographic = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3);


        //      Abb. 7
//        AxisAlignedBox box = new AxisAlignedBox(new Color(1,0,1),new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(box);
//        world = new World(objects,new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(3,3,3),new Vector3(-3,-3,-3), new Vector3(0,1,0) ,Math.PI/4);
//        //orthographic = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3);

        //      Abb. 8
//        Triangle triangle = new Triangle(new Color(1,0,1), new Point3(-0.5,0.5,-3), new Point3(0.5,0.5,-3), new Point3(0.5,-0.5,-3));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(triangle);
//        world = new World(objects,new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0) ,Math.PI/4);
//        //orthographic = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3);


        //      Abb. 9
        Sphere sphere1 = new Sphere(new Color(1,0,0), new Point3(-1,0,-3), 0.5);
        Sphere sphere2 = new Sphere(new Color(1,0,0), new Point3(1,0,-6), 0.5);
        ArrayList<Geometry> objects = new ArrayList<>();
        objects.add(sphere1);
        objects.add(sphere2);
        world = new World(objects,new Color(0,0,0));
        //perspective = new PerspectiveCamera(new Point3(0,0,0),new Vector3(0,0,-1), new Vector3(0,1,0) ,Math.PI/4);
        //      Abb. 10
        orthographic = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3);

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

        //raytracer = new Raytracer(raster, world, perspective);
        raytracer = new Raytracer(raster, world, orthographic);

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
