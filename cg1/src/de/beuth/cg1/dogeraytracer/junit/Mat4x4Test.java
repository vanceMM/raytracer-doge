package de.beuth.cg1.dogeraytracer.junit;

import de.beuth.cg1.dogeraytracer.vecmatlib.Mat3x3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Mat4x4;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by baetschjunge on 05/05/16.
 * Project name is Raytracer-doge.
 */
public class Mat4x4Test {

    private Mat4x4 eMat1;
    private Mat4x4 mat1;
    private Vector3 v1;
    private Vector3 v2;
    private Point3 p1;
    private double determinant;

    @Before
    public void setUp() throws Exception {
        this.eMat1 = new Mat4x4(1,1,0,0,0,1,2,0,4,1,1,1,0,3,0,1);
        this.v1 = new Vector3(3,2,1);
        this.p1 = new Point3(3,2,1);
        this.mat1 = new Mat4x4(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        this.v2 = new Vector3(8,8,8);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void mulMat4x4() throws Exception {
        Mat4x4 resM = mat1.mul(eMat1);
        assertEquals(new Mat4x4(6,8,10,12,23,26,29,32,31,38,45,52,28,32,36,40), resM);
    }

    @Test
    public void transposeMat4x4() throws Exception {
        Mat4x4 resM = mat1.transposed();
        assertEquals(new Mat4x4(1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,16), resM);
    }

}