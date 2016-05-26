package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Sphere Objects in 3d Space
 */
public class Sphere extends Geometry {

    /**
     * the Center {@link Point3} on the Sphere
     */
    public final Point3 c;
    /**
     * the Radius of the Sphere
     */
    public final double r;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Sphere} with defined attributes.
     *
     * @param color the Color Value of a {@link Color}
     * @param c the Point3 Value of a {@link Point3}
     * @param r the double Value of Radius
     */
    public Sphere(final Color color, final Point3 c, final double r) {
        super(color);
        this.c = c;
        this.r = r;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Sphere Object
     * @return Hit Object which represents the Intersection between the Sphere and the given {@link Ray}.
     */
    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
