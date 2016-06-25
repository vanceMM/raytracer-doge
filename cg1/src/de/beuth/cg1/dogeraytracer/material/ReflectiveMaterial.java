package de.beuth.cg1.dogeraytracer.material;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.geometry.Geometry;
import de.beuth.cg1.dogeraytracer.geometry.Hit;
import de.beuth.cg1.dogeraytracer.world.World;

/**
 * Created by baetschjunge on 15/06/16.
 * Project name is raytracer-doge.
 *
 * This class implements a perfect reflecting material
 */
@SuppressWarnings("WeakerAccess")
public class ReflectiveMaterial extends Material {

    /**
     * diffuse {@link Color} for the material
     */
    public final Color diffuse;
    /**
     * specular {@link Color} for the material
     */
    public final Color specular;
    /**
     * exponent of the specular light value
     */
    public final int exponent;
    /**
     * reflection {@link Color} for the material (?)
     */
    public final Color reflection;

    /**
     * Constructor for the ReflectiveMaterial Object
     * Creates a new instance of ReflectiveMaterial with defined attributes.
     *
     * @param diffuse value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     * @param specular value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     * @param exponent value for the exponent of specular
     * @param reflection value for {@link Color} of the material, if null throw new {@link IllegalArgumentException}
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        if (diffuse == null || specular == null || reflection == null) throw new IllegalArgumentException("params of constructor cant be null or NaN");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
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
        return null;
    }
}
