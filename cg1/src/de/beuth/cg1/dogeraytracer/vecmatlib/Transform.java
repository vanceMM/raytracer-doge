package de.beuth.cg1.dogeraytracer.vecmatlib;

import de.beuth.cg1.dogeraytracer.raytracer.Ray;

/**
 * Created by valentin on 08/07/16.
 * This Class represents a transformation object. This Class creates the transformation matrices and it's inverses.
 * It has a public Constructor to create the unit matrix and it's inverse which are equal at point of initialisation.
 * The private constructor is only called from within the Classes methods. It is therefore  possible to chain different
 * transformations.
 */
@SuppressWarnings("WeakerAccess")
public class Transform {
    /**
     * Transformation / Unit-Matrix
     */
    public final Mat4x4 m;
    /**
     * Inverse of the transformation Matrix
     */
    public final Mat4x4 i;

    /**
     * Default constructor initializes the Transform Object Instance with the unit {@link Mat4x4}.
     */
    public Transform() {
        this.m = new Mat4x4(1,0,0,0,
                            0,1,0,0,
                            0,0,1,0,
                            0,0,0,1);
        this.i = this.m;
    }

    /**
     * This constructor is private and can only be called inside the Transform Class.
     * @param m Transformation Matrix
     * @param i Inverse of the Transform Matrix
     */
    private Transform(Mat4x4 m, Mat4x4 i) {
        if (m == null || i == null) throw new IllegalArgumentException("Param of method can't be null");
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
        if (p == null) throw new IllegalArgumentException("Param of method can't be null");
        Transform t = new Transform(
                new Mat4x4( 1, 0, 0, p.x,
                            0, 1, 0, p.y,
                            0, 0, 1, p.z,
                            0, 0, 0, 1 ),
                new Mat4x4( 1, 0, 0, -p.x,
                            0, 1, 0, -p.y,
                            0, 0, 1, -p.z,
                            0, 0, 0, 1 )
        );
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
        if (p == null) throw new IllegalArgumentException("Param of method can't be null");
        Transform t = new Transform(
                new Mat4x4( p.x, 0, 0, 0,
                            0, p.y, 0, 0,
                            0, 0, p.z, 0,
                            0, 0, 0, 1 ),
                new Mat4x4( 1.0/p.x, 0, 0, 0,
                            0, 1.0/p.y, 0, 0,
                            0, 0, 1.0/p.z, 0,
                            0, 0, 0, 1 )
        );
        return new Transform(m.mul(t.m),t.i.mul(i));
    }

    /**
     * This method does a rotation on the x-axis.
     * @param degree in double, if NaN throw new {@link IllegalArgumentException}
     * @return Transform Object
     */
    public Transform rotateX(double degree) {
        if (Double.isNaN(degree)) throw new IllegalArgumentException("Param of method cant be NaN");
        double radian = DegreeToRadian(degree);
        Transform t = new Transform(
                new Mat4x4( 1, 0, 0, 0,
                            0, Math.cos(radian), -Math.sin(radian), 0,
                            0, Math.sin(radian), Math.cos(radian), 0,
                            0, 0, 0, 1),
                new Mat4x4( 1, 0, 0, 0,
                            0, Math.cos(radian), Math.sin(radian), 0,
                            0, -Math.sin(radian), Math.cos(radian), 0,
                            0, 0, 0, 1)
        );
        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * This method does a rotation on the y-axis.
     * @param degree in double, if NaN throw new {@link IllegalArgumentException}
     * @return Transform Object
     */
    public Transform rotateY(double degree) {
        if (Double.isNaN(degree)) throw new IllegalArgumentException("Param of method cant be NaN");
        double radian = DegreeToRadian(degree);
        Transform t = new Transform(
                new Mat4x4( Math.cos(radian), 0, Math.sin(radian), 0,
                            0, 1, 0, 0,
                            -Math.sin(radian), 0, Math.cos(radian), 0,
                            0, 0, 0, 1),
                new Mat4x4( Math.cos(radian), 0, -Math.sin(radian), 0,
                            0, 1, 0, 0,
                            Math.sin(radian), 0, Math.cos(radian), 0,
                            0, 0, 0, 1)
        );
        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * This method does a rotation on the z-axis.
     * @param degree in double, if NaN throw new {@link IllegalArgumentException}
     * @return Transform Object
     */
    public Transform rotateZ(double degree) {
        if (Double.isNaN(degree)) throw new IllegalArgumentException("Param of method cant be NaN");
        double radian = DegreeToRadian(degree);
        Transform t = new Transform(
                new Mat4x4( Math.cos(radian), -Math.sin(radian), 0, 0,
                            Math.sin(radian), Math.cos(radian), 0, 0,
                            0, 0, 1, 0,
                            0, 0, 0, 1),
                new Mat4x4( Math.cos(radian), Math.sin(radian), 0, 0,
                            -Math.sin(radian), Math.cos(radian), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)
        );
        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * This method trasforms the passed degree value into radian value
     * @param degree value
     * @return radian value
     */
    private double DegreeToRadian(double degree){
        if (Double.isNaN(degree)) throw new IllegalArgumentException("Param degree cant be null");
        return degree * (Math.PI / 180.0);
    }

    /**
     * This method calculates the transformed {@link Ray}
     * @param ray Ray to be transformed
     * @return transformed Ray
     */
    public Ray mul(final Ray ray) {
        if (ray == null) throw new IllegalArgumentException("Param ray of method can't be null");
        return new Ray(i.mul(ray.o), i.mul(ray.d));
    }


    /**
     * This method calculates the re-transformed {@link Normal3}
     * needed for scale
     * @param n {@link Normal3} to be transformed
     * @return transformed {@link Normal3}
     */
    public Normal3 mul(final Normal3 n) {
        if (n == null) throw new IllegalArgumentException("Param n of method can't be null");
        return i.transposed().mul(n.asVector()).normalized().asNormal();
    }
}

