package de.beuth.cg1.dogeraytracer.light;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by baetschjunge on 31/05/16.
 * Project name is raytracer-doge.
 * This Class is representing the PointLight which shines evenly in every direction
 */

public class PointLight extends Light {

    /**
     * the position of the PointLight
     */
    public final Point3 position;

    /**
     * Constructor for the PointLight Object
     * Creates a new instance of PointLight with defined attributes.
     *
     * @param color value for the {@link Color} of the {@link Light}, if null throw new {@link IllegalArgumentException}
     * @param position value for the position of the PointLight, if null throw new {@link IllegalArgumentException}
     */
    public PointLight(final Color color,final boolean castsShadow, final Point3 position) {
        super(color, castsShadow);
        if (position == null) throw new IllegalArgumentException("Param position of constructor can't be null");
        this.position = position;
    }

    /**
     * This method calculates if the passed {@link Point3} point is hit by the light
     *
     * @param point to check if the {@link Point3} is hit by light, if null throw new {@link IllegalArgumentException}
     * @return true if point is hit, false if not
     */
    @Override
    public boolean illuminates(final Point3 point, final World world) {
        if (point == null) throw new IllegalArgumentException("Param point cant be null");
        return true;
    }

    /**
     * This method calculates the {@link Vector3} l⃗  which points to the light-source, for the passed {@link Point3} point
     *
     * @param point from which we calculate the {@link Vector3} that points to the light-source, if null throw new {@link IllegalArgumentException}
     * @return calculated {@link Vector3} l⃗  which points to the light-source
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        if (point == null) throw new IllegalArgumentException("Param point cant be null");
        // l⃗ = P⃗l-P⃗r/|P⃗l-P⃗r|
        //return (position.sub(point)).normalized();
        return new Vector3(position.x - point.x, position.y - point.y, position.z - point.z).normalized();
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "PointLight{" +
                "position=" + position +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PointLight that = (PointLight) o;

        return position.equals(that.position);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}
