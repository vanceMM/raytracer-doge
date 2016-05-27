package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * 
 * @author doge
 *
 */

public class OrthographicCamera extends Camera{
	
	public final double s;
	
	public OrthographicCamera (final Point3 e, final Vector3 g, final Vector3 t, final double s){
		super(e, g, t);
		this.s = s;
	}
	
	@Override
	public Ray rayFor(int w, int h, int x, int y){
		// width/height = aspect ratio
		final double a = (double) w / (double) h;
		// direction = -width
		final Vector3 d = this.w.mul(-1);
		
		final Vector3 px = u.mul(a*s*(x-((w)/2))/(w-1));
		
		final Vector3 py = v.mul(s*(y-((h-1)/2))/(h-1));
		
		final Point3 o = e.add(px).add(py);

		// Ray(final Point3 o, final Vector3 d)
		return new Ray(o, d);
		
	}
}
