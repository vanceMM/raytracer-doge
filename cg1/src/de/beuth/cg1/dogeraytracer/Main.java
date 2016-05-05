package de.beuth.cg1.dogeraytracer;

import de.beuth.cg1.dogeraytracer.vecmatlib.Mat3x3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;

/**
 * Created by baetschjunge on 03/05/16.
 */
public class Main {
    public static void main(String[] args) {

        //----------- Point3 ------
        Point3 testPoint = new Point3(2,2,2);
        System.out.println(testPoint);


        //----------- Vector3 -----

        Vector3 testVec = new Vector3(0,1,0);
        Vector3 testVec2 = new Vector3(-1,0,0);
        //double c = 2;


        Normal3 addTestNormal3 = testVec.asNormal();
        System.out.println(addTestNormal3);

        Vector3 addTestVector3 = testVec.xProduct(testVec2);
        System.out.println(addTestVector3);


        //-------------- mat3x3 -------
        Mat3x3 testMat1 = new Mat3x3(0.707, 0.707, 0, -0.707, 0.707, 0, 0, 0, 1);
        System.out.println(testMat1);
    }
}
