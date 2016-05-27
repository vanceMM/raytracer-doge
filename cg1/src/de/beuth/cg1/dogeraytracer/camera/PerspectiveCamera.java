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

	@Override
	public Ray rayFor(int w, int h, int x, int y){
		final Vector3 px = u.mul(x-(w-1/2));
		final Vector3 py = v.mul(y-(h-1/2));
		final Point3 o = e.add(px).add(py);
		final Vector3 d = g.mul(-((h/2)/Math.tan(angle)));
		return new Ray(o, d);
	}
}