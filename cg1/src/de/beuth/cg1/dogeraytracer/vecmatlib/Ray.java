package de.beuth.cg1.dogeraytracer.vecmatlib;


/**
 * 
 * @author doge
 *
 */

public class Ray {

	public final Point3 o;
	public final Vector3 d;
	
	public Ray(final Point3 o, final Vector3 d){
		this.o = o;
		this.d = d;
	}
	
	public Point3 at(final double t) {
		// ray = origin + (direction*distance)
		// return the point on the ray
		return o.add(d.mul(t));
	}
	
	public double tOf(final Point3 p){
		// distance = origin - point
		// return the distance between origin and point
		return d.dot(o.sub(p));
	}


}
