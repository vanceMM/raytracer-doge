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
 */
public class Raytracer {

    public final WritableRaster raster;

    public final World world;

    public final Camera camera;

    public Raytracer( WritableRaster raster,  World world, Camera camera) {
        this.raster = raster;
        this.world = world;
        this.camera = camera;
    }

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
                java.awt.Color color1 = new java.awt.Color((int) color.r*255, (int) color.g*255,(int) color.b*255);
                raster.setDataElements(x,y, colorModel.getDataElements(color1.getRGB(),null));
            }
        }
    }
}
