package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * 
 * @author doge
 *
 */

public class PerspectiveCamera extends Camera{

	public final double angle;

	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle){
		super(e, g, t);
		this.angle = angle;
	}

	/**
	 *
	 * vec(r) = -vec(w)*(h/2)/tan(alpha)+(px-(w-1)/2)*vec(u)+(py-(h-1)/2)*vec(v)
	 *
	 * @param w
	 * @param h
	 * @param x
	 * @param y
     * @return
     */
	@Override
	public Ray rayFor(final int w1, final int h1, final int x1, final int y1) {

		double w = (double) w1;
		double h = (double) h1;
		double x = (double) x1;
		double y = (double) y1;

		Point3 o = e;

		Vector3 r = this.w.mul(-1).mul((h/2)/(Math.tan(angle/2))).add(u.mul(x-((w-1)/2))).add(v.mul(y-((h-1)/2)));
		Vector3 d = r.normalized();
		return new Ray(o,d);
	}
}