package de.beuth.cg1.dogeraytracer.color;

/**
 * @author Benjamin Blekmann, Aryan Rezai, Valentin Risch
 *
 * This Class represents a Color in RGB Color Space.
 */
public class Color {

    /**
     *  color values for red, green & blue
     */
    public final double r;
    public final double g;
    public final double b;

    public Color ( double r, double g ,double b) {

        if ((r < 0 && r > 1) || (g < 0 && g > 1) || (b < 0 && b > 1)) {
            throw new IllegalArgumentException("Color Values should be betwenn 0 and 1");
        }
        
        this.b = b;
        this.g = g;
        this.r = r;
    }

    /**
     *
     * @param color Input of the Color that is added.
     * @return returns a new additive Color
     */
    public Color addColor(Color color) {

        return new Color(color.r + this.r, color.g + this.g, color.b + this.b);

    }

    /**
     *
     * @param color Input of the Color that is subtracted
     * @return returns a new additive Color
     */
    public Color subColor(Color color) {

        return new Color(color.r-this.r, color.g-this.g, color.b-this.b);
    }

    /**
     *
     * @param color Input of the Color that is subtracted
     * @return returns a new additive Color
     */
    public Color mulColor(Color color) {

        return new Color(color.r*this.r, color.g*this.g, color.b*this.b);
    }

    /**
     *
     * @param scalar Multiply the Color with a given Scalar
     * @return returns a new additive Color
     */
    public Color mulScalarColor(double scalar) {

        return new Color(scalar*this.r, scalar*this.g, scalar*this.b);

    }

    /**
     *
     * @return returns a String representaiton of the Color
     */
    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (Double.compare(color.r, r) != 0) return false;
        if (Double.compare(color.g, g) != 0) return false;
        return Double.compare(color.b, b) == 0;

    }

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
