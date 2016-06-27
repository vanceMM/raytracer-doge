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
public class Tracer {

    /**
     * the actual world
     */
    public final World world;

    public Tracer(final World world) {
        super();
        this.world = world;
    }


    public Color colorFor (final Ray ray) {

        Hit hit = world.hit(ray);
        if (hit != null) {
            return hit.geo.material.colorFor(hit, world, new Tracer(this.world));
        } else {
            return world.backgroundColor;
        }
    }
}
