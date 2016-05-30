package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * @author doge
 * Project name is raytracer-doge.
 * This is Class representing PerspectiveCamera Objects in 3d Space
 */

@SuppressWarnings("WeakerAccess")
public class OrthographicCamera extends Camera{

	/**
	 * s is the value the scale-factor of the {@link OrthographicCamera}
	 */
	public final double s;

	/**
	 * Constructor for the OrthographicCamera Object
	 * Creates a new instance of {@link OrthographicCamera} with defined attributes.
	 *
	 * @param e value of the {@link Camera} position as a {@link Point3}
	 * @param g value of the {@link Camera} gaze as a {@link Vector3}
	 * @param t value of the {@link Camera} of the up-Vector as a {@link Vector3}
     * @param s value of the scale-factor of the OrthographicCamera as double
     */
	public OrthographicCamera (final Point3 e, final Vector3 g, final Vector3 t, final double s){
		super(e, g, t);
		this.s = s;
	}

	/**
	 * This Method calculates a {@link Ray} for every pixel of the Image for an {@link OrthographicCamera}
	 *
	 * @param w is the passed width of the Image as int
	 * @param h is the passed height of the Image as int
	 * @param x coordinate of the pixel of the Image as int
	 * @param y coordinate of the pixel of the Image as int
     * @return {@link Ray} Object
     */
	@Override
	public Ray rayFor(int w, int h, int x, int y){

		final double a = (double) w / (double) h;			// width/height = aspect ratio
		final Vector3 d = this.w.mul(-1);					// direction = -width
		final Vector3 px = u.mul(a*s*(x-((w)/2))/(w-1));
		final Vector3 py = v.mul(s*(y-((h-1)/2))/(h-1));
		final Point3 o = e.add(px).add(py);

		return new Ray(o, d);		// Ray(final Point3 o, final Vector3 d)
	}

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "OrthographicCamera{" +
                "s=" + s +
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

        OrthographicCamera that = (OrthographicCamera) o;

        return Double.compare(that.s, s) == 0;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(s);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
