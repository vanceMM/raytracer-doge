package de.beuth.cg1.dogeraytracer.color;

/**
 * @author Benjamin Blekmann, Aryan Rezai, Valentin Risch
 *
 * This Class represents a Color in RGB Color Space.
 */
@SuppressWarnings("unused")
public class Color {

    /**
     *  color values for red, green & blue
     */
    public final double r;
    public final double g;
    public final double b;

    /**
     * Constructor for the Color Object
     * Creates a new instance of {@link Color} with defined attributes.
     *
     * @param r value for the red color as double
     * @param g value for the green color as double
     * @param b value for the blue color as double
     */
    public Color ( final double r, final double g , final double b) {

        if ((r < 0 && r > 1) || (g < 0 && g > 1) || (b < 0 && b > 1)) {
            throw new IllegalArgumentException("Color Values should be betwenn 0 and 1");
        }
        
        this.b = b;
        this.g = g;
        this.r = r;
    }

    /**
     * this method adds color to the existing color
     *
     * @param color Input of the Color that is added, if color is null throw new {@link IllegalArgumentException}
     * @return returns a new additive Color
     */
    public Color addColor(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color can't be null");
        return new Color(color.r + this.r, color.g + this.g, color.b + this.b);

    }

    /**
     * this method subtracts a color from the existing color
     *
     * @param color Input of the Color that is subtracted, if color is null throw new {@link IllegalArgumentException}
     * @return returns a new additive Color
     */
    public Color subColor(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color can't be null");
        return new Color(color.r-this.r, color.g-this.g, color.b-this.b);
    }

    /**
     * this method multiplies a color with the existing color
     *
     * @param color Input of the Color that is multiplied, if color is null throw new {@link IllegalArgumentException}
     * @return returns a new additive Color
     */
    public Color mulColor(final Color color) {
        if (color == null) throw new IllegalArgumentException("Param color can't be null");
        return new Color(color.r*this.r, color.g*this.g, color.b*this.b);
    }

    /**
     * this method multiplies the existing color with a passed scalar-value
     *
     * @param scalar Multiply the Color with a given Scalar
     * @return returns a new additive Color
     */
    public Color mulScalarColor(final double scalar) {
        return new Color(scalar*this.r, scalar*this.g, scalar*this.b);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        return Double.compare(color.r, r) == 0 && Double.compare(color.g, g) == 0 && Double.compare(color.b, b) == 0;

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
