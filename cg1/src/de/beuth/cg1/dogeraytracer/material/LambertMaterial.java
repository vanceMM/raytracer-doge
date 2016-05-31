package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by User on 31.05.2016.
 * Project name is raytracer-doge.
 * This is a class representing a LambertMaterial Object which represents a perfect diffuse reflecting Geometry
 */
public class LambertMaterial extends Material{

    /**
     * the {@link Color} of the LambertMaterial
     */
    public final Color color;

    /**
     * Constructor for the LambertMaterial Object
     * Creates a new instance of LambertMaterial with defined attributes.
     *
     * @param color value for {@link Color} of the LambertMaterial, if null throw new {@link IllegalArgumentException}
     */
    public LambertMaterial(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color of constructor can't be null");
        this.color = color;
    }

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit   on the {@link Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */
    @Override
    public Color colorFor(Hit hit, World world) {
        if(hit == null || world == null) throw new IllegalArgumentException("Param color of constructor can't be null ");
        return null;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "LambertMaterial{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LambertMaterial that = (LambertMaterial) o;

        return color.equals(that.color);

    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }
}
