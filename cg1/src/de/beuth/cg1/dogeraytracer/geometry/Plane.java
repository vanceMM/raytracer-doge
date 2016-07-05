package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 23/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Plane Objects in 3d Space
 */

@SuppressWarnings({"WeakerAccess", "SimplifiableIfStatement"})
public class Plane extends Geometry {

    /**
     * a known {@link Point3} on the Plane
     */
    public final Point3 a;
    /**
     * a {@link Normal3} to the Plane
     */
    public final Normal3 n;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Plane} with defined attributes.
     *
     * @param color the Color Value of a {@link Color}
     * @param a the Point3 Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param n the Normal3 Value of a {@link Normal3}, if null throw new {@link IllegalArgumentException}
     */
    public Plane (final Material color, final Point3 a, final Normal3 n) {
        super(color);
        if (a == null || n == null) throw new IllegalArgumentException("Params of constructor can't be null");
        this.a = a;
        this.n = n;
    }

    /**
     * This Method takes a {@link Ray} as input and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object, if r null throw new {@link IllegalArgumentException}
     * @return Hit Object which represents the Intersection between the Plane and the given {@link Ray}.
     */
    @Override
    public Hit hit(final Ray r) {
        if (r == null) throw new IllegalArgumentException("Param r (ray) can't be null");

        double numerator = n.dot(a.sub(r.o));
        double denominator = (r.d).dot(n);

        double t = numerator/denominator;


        if (denominator != 0.0 && t > Raytracer.DELTA) {
            return new Hit(t, r, this, this.n);
        } else {
            return null;
        }

    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", n=" + n +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
        return n != null ? n.equals(plane.n) : plane.n == null;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
