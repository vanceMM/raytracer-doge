package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * Created by valentin on 22/05/16.
 * This is an abstract Class representing gemotry Objects in 3d Space
 */
public abstract class Geometry {

    public final Color color;

    /**
     * Constructor for the Geometry Object
     * @param color
     */
    public Geometry(Color color) {

        this.color = color;

    }

    /**
     * This Methods takes an Ray as inputs and calculates the intersection between the Ray and the Geometry Object.
     * @param r Ray that hits the Object
     * @return returns a Hit Object which references to a Ray an the intersection point t where the object is hit by the
     * Ray.
     */
    public abstract Hit hit(Ray r);
}
