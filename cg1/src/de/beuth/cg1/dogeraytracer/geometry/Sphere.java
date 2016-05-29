package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Sphere Objects in 3d Space
 */

@SuppressWarnings("WeakerAccess")
public class Sphere extends Geometry {

    /**
     * the Center {@link Point3} on the Sphere
     */
    public final Point3 c;
    /**
     * the Radius of the Sphere
     */
    public final double radius;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Sphere} with defined attributes.
     *
     * @param color the Color Value of a {@link Color}
     * @param c the Point3 Value of a {@link Point3}
     * @param radius the double Value of Radius
     */
    public Sphere(final Color color, final Point3 c, final double radius) {
        super(color);
        this.c = c;
        this.radius = radius;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Sphere Object
     * @return Hit Object which represents the Intersection between the Sphere and the given {@link Ray}.
     */
    @Override
    public Hit hit(Ray r) {
        double a;
        a = r.d.dot(r.d);
        double b;
        b = r.d.dot((r.o.sub(c)).mul(2));
        double cc;
        // ( ⃗o − ⃗c ) • ( ⃗o − ⃗c ) − r 2
        cc = r.o.sub(c).dot(r.o.sub(c)) - Math.pow(radius,2);

        double d;
        d = Math.pow(b,2) - 4 * a *cc;

        double t, t2;

        //if (d < 0) return null;
        if (d == 0) {
            t = (-1)*b + Math.sqrt(d) / 2*a;
            return new Hit(t, r, this);
        }
        if (d > 0) {
            t = (-1)*b + Math.sqrt(d) / 2*a;
            t2 = (-1)*b - Math.sqrt(d) / 2*a;

            if (t < t2) return new Hit(t, r, this);
            else return new Hit(t2, r, this);
        }
        else return null;
    }


    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "c=" + c +
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

        return Double.compare(sphere.radius, radius) == 0 && (c != null ? c.equals(sphere.c) : sphere.c == null);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = c != null ? c.hashCode() : 0;
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
