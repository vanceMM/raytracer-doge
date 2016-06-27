package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by baetschjunge on 15/06/16.
 * Project name is raytracer-doge.
 *
 * This class implements a perfect transparent / refracting material
 */
public class TransparentMaterial extends Material {

    /**
     * index for the refraction of the material
     */
    public final double indexOfRefraction;

    /**
     * Constructor for the ReflectiveMaterial Object
     * Creates a new instance of ReflectiveMaterial with defined attributes.
     *
     * @param indexOfRefraction value for the refraction of the material, if NaN throw new {@link IllegalArgumentException}
     */
    public TransparentMaterial(final double indexOfRefraction) {
        if (Double.isNaN(indexOfRefraction)) throw new IllegalArgumentException("params of constructor cant be null");
        this.indexOfRefraction = indexOfRefraction;
    }

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit   on the {@link Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */
    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        // Normale der Oberfläche
        final Normal3 n = hit.normal;
        // Reflektierter Vektor d⃗
        final Vector3 rd = hit.ray.d.mul(-1).reflectOn(n);
        // Winkel = (-d⃗)°n⃗
        final double angle1 = n.dot(rd);
        // Winkel2 = sqrt(1-(n1/n2)^2*(1-cos^2*angle1))
        final double angle2 = Math.sqrt(1-(Math.pow(world.indexOfRefraction/indexOfRefraction, 2)*(1-Math.pow(angle1,2))));
        // t⃗ = n⃗*(n1/n2*d⃗-(cos(angle2)-(n1/n2)cos(angle1)))
        final Vector3 t = hit.ray.d.mul(world.indexOfRefraction/indexOfRefraction).sub(n.mul(angle2-((world.indexOfRefraction/indexOfRefraction)*angle1)));

        // R0 = (n1-n2/n1+n2)^2
        final double R0 = Math.pow((world.indexOfRefraction-indexOfRefraction)/(world.indexOfRefraction+indexOfRefraction),2);
        // R = Anteil der Reflexion
        // R = R0+(1-R0)(1-cos(angle1))^5
        final double R = R0+(1-R0)*Math.pow(1-angle1,5);
        // T = Anteil der Transmission
        // T = 1-R
        final double T = 1-R;

        // Transparentes Material Gleichung (Uebungsblatt)
        return tracer.colorFor(new Ray(hit.ray.at(hit.t - Raytracer.DELTA), rd)).mulScalarColor(R).addColor(tracer.colorFor(new Ray(hit.ray.at(hit.t + Raytracer.DELTA), t)).mulScalarColor(T));
    }
}

