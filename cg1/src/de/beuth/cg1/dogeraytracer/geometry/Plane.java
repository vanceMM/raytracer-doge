package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 23/05/16.
 */
public class Plane extends Geometry {

    /**
     * a known Point on the Plane
     */
    public final Point3 a;
    /**
     * a normal to the Plane
     */
    public final Normal3 n;

    /**
     * Constructor of the Plane
     * @param a
     * @param n
     */
    public Plane (Color color, final Point3 a, final Normal3 n) {
        super(color);
        this.a = a;
        this.n = n;
    }

    /**
     *
     * @param r Ray that hits the Object
     * @return returns the Hit Object which represents the Intersection between the Plane and the given Ray.
     */
    @Override
    public Hit hit(Ray r) {
        return new Hit((n.dot(a.sub(r.o)))/(r.d).dot(n), r, this);
    }
}
