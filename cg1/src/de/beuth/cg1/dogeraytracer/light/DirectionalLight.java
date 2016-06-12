package de.beuth.cg1.dogeraytracer.light;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 31/05/16.
 * Project name is raytracer-doge.
 * This class is representing the Directional-light imitating the sunlight which shines evenly from the same direction
 */

public class DirectionalLight extends Light {

    /**
     * the direction of the DirectionalLight
     */
    public final Vector3 direction;

    /**
     * Constructor for the DirectionalLight Object
     * Creates a new instance of DirectionalLight with defined attributes.
     *
     * @param color value for the {@link Color} of the {@link Light}, if null throw new {@link IllegalArgumentException}
     * @param direction value for the direction of the DirectionalLight
     */
    public DirectionalLight(final Color color, final Vector3 direction) {
        super(color);
        this.direction = direction;
    }

    /**
     * This method calculates if the passed {@link Point3} point is hit by the light
     *
     * @param point to check if the {@link Point3} is hit by light, if null throw new {@link IllegalArgumentException}
     * @return true if point is hit, false if not
     */
    @Override
    public boolean illuminates(Point3 point) {
        if (point == null) throw new IllegalArgumentException("Param point cant be null");
        // -d⃗L;
        direction.mul(-1);
        return true;
    }

    /**
     * This method calculates the {@link Vector3} l⃗  which points to the light-source, for the passed {@link Point3} point
     *
     * @param point from which we calculate the {@link Vector3} that points to the light-source, if null throw new {@link IllegalArgumentException}
     * @return calculated {@link Vector3} l⃗  which points to the light-source
     */
    @Override
    public Vector3 directionFrom(Point3 point) {
        if (point == null) throw new IllegalArgumentException("Param point cant be null");
        return direction.mul(-1).normalized();
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "DirectionalLight{" +
                "direction=" + direction +
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

        DirectionalLight that = (DirectionalLight) o;

        return direction.equals(that.direction);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}
