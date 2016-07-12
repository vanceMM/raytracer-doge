package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.raytracer.Tracer;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by User on 31.05.2016.
 * Project name is raytracer-doge.
 * This is an abstract class representing Material in 3d space
 */
public abstract class Material {

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit on the {@link de.beuth.cg1.dogeraytracer.geometry.Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */
    public abstract Color colorFor(final Hit hit, final World world, Tracer tracer);

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Material{}";
    }
}
