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
@SuppressWarnings("Duplicates")
public class Cone extends Geometry {

    /**
     * center of the cone
     */
    public final Point3 center;

    /**
     * radius of the cone
     */
    public final double radius;
    /**
     * Constructor for the Geometry Object
     *
     * @param material the Value of the {@link Material}, if material is null throw new {@link IllegalArgumentException}
     */
    public Cone(Material material) {
        super(material);
        this.center = new Point3(0,0,0);
        this.radius = 1.0;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param ray passed {@link Ray} that hits the Object
     * @return Hit Object which references to a {@link Ray} an the intersection point t where the object is hit by the {@link Ray}.
     */
    @Override
    public Hit hit(Ray ray) {
        double a, b, c, d, t1, t2, z1, z2;
        //double z = 2.0;


        a = (ray.d.x * ray.d.x) + (ray.d.y * ray.d.y) - (ray.d.z * ray.d.z);
        b = (2.0 * (ray.o.x * ray.d.x)) + (2.0 * (ray.o.x * ray.d.y)) - (2.0 * (ray.o.z * ray.d.z));
        c = (ray.o.x * ray.o.x) + (ray.o.y * ray.o.y) - (ray.o.z * ray.d.z);

        d = (b * b) - (4.0 * a * c);

//        double zMin = -1.0;
        //       double zMax = 1.0;

        if (d > 0) {

            double t = Raytracer.DELTA;
            t1 = ((-1.0) * b + Math.sqrt(d)) / (2.0 * a);
            t2 = ((-1.0) * b - Math.sqrt(d)) / (2.0 * a);

            if (t1 < 0.0 && t2 < 0.0) return null;

            double tmin = Math.min(t1,t2);

            if (tmin > t){
                return new Hit(tmin, ray, this, calcIntersectionNormal(ray, tmin));
            }
        }

        return null;
    }


    /**
     * this method calculates the intersection {@link Normal3} for the hit point
     * @param ray that's passed
     * @param t double value for distance
     * @return Normal3 of the intersection Hit point
     */
    private Normal3 calcIntersectionNormal(Ray ray, double t){
        //return ray.at(t).sub(center).normalized().asNormal();
        Point3 at = ray.at(t);
        //Normal3 normal3 = new Normal3(at.x - center.x, at.y - center.y, at.z - center.z);
        //return normal3.mul(1);
        //Normal3 normal3 = new Vector3(at.x - center.x, at.y - center.y, at.z - center.z).normalized().asNormal();
        return new Vector3(at.x - center.x, at.y - center.y, at.z - center.z).normalized().asNormal();
    }
}
