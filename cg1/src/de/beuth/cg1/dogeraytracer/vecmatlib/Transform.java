package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by valentin on 08/07/16.
 * This Class represents a transformation object. This Class creates the transformation matrices and it's inverses.
 * It has a public Constructor to create the unit matrix and it's inverse which are equal at point of initialisation.
 * The private constructor is only called from within the Classes methods. It is therefore  possible to chain different
 * transformations.
 */
public class Transform {
    /**
     * Transformation Matrix
     */
    public final Mat4x4 m;
    /**
     * Inverse of the transformation Matrix
     */
    public final Mat4x4 i;

    /**
     * Default constructor initiliazes the Transform Object Instance with the unit {@link Mat4x4}.
     */
    public Transform() {
        this.m = new Mat4x4(1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1);
        this.i = this.m;
    }

    /**
     *  This constructor is private and can only be called inside the Transform Class.
     * @param m Transformation Matrix
     * @param i Inverse of the Transform Matrix
     */
    private Transform(Mat4x4 m, Mat4x4 i) {
        this.m = m;
        this.i = i;
    }

    /**
     * This method does a translation. It creates a new {@link Transform} Object which is created with the transformation
     * {@link Mat4x4} and it's inverse.
     * @param p transformed Point
     * @return new Transform Object where the actual m and i are multiplied with the before created transformation object's
     * m and i.
     */
    public Transform translate(final Point3 p) {
        Transform t = new Transform(new Mat4x4( 1,0,0,p.x,
                                                0,1,0,p.y,
                                                0,0,1,p.z,
                                                0,0,0,1),
                                    new Mat4x4(1,0,0,-p.x,
                                                0,1,0,-p.y,
                                                0,0,1,-p.z,
                                                0,0,0,1));
        return new Transform(m.mul(t.m),t.i.mul(i)) ;
    }

    /**
     * This method does a scale transformation. It creates a new {@link Transform} Object which is created with the transformation
     * {@link Mat4x4} and it's inverse.
     * @param p transformed Point
     * @return new Transform Object where the actual m and i are multiplied with the before created transformation object's
     * m and i.
     */
    public Transform scale(final Point3 p) {
        Transform t = new Transform(new Mat4x4(p.x,0,0,0,0,p.y,0,0,0,0,p.z,0,0,0,0,1), new Mat4x4(1/p.x,0,0,0,0,1/p.y,0,0,0,0,1/p.z,0,0,0,0,1));
        return new Transform(m.mul(t.m),t.i.mul(i));
    }

    /**
     * This method does a rotation on the x-axis.
     * @param p
     * @return
     */
    public Transform rotateX(final Point3 p) {
        Transform t = new Transform(new Mat4x4(1,0,0,0,0,Math.cos(p.x), Math.sin(p.x)*(-1),0,0,Math.sin(p.x), Math.cos(p.x),0,0,0,0,1),
                new Mat4x4(1,0,0,0,0,Math.cos(p.x), Math.sin(p.x),0,0,Math.sin(p.x)*(-1), Math.cos(p.x),0,0,0,0,1));
        return new Transform(m.mul(t.m),i.mul(t.i));
    }

    /**
     * This method does a rotation on the y-axis.
     * @param p
     * @return
     */
    public Transform rotateY(final Point3 p) {
        Transform t = new Transform(new Mat4x4(Math.cos(p.y),0,Math.sin(p.y),0,0,1,0,0,Math.sin(p.y)*(-1),0,Math.cos(p.y),0,0,0,0,1),
                new Mat4x4(Math.cos(p.y),0,Math.sin(p.y)*(-1),0,0,1,0,0,Math.sin(p.y),0,Math.cos(p.y),0,0,0,0,1));
        return new Transform(m.mul(t.m),i.mul(t.i));
    }

    /**
     * This method does a rotation on the z-axis.
     * @param p
     * @return
     */
    public Transform rotateZ(final Point3 p) {
        Transform t = new Transform(new Mat4x4(Math.cos(p.z), Math.sin(p.z)*(-1),0,0,Math.sin(p.z),Math.cos(p.z),0,0,0,0,1,0,0,0,0,1),
                new Mat4x4(Math.cos(p.z),Math.sin(p.z),0,0,Math.sin(p.z)*(-1),Math.cos(p.z),0,0,0,0,1,0,0,0,0,1));
        return new Transform(m.mul(t.m),i.mul(t.i));
    }

    public Ray mul(final Ray r) {
        return new Ray(i.mul(r.o), i.mul(r.d));
    }

    public Normal3 mul(final Normal3 n) {
        //TODO calculate normal on transformed object
        return i.transposed().mul(n.asVector()).normalized().asNormal();
    }
}
