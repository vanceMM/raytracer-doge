package de.beuth.cg1.dogeraytracer.vecmatlib;

import static java.lang.Math.*;

/**
 * Created by baetschjunge on 03/05/16.
 * Project name is VecMatLib.
 */


public class Vector3 {

    /**
     * x, y, z components of a Vector3
     */
    public final double x;
    public final double y;
    public final double z;
    public final double magnitude;

    /**
     * Creates a new instance of {@link Vector3} with defined attributes.
     *
     * @param x the double value for x
     * @param y the double value for y
     * @param z the double value for z
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.magnitude = sqrt( (pow(x,2)) + (pow(y,2)) + pow(z,2));
    }

    /**
     * this function adds a new Vector3 v to this Vector3
     *
     * @param v is the new Vector3
     * @return returns the resulting Vector3 of this Vector3 and the passed (v) Vector3
     */
    public Vector3 add(Vector3 v){
        double resultX = this.x + v.x,
                resultY = this.y + v.y,
                resultZ = this.z + v.z;

        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function adds a {@link Normal3} n to this Vector3
     *
     * @param n is the Normal3
     * @return the resulting Vector3 of the passed Normal3 n and this Vector3
     */
    public Vector3 add(Normal3 n){
        double resultX = this.x + n.x,
                resultY = this.y + n.y,
                resultZ = this.z + n.z;

        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     *
     * @param n
     * @return
     */
    public Vector3 sub(Normal3 n){
        double resultX = this.x - n.x,
                resultY = this.y - n.y,
                resultZ = this.z - n.z;

        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     *
     * @param c
     * @return
     */
    public Vector3 mulVector3(double c){
        return new Vector3(this.x * c, this.y * c, this.z * c);
    }

    /**
     *
     * @param v
     * @return
     */
    public Vector3 xProduct(Vector3 v){
        double resultX = (this.y * v.z) - (this.z * v.y);
        double resultY = (this.z * v.x) - (this.x * v.z);
        double resultZ = (this.x * v.y) - (this.y * v.x);

        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     *
     * @param v
     * @return
     */
    public double dot(Vector3 v){
        double result = (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
        return result;
    }

    /**
     *
     * @param n
     * @return
     */
     public double dot(Normal3 n){
        return (this.x * n.x) + (this.y * n.y) + (this.z * n.z);
    }

    /**
     * this function is normalizing this Vector by teilen der punkte? durch magnitude
     *
     * @return an normalized Vector3
     */
    public Vector3 normalized(){
        double resultX = this.x / magnitude,
                resultY = this.y / magnitude,
                resultZ = this.z / magnitude;
        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function is calling the method normalized() to normalize this Vector3
     * it then returns this Vector3 as a {@link Normal3}
     *
     * @return a {@link Normal3}
     */
    public Normal3 asNormal(){
        Vector3 newNormal3 = this.normalized();
        return new Normal3(newNormal3.x, newNormal3.y, newNormal3.z);
    }

    /**
     * this function reflects this Vector3 on its passed {@link Normal3}
     *
     * @param n the passed {@link Normal3}
     * @return the reflected Vector3
     */
    public Vector3 reflectOn(Normal3 n){
        return null;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", magnitude=" + magnitude +
                '}';
    }
}
