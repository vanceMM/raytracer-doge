package de.beuth.cg1.dogeraytracer.camera;

import de.beuth.cg1.dogeraytracer.color.Color;
import de.beuth.cg1.dogeraytracer.raytracer.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 24/07/16.
 * Project name is raytracer-doge.
 */
@SuppressWarnings("ALL")
public class FishEyeCam extends Camera {

    public final double a;

    /**
     * Constructor for the Camera Object
     * Creates a new instance of {@link Camera} with defined attributes.
     *  @param e the value of the e {@link Point3}, if null throw new {@link IllegalArgumentException}
     * @param g the value of the g {@link Vector3}, if null throw new {@link IllegalArgumentException}
     * @param t the value of the t {@link Vector3}, if null throw new {@link IllegalArgumentException}
     * @param a
     */
    public FishEyeCam(Point3 e, Vector3 g, Vector3 t, double a) {
        super(e, g, t);
        this.a = a;
    }

    /**
     * This Method calculates a {@link Ray} for every pixel of the Image for a {@link Camera}
     *
     * @param width is the passed width of the Image as int
     * @param height is the passed height of the Image as int
     * @param x coordinate of the pixel of the Image as int
     * @param y coordinate of the pixel of the Image as int
     * @return {@link Ray} Object
     */
    @Override
    public Ray rayFor(int width, int height, int x, int y) {
        if (Double.isNaN(width) || Double.isNaN(height) || Double.isNaN(x) || Double.isNaN(y)) throw new IllegalArgumentException("Params can't be NaN");
        // vec(r) = -vec(w)*(h/2)/tan(alpha)+(px-(w-1)/2)*vec(u)+(py-(h-1)/2)*vec(v)

        final double fov = 2.0 * a;


        // Transform the pixel coordinates into normalized coordinates
        double xn = 1.0*x/(width-1.0);
        double yn = 1.0*y/(height-1.0);

        final double r = Math.sqrt((xn*xn) + (yn*yn));

        //System.out.println(r);
        double sin = yn / r;
        double cos = xn / r;

        if ( r <= 1 && r > 0) {
            double phi;
            phi = r * a;


            Vector3 d = new Vector3(((Math.sin(phi) * cos * u.x) + (Math.sin(phi) * sin * v.x - Math.cos(phi)) * w.x),
                    ((Math.sin(phi) * cos * u.y) + (Math.sin(phi) * sin * v.y - Math.cos(phi)) * w.y),
                    ((Math.sin(phi) * cos * u.z) + (Math.sin(phi) * sin * v.z - Math.cos(phi)) * w.z));
        //System.out.println(d);

/*
            if ( xn >= 0.0 ){
                phi = Math.asin(yn/r);
            }
            else if ( xn < 0.0){
                phi = Math.PI - Math.asin(yn/r);
            }
            else {
                phi = 0.0;
            }
            double thetha = r*fov;

            Vector3 d2 = new Vector3(Math.sin(a)*Math.cos(phi), Math.sin(a)*Math.sin(phi), Math.cos(a));
*/
            return new Ray(e, d);
       }
        else {
            return new Ray(e, w);
             }

        //return calcPerspective(x, y, width, height);

    }

     Ray calcPerspective(int x, int y, int width, int height){
         final Vector3 px = u.mul(x-((width-1)/2));													//    u*(pz-(w-1/2))
         final Vector3 py = v.mul(y-((height-1)/2));													//    v*(py-(h-1/2))
         final Vector3 ray = this.w.mul(-1).mul((height/2)/(Math.tan(a/2.0))).add(px).add(py);		//    r = w*(-((h/2)/tan(alpha))) + o

         final Vector3 d = ray.normalized();														//    d = r/|r|

         return new Ray(e, d);
    }

}

