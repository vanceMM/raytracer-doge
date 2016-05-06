package de.beuth.cg1.dogeraytracer.vecmatlib;

import static java.lang.Math.*;

/**
 * Created by baetschjunge on 03/05/16.
 * Project name is VecMatLib.
 */

public class Vector3 {

    /**
     * x, y, z components of a Vector3
     * and the magnitude of a Vector3
     */
    public final double x;
    public final double y;
    public final double z;
    public final double magnitude;

    /**
     * Creates a new instance of {@link Vector3} with defined attributes.
     * also creates a new instance of the Vector3 magnitude
     *
     * @param x the double value for x
     * @param y the double value for y
     * @param z the double value for z
     */
    public Vector3(double x, double y, double z){
            this.x = x;
            this.y = y;
            this.z = z;

            this.magnitude = sqrt((pow(x, 2)) + (pow(y, 2)) + pow(z, 2));
    }

    /**
     * this function adds a new Vector3 v to this Vector3
     * @param v is the new Vector3
     * @return returns the resulting Vector3 of this Vector3 and the passed (v) Vector3
     */
    public Vector3 add(Vector3 v){
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * this function adds a {@link Normal3} n to this Vector3
     * @param n the passed {@link Normal3}
     * @return the resulting Vector3 of the passed {@link Normal3} n and this Vector3
     */
    public Vector3 add(Normal3 n){
        return new Vector3(this.x + n.x, this.y + n.y, this.z + n.z);
    }

    /**
     * this function subtracts a passed {@link Normal3} from this Vector3
     * @param n passed {@link Normal3}
     * @return resulting Vector3
     */
    public Vector3 sub(Normal3 n){
        return new Vector3(this.x - n.x, this.y - n.y, this.z - n.z);
    }

    /**
     * this function multiplies the passed double Value with this Vector3
     * @param c passed double value
     * @return resulting Vector3
     */
    public Vector3 mul(double c){
        return new Vector3(this.x * c, this.y * c, this.z * c);
    }

    /**
     * this function calculates the x-product of this Vector3 and the passed Vector3
     * @param v passed Vector3
     * @return resulting Vector3
     */
    public Vector3 x(Vector3 v){
        double resultX = (this.y * v.z) - (this.z * v.y);
        double resultY = (this.z * v.x) - (this.x * v.z);
        double resultZ = (this.x * v.y) - (this.y * v.x);
        return new Vector3(resultX, resultY, resultZ);
    }

    /**
     * this function calculates the dot-product of this Vector3 and a passed Vector3
     * @param v passed Vector3
     * @return resulting dot-product / double value
     */
    public double dot(Vector3 v){
        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }

    /**
     * this function calculates the dot-product of this Vector3 and a passed {@link Normal3}
     * @param n passed {@link Normal3}
     * @return resulting dot-product / double value
     */
     public double dot(Normal3 n){
        return (this.x * n.x) + (this.y * n.y) + (this.z * n.z);
    }

    /**
     * this function is normalizing this Vector3 by dividing its components by its magnitude
     * @return an normalized Vector3
     */
    public Vector3 normalized(){
        return new Vector3(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }

    /**
     * this function is calling the method normalized() to normalize this Vector3
     * it then returns this Vector3 as a {@link Normal3}
     * @return a {@link Normal3}
     */
    public Normal3 asNormal(){
        return new Normal3(this.x, this.y, this.z);
    }

    /**
     * this function reflects this Vector3 on the passed {@link Normal3}
     * r = d − 2 ( d⋅n ) n
     * @param n the passed {@link Normal3}
     * @return the reflected Vector3
     */
    public Vector3 reflectOn(Normal3 n){
        Vector3 thisVec = this;
        double innerDot = thisVec.dot(n)*2;
        //double next = innerDot*2;
        Vector3 rVec = thisVec.sub(n.mul(innerDot)).mul(-1);
        return rVec;
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

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Double.compare(vector3.x, x) == 0 && Double.compare(vector3.y, y) == 0 && Double.compare(vector3.z, z) == 0 && Double.compare(vector3.magnitude, magnitude) == 0;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(magnitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
