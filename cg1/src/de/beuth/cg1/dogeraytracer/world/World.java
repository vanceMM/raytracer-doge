package de.beuth.cg1.dogeraytracer.world;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 23/05/16.
 */
public class World {

    public final Geometry[] objects;

    public final Color backgroundColor;

    public World(Geometry[] objects, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.objects = objects;
    }

    public Hit hit(Ray ray) {
        Hit hit;
        if(objects[0]!=null) {
            hit = objects[0].hit(ray);
        }
        for (int i = 1; i < objects.length; i++) {
            if(objects[i].hit(ray).t<hit.t){
                hit = objects[i].hit(ray);
            }

        }
    }

}
