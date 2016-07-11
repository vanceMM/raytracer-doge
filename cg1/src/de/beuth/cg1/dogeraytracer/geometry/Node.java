package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.material.SingleColorMaterial;
import de.beuth.cg1.dogeraytracer.vecmatlib.Transform;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by valentin on 08/07/16.
 */
public class Node extends Geometry {

    public final Transform transform;

    public final ArrayList<Geometry> geometries;

    public Node(Transform transform, ArrayList<Geometry> geometries) {
        super(new SingleColorMaterial(new Color(0,0,0)));
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
            if (hit == null) continue;
            if(hit.t< t) {
                t= hit.t;
                minHit = hit;
            }
        }
        if( minHit == null ) return null;
        return new Hit(minHit.t, r, minHit.geo, transform.mul(minHit.normal));
    }
}
