package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.raytracer.Raytracer;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

import java.util.ArrayList;
import java.util.Iterator;

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
     * @param lbf the Value of the left bottom far {@link Point3}
     * @param run the Value of the right upper near {@link Point3}
     */
    public AxisAlignedBox(final Color color, final Point3 lbf, final Point3 run) {
        super(color);
        this.lbf = lbf;
        this.run = run;

        this.upper = new Plane(color,run, new Normal3(0,1,0));
        this.bottom = new Plane(color,lbf, new Normal3(0,-1,0));
        this.left = new Plane(color,lbf, new Normal3(-1,0,0));
        this.right = new Plane(color,run, new Normal3(1,0,0));
        this.far = new Plane(color,lbf, new Normal3(0,0,-1));
        this.near = new Plane(color,run, new Normal3(0,0,1));
    }

    /**
     * This Methods takes a {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object, if r null throw new {@link IllegalArgumentException}
     * @return Hit Object which represents the Intersection between the AxisAlignedBox and the given {@link Ray}.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public Hit hit(final Ray r) {
        if (r == null) throw new IllegalArgumentException("Param r (ray) can't be null");
        ArrayList<Plane> planes = new ArrayList<>();
        planes.add(upper);
        planes.add(bottom);
        planes.add(left);
        planes.add(right);
        planes.add(far);
        planes.add(near);

        Iterator<Plane> it = planes.iterator();
        while (it.hasNext()) {
            Plane plane = it.next();
            if (r.d.mul(-1).dot(plane.n) <= 0) {
                it.remove();
            }
        }

        Hit hit = null;

        for (Plane p : planes) {
            Hit temp = p.hit(r);
            if (hit == null || hit.t < temp.t) {
                hit = temp;
            }
        }

        Point3 p = r.at(hit.t);

        if (lbf.x <= p.x + Raytracer.DELTA && p.x <= run.x + Raytracer.DELTA && lbf.y <= p.y + Raytracer.DELTA && p.y <= run.y + Raytracer.DELTA && lbf.z <= p.z + Raytracer.DELTA  && p.z <= run.z + Raytracer.DELTA ) {
            return hit;
        } else {
            return null;
        }
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
