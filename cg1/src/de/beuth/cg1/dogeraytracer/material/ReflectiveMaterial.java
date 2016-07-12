package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.light.Light;
import de.beuth.cg1.dogeraytracer.raytracer.Tracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;

import java.util.ArrayList;

/**
 * Created by baetschjunge on 15/06/16.
 * Project name is raytracer-doge.
 *
 * This class implements a perfect reflecting material
 */
@SuppressWarnings("WeakerAccess")
public class ReflectiveMaterial extends Material {

    /**
     * diffuse {@link Color} for the material
     */
    public final Color diffuse;
    /**
     * specular {@link Color} for the material
     */
    public final Color specular;
    /**
     * exponent of the specular light value
     */
    public final int exponent;
    /**
     * reflection {@link Color} for the material (?)
     */
    public final Color reflection;

    /**
     * Constructor for the ReflectiveMaterial Object
     * Creates a new instance of ReflectiveMaterial with defined attributes.
     *
     * @param diffuse value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     * @param specular value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     * @param exponent value for the exponent of specular
     * @param reflection value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        if (diffuse == null || specular == null || reflection == null) throw new IllegalArgumentException("params of constructor cant be null or NaN");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
    }

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit on the {@link Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */

    @SuppressWarnings("Duplicates")
    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        if (hit == null || world == null || tracer == null) throw new IllegalArgumentException("Param of method can't be null");

        final Normal3 n = hit.normal;
        final Point3 p = hit.ray.at(hit.t);
        Color ambient = world.ambientLightColor.mulColor(diffuse);

        ArrayList<Light> lights = world.lightSources;
        Vector3 e = hit.ray.d.mul(-1.0).normalized();
        final double phi = n.dot(hit.ray.d.mul(-1.0))*2.0;

        for (Light light : lights) {
           if (light.illuminates( p, world )){

               final Vector3 l = light.directionFrom(p);
               final Vector3 r = l.reflectOn(hit.normal);

               double max = Math.max(0.0, l.dot(n));
               double max2 = Math.pow((Math.max(0.0, r.dot(e))), this.exponent);

               Color lightColor = light.color;
               ambient = ambient
                       .addColor(this.diffuse.mulColor(lightColor).mulScalarColor(max))
                       .addColor(this.specular.mulColor(lightColor).mulScalarColor(max2));
           }
        }
        Color reflected = tracer.colorFor(new Ray(p, hit.ray.d.add(hit.normal.mul(phi))));
        return ambient.addColor(this.reflection.mulColor(reflected));
    }

}
