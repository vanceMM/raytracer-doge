package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Ray;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.*;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing Triangle Objects in 3d Space
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Triangle extends Geometry{

    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 a;
    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 b;
    /**
     * a {@link Point3} of the Triangle
     */
    public final Point3 c;

    /**
     * plane {@link Normal3} of a triangle, by a plane between the 3 given Points
     */
    public final Normal3 normal;

    /**
     * the passed {@link Normal3} of the endpoint a
     */
    public final Normal3 an;
    /**
     * the passed {@link Normal3} of the endpoint b
     */
    public final Normal3 bn;
    /**
     * the passed {@link Normal3} of the endpoint c
     */
    public final Normal3 cn;


    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Triangle} with defined attributes.
     *
     * @param color the Value of the {@link Color}
     * @param a the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param b the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param c the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param an Value of the {@link Normal3} of a, if null throw new {@link IllegalArgumentException}
     * @param bn Value of the {@link Normal3} of b, if null throw new {@link IllegalArgumentException}
     * @param cn Value of the {@link Normal3} of c, if null throw new {@link IllegalArgumentException}
     */
    public Triangle(final Material color, final Point3 a, final Point3 b, final Point3 c, final Normal3 an, final Normal3 bn, final Normal3 cn){
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        this.an = an;
        this.bn = bn;
        this.cn = cn;
        this.normal = null;
    }

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link Triangle} with defined attributes.
     *
     * @param color the Value of the {@link Color}
     * @param a the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param b the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param c the Value of a {@link Point3}, if null throw new {@link IllegalArgumentException}
     */
    public Triangle(final Material color, final Point3 a, final Point3 b, final Point3 c) {
        super(color);
        if (a == null || b == null || c == null) throw new IllegalArgumentException("Params of constructor can't be null");
        this.a = a;
        this.b = b;
        this.c = c;
        // plane normal that's on every plane triangle (p2 - p1 x p3 - p1)
        this.normal = b.sub(a).x(c.sub(a)).normalized().asNormal();
        this.an = null;
        this.bn = null;
        this.cn = null;
    }


    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object, if ray is null throw new {@link IllegalArgumentException}
     * @return Hit Object which represents the Intersection between the Triangle and the given {@link Ray}.
     */
    @Override
    public Hit hit(final Ray r) {
        if (r == null) throw new IllegalArgumentException("Param r (ray) can't be null");
        Mat3x3 A = new Mat3x3(a.x-b.x, a.y-b.y, a.z-b.z, a.x-c.x, a.y-c.y, a.z-c.z, r.d.x, r.d.y, r.d.z);

        Vector3 swapVec = new Vector3(a.x-r.o.x, a.y-r.o.y, a.z-r.o.z);   // (A1,2,3) changeCol needs vec3

        Mat3x3 A1 = A.changeCol1(swapVec);
        Mat3x3 A2 = A.changeCol2(swapVec);
        Mat3x3 A3 = A.changeCol3(swapVec);

        double beta     = A1.determinant / A.determinant;
        double gamma    = A2.determinant / A.determinant;
        double t        = A3.determinant / A.determinant;
        double alpha    = 1.0 - beta - gamma;

        // calculate normal3 for the hit point on the geometry


        if (beta >= Raytracer.DELTA && beta <= 1 && gamma >= Raytracer.DELTA && gamma <= 1 && beta + gamma <= 1 && t > Raytracer.DELTA && !Double.isNaN(t)){
            if (normal == null) {
                Normal3 interpolatedNormal = an.mul(alpha).add(bn.mul(beta)).add(cn.mul(gamma));
                return new Hit(t, r, this, interpolatedNormal);
            }
            else return new Hit(t, r, this, normal);
        }
        else return null;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", center=" + c +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        return a != null ? a.equals(triangle.a) : triangle.a == null && (b != null ? b.equals(triangle.b) : triangle.b == null && (c != null ? c.equals(triangle.c) : triangle.c == null));

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
