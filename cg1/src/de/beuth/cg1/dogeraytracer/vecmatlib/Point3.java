package de.beuth.cg1.dogeraytracer.vecmatlib;

/**
 * Created by baetschjunge on 03/05/16.
 */

public class Point3 {

    /**
     * x, y, z components of a Point3
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
    public Point3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * this function subtracts a passed Point3 from this Point3
     * @param p passed Point3
     * @return  resulting {@link Vector3}
     */
    public Vector3 sub(Point3 p){
        return new Vector3(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    /**
     * this function subtracts a passed {@link Vector3} from this Point3
     * @param v passed {@link Vector3}
     * @return resulting Point3
     */
    public Point3 sub(Vector3 v){
        return new Point3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    /**
     * this function adds a passed {@link Vector3} to this Point3
     * @param v passed {@link Vector3}
     * @return resulting Point3
     */
    public Point3 add(Vector3 v){
        return new Point3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Point3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        return Double.compare(point3.y, y) == 0 && Double.compare(point3.z, z) == 0;

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
        return result;
    }
}
