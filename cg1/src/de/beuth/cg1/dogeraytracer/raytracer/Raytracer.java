package de.beuth.cg1.dogeraytracer.raytracer;

import de.beuth.cg1.dogeraytracer.camera.Camera;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.world.World;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by valentin on 24/05/16.

 * This Class represents the Raytracer, where for every Pixel of the images's Raster a Ray is created and for which we
 * check if there is a Hit or not.
 * Project name is raytracer-doge.

 */
public class Raytracer {

    /**
     * A Writable raster in which we can set the Pixel data.
     */
    public final WritableRaster raster;

    /**
     * A world which holds Geometry Objects and has a background color
     */
    public final World world;

    /**
     * A Camera which is the origin of the Rays
     */
    public final Camera camera;

    /**
     *
     * @param raster a writable Raster
     * @param world a world object
     * @param camera an instance of a camera(eg. perspective)
     */
    public Raytracer( WritableRaster raster,  World world, Camera camera) {
        this.raster = raster;
        this.world = world;
        this.camera = camera;
    }

    /**
     * This Methods iterates over every pixel of an image. For every Pixel a ray is created and in the world we check for
     * Hits with Objects. If there is a Hit, the Pixel Color is set as the Geometry's Color which is hitten. Otherwise, the
     * backgroundcolor of the world is set as Pixel Color.
     * @param colorModel The ColorModel of the image which is created.
     */
    public void trace(ColorModel colorModel) {
        for (int x=0; x<raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                final  Ray ray = camera.rayFor(raster.getWidth(), raster.getHeight(), x,y);
                final Color color;
                Hit hit = this.world.hit(ray);
                if (hit!=null) {
                    color = hit.geo.color;
                } else {
                    color = world.getBackgroundColor();
                }
                /* workaround for transforming our own normalized Color Representation to a awt Color.
                 */
                java.awt.Color color1 = new java.awt.Color((int) color.r*255, (int) color.g*255,(int) color.b*255);
                raster.setDataElements(x,raster.getHeight()-y, colorModel.getDataElements(color1.getRGB(),null));
            }
        }
    }
}
