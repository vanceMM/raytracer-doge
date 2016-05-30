package de.beuth.cg1.dogeraytracer.raytracer;

import de.beuth.cg1.dogeraytracer.camera.Camera;
import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.world.World;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Created by valentin on 24/05/16.
 * This Class represents the Raytracer, where for every Pixel of the images's Raster a Ray is created and for which we
 * check if there is a Hit or not.
 * Project name is raytracer-doge.
 */
@SuppressWarnings({"WeakerAccess", "SimplifiableIfStatement"})
public class Raytracer {

    /**
     * Used for round-off errors
     */
    public static double DELTA = 0.00001;

    /**
     * A {@link WritableRaster} in which we can set the Pixel data.
     */
    public final WritableRaster raster;
    /**
     * A {@link World} which holds Geometry Objects and has a background color
     */
    public final World world;
    /**
     * A {@link Camera} which is the origin of the Rays
     */
    public final Camera camera;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Raytracer} with defined attributes.
     *
     * @param raster Value of a {@link WritableRaster} Object
     * @param world the Value of a {@link World} Object
     * @param camera the Value of a {@link Camera} Object
     */
    public Raytracer(final WritableRaster raster,  final World world, final Camera camera) {
        this.raster = raster;
        this.world = world;
        this.camera = camera;
    }

    /**
     * This Methods iterates over every pixel of an image. For every Pixel a {@link Ray} is created and in the {@link World} we check for
     * Hits with Objects. If there is a {@link Hit}, the Pixel Color is set as the {@link de.beuth.cg1.dogeraytracer.geometry.Geometry}'s Color which is hitten. Otherwise, the
     * Background-color of the world is set as Pixel Color.
     *
     * @param colorModel The {@link ColorModel} of the image which is created.
     */
    public void trace(final ColorModel colorModel) {
        if (colorModel == null) throw new IllegalArgumentException("Param colorModel can't be null");
        for (int x=0; x<raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                final  Ray ray = camera.rayFor(raster.getWidth(), raster.getHeight(), x,y);
                final Color color;
                Hit hit = this.world.hit(ray);
                if (hit!=null) {
                    color = hit.geo.color;
                } else {
                    color = world.backgroundColor;
                }
                /* workaround for transforming our own normalized Color Representation to a awt Color.
                 */
                java.awt.Color color1 = new java.awt.Color((int) color.r*255, (int) color.g*255,(int) color.b*255);
                raster.setDataElements(x,raster.getHeight()-y-1, colorModel.getDataElements(color1.getRGB(),null)); // get rid of mirroring
            }
        }
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Raytracer{" +
                "raster=" + raster +
                ", world=" + world +
                ", camera=" + camera +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Raytracer raytracer = (Raytracer) o;

        if (raster != null ? !raster.equals(raytracer.raster) : raytracer.raster != null) return false;
        if (world != null ? !world.equals(raytracer.world) : raytracer.world != null) return false;
        return camera != null ? camera.equals(raytracer.camera) : raytracer.camera == null;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = raster != null ? raster.hashCode() : 0;
        result = 31 * result + (world != null ? world.hashCode() : 0);
        result = 31 * result + (camera != null ? camera.hashCode() : 0);
        return result;
    }
}
