package de.beuth.cg1.dogeraytracer.junit;

import de.beuth.cg1.dogeraytracer.vecmatlib.*;

import static org.junit.Assert.*;

/**
 * Created by baetschjunge on 05/05/16.
 * Project name is raytracer-doge.
 */
public class Point3Test {

    private Point3 p1;
    private Point3 p2;
    private Vector3 v1;

    @org.junit.Before
    public void setUp() throws Exception {
        this.p1 = new Point3(1,1,1);
        this.p2 = new Point3(2,2,0);
        this.v1 = new Vector3(4,3,2);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void subPoint3() throws Exception {
        Vector3 resV = p1.sub(p2);
        assertEquals(new Vector3(-1, -1, 1), resV);
    }

    @org.junit.Test
    public void subVector3() throws Exception {
        Point3 resP = p1.sub(v1);
        assertEquals(new Point3(-3,-2,-1), resP);
    }

    @org.junit.Test
    public void add() throws Exception {
        Point3 resP = p1.add(v1);
        assertEquals(new Point3(5,4,3), resP);
    }
}