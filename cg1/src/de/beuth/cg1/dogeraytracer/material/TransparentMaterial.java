package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by baetschjunge on 15/06/16.
 * Project name is raytracer-doge.
 *
 * This class implements a perfect transparent / refracting material
 */
public class TransparentMaterial extends Material {

    /**
     * index for the refraction of the material
     */
    public final double indexOfRefraction;

    /**
     * Constructor for the ReflectiveMaterial Object
     * Creates a new instance of ReflectiveMaterial with defined attributes.
     *
     * @param indexOfRefraction value for the refraction of the material, if NaN throw new {@link IllegalArgumentException}
     */
    public TransparentMaterial(final double indexOfRefraction) {
        if (Double.isNaN(indexOfRefraction)) throw new IllegalArgumentException("params of constructor cant be null");
        this.indexOfRefraction = indexOfRefraction;
    }

    /**
     * This method calculates the {@link Color} for a passed {@link Hit} Object in the given {@link World}
     *
     * @param hit   on the {@link Geometry}, if null throw new {@link IllegalArgumentException}
     * @param world is used for determining the light sources, if null throw new {@link IllegalArgumentException}
     * @return {@link Color} of the {@link Hit} Object
     */
    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        return null;
    }
}
