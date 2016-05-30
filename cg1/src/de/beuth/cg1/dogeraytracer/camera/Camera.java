package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * 
 * @author doge
 *
 */

public abstract class Camera {
	
	public final Point3 e;
	public final Vector3 g;
	public final Vector3 t;
	public final Vector3 u;
	public final Vector3 v;
	public final Vector3 w;

	public Camera(final Point3 e, final Vector3 g, final Vector3 t){
		this.e = e;
		this.g = g;
		this.t = t;
		// w = -g / |g|     value is the square root of g^2 
		//w = g.mul(1/Math.sqrt(g.dot(g))).mul(-1);
		this.w = g.normalized().mul(-1.0);
		// w = -t X w / | t X w |     value is the square root of (t X w)^2 
		//u = t.x(w).mul(1/Math.sqrt(t.x(w).dot(t.x(w))));
        this.u = t.x(w).normalized();
		// v = w X u
		this.v = w.x(u);

	}
	
	public abstract Ray rayFor(final int w, final int h, final int x, final int y);

}