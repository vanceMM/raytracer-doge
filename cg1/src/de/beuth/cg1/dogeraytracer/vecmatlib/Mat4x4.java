package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by baetschjunge on 03/05/16.
 * Project name is VecMatLib.
 * This Class represents a Mat4x4 / 4 dimensional Matrix
 */

@SuppressWarnings({"WeakerAccess", "SimplifiableIfStatement"})
public class Mat4x4 {

    /**
     * first row of mat4x4
     */
    public final double m11;
    public final double m12;
    public final double m13;
    public final double m14;
    /**
     * second row of mat4x4
     */
    public final double m21;
    public final double m22;
    public final double m23;
    public final double m24;
    /**
     * third row of mat4x4
     */
    public final double m31;
    public final double m32;
    public final double m33;
    public final double m34;
    /**
     * fourth row of mat4x4
     */
    public final double m41;
    public final double m42;
    public final double m43;
    public final double m44;


    /**
     * Creates a new instance of {@link Mat4x4} with defined attributes.
     *
     * @param m11 the double value for m11
     * @param m12 the double value for m12
     * @param m13 the double value for m13
     * @param m14 the double value for m14
     * @param m21 the double value for m21
     * @param m22 the double value for m22
     * @param m23 the double value for m23
     * @param m24 the double value for m24
     * @param m31 the double value for m31
     * @param m32 the double value for m32
     * @param m33 the double value for m33
     * @param m34 the double value for m34
     * @param m41 the double value for m41
     * @param m42 the double value for m42
     * @param m43 the double value for m43
     * @param m44 the double value for m44
     *
     *              if params are NaN, throw new {@link IllegalArgumentException}
     */
    public Mat4x4(final double m11, final double m12, final double m13, final double m14, final double m21, final double m22, final double m23, final double m24, final double m31, final double m32, final double m33, final double m34, final double m41, final double m42, final double m43, final double m44) {
        if (Double.isNaN(m11) || Double.isNaN(m12) ||Double.isNaN(m13) ||Double.isNaN(m14) ||Double.isNaN(m21) ||Double.isNaN(m22) ||Double.isNaN(m23) ||Double.isNaN(m24) ||Double.isNaN(m31) || Double.isNaN(m32) || Double.isNaN(m33) ||Double.isNaN(m34) ||Double.isNaN(m41) ||Double.isNaN(m42) ||Double.isNaN(m43) ||Double.isNaN(m44) ) throw new IllegalArgumentException("Params of constructor can't be NaN");
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;


    }

    /**
     * this function multiplies two Mat4x4
     * if number of columns (mat1) != rows (mat2) then the mul is not defined
     *
     * @param m matrix that is passed, if m is null throw IllegalArgumentException
     * @return resulting mat4x4 of the passed mat4x4 and this mat4x4
     */
    public Mat4x4 mul(final Mat4x4 m){
        if (m == null) throw new IllegalArgumentException("Parameter m can't be null");
        //first column of result mat4x4
        final double resultM11 = (this.m11 * m.m11) + (this.m21 * m.m12) + (this.m31 * m.m13) + (this.m41 * m.m14);
        final double resultM12 = (this.m12 * m.m11) + (this.m22 * m.m12) + (this.m32 * m.m13) + (this.m42 * m.m14);
        final double resultM13 = (this.m13 * m.m11) + (this.m23 * m.m12) + (this.m33 * m.m13) + (this.m43 * m.m14);
        final double resultM14 = (this.m14 * m.m11) + (this.m24 * m.m12) + (this.m34 * m.m13) + (this.m44 * m.m14);
        //second column of result mat4x4
        final double resultM21 = (this.m11 * m.m21) + (this.m21 * m.m22) + (this.m31 * m.m23) + (this.m41 * m.m24);
        final double resultM22 = (this.m12 * m.m21) + (this.m22 * m.m22) + (this.m32 * m.m23) + (this.m42 * m.m24);
        final double resultM23 = (this.m13 * m.m21) + (this.m23 * m.m22) + (this.m33 * m.m23) + (this.m43 * m.m24);
        final double resultM24 = (this.m14 * m.m21) + (this.m24 * m.m22) + (this.m34 * m.m23) + (this.m44 * m.m24);
        //third column of result matrix
        final double resultM31 = (this.m11 * m.m31) + (this.m21 * m.m32) + (this.m31 * m.m33) + (this.m41 * m.m34);
        final double resultM32 = (this.m12 * m.m31) + (this.m22 * m.m32) + (this.m32 * m.m33) + (this.m42 * m.m34);
        final double resultM33 = (this.m13 * m.m31) + (this.m23 * m.m32) + (this.m33 * m.m33) + (this.m43 * m.m34);
        final double resultM34 = (this.m14 * m.m31) + (this.m24 * m.m32) + (this.m34 * m.m33) + (this.m44 * m.m34);
        //fourth column of result matrix
        final double resultM41 = (this.m11 * m.m41) + (this.m21 * m.m42) + (this.m31 * m.m43) + (this.m41 * m.m44);
        final double resultM42 = (this.m12 * m.m41) + (this.m22 * m.m42) + (this.m32 * m.m43) + (this.m42 * m.m44);
        final double resultM43 = (this.m13 * m.m41) + (this.m23 * m.m42) + (this.m33 * m.m43) + (this.m43 * m.m44);
        final double resultM44 = (this.m14 * m.m41) + (this.m24 * m.m42) + (this.m34 * m.m43) + (this.m44 * m.m44);

        return new Mat4x4(resultM11, resultM12, resultM13, resultM14, resultM21, resultM22, resultM23,resultM24, resultM31, resultM32, resultM33, resultM34, resultM41, resultM42, resultM43, resultM44);
    }


