package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

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
    public Sphere(final Material color, final Point3 center, final double radius) {
        super(color);
        if (center == null || Double.isNaN(radius)) throw new IllegalArgumentException("Param of constructor can't be null or NaN");
        this.center = center;
        this.radius = radius;
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
        double a, b, c, dis, t, t2;
        a = ray.d.dot(ray.d);                                                   // d⃗ • d⃗
        b = ray.d.dot((ray.o.sub(center)).mul(2));                              // d⃗ • [2(⃗o − ⃗c)]
        c = ray.o.sub(center).dot(ray.o.sub(center)) - Math.pow(radius,2);      // ( ⃗o − ⃗c ) • ( ⃗o − ⃗c ) − ray 2

        // if discriminant of t calculation is negative this will result in error in sqrt = no hit
        dis = Math.pow(b,2) - 4 * a *c;                                       // d = b^2 −4ac

        // intersectionNormal of the hit point3
        //Normal3 intersectionNormal = null;

        if (dis == 0) {
            t = (-1)*b + Math.sqrt(dis) / 2*a;
            if (t <= 0) return null;
            return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
        }
        if (dis > 0 ) {
            t = (-1)*b + Math.sqrt(dis) / 2*a;
            t2 = (-1)*b - Math.sqrt(dis) / 2*a;

            // get the nearest hit t
            if (t < t2) {
                if (t >= 0)
                    return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
            }
            else {
                if (t2 >= 0)
                    return new Hit(t2, ray, this, calcIntersectionNormal(ray, t2));
            }
        }
        return null;
    }

    private Normal3 calcIntersectionNormal(Ray r, double t){
        //return r.at(t).sub(center).normalized().asNormal();
        return new Normal3(r.at(t).x - center.x, r.at(t).y - center.y, r.at(t).z - center.z);
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
