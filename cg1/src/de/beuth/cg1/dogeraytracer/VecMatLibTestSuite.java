package de.beuth.cg1.dogeraytracer;

import de.beuth.cg1.dogeraytracer.junit.Mat3x3Test;
import de.beuth.cg1.dogeraytracer.junit.Normal3Test;
import de.beuth.cg1.dogeraytracer.junit.Point3Test;
import de.beuth.cg1.dogeraytracer.junit.Vector3Test;
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
        Mat3x3Test.class
})

public class VecMatLibTestSuite {
}
