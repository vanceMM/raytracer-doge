package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * Created by valentin on 22/05/16.
 * Project name is raytracer-doge.
 * This is an abstract Class representing Geometry Objects in 3d Space
 */
public abstract class Geometry {

    /**
     * the {@link Color} of a Geometry
     */
    public final Color color;

    /**
     * Constructor for the Geometry Object
     *
     * @param color the Value of the {@link Color}, if color is null throw new {@link IllegalArgumentException}
     */
    public Geometry(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color of constructor can't be null");
        this.color = color;
    }

    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object
     * @return Hit Object which references to a {@link Ray} an the intersection point t where the object is hit by the {@link Ray}.
     */
    public abstract Hit hit(final Ray r);

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Geometry{" +
                "color=" + color +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return color.equals(geometry.color);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return color.hashCode();
    }
}