package de.beuth.cg1.dogeraytracer;

import de.beuth.cg1.dogeraytracer.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Created by baetschjunge on 09/05/16.
 * Project name is Raytracer-doge.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Point3Test.class,
        Normal3Test.class,
        Vector3Test.class,
        Mat3x3Test.class,
        Mat4x4Test.class
})

public class VecMatLibTestSuite {
}
