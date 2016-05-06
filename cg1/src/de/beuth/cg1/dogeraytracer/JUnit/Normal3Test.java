package de.beuth.cg1.dogeraytracer.JUnit;

import de.beuth.cg1.dogeraytracer.vecmatlib.Normal3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Vector3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by baetschjunge on 05/05/16.
 * Project name is raytracer-doge.
 */
public class Normal3Test {
    private Normal3 n1;
    private Normal3 n2;
    private Normal3 n3;

    private Vector3 v1;
    private Vector3 v2;

    @Before
    public void setUp() throws Exception {
        this.n1 = new Normal3(1,2,3);
        this.n2 = new Normal3(3,2,1);
        this.n3 = new Normal3(1,0,0);

        this.v1 = new Vector3(1,0,0);
        this.v2 = new Vector3(0,1,0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mul() throws Exception {
        Normal3 resN = n1.mul(0.5);
        assertEquals(new Normal3(0.5,1,1.5), resN);
    }

    @Test
    public void add() throws Exception {
        Normal3 resN = n1.add(n2);
        assertEquals(new Normal3(4,4,4), resN);
    }

    @Test
    public void dotV1() throws Exception {
        double res = n3.dot(v1);
        assertEquals(1.0, res, 0.00001);
    }

    @Test
    public void dotV2() throws Exception {
        double res = n3.dot(v2);
        assertEquals(0, res, 0.00001);
    }
}