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
        return new Hit((n.dot(a.sub(r.o)))/(r.d).dot(n), r, this);
    }
}
