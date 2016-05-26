package de.beuth.cg1.dogeraytracer.raytracer;

import de.beuth.cg1.dogeraytracer.camera.Camera;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.world.World;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by valentin on 24/05/16.
 */
public class Raytracer {

    public final BufferedImage image;

    public final World world;

    public final Camera camera;

    public Raytracer(final BufferedImage image, final World world,final Camera camera) {
        this.image = image;
        this.world = world;
        this.camera = camera;
    }

    public void trace() {
        final WritableRaster raster = image.getRaster();
        for (int i=0; i<image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Ray ray = camera.rayFor(image.getWidth(), image.getHeight(), i,j);
                if (world.hit(ray)!=null) {
                    /**
                     * @TODO
                     * set the pixel color to the color of the geometry if hit() returns a value, if not set to
                     * world.backgroundColor.
                     */

                }
            }
        }
    }
}
