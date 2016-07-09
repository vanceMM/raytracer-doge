package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing AxisAlignedBoxes Objects in 3d Space
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AxisAlignedBox extends Geometry {

    /**
     * the left bottom far {@link Point3} of the AxisAlignedBox
     */
    public final Point3 lbf;
    /**
     * the right upper near {@link Point3} of the AxisAlignedBox
     */
    public final Point3 run;

    /**
     * the 6 Planes for the 3D Axis-Aligned-Box
     */
    private final Plane upper;
    private final Plane bottom;
    private final Plane left;
    private final Plane right;
    private final Plane far;
    private final Plane near;

    /**
     * Constructor for the Geometry Object
     * Creates a new instance of {@link AxisAlignedBox} with defined attributes.
     *
     * @param color the Value of the {@link Color}
     * @param lbf the Value of the left bottom far {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param run the Value of the right upper near {@link Point3}, if null throw new {@link IllegalArgumentException}
     */
    public AxisAlignedBox(final Material color, final Point3 lbf, final Point3 run) {
        super(color);
        if (lbf == null || run == null) throw new IllegalArgumentException("Params of constructor can't be null");
        this.lbf = lbf;
        this.run = run;

        this.upper = new Plane(color,run, new Normal3(0,0.5,0));
        this.bottom = new Plane(color,lbf, new Normal3(0,-0.5,0));
        this.left = new Plane(color,lbf, new Normal3(-0.5,0,0));
        this.right = new Plane(color,run, new Normal3(0.5,0,0));
        this.far = new Plane(color,lbf, new Normal3(0,0,-0.5));
        this.near = new Plane(color,run, new Normal3(0,0,0.5));
    }

    /**
     * This Methods takes a {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object, if r is null throw new {@link IllegalArgumentException}
     * @return Hit Object which represents the Intersection between the AxisAlignedBox and the given {@link Ray}.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public Hit hit(final Ray r) {
        if (r == null) throw new IllegalArgumentException("Param r (ray) can't be null");

        final ArrayList<Plane> planes = new ArrayList<>();
        final ArrayList<Hit> hits = new ArrayList<>();

        planes.add(upper);
        planes.add(bottom);
        planes.add(left);
        planes.add(right);
        planes.add(far);
        planes.add(near);

        for (final Plane p : planes) {

            final Point3 b = new Point3(run.x, run.y, run.z);

            if (r.o.sub(b).dot(p.n) >= Raytracer.DELTA) {
                hits.add(p.hit(r));
            }
        }
        Hit tHit = null;

        for (final Hit hit : hits) {
            if (hit != null && hitsBox(hit) && hit.t > Raytracer.DELTA) {
                if (tHit == null) {
                    tHit = hit;
                } else if (hit.t > Raytracer.DELTA) {
                    tHit = hit;
                }
            }
        }

        return tHit;

    }


    private boolean hitsBox(final Hit hit) {
        final Point3 point = hit.ray.at(hit.t);

        final Plane plane = (Plane) hit.geo;
        final boolean xInBox = lbf.x <= point.x + Raytracer.DELTA && point.x <= run.x + Raytracer.DELTA ;
        final boolean yInBox = lbf.y <= point.y + Raytracer.DELTA && point.y <= run.y + Raytracer.DELTA ;
        final boolean zInBox = lbf.z <= point.z + Raytracer.DELTA && point.z <= run.z + Raytracer.DELTA ;

        final boolean inBox = xInBox && yInBox && zInBox;
        return inBox;
    }


    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf=" + lbf +
                ", run=" + run +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        return lbf != null ? lbf.equals(that.lbf) : that.lbf == null && (run != null ? run.equals(that.run) : that.run == null);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = lbf != null ? lbf.hashCode() : 0;
        result = 31 * result + (run != null ? run.hashCode() : 0);
        return result;
    }
}
