package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.light.Light;
import de.beuth.cg1.dogeraytracer.raytracer.Tracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Mat4x4;
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

@SuppressWarnings("WeakerAccess")
public class BlinnPhongMaterial extends Material{

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
    public BlinnPhongMaterial(final Color diffuse, final Color specular, final int exponent) {
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
    public Color colorFor(Hit hit, World world, final Tracer tracer) {
        if (hit == null || world == null)
            throw new IllegalArgumentException("Param hit or world can't be null ");
        
        final Normal3 n = hit.normal;
        final Point3 p = hit.ray.at(hit.t);

        Color ambient = world.ambientLightColor.mulColor(diffuse);
        ArrayList<Light> lights = world.lightSources;

        Vector3 e = hit.ray.d.normalized();


        for (Light light : lights) {
            if (light.illuminates(p, world)) {


                final Vector3 l = light.directionFrom(p).normalized();

                Vector3 halfVector = l.add(e).normalized();
                //System.out.println(halfVector);


                //final Vector3 r = l.reflectOn(hit.normal);
                double diffusePart = Math.max(0.0, l.dot(n));
                double specularPart = Math.pow((Math.max(0.0, n.dot(halfVector))), 2);
                Color lightColor = light.color;
                ambient = ambient
                        .addColor(this.diffuse.mulColor(lightColor).mulScalarColor(diffusePart))
                        .addColor(this.specular.mulColor(lightColor).mulScalarColor(specularPart));
            }

        }
        return ambient;
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
    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlinnPhongMaterial that = (BlinnPhongMaterial) o;

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
