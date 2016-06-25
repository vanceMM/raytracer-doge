package de.beuth.cg1.dogeraytracer.light;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by baetschjunge on 31/05/16.
 * Project name is raytracer-doge.
 * This is an abstract class representing Light in 3d space
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class Light {

    /**
     * the color of the light as {@link Color}
     */
    public final Color color;

    /**
     * this field defines if a light is casting a shadow or not
     */
    public final boolean castsShadow;

    /**
     * Constructor for the Light Object
     * Creates a new instance of Light with defined attributes.
     *
     * @param color value for the {@link Color} of the {@link Light}, if null throw new {@link IllegalArgumentException}
     * @param castsShadow value for a boolean, if true the light casts shadow, if false it doesn't
     */
    public Light(final Color color, final boolean castsShadow) {
        if (color == null) throw new IllegalArgumentException("Param color of constructor cant be null");
        this.color = color;
        this.castsShadow = castsShadow;
    }

    /**
     * This method calculates if the passed {@link Point3} point is hit by the light
     *
     * @param point to check if the {@link Point3} is hit by light, if null throw new {@link IllegalArgumentException}
     * @return true if point is hit, false if not
     */
    public abstract boolean illuminates(final Point3 point, final World world);

    /**
     * This method calculates the {@link Vector3} l⃗  which points to the light-source, for the passed {@link Point3} point
     *
     * @param point from which we calculate the {@link Vector3} that points to the light-source, if null throw new {@link IllegalArgumentException}
     * @return calculated {@link Vector3} l⃗  which points to the light-source
     */
    public abstract Vector3 directionFrom(final Point3 point);

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Light{" +
                "color=" + color +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Light light = (Light) o;

        return color.equals(light.color);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return color.hashCode();
    }
}

