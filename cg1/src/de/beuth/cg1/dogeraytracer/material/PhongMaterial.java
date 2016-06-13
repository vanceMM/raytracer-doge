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
 * This is a class representing a PhongMaterial Object which represents a perfect diffuse reflecting Geometry with a highlight
 */
public class PhongMaterial extends Material{

    /**
     * the {@link Color} of the PhongMaterial
     */
    public final Color diffuse;
    /**
     * the {@link Color} of the PhongMaterial
     */
    public final Color specular;
    /**
     * the exponent of the PhongMaterial
     */
    public final int exponent;

    /**
     * Constructor for the PhongMaterial Object
     * Creates a new instance of PhongMaterial with defined attributes.
     *
     * @param diffuse value for {@link Color} of the PhongMaterial, if null throw new {@link IllegalArgumentException}
     * @param specular value for {@link Color} of the PhongMaterial, if null throw new {@link IllegalArgumentException}
     * @param exponent value for exponent of the PhongMaterial, if null throw new {@link IllegalArgumentException}
     */
    public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
        if(diffuse == null || specular == null) throw new IllegalArgumentException("Param color of constructor can't be null");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
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
        if (hit == null || world == null)
            throw new IllegalArgumentException("Param color of constructor can't be null ");

        final Normal3 n = hit.normal;
        final Point3 p = hit.ray.at(hit.t);
        Color colorHit = world.ambientLightColor;
        ArrayList<Light> lights = world.lightSources;
        hit.ray.d.mul(-1).normalized();
        for (Light light : lights) {
            Vector3 l = light.directionFrom(p).normalized();
            Color color = new Color(0, 0, 0);
            Vector3 r = l.reflectOn(n);
            if (light.illuminates(p)) {
                color = color.addColor(light.color).mulColor(this.diffuse).mulScalarColor(Math.max(0, n.dot(l))).addColor(light.color).mulColor(this.specular).mulScalarColor(Math.pow(Math.max(0, r.dot(l)),exponent));
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
        return "PhongMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhongMaterial that = (PhongMaterial) o;

        if (exponent != that.exponent) return false;
        if (!diffuse.equals(that.diffuse)) return false;
        return specular.equals(that.specular);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = diffuse.hashCode();
        result = 31 * result + specular.hashCode();
        result = 31 * result + exponent;
        return result;
    }
}
