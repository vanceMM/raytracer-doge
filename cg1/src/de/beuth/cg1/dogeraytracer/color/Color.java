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
     * @param r value for the red color as double, if r < 0 || > 1 clip r, if r isNaN throw new {@link IllegalArgumentException}
     * @param g value for the green color as double, if g < 0 || > 1 clip g, if g isNaN throw new {@link IllegalArgumentException}
     * @param b value for the blue color as double, if b < 0 || > 1 clip b, if b isNaN throw new {@link IllegalArgumentException}
     */
    public Color ( final double r, final double g , final double b) {
        if (Double.isNaN(r) || Double.isNaN(g) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Color Values should be betwenn 0 and 1");
        }
        if (r < 0 || r > 1 ) {
            if (r < 0 ) this.r = 0;
            else this.r = 1;
        } else this.r = r;

        if (g < 0 || g > 1) {
            if (g < 0) this.g = 0;
            else this.g = 1;
        } else this.g = g;

        if (b < 0 || b > 1) {
            if (b < 0) this.b = 0;
            else this.b = 1;
        } else this.b = b;

        //this.b = b;
        //this.g = g;
        //this.r = r;
    }

    /**
     * this method defines our colormodel
     * @return colormodel for rgb
     */
    public java.awt.Color renderInRGB(){
        final int r = (int) (255 * this.r);
        final int g = (int) (255 * this.g);
        final int b = (int) (255 * this.b);
        return new java.awt.Color(r,g,b);
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
     * @param scalar Multiply the Color with a given Scalar, if scalar is NaN throw new {@link IllegalArgumentException}
     * @return returns a new additive Color
     */
    public Color mulScalarColor(final double scalar) {
        if (Double.isNaN(scalar)) throw new IllegalArgumentException();
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
