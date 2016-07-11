package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Sphere Objects in 3d Space
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Sphere extends Geometry {

    /**
     * the Center {@link Point3} on the Sphere
     */
    public final Point3 center;
    /**
     * the Radius of the Sphere
     */
    public final double radius;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Sphere} with defined attributes.
     *
     * @param color the Color Value of a {@link Color}, if color null throw {@link IllegalArgumentException}
     * @param center the Point3 Value of a {@link Point3}
     * @param radius the double Value of Radius
     */
    public Sphere(final Material color) {
        super(color);

        this.center = new Point3(0,0,0);
        this.radius = 1;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param ray passed {@link Ray} that hits the Sphere Object, if ray null throw new {@link IllegalArgumentException}
     * @return Hit Object which represents the Intersection between the Sphere and the given {@link Ray}.
     */
    @Override
    public Hit hit(final Ray ray) {
        if (ray == null) throw new IllegalArgumentException("Param ray (ray) can't be null");
        double a, b, c, discremenant, t, t2;
        a = ray.d.dot(ray.d);                                                   // d⃗ • d⃗
        b = ray.d.dot((ray.o.sub(center)).mul(2.0));                              // d⃗ • [2(⃗o − ⃗c)]
        c = ray.o.sub(center).dot(ray.o.sub(center)) - Math.pow(radius,2.0);      // ( ⃗o − ⃗c ) • ( ⃗o − ⃗c ) − ray 2

        // if discriminant of t calculation is negative this will result in error in sqrt = no hit
        discremenant = Math.pow(b,2.0) - 4.0 * a *c;                                       // d = b^2 −4ac

        // intersectionNormal of the hit point3
        //Normal3 intersectionNormal = null;

        if (discremenant == 0) {
            t = ((-1.0)*b + Math.sqrt(discremenant)) / 2.0*a;
            if (t <= 0) return null;
            return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
        }
        if (discremenant > 0 ) {
            t = ((-1.0)*b + Math.sqrt(discremenant)) / 2.0*a;
            t2 = ((-1.0)*b - Math.sqrt(discremenant)) / 2.0*a;

            // get the nearest hit t
            if (t < t2) {
                if (t >= Raytracer.DELTA)
                    return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
            }
            else {
                if (t2 >= Raytracer.DELTA)
                    return new Hit(t2, ray, this, calcIntersectionNormal(ray, t2));
            }
        }
        return null;
    }

    private Normal3 calcIntersectionNormal(Ray r, double t){
        //return r.at(t).sub(center).normalized().asNormal();
        Point3 at = r.at(t);
        //Normal3 normal3 = new Normal3(at.x - center.x, at.y - center.y, at.z - center.z);
        //return normal3.mul(1);
        Normal3 normal3 = new Vector3(at.x - center.x, at.y - center.y, at.z - center.z).normalized().asNormal();
        return normal3;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", r=" + radius +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        return Double.compare(sphere.radius, radius) == 0 && (center != null ? center.equals(sphere.center) : sphere.center == null);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = center != null ? center.hashCode() : 0;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
