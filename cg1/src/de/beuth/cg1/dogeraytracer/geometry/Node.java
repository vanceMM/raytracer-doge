package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.material.SingleColorMaterial;
import de.beuth.cg1.dogeraytracer.vecmatlib.Transform;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by Doge on 08/07/16.
 * Project name is raytracer-doge.
 * This Class creates a Node Object
 */
@SuppressWarnings("WeakerAccess")
public class Node extends Geometry {

    /**
     * The Transform Object
     */
    public final Transform transform;

    /**
     * A List of the Geometries
     */
    public final ArrayList<Geometry> geometries;

    /**
     * Constructor for the Node Object
     * Creates a new instance of Node with defined attributes.
     * @param transform value for the {@link Transform} Object, if null throw new {@link IllegalArgumentException}
     * @param geometries value for the {@link Geometry} List
     */
    public Node(Transform transform, ArrayList<Geometry> geometries) {
        super(new SingleColorMaterial(new Color(0,0,0)));
        if(transform == null) throw new IllegalArgumentException("Param transform cant be null");
        this.transform = transform;
        this.geometries = geometries;
    }

    /**
     * This method calculates the minimal {@link Hit} of a Node
     * @param ray passed {@link Ray} that hits the Object and shall be transformed, if null throw new {@link IllegalArgumentException}
     * @return minimal {@link Hit}
     */
    @Override
    public Hit hit(Ray ray) {
        if(ray == null) throw new IllegalArgumentException("Param ray cant be null");
        Ray transformed = transform.mul(ray);
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
        return new Hit(minHit.t, ray, minHit.geo, transform.mul(minHit.normal));
    }
}

