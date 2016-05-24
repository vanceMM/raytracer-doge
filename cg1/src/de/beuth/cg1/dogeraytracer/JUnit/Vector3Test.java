package de.beuth.cg1.dogeraytracer.junit;

import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static java.lang.Math.*;

/**
 * Created by baetschjunge on 05/05/16.
 * Project name is Raytracer-doge.
 */
public class Vector3Test {

    //magnitude
    private double mag;
    //dot-product
    private Vector3 v1;
    private Vector3 v2;
    private Normal3 n1;
    private Normal3 n2;
    //mul & add & sub
    private Vector3 v3;
    private Normal3 n3;
    private Vector3 v4;
    private Vector3 v5;
    private Normal3 n4;
    //reflectOn
    private Vector3 v7;
    private Vector3 v8;
    private Vector3 v9;

    @Before
    public void setUp() throws Exception {
        //magnitude
        this.mag = new Vector3(1,1,1).magnitude;
        //dot-product
        this.v1 = new Vector3(1,0,0);
        this.n1 = new Normal3(1,0,0);
        this.v2 = new Vector3(0,1,0);
        this.n2 = new Normal3(0,1,0);
        //mul & add
        this.v3 = new Vector3(1,2,3);
        this.n3 = new Normal3(3,2,1);
        this.v4 = new Vector3(1,1,1);
        this.v5 = new Vector3(4,3,2);
        this.n4 = new Normal3(4,3,2);
        //reflectOn
        this.v7 = new Vector3(-0.707, 0.707, 0);
        this.v8 = new Vector3(0.707, 0.707, 0);
        this.v9 = new Vector3(1,2,1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getkMag() throws Exception {
        double check = sqrt(3);
        assertEquals(check, this.mag, 0.0001);
    }

    @Test
    public void addVector3() throws Exception {
        Vector3 resV = v4.add(v5);
        assertEquals(new Vector3(5,4,3), resV);
    }

    @Test
    public void addNormal3() throws Exception {
        Vector3 resV = v3.add(n3);
        assertEquals(new Vector3(4,4,4), resV);
    }

    @Test
    public void subNormal3() throws Exception {
        Vector3 resV = v4.sub(n4);
        assertEquals(new Vector3(-3,-2,-1), resV);
    }

    @Test
    public void mul() throws Exception {
        Vector3 resV = v3.mul(0.5);
        assertEquals(new Vector3(0.5, 1, 1.5), resV);
    }

    @Test
    public void x() throws Exception {
        Vector3 resV = v9.x(v5);
        assertEquals(new Vector3(1,2,-5), resV);
    }

    @Test
    public void dotNormal3v1() throws Exception {
        double res = v1.dot(n1);
        assertEquals(1.0, res, 0.00001);
    }

    @Test
    public void dotNormal3v2() throws Exception {
        double res = v1.dot(n2);
        assertEquals(0.0, res, 0.00001);
    }

    @Test
    public void dotVector3n1() throws Exception {
        double res = v1.dot(v1);
        assertEquals(1.0, res, 0.00001);
    }

    @Test
    public void dotVector3n2() throws Exception {
        double res = v1.dot(v2);
        assertEquals(0.0, res, 0.00001);
    }

    @Test
    public void normalized() throws Exception {
    }

    @Test
    public void asNormal() throws Exception {
        Normal3 resN = v5.asNormal();
        assertEquals(new Normal3(4,3,2), resN);
    }

    @Test
    public void reflectOn1() throws Exception {
        Vector3 resV = v7.reflectOn(n2);
        assertEquals(new Vector3(0.707,0.707,-0.0), resV);
    }
    @Test
    public void reflectOn2() throws Exception {
        Vector3 resV = v8.reflectOn(n1);
        assertEquals(new Vector3(0.707, -0.707, -0.0), resV);
    }

}