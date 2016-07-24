package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Ray;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

import java.util.concurrent.Delayed;

/**
 * Created by baetschjunge on 24/07/16.
 * Project name is raytracer-doge.
 */
public class Cylinder extends Geometry {

    public final Point3 center;

    public final double radius;

    /**
     * Constructor for the Geometry Object
     *  @param material the Value of the {@link Material}, if material is null throw new {@link IllegalArgumentException}
     */
    public Cylinder(Material material) {
        super(material);

        this.center = new Point3(0,0,0);
        this.radius = 1.0;
    }


    /**
     * This Methods takes an {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param ray passed {@link Ray} that hits the Object
     * @return Hit Object which references to a {@link Ray} an the intersection point t where the object is hit by the {@link Ray}.
     */
    @Override
    public Hit hit(Ray ray) {

        double a, b, c, d, t1, t2, z1, z2;
        //double z = 2.0;

        //a=xD2+yD2-zD2
        a = (ray.d.x * ray.d.x) + (ray.d.y * ray.d.y);
        b = 2.0 * ((ray.o.x * ray.d.x) + (ray.o.x * ray.d.y));
        c = ((ray.o.x * ray.o.x) + (ray.o.y * ray.o.y)) - (radius * radius);

        d = (b * b) - (4.0 * a * c);

        double zMin = -1.0;
        double zMax = 1.0;

        if(d > 0){
            double t = Raytracer.DELTA;
            t1 = (((-1.0)*b + Math.sqrt(d))) / (2.0 * a);
            t2 = (((-1.0)*b - Math.sqrt(d))) / (2.0 * a);

            z1 = (ray.o.z + (t1 * ray.d.z));
            z2 = (ray.o.z + (t2 * ray.d.z));

            //System.out.println(z1);
            //System.out.println(z2);

            if (zMin < z1 && z1 < zMax && zMin < z2 && z2 < zMax){


                    if(t2 > Raytracer.DELTA && t1 < Raytracer.DELTA){
                        t = t2;
                    }
                    if(t2 < Raytracer.DELTA && t1 > Raytracer.DELTA){
                        t = t1;
                    }

                    if(t1 > t2) t = t1;

                    if(t > Raytracer.DELTA) {
                        return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
                    }
            }


            if(t > Raytracer.DELTA) {
                return new Hit(t, ray, this, calcIntersectionNormal(ray, t));
            }
        }

        return null;
    }

    /**
     * this method calculates the intersection {@link Normal3} for the hit point
     * @param ray that's passed
     * @param t double value for distance
     * @return Normal3 of the intersection Hit point
     */
    private Normal3 calcIntersectionNormal(Ray ray, double t){
        //return ray.at(t).sub(center).normalized().asNormal();
        Point3 at = ray.at(t);
        //Normal3 normal3 = new Normal3(at.x - center.x, at.y - center.y, at.z - center.z);
        //return normal3.mul(1);
        //Normal3 normal3 = new Vector3(at.x - center.x, at.y - center.y, at.z - center.z).normalized().asNormal();
        return new Vector3(at.x - center.x, at.y - center.y, at.z - center.z).normalized().asNormal();
    }
}
