package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by baetschjunge on 03/05/16.
 */

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
    public Mat3x3(double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
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
                        - (m13 * m22 * m31) - (m23 * m32 * m13) - (m33 * m12 * m21);
    }

    /**
     * this function multiplies two Mat3x3
     * if number of columns (mat1) != rows (mat2) then the mul is not defined
     *
     * @param m matrix that is passed to the function
     * @return resulting mat3x3 of the passed mat3x3 and this mat3x3
     */
    public Mat3x3 mul(Mat3x3 m){
        //first column of result mat3x3
        double resultM11 = (this.m11 * m.m11) + (this.m21 * m.m12) + (this.m31 * m.m13);
        double resultM12 = (this.m12 * m.m11) + (this.m22 * m.m12) + (this.m32 * m.m13);
        double resultM13 = (this.m13 * m.m11) + (this.m23 * m.m12) + (this.m33 * m.m13);
        //second column of result mat3x3
        double resultM21 = (this.m11 * m.m21) + (this.m21 * m.m22) + (this.m31 * m.m23);
        double resultM22 = (this.m12 * m.m21) + (this.m22 * m.m22) + (this.m32 * m.m23);
        double resultM23 = (this.m13 * m.m21) + (this.m23 * m.m22) + (this.m33 * m.m23);
        //third column of result matrix
        double resultM31 = (this.m11 * m.m31) + (this.m21 * m.m32) + (this.m31 * m.m33);
        double resultM32 = (this.m12 * m.m31) + (this.m22 * m.m32) + (this.m32 * m.m33);
        double resultM33 = (this.m13 * m.m31) + (this.m23 * m.m32) + (this.m33 * m.m33);

        return new Mat3x3(resultM11, resultM12, resultM13, resultM21, resultM22, resultM23, resultM31, resultM32, resultM33);
    }

    /**
     * this function multiplies this Mat3x3 with a passed {@link Vector3}
     *
     * @param v the passed {@link Vector3}
     * @return resulting {@link Vector3}
     */
    public Vector3 mul(Vector3 v){
        double resultX = (this.m11 * v.x) + (this.m21 * v.y) + (this.m31 * v.z);
        double resultY = (this.m12 * v.x) + (this.m22 * v.y) + (this.m32 * v.z);
        double resultZ = (this.m13 * v.x) + (this.m23 * v.y) + (this.m33 * v.z);
        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function multiplies this Mat3x3 with a passed {@link Point3}
     *
     * @param p passed {@link Point3}
     * @return resulting {@link Point3} of this Mat3x3 and the passed Point3
     */
    public Point3 mul(Point3 p){
        double resultX = (this.m11 * p.x) + (this.m21 * p.y) + (this.m31 * p.z);
        double resultY = (this.m12 * p.x) + (this.m22 * p.y) + (this.m32 * p.z);
        double resultZ = (this.m13 * p.x) + (this.m23 * p.y) + (this.m33 * p.z);
        return new Point3(resultX, resultY, resultZ);
    }

    /**
     * this function changes the first column of this Mat3x3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol1(Vector3 v){
        return new Mat3x3(v.x, v.y, v.z, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
    }

    /**
     * this function changes the second column of this Mat3y3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol2(Vector3 v){
        return new Mat3x3(this.m11, this.m12, this.m13, v.x, v.y, v.z, this.m31, this.m32, this.m33);
    }

    /**
     * this function changes the third column of this Mat3y3 with the passed {@link Vector3}
     * @param v passed {@link Vector3}
     * @return resulting Mat3x3
     */
    public Mat3x3 changeCol3(Vector3 v){
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
}
