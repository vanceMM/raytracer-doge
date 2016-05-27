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

        if(!objects.isEmpty()) {
            for (Geometry object: objects) {
                double t = object.hit(ray).t;
                if (hit != null) {
                    if(t>0 && t < hit.t) {
                        hit = object.hit(ray);
                    } else {
                        continue;
                    }
                }

            }
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
