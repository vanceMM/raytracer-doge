package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Triangle Objects in 3d Space
 */
public class Triangle extends Geometry{

    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 a;
    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 b;
    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 c;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Triangle} with defined attributes.
     *
     * @param color the Value of the {@link Color}
     * @param a the Value of a {@link Point3}
     * @param b the Value of a {@link Point3}
     * @param c the Value of a {@link Point3}
     */
    public Triangle(final Color color, final Point3 a, final Point3 b, final Point3 c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object
     * @return Hit Object which represents the Intersection between the Triangle and the given {@link Ray}.
     */
    @Override
    public Hit hit(Ray r) {
        return null;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return c != null ? c.equals(triangle.c) : triangle.c == null;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
