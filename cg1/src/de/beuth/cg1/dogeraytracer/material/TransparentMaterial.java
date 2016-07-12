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

        if (hit == null || world == null || tracer == null) throw new IllegalArgumentException("params of method cant be null");

            final Point3 pointHit = hit.ray.at(hit.t);
            Color ambient = world.ambientLightColor;
            final Vector3 d = hit.ray.d.normalized();
            final Normal3 n = hit.normal;
            final double worldRefraction = world.indexOfRefraction;
            final double matRefraction = indexOfRefraction;
            final double rateRef = worldRefraction / matRefraction;


            for (final Light light : world.lightSources) {

                if (light.illuminates(pointHit, world)) {
                    final double cos1 = d.mul(-1.0).dot(n);
                    double cos2 = Math.sqrt((1.0 + (Math.pow(rateRef, 2.0)) - (1.0 - Math.pow(cos1, 2.0))));

                    final double R0 = Math.pow(((worldRefraction - matRefraction) / (worldRefraction + matRefraction)), 2.0);
                    final double R = R0 + ((1.0 - R0) * Math.pow((1.0 - Math.cos(cos1)), 5.0));
                    final double T = 1.0 - R;


                    if (cos2 > 0.0) {
                        cos2 = Math.sqrt((1.0 + (Math.pow(rateRef, 2.0)) - (1.0 - Math.pow(cos1, 2.0))));
                    }


                    final Vector3 rd = d.mul(-1.0).reflectOn(n).normalized();
                    final Vector3 t = d.mul(rateRef).sub(n.mul(cos2 - (rateRef * cos1))).normalized();
                    final Ray reflectedRay = new Ray(hit.ray.at(hit.t), rd);
                    final Ray refractoredRay = new Ray(hit.ray.at(hit.t), t);


                    ambient = (tracer.colorFor(reflectedRay).mulScalarColor(R)).addColor(tracer.colorFor(refractoredRay).mulScalarColor(T));
                }
            }
            return ambient;
    }
}

