package de.beuth.cg1.dogeraytracer.light;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 31/05/16.
 * Project name is raytracer-doge.
 * This class is representing the SpotLight which shines from a specific origin in a specific direction within a specific angle
 */

public class SpotLight extends Light {

    /**
     * the position of the SpotLight
     */
    public final Point3 position;
    /**
     * the direction of the SpotLight
     */
    public final Vector3 direction;
    /**
     * the angle of the SpotLight
     */
    public final double halfAngle;

    /**
     * Constructor for the Light Object
     * Creates a new instance of Light with defined attributes.
     *  @param color value for the {@link Color} of the {@link Light}, if null throw new {@link IllegalArgumentException}
     * @param position value for the position of the SpotLight, if null throw new {@link IllegalArgumentException}
     * @param direction value for the direction of the SpotLight, if null throw new {@link IllegalArgumentException}
     * @param halfAngle value for the angle of the SpotLight, if NaN throw new {@link IllegalArgumentException}
     */
    public SpotLight(Color color, Point3 position, Vector3 direction, double halfAngle) {
        super(color);
        if (position == null || direction == null || Double.isNaN(halfAngle)) throw new IllegalArgumentException("Params of constructor cant be null or NaN");
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
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
        // -l⃗ dot d⃗l<cos(alpha)
        final Vector3 pl = point.sub(position);
        final Vector3 dl = direction;
        final double angle = pl.dot(dl);
        return angle <= halfAngle;
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
        // l⃗ = P⃗l-P⃗r/|P⃗l-P⃗r|
        return position.sub(point).normalized();
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "SpotLight{" +
                "position=" + position +
                ", direction=" + direction +
                ", halfAngle=" + halfAngle +
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

        SpotLight spotLight = (SpotLight) o;

        return Double.compare(spotLight.halfAngle, halfAngle) == 0 && position.equals(spotLight.position) && direction.equals(spotLight.direction);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + position.hashCode();
        result = 31 * result + direction.hashCode();
        temp = Double.doubleToLongBits(halfAngle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
