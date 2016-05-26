package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * 
 * @author doge
 *
 */

public class PerspectiveCamera extends Camera{
	
	private final double angle;
	
	public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle){
		super(e, g, t);
		this.angle = angle;
	}
	
	@Override
	public Ray rayFor(int w, int h, int x, int y){
		final Vector3 px = u.mul(x-(w/2));
		final Vector3 py = v.mul(y-(h/2));
		final Point3 o = e.add(px).add(py);
//		final Vector3 d = w.mul()           tan(alpha) = w*((height/2)/tan(alpha))+px+py  
		return new Ray(o, d);
	}
}
