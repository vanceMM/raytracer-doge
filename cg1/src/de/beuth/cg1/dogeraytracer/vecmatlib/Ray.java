package de.beuth.cg1.dogeraytracer.vecmatlib;


/**
 * @author doge
 * Project name is raytracer-doge.
 * This is Class representing Ray Objects
 */
public class Ray {

    /**
     * o is the starting position of the Ray as a {@link Point3}
     */
	public final Point3 o;
    /**
     * d is the direction of the Ray as a {@link Vector3}
     */
	public final Vector3 d;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Ray} with defined attributes.
     *
     * @param o value of the position of the Ray as {@link Point3}
     * @param d value of the direction of the Ray as {@link Vector3}
     */
	public Ray(final Point3 o, final Vector3 d){
		this.o = o;
		this.d = d;
	}

    /**
     * this method calculates the {@link Point3} that the Ray hits by Ray-direction and distance
     *
     * @param t distance
     * @return Point3 that is hit by the ray
     */
	public Point3 at(final double t) {
		// ray = origin + (direction*distance)
		// return the point on the ray
		return o.add(d.mul(t));
	}

    /**
     * this method calculates the distance between ray-origin and the hit point
     *
     * @param p the pixel that is hit by the Ray as a {@link Point3}, if p is null throw {@link IllegalArgumentException}
     * @return distance between ray-origin and hit point
     */
	public double tOf(final Point3 p){
		if (p == null) throw new IllegalArgumentException("Parameter p can't be null");
		return d.dot(o.sub(p));
	}

}
