package de.beuth.cg1.dogeraytracer.world;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by valentin on 23/05/16.
 * Project name is raytracer-doge.
 * This is Class representing World Object in 3d Space
 */
@SuppressWarnings("WeakerAccess")
public class World {

    /**
     * objects is an ArrayList of all {@link Geometry}'s in the World
     */
    public final ArrayList<Geometry> objects;
    /**
     * backgroundColor is the {@link Color} of the World
     */
    public  final Color backgroundColor;

    /**
     * Constructor for the World Object
     * Creates a new instance of {@link World} with defined attributes.
     *
     * @param objects the ArrayList with the {@link Geometry} Objects
     * @param backgroundColor value for the {@link Color} of the World
     */
    public World(ArrayList<Geometry> objects, final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = objects;
    }

    /**
     * this method calculates the Hits of the {@link Ray}'s for the {@link Geometry} Objects
     *
     * @param ray that is passed to the Hit method, if ray is null throw new {@link IllegalArgumentException}
     * @return Hit Object
     */
    public Hit hit(final Ray ray) {
        if (ray == null) throw new IllegalArgumentException("Param ray can't be null");
        Hit hit = null;
        ArrayList<Hit> hits = new ArrayList<>();

        for (Geometry object: objects) {
            hit = object.hit(ray);
                if (hit != null) {
                    hits.add(hit);
                }
            }

        double min = Double.MAX_VALUE;
        for ( Hit h : hits) {
            if (h.t < min) {
                min = h.t;
            }
            hit = h;
        }
        return hit;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "World{" +
                "objects=" + objects +
                ", backgroundColor=" + backgroundColor +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        return objects != null ? objects.equals(world.objects) : world.objects == null && (backgroundColor != null ? backgroundColor.equals(world.backgroundColor) : world.backgroundColor == null);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = objects != null ? objects.hashCode() : 0;
        result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
        return result;
    }
}
