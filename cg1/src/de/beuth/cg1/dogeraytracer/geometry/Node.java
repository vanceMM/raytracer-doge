package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.junit.Transform;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by valentin on 08/07/16.
 */
public class Node extends Geometry {

    public final Transform transform;

    public final ArrayList<Geometry> geometries;

    public Node(Transform transform, ArrayList<Geometry> geometries) {
        this.transform = transform;
        this.geometries = geometries;

    }

    @Override
    public Hit hit(Ray r) {
        Ray transformed = transform.mul(r);
        Hit minHit = null;
        double t = Double.MAX_VALUE;
        for (Geometry g: geometries) {
            Hit hit = g.hit(transformed);
            if(hit.t< t) {
                minHit = hit;
            }
        }
        return new Hit(minHit.t, transformed, minHit.geo, transform.mul(minHit.normal));
    }
}
