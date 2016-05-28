package de.beuth.cg1.dogeraytracer.world;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by valentin on 23/05/16.
 */
public class World {

    public final ArrayList<Geometry> objects;

    public  final Color backgroundColor;

    public World(ArrayList<Geometry> objects, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = objects;
    }

    public Hit hit(Ray ray) {
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
     *
     * @return returns the BackgroundColor for this Scene
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
