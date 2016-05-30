package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by baetschjunge on 03/05/16.
 * Project name is VecMatLib.
 */

/**
 * This Class represents a Mat3x3 / 3 dimensional Matrix
 * @author baetschjunge
 */
@SuppressWarnings("WeakerAccess")
public class Mat3x3 {

    /**
     * first column of mat3x3
     */
    public final double m11;
    public final double m12;
    public final double m13;
    /**
     * second column of mat3x3
     */
    public final double m21;
    public final double m22;
    public final double m23;
    /**
     * third column of mat3x3
     */
    public final double m31;
    public final double m32;
    public final double m33;

    /**
     * determinant of mat3x3
     */
    public final double determinant;

    /**
     * Creates a new instance of {@link Mat3x3} with defined attributes.
     *
     * @param m11 the double value for m11
     * @param m12 the double value for m12
     * @param m13 the double value for m13
     * @param m21 the double value for m21
     * @param m22 the double value for m22
     * @param m23 the double value for m23
     * @param m31 the double value for m31
     * @param m32 the double value for m32
     * @param m33 the double value for m33
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;

        determinant = (m11 * m22 * m33) + (m21 * m32 * m13) + (m31 * m12 * m23)
                        - (m13 * m22 * m31) - (m23 * m32 * m11) - (m33 * m12 * m21);
    }

    /**
     * this function multiplies two Mat3x3
     * if number of columns (mat1) != rows (mat2) then the mul is not defined
     *
     * @param m matrix that is passed, if m is null throw IllegalArgumentException
     * @return resulting mat3x3 of the passed mat3x3 and this mat3x3
     */
    public Mat3x3 mul(final Mat3x3 m){
        if (m == null) throw new IllegalArgumentException("Parameter m can't be null");
        //first column of result mat3x3
        final double resultM11 = (this.m11 * m.m11) + (this.m21 * m.m12) + (this.m31 * m.m13);
        final double resultM12 = (this.m12 * m.m11) + (this.m22 * m.m12) + (this.m32 * m.m13);
        final double resultM13 = (this.m13 * m.m11) + (this.m23 * m.m12) + (this.m33 * m.m13);
        //second column of result mat3x3
        final double resultM21 = (this.m11 * m.m21) + (this.m21 * m.m22) + (this.m31 * m.m23);
        final double resultM22 = (this.m12 * m.m21) + (this.m22 * m.m22) + (this.m32 * m.m23);
        final double resultM23 = (this.m13 * m.m21) + (this.m23 * m.m22) + (this.m33 * m.m23);
        //third column of result matrix
        final double resultM31 = (this.m11 * m.m31) + (this.m21 * m.m32) + (this.m31 * m.m33);
        final double resultM32 = (this.m12 * m.m31) + (this.m22 * m.m32) + (this.m32 * m.m33);
        final double resultM33 = (this.m13 * m.m31) + (this.m23 * m.m32) + (this.m33 * m.m33);

        return new Mat3x3(resultM11, resultM12, resultM13, resultM21, resultM22, resultM23, resultM31, resultM32, resultM33);
    }

    /**
     * this function multiplies this Mat3x3 with a passed {@link Vector3}
     *
     * @param v the passed {@link Vector3}, if v is null throw IllegalArgumentException
     * @return resulting {@link Vector3}
     */
    public Vector3 mul(final Vector3 v){
        if (v == null) throw new IllegalArgumentException("Parameter v can't be null");
        final double resultX = (this.m11 * v.x) + (this.m21 * v.y) + (this.m31 * v.z);
        final double resultY = (this.m12 * v.x) + (this.m22 * v.y) + (this.m32 * v.z);
        final double resultZ = (this.m13 * v.x) + (this.m23 * v.y) + (this.m33 * v.z);
        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function multiplies this Mat3x3 with a passed {@link Point3}
     *
     * @param p passed {@link Point3}, if p is null throw IllegalArgumentException
     * @return resulting {@link Point3} of this Mat3x3 and the passed Point3
     */
    public Point3 mul(final Point3 p){
        if (p == null) throw new IllegalArgumentException("Parameter p can't be null");
        final double resultX = (this.m11 * p.x) + (this.m21 * p.y) + (this.m31 * p.z);
        final double resultY = (this.m12 * p.x) + (this.m22 * p.y) + (this.m32 * p.z);
        final double resultZ = (this.m13 * p.x) + (this.m23 * p.y) + (this.m33 * p.z);
        return new Point3(resultX, resultY, resultZ);
    }

    /**
     * this function changes the first column of this Mat3x3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}, if v is null throw IllegalArgumentException
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol1(final Vector3 v){
        if (v == null) throw new IllegalArgumentException("Parameter v can't be null");
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    /**
     * this function changes the second column of this Mat3y3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}, if v is null throw IllegalArgumentException
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol2(final Vector3 v){
        if (v == null) throw new IllegalArgumentException("Parameter v can't be null");
        return new Mat3x3(this.m11, this.m12, this.m13, v.x, v.y, v.z, this.m31, this.m32, this.m33);
    }

    /**
     * this function changes the third column of this Mat3y3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}, if v is null throw IllegalArgumentException
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol3(final Vector3 v){
        if (v == null) throw new IllegalArgumentException("Parameter v can't be null");
        return new Mat3x3(this.m11, this.m12, this.m13, this.m21, this.m22, this.m23, v.x, v.y, v.z);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Mat3x3{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", determinant=" + determinant +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (Double.compare(mat3x3.m11, m11) != 0) return false;
        if (Double.compare(mat3x3.m12, m12) != 0) return false;
        if (Double.compare(mat3x3.m13, m13) != 0) return false;
        if (Double.compare(mat3x3.m21, m21) != 0) return false;
        if (Double.compare(mat3x3.m22, m22) != 0) return false;
        if (Double.compare(mat3x3.m23, m23) != 0) return false;
        if (Double.compare(mat3x3.m31, m31) != 0) return false;
        if (Double.compare(mat3x3.m32, m32) != 0) return false;
        if (Double.compare(mat3x3.m33, m33) != 0) return false;
        return Double.compare(mat3x3.determinant, determinant) == 0;
    }

    /**
     * @see Object#hashCode()
     */
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
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(determinant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
