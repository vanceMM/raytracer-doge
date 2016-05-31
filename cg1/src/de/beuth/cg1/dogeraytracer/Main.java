package de.beuth.cg1.dogeraytracer;

import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;

/**
 * Created by baetschjunge on 03/05/16.
 * Project name is Raytracer-doge.
 */
public class Main {
    public static void main(String[] args) {
        /*
        Vector3 g = new Vector3(-4,-4,-4);
        Vector3 t = new Vector3(0,1,0);

        System.out.println(g.magnitude);
        System.out.println(g.normalized());

        Vector3 w = g.normalized().mul(-1);
        Vector3 u = t.x(w).normalized();

        System.out.println("w:"+w);
        System.out.println("u:"+u);

        Vector3 v = w.x(u);
        System.out.println("v:"+v);

        double s = 3.0;
        double aR = 1.6;
        int width = 1920;
        int heigth = 1200;
        int px = 1000;
        int py = 800;

        Point3 e = new Point3(4,4,4);

        Point3 o = e.add(u.mul(aR*s*((px-(width-1/2))/width-1))).add(v.mul((py-(heigth-1/2))/heigth-1));
        System.out.println("o :"+o);

        Vector3 dir = new Vector3(-0.5773502691896258, -0.5773502691896258, -0.5773502691896258);

        //kugel hit
        Point3 center = new Point3(-1,-1,-1);
        double radius = 3;

        double a,b,c,dis,t1,t2;
        a = dir.dot(dir);                                                   // d⃗ • d⃗
        b = dir.dot((o.sub(center)).mul(2));                              // d⃗ • [2(⃗o − ⃗c)]
        c = o.sub(center).dot(o.sub(center)) - Math.pow(radius,2);      // ( ⃗o − ⃗c ) • ( ⃗o − ⃗c ) − r 2

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        dis = Math.pow(b,2) - 4 * a *c;

        System.out.println("dis: "+dis);
        */
    }
}
