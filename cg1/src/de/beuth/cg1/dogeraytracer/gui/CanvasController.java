package de.beuth.cg1.dogeraytracer.gui;

import de.beuth.cg1.dogeraytracer.camera.*;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.*;
import de.beuth.cg1.dogeraytracer.light.*;
import de.beuth.cg1.dogeraytracer.material.*;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by valentin on 05/05/16.
 * FXML Controller Class for the CanvasViewer.
 */
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class CanvasController implements Initializable {


    @FXML
    private ImageView view;

    @FXML
    private Menu menu;

    private Raytracer raytracer;

    private World world;

    private Camera perspective;

    private Camera orthographic;

    /**
     * variable to store a bufferedImage Object
     */
    private BufferedImage bufferedImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
//        AxisAlignedBox box = new AxisAlignedBox(new Color(0,0,1),new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5));
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


// ################################################################################################################################################################
// ################################################################################################################################################################
// ################################################################################################################################################################


//        //      Demo 3    -   SingleColorMaterial
//        Plane plane = new Plane(new SingleColorMaterial(new Color(1,0,0)),new Point3(0,0,0),new Normal3(0,1,0));
//        Sphere sphere1 = new Sphere(new SingleColorMaterial(new Color(0,1,0)), new Point3(1,1,1), 0.5);
//        AxisAlignedBox box = new AxisAlignedBox(new SingleColorMaterial(new Color(0,0,1)),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//        Triangle triangle = new Triangle(new SingleColorMaterial(new Color(0,1,1)), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        objects.add(sphere1);
//        objects.add(box);
//        objects.add(triangle);
//        // light
//        ArrayList<Light> lightSources = new ArrayList<>();
//        lightSources.add(null);
//        // initialize world and cam
//        world = new World(objects,new Color(0,0,0), lightSources, new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(4,4,4),new Vector3(-1,-1,-1), new Vector3(0,1,0) ,Math.PI/4);
//    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

//        //      Demo 4    -   LambertMaterial
//        Plane plane = new Plane(new LambertMaterial(new Color(1,0,0)),new Point3(0,0,0),new Normal3(0,1,0));
//        Sphere sphere1 = new Sphere(new LambertMaterial(new Color(0,1,0)), new Point3(1,1,1), 0.5);
//        AxisAlignedBox box = new AxisAlignedBox(new LambertMaterial(new Color(0,0,1)),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//        Triangle triangle = new Triangle(new LambertMaterial(new Color(0,1,1)), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        objects.add(sphere1);
//        objects.add(box);
//        objects.add(triangle);
//        // light
//        PointLight sun = new PointLight(new Color(1,1,1), new Point3(4,4,4));
//        ArrayList<Light> lightSources = new ArrayList<>();
//        lightSources.add(sun);
//        // initialize world and cam
//        world = new World(objects,new Color(0,0,0), lightSources, new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(4,4,4),new Vector3(-1,-1,-1), new Vector3(0,1,0) ,Math.PI/4);
//    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        //      Demo 5      -   PhongMaterial
//    Plane plane = new Plane(new PhongMaterial(new Color(1,0,0),new Color(1,1,1),64),new Point3(0,0,0),new Normal3(0,1,0));
//    Sphere sphere1 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(1,1,1), 0.5);
//    AxisAlignedBox box = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//    Triangle triangle = new Triangle(new PhongMaterial(new Color(1,1,0), new Color(1,1,1), 64), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//    ArrayList<Geometry> objects = new ArrayList<>();
//    objects.add(plane);
//    objects.add(sphere1);
//    objects.add(box);
//    objects.add(triangle);
//    // light
//    PointLight sun = new PointLight(new Color(1,1,1), false, new Point3(4,4,4));
//    ArrayList<Light> lightSources = new ArrayList<>();
//    lightSources.add(sun);
//    // initialize world and cam
//    world = new World(objects,new Color(0,0,0), lightSources, new Color(0,0,0));
//    perspective = new PerspectiveCamera(new Point3(4,4,4),new Vector3(-1,-1,-1), new Vector3(0,1,0) ,Math.PI/4);
//}


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

//        //      Demo 6      -   DirectionLight
//        Plane plane = new Plane(new PhongMaterial(new Color(1,0,0),new Color(1,1,1),64),new Point3(0,0,0),new Normal3(0,1,0));
//        Sphere sphere1 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(1,1,1), 0.5);
//        AxisAlignedBox box = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//        Triangle triangle = new Triangle(new PhongMaterial(new Color(0,1,1), new Color(1,1,1), 64), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        objects.add(sphere1);
//        objects.add(box);
//        objects.add(triangle);
//        // light
//        DirectionalLight sun = new DirectionalLight(new Color(1,1,1), false, new Vector3(-1,-1,-1));
//        ArrayList<Light> lightSources = new ArrayList<>();
//        lightSources.add(sun);
//        // initialize world and cam
//        world = new World(objects,new Color(0,0,0), lightSources, new Color(0,0,0));
//        perspective = new PerspectiveCamera(new Point3(4,4,4),new Vector3(-1,-1,-1), new Vector3(0,1,0) ,Math.PI/4);
//    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

//    //      Demo 7      -   Spotlight
//    Plane plane = new Plane(new PhongMaterial(new Color(1,0,0),new Color(1,1,1),64),new Point3(0,0,0),new Normal3(0,1,0));
//    Sphere sphere1 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(1,1,1), 0.5);
//    AxisAlignedBox box = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//    Triangle triangle = new Triangle(new PhongMaterial(new Color(0,1,1), new Color(1,1,1), 64), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//    ArrayList<Geometry> objects = new ArrayList<>();
//    objects.add(plane);
//    objects.add(sphere1);
//    objects.add(box);
//    objects.add(triangle);
//    // light
//    SpotLight sun = new SpotLight(new Color(1,1,1),new Point3(4,4,4), false, new Vector3(-1,-1,-1), Math.PI/14);
//    ArrayList<Light> lightSources = new ArrayList<>();
//    lightSources.add(sun);
//    // initialize world and cam
//    world = new World(objects,new Color(0,0,0), lightSources, new Color(0,0,0));
//    perspective = new PerspectiveCamera(new Point3(4,4,4),new Vector3(-1,-1,-1), new Vector3(0,1,0) ,Math.PI/4);
//}

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------

        //      Demo 8      -   Spotlight + ambientLight
//        Plane plane = new Plane(new PhongMaterial(new Color(1,0,0),new Color(1,1,1),64),new Point3(0,0,0),new Normal3(0,1,0));
//        Sphere sphere1 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(1,1,1), 0.5);
//        AxisAlignedBox box = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//        Triangle triangle = new Triangle(new PhongMaterial(
//                new Color(0,1,1), new Color(1,1,1), 64), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        objects.add(sphere1);
//        objects.add(box);
//        objects.add(triangle);
//        // light
//        SpotLight sun = new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), false, new Vector3(-1, -1, -1), Math.PI / 14);
//        ArrayList<Light> lightSources = new ArrayList<>();
//        lightSources.add(sun);
//        // initialize world and cam
//        world = new World(objects, new Color(0, 0, 0), lightSources, new Color(0.25,0.25,0.25));
//        perspective = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);
//    }

        //    private void showDemo9(){
//                //      Demo 3    -   SingleColorMaterial
//        Plane plane = new Plane(new PhongMaterial(new Color(1,0,0),new Color(1,1,1),64),new Point3(0,0,0),new Normal3(0,1,0));
//        Sphere sphere1 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(1,1,1), 0.5);
//        AxisAlignedBox box = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-1.5,0.5,0.5), new Point3(-0.5,1.5,1.5));
//        Triangle triangle = new Triangle(new PhongMaterial(
//                new Color(0,1,1), new Color(1,1,1), 64), new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1));
//        AxisAlignedBox box2 = new AxisAlignedBox(new PhongMaterial(new Color(0,0,1), new Color(1,1,1), 64),new Point3(-3,0.5,0.5), new Point3(-2,1.5,1.5));
//        Sphere sphere2 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(1,1,1), 64), new Point3(2,2.5,1), 0.25);
//        ArrayList<Geometry> objects = new ArrayList<>();
//        objects.add(plane);
//        objects.add(sphere1);
//        objects.add(box);
//        objects.add(triangle);
//        objects.add(box2);
//        objects.add(sphere2);
//        // light
//        DirectionalLight dir = new DirectionalLight(new Color(1,1,1), false, new Vector3(-1,-1,-1));
//        SpotLight sun = new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), false, new Vector3(-1, -1, -1), Math.PI / 14);
//        ArrayList<Light> lightSources = new ArrayList<>();
//        lightSources.add(sun);
//
//        // initialize world and cam
//        //world = new World(objects, new Color(0, 0, 0), lightSources, new Color(0.25,0.25,0.25));
//        perspective = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);
//    }

        // --------------------------- D E M O -------------------------

        showDemo4_5();

    }


    // ------------------------------------- R E F L E C T I O N   D E M O   ----------------------------------------
    private void showDemo4_1() {

        Plane plane = new Plane(new ReflectiveMaterial(new Color(0.1,0.1,0.1), new Color(0,0,0), 64, new Color(0.5,0.5,0.5)), new Point3(0,0,0), new Normal3(0,1,0) );
        Sphere sphere1 = new Sphere(new ReflectiveMaterial(new Color(1,0,0), new Color(1,1,1), 64, new Color(0.5,0.5,0.5)), new Point3(-3,1,0), 1);
        Sphere sphere2 = new Sphere(new ReflectiveMaterial(new Color(0,1,0), new Color(1,1,1), 64, new Color(0.5,0.5,0.5)), new Point3(0,1,0), 1);
        Sphere sphere3 = new Sphere(new ReflectiveMaterial(new Color(0,0,1), new Color(1,1,1), 64, new Color(0.5,0.5,0.5)), new Point3(3,1,0), 1);
        AxisAlignedBox box1 = new AxisAlignedBox(new ReflectiveMaterial(new Color(0,1,1), new Color(1,1,1), 64, new Color(0.5, 0.5, 0.5)), new Point3(-3,0.5,1.5), new Point3(-2,1.5,2.5));


        ArrayList<Geometry> objects = new ArrayList<>();
        objects.add(plane);
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
        objects.add(box1);


        PointLight light = new PointLight(new Color(1,1,1), true, new Point3(8,8,8));
        DirectionalLight light2 = new DirectionalLight(new Color(1,1,1), false, new Vector3(0,1,1));

        ArrayList<Light> lightSources = new ArrayList<>();
        lightSources.add(light);
        //lightSources.add(light2);

        world = new World(objects,new Color(0,0,0), lightSources, new Color(0.25, 0.25, 0.25), 0);
        perspective = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4);

    }


    // ------------------------------------- S H A D O W   D E M O   ----------------------------------------
    private void showDemo4_2() {

        Plane plane = new Plane(new LambertMaterial(new Color(0.8,0.8,0.8)),new Point3(0,0,0), new Normal3(0,1,0) );
        AxisAlignedBox box = new AxisAlignedBox(new LambertMaterial(new Color(1,0,0)), new Point3(-0.5,0,-0.5), new Point3(0.5,1,0.5));
        Sphere sphere1 = new Sphere(new LambertMaterial(new Color(0,0,1)), new Point3(-3,1,0), 1);

        ArrayList<Geometry> objects = new ArrayList<>();
        objects.add(plane);
        objects.add(box);
        //objects.add(sphere1);


        PointLight light =  new PointLight(new Color(1,1,1), true, new Point3(8,8,0));

        ArrayList<Light> lightSources = new ArrayList<>();
        lightSources.add(light);

        world = new World(objects,new Color(0,0,0), lightSources, new Color(0.25, 0.25, 0.25), 0);
        perspective = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4);

    }


    // ------------------------------------------- F U L L   S C E N E ------------------------------------
    private void showDemo4_3() {

        Plane plane = new Plane(new LambertMaterial(new Color(0.8,0.8,0.8)),new Point3(0,0,0), new Normal3(0,1,0));
        Sphere sphere1 = new Sphere(new TransparentMaterial(1.0), new Point3(-3,1,0), 1);
        Sphere sphere2 = new Sphere(new TransparentMaterial(1.33), new Point3(0,1,0), 1);
        Sphere sphere3 = new Sphere(new TransparentMaterial(1.0), new Point3(3,1,0), 1);
        AxisAlignedBox box1 = new AxisAlignedBox(new TransparentMaterial(1.33), new Point3(-2,0.5,1.5), new Point3(-1,1.5,2.5));
        Sphere s4 = new Sphere(new ReflectiveMaterial(new Color(1,1,0),new Color(1,1,1), 64, new Color(0.5,0.5,0.5) ), new Point3(2,4,1), 1);
        Sphere s5 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(0,1,0), 64), new Point3(1,1,1), 0.5);

        ArrayList<Geometry> objects = new ArrayList<>();

        objects.add(plane);
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
        objects.add(box1);
        objects.add(s4);
        objects.add(s5);


        PointLight light = new PointLight(new Color(1,1,1), true, new Point3(2,8,8));

        ArrayList<Light> lightSources = new ArrayList<>();
        lightSources.add(light);

        world = new World(objects,new Color(0,0,0), lightSources, new Color(0.1, 0.1, 0.1), 1);
        perspective = new PerspectiveCamera(new Point3(6,6,6), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4);

    }


    // ------------------------------------- TRANSPARENT MAT  ----------------------------------------
    private void showDemo4_5() {

        Plane plane = new Plane(new LambertMaterial(new Color(0.8,0.8,0.8)),new Point3(0,0,0), new Normal3(0,1,0));
        Sphere sphere1 = new Sphere(new TransparentMaterial(1.5), new Point3(-3,1,0), 1);
        Sphere sphere2 = new Sphere(new TransparentMaterial(1.33), new Point3(0,1,0), 1);
        Sphere sphere3 = new Sphere(new TransparentMaterial(1.5), new Point3(3,1,0), 1);
        AxisAlignedBox box1 = new AxisAlignedBox(new TransparentMaterial(1.33), new Point3(-2,0.5,1.5), new Point3(-1,1.5,2.5));
        Sphere s4 = new Sphere(new ReflectiveMaterial(new Color(1,1,0),new Color(1,1,1), 64, new Color(0.5,0.5,0.5) ), new Point3(2,4,1), 1);
        Sphere s5 = new Sphere(new PhongMaterial(new Color(0,1,0), new Color(0,1,0), 64), new Point3(1,1,1), 0.5);

        ArrayList<Geometry> objects = new ArrayList<>();

        objects.add(plane);
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
        objects.add(box1);
        objects.add(s4);
        objects.add(s5);


        PointLight light = new PointLight(new Color(1,1,1), true, new Point3(2,8,8));

        ArrayList<Light> lightSources = new ArrayList<>();
        lightSources.add(light);

        world = new World(objects,new Color(0,0,0), lightSources, new Color(0.1, 0.1, 0.1), 1);
        perspective = new PerspectiveCamera(new Point3(6,6,6), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI / 4);

    }

    /**
     * Building the Menu and adding an eventHandler to the "save" MenuItem
     */
    private void initMenu() {

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
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for initiating the ImageView and creating the BufferedImage.
     * @param width the width of the shown Image, if NaN throw new {@link IllegalArgumentException}
     * @param height the height of the shown Image, if NaN throw new {@link IllegalArgumentException}
     */
    private void initImageView(final int width, final int height) {
        if (Double.isNaN(width) || Double.isNaN(height)) throw new IllegalArgumentException("Params width and height cant be NaN");
        bufferedImage = new BufferedImage(width, height, TYPE_INT_RGB);
        final WritableRaster raster = bufferedImage.getRaster();

        raytracer = new Raytracer(raster, world, perspective);
        //raytracer = new Raytracer(raster, world, orthographic);

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

        /*
         * Setting the image to the Image View
         */
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        view.setImage(wr);
    }

}
