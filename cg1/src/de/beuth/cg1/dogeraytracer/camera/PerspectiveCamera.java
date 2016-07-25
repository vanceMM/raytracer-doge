package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.raytracer.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * 
 * @author doge
 * Project name is raytracer-doge.
 * This is Class representing PerspectiveCamera Objects in 3d Space
 */
@SuppressWarnings("WeakerAccess")
public class PerspectiveCamera extends Camera{

	/**
	 * angle is the angle of the {@link Camera} with a double value
	 */
	public final double angle;

	/**
	 * Constructor for the PerspectiveCamera Object
	 * Creates a new instance of {@link PerspectiveCamera} with defined attributes.
	 *
	 * @param e value of the {@link Camera} position as a {@link Point3}
	 * @param g value of the {@link Camera} gaze as a {@link Vector3}
	 * @param t value of the {@link Camera} of the up-Vector as a {@link Vector3}
     * @param angle value of the {@link Camera} angle as a double, if NaN throw new {@link IllegalArgumentException}
     */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle){
		super(e, g, t);
        if (Double.isNaN(angle)) throw new IllegalArgumentException("Param angle can't be NaN");
		this.angle = angle;
	}

	/**
	 * This Method calculates a {@link Ray} for every pixel of the Image for a {@link PerspectiveCamera}
	 *
     * @param w is the passed width of the Image as int, if NaN throw new {@link IllegalArgumentException}
     * @param h is the passed height of the Image as int, if NaN throw new {@link IllegalArgumentException}
     * @param x coordinate of the pixel of the Image as int, if NaN throw new {@link IllegalArgumentException}
     * @param y coordinate of the pixel of the Image as int, if NaN throw new {@link IllegalArgumentException}
     * @return {@link Ray} Object
     */
	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
        if (Double.isNaN(w) || Double.isNaN(h) || Double.isNaN(x) || Double.isNaN(y)) throw new IllegalArgumentException("Params can't be NaN");
		// vec(r) = -vec(w)*(h/2)/tan(alpha)+(px-(w-1)/2)*vec(u)+(py-(h-1)/2)*vec(v)

		final Vector3 px = u.mul(x-((w-1)/2));													//    u*(pz-(w-1/2))
		final Vector3 py = v.mul(y-((h-1)/2));													//    v*(py-(h-1/2))
		final Vector3 r = this.w.mul(-1).mul((h/2)/(Math.tan(angle/2.0))).add(px).add(py);		//    r = w*(-((h/2)/tan(alpha))) + o
		final Vector3 d = r.normalized();														//    d = r/|r|

		return new Ray(e, d);
	}

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "PerspectiveCamera{" +
                "angle=" + angle +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PerspectiveCamera that = (PerspectiveCamera) o;

        return Double.compare(that.angle, angle) == 0;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}