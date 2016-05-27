package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;

/**
 * Created by baetschjunge on 26/05/16.
 * Project name is raytracer-doge.
 * This is Class representing AxisAlignedBoxes Objects in 3d Space
 */
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
    }

    /**
     * This Methods takes a {@link Ray} as inputs and calculates the intersection between the {@link Ray} and the Geometry Object.
     *
     * @param r passed {@link Ray} that hits the Object
     * @return Hit Object which represents the Intersection between the AxisAlignedBox and the given {@link Ray}.
     */
    @Override
    public Hit hit(Ray r) {
        return null;
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

        if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) return false;
        return run != null ? run.equals(that.run) : that.run == null;

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
