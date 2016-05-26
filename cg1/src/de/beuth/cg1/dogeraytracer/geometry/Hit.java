package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 22/05/16.
 * This Class referes to a Ray a Geometry Object and the intersection point between the both of them.
 */
public class Hit {

    /**
     * double value fot the intersection point
     */
    public final double t;
    /**
     * Ray object to calculate the intersection point
     */
    public final Ray ray;
    /**
     * Geometry object to be hit by the Ray
     */
    public final Geometry geo;

    /**
     * Constructor for the Hit Object
     * @param t
     * @param ray
     * @param geo
     */
    public Hit(double t,Ray ray,Geometry geo) {
        this.t = t;
        this.ray = ray;
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (ray != null ? !ray.equals(hit.ray) : hit.ray != null) return false;
        return geo != null ? geo.equals(hit.geo) : hit.geo == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                '}';
    }
}
