package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 23/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Plane Objects in 3d Space
 */
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
     * @param a the Point3 Value of a {@link Point3}
     * @param n the Normal3 Value of a {@link Normal3}
     */
    public Plane (final Color color, final Point3 a, final Normal3 n) {
        super(color);
        this.a = a;
        this.n = n;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object
     * @return Hit Object which represents the Intersection between the Plane and the given {@link Ray}.
     */
    @Override
    public Hit hit(Ray r) {

        double zähler = (n.dot(a.sub(r.o)));
        double nenner = (r.d).dot(n);

        double t = zähler/nenner;

        if (nenner != 0.0 && t < 0) {
            return  new Hit(t, r, this);
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
