package de.beuth.cg1.dogeraytracer.JUnit;

import de.beuth.cg1.dogeraytracer.vecmatlib.Mat3x3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by baetschjunge on 05/05/16.
 * Project name is raytracer-doge.
 */
public class Mat3x3Test {

    private Mat3x3 eMat1;
    private Mat3x3 mat1;
    private Vector3 v1;
    private Vector3 v2;
    private Point3 p1;

    @Before
    public void setUp() throws Exception {
        this.eMat1 = new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);
        this.v1 = new Vector3(3,2,1);
        this.p1 = new Point3(3,2,1);
        this.mat1 = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        this.v2 = new Vector3(8,8,8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void mulMat3x3() throws Exception {
        Mat3x3 resM = mat1.mul(eMat1);
        assertEquals(new Mat3x3(1,2,3,4,5,6,7,8,9), resM);
    }

    @Test
    public void mulVector3() throws Exception {
        Vector3 resV = eMat1.mul(v1);
        assertEquals(new Vector3(3,2,1), resV);
    }

    @Test
    public void mulPoint3() throws Exception {
        Point3 resP = eMat1.mul(p1);
        assertEquals(new Point3(3,2,1), resP);
    }

    @Test
    public void changeCol1() throws Exception {
        Mat3x3 resM = mat1.changeCol1(v2);
        assertEquals(new Mat3x3(8, 8, 8, 4, 5, 6, 7, 8, 9), resM);
    }

    @Test
    public void changeCol2() throws Exception {
        Mat3x3 resM = mat1.changeCol2(v2);
        assertEquals(new Mat3x3(1, 2, 3, 8, 8, 8, 7, 8, 9), resM);
    }

    @Test
    public void changeCol3() throws Exception {
        Mat3x3 resM = mat1.changeCol3(v2);
        assertEquals(new Mat3x3(1, 2, 3, 4, 5, 6, 8, 8, 8), resM);
    }

}