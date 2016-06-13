package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.light.Light;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;

import java.util.ArrayList;

/**
 * Created by User on 31.05.2016.
 * Project name is raytracer-doge.
 * This is a class representing a LambertMaterial Object which represents a perfect diffuse reflecting Geometry
 */
public class LambertMaterial extends Material{

    /**
     * the {@link Color} of the LambertMaterial
     */
    public final Color color;

    /**
     * Constructor for the LambertMaterial Object
     * Creates a new instance of LambertMaterial with defined attributes.
     *
     * @param color value for {@link Color} of the LambertMaterial, if null throw new {@link IllegalArgumentException}
     */
    public LambertMaterial(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color of constructor can't be null");
        this.color = color;
    }

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit   on the {@link Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */
    @Override
    public Color colorFor(Hit hit, World world) {
        if(hit == null || world == null) throw new IllegalArgumentException("Param color of constructor can't be null ");
        final Normal3 n = hit.normal;
        final Point3 p = hit.ray.at(hit.t);
        Color colorHit = world.ambientLightColor;
        ArrayList<Light> lights = world.lightSources;
        for (Light light: lights) {
           Vector3 l = light.directionFrom(p);
           Color color = new Color(0,0,0);
           if (light.illuminates(p)) {
               color = color.addColor(light.color).mulColor(this.color).mulScalarColor(Math.max(0, n.dot(l)));
           }
           colorHit = colorHit.addColor(color);
        }
        return colorHit;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "LambertMaterial{" +
                "color=" + color +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LambertMaterial that = (LambertMaterial) o;

        return color.equals(that.color);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return color.hashCode();
    }
}
