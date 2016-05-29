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
	public Ray rayFor(int w, int h, int x, int y){
		final Vector3 px = u.mul(x-((w-1)/2));
		final Vector3 py = v.mul(y-((h-1)/2));
		final Vector3 vecW = g.normalized().mul(-1.0);
		//final Point3 o = e.add(px).add(py);
		final Vector3 r = vecW.mul(-((h/2))/Math.tan(angle)).add(px).add(py);
		final Vector3 d = r.normalized();
		final Point3 o = e;
		//final Vector3 d = g.mul(-((h/2)/Math.tan(angle)));
		return new Ray(o, d);
	}
}