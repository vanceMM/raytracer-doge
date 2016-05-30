package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by valentin on 22/05/16.
 * This Class referes to a Ray a Geometry Object and the intersection point between the both of them.
 */
@SuppressWarnings("WeakerAccess")
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
     * Creates a new instance of {@link Hit} with defined attributes.
     *
     * @param t double Value for the intersection Point, if NaN throw new {@link IllegalArgumentException}
     * @param ray the Ray Value for a {@link Ray}, if null throw new {@link IllegalArgumentException}
     * @param geo the Geometry Value for a {@link Geometry}, if null throw new {@link IllegalArgumentException}
     */
    public Hit(final double t, final Ray ray, final Geometry geo) {
        if (Double.isNaN(t) || ray == null || geo == null) throw new IllegalArgumentException("Params of constructor can't be null or NaN");
        this.t = t;
        this.ray = ray;
        this.geo = geo;
    }

    /**
     * @see Object#equals(Object)
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        return Double.compare(hit.t, t) == 0 && (ray != null ? ray.equals(hit.ray) : hit.ray == null && (geo != null ? geo.equals(hit.geo) : hit.geo == null));

    }

    /**
     * @see Object#hashCode()
     *
     */
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

    /**
     * @see Object#toString()
     *
     */
    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                '}';
    }
}
