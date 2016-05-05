package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by baetschjunge on 03/05/16.
 */
public class Normal3 {

    /**
     * x, y, z components of a Normal3
     */
    public final double x;
    public final double y;
    public final double z;

    /**
     * Creates a new instance of {@link Normal3} with defined attributes.
     *
     * @param x the double value for x
     * @param y the double value for y
     * @param z the double value for z
     */
    public Normal3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * this function multiplies this Normal3 with a double value n
     *
     * @param n the double value the Normal3 is multiplied with
     * @return resulting Normal3 of this Normal3 and n
     */
    public Normal3 mul(double n){
        return new Normal3(this.x * n, this.y * n, this.z * n);
    }

    /**
     * Addition of a passed Normal3 with this Normal3
     *
     * @param n passed Normal3
     * @return resulting Normal3 of this Normal3 and the passed Normal3
     */
    public Normal3 add(Normal3 n){
        return new Normal3(this.x + n.x, this.y + n.y, this.z + n.z);
    }

    /**
     * this function calculates the dot-product of this Normal3 and a passed {@link Vector3}
     *
     * @param v the passed {@link Vector3}
     * @return resulting dot-product of this Normal3 and passed {@link Vector3}
     */
    public double dot(Vector3 v){
        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Normal3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
