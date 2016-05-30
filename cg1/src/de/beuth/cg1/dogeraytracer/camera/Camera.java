package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * @author doge
 * Project name is raytracer-doge.
 * This is Class representing Camera Objects in 3d Space
 */
@SuppressWarnings({"WeakerAccess", "SimplifiableIfStatement"})
public abstract class Camera {

    /**
     * e is the eye position of the Camera as a {@link Point3}
     */
	public final Point3 e;
    /**
     * g is the gaze direction of the camera as a {@link Vector3}
     */
	public final Vector3 g;
    /**
     * t is the up-vector of the camera as a {@link Vector3}
     */
	public final Vector3 t;

    /**
     * u,v,w are the local coordinate system to the camera
     * all axes have the value 1
     */
    /**
     * u is the normalized x-product of t and w
     */
	public final Vector3 u;
    /**
     * v is the normalized x-product of w and u
     */
	public final Vector3 v;
    /**
     * w is the normalized and inverted g {@link Vector3}
     */
	public final Vector3 w;

    /**
     * Constructor for the Camera Object
     * Creates a new instance of {@link Camera} with defined attributes.
     *
     * @param e the value of the e {@link Point3}
     * @param g the value of the g {@link Vector3}
     * @param t the value of the t {@link Vector3}
     */
	public Camera(final Point3 e, final Vector3 g, final Vector3 t){
		this.e = e;
		this.g = g;
		this.t = t;
		this.w = g.normalized().mul(-1.0);  // w = -g / |g|     value is the square root of g^2
        this.u = t.x(w).normalized();       // w = t X w / | t X w |     value is the square root of (t X w)^2
		this.v = w.x(u);                    // v = w X u
	}

    /**
     * This Method calculates a {@link Ray} for every pixel of the Image for a {@link Camera}
     *
     * @param w is the passed width of the Image as int
     * @param h is the passed height of the Image as int
     * @param x coordinate of the pixel of the Image as int
     * @param y coordinate of the pixel of the Image as int
     * @return {@link Ray} Object
     */
	public abstract Ray rayFor(final int w, final int h, final int x, final int y);

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Camera{" +
                "e=" + e +
                ", g=" + g +
                ", t=" + t +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (e != null ? !e.equals(camera.e) : camera.e != null) return false;
        if (g != null ? !g.equals(camera.g) : camera.g != null) return false;
        if (t != null ? !t.equals(camera.t) : camera.t != null) return false;
        if (u != null ? !u.equals(camera.u) : camera.u != null) return false;
        if (v != null ? !v.equals(camera.v) : camera.v != null) return false;
        return w != null ? w.equals(camera.w) : camera.w == null;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = e != null ? e.hashCode() : 0;
        result = 31 * result + (g != null ? g.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (u != null ? u.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (w != null ? w.hashCode() : 0);
        return result;
    }
}