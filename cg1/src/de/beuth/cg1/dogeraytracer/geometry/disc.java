package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Ray;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 24/07/16.
 * Project name is raytracer-doge.
 */
@SuppressWarnings("WeakerAccess")
public class Disc extends Geometry{


    /**
     * center of the disc
     */
    public final Point3 a;

    /**
     * radius of the disc
     */
    public final double radius;

    /**
     * normal of the disc
     */
    public final Normal3 n;

    /**
     * Constructor for the Geometry Object
     * @param material the Value of the {@link Material}, if material is null throw new {@link IllegalArgumentException}
     */
    public Disc(Material material) {
        super(material);
        this.a = new Point3(0,0,0);
        this.radius = 3.0;
        this.n = new Normal3(1,0,0);
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param ray passed {@link Ray} that hits the Object
     * @return Hit Object which references to a {@link Ray} an the intersection point t where the object is hit by the {@link Ray}.
     */
    @Override
    public Hit hit(Ray ray) {

        if (ray == null) throw new IllegalArgumentException("Param ray can't be null");

        double numerator = n.dot(a.sub(ray.o));
        double denominator = (ray.d).dot(n);

        double t = numerator/denominator;


        double r = Math.pow(this.radius, 2.0);
        double ir = Math.pow(this.radius/4, 2.0);

        if (denominator != 0.0 && t > Raytracer.DELTA) {
            Point3 p = ray.at(t);
            Vector3 pq = (p.sub(a));
            if (pq.dot(pq) <= r && pq.dot(pq) > ir) {

                return new Hit(t, ray, this, this.n);
            }
            else {
                return null;
            }
        }
        return null;

    }
}