    /**
     * this function multiplies this Mat4x4 with a passed {@link Vector3}
     *
     * @param v the passed {@link Vector3}, if v is null throw IllegalArgumentException
     * @return resulting {@link Vector3}
     */
    @SuppressWarnings("Duplicates")
    public Vector3 mul(final Vector3 v){
        if (v == null) throw new IllegalArgumentException("Parameter v can't be null");
        final double resultX = (this.m11 * v.x) + (this.m12 * v.y) + (this.m13 * v.z) + (this.m14 * 0.0);
        final double resultY = (this.m21 * v.x) + (this.m22 * v.y) + (this.m23 * v.z) + (this.m24 * 0.0);
        final double resultZ = (this.m31 * v.x) + (this.m32 * v.y) + (this.m33 * v.z) + (this.m34 * 0.0);
        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function multiplies this Mat4x4 with a passed {@link Point3}
     *
     * @param p passed {@link Point3}, if p is null throw IllegalArgumentException
     * @return resulting {@link Point3} of this Mat4x4 and the passed Point3
     */
    @SuppressWarnings("Duplicates")
    public Point3 mul(final Point3 p){
        if (p == null) throw new IllegalArgumentException("Parameter p can't be null");
        final double resultX = (this.m11 * p.x) + (this.m12 * p.y) + (this.m13 * p.z) + (this.m14 * 1.0);
        final double resultY = (this.m21 * p.x) + (this.m22 * p.y) + (this.m23 * p.z) + (this.m24 * 1.0);
        final double resultZ = (this.m31 * p.x) + (this.m32 * p.y) + (this.m33 * p.z) + (this.m34 * 1.0);
        return new Point3(resultX, resultY, resultZ);
    }

    /**
     * This method returns the transposed Matrix of this {@link Mat4x4} Instance. A transposed Matrix is created by
     * switching rows and columns on it's main axis.
     * @return the transposed Matrix
     */
    public Mat4x4 transposed() {
        return new Mat4x4(this.m11, this.m21, this.m31, this.m41, this.m12, this.m22, this.m32, this.m42, this.m13, this.m23, this.m33, this.m43, this.m14, this.m24, this.m34, this.m44);
    }

    @Override
    public String toString() {
        return "Mat4x4{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m24=" + m24 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", m34=" + m34 +
                ", m41=" + m41 +
                ", m42=" + m42 +
                ", m43=" + m43 +
                ", m44=" + m44 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat4x4 mat4x4 = (Mat4x4) o;

        if (Double.compare(mat4x4.m11, m11) != 0) return false;
        if (Double.compare(mat4x4.m12, m12) != 0) return false;
        if (Double.compare(mat4x4.m13, m13) != 0) return false;
        if (Double.compare(mat4x4.m14, m14) != 0) return false;
        if (Double.compare(mat4x4.m21, m21) != 0) return false;
        if (Double.compare(mat4x4.m22, m22) != 0) return false;
        if (Double.compare(mat4x4.m23, m23) != 0) return false;
        if (Double.compare(mat4x4.m24, m24) != 0) return false;
        if (Double.compare(mat4x4.m31, m31) != 0) return false;
        if (Double.compare(mat4x4.m32, m32) != 0) return false;
        if (Double.compare(mat4x4.m33, m33) != 0) return false;
        if (Double.compare(mat4x4.m34, m34) != 0) return false;
        if (Double.compare(mat4x4.m41, m41) != 0) return false;
        if (Double.compare(mat4x4.m42, m42) != 0) return false;
        if (Double.compare(mat4x4.m43, m43) != 0) return false;
        return Double.compare(mat4x4.m44, m44) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
