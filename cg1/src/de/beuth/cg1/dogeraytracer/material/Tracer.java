package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by valentin on 26/06/16.
 *
 * This class represents an Object which handles recursive Raytracing
 */
@SuppressWarnings("WeakerAccess")
public class Tracer {

    /**
     * the actual world
     */
    public final World world;

    /**
     * the maximal depth of recursion
     */
    public final int depth;

    /**
     * Constructor for the Tracer Object
     * Creates a new instance of Tracer with defined attributes.
     *
     * @param world the given {@link World} Object, if null throw new {@link IllegalArgumentException}
     * @param depth the given maximal depth of recursion
     */
    public Tracer(final World world, final int depth) {
        super();
        if(world == null) throw new IllegalArgumentException("world cant be null");
        this.world = world;
        this.depth = depth;
    }

    /**
     * gets {@link Color} of the Geometries {@link Material}
     *
     * @param ray that is passed, if null throw new {@link IllegalArgumentException}
     * @return Color of the Geometries Material
     */
    public Color colorFor (final Ray ray) {

        if (ray == null) throw new IllegalArgumentException("ray can't be null");
        /*
        Hit hit = world.hit(ray);
        if (hit != null) {
            return hit.geo.material.colorFor(hit, world, new Tracer(this.world, depth));
        } else {
            return world.backgroundColor;
        }
        */
        if(depth <= 0){
            return world.backgroundColor;
        } else {
            Hit hit = world.hit(ray);
            if (hit != null) {
                return hit.geo.material.colorFor(hit, world, new Tracer(this.world, depth-1));
            } else {
                return world.backgroundColor;
            }
        }
    }
}
