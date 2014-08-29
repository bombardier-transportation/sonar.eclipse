
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.bombardier.sonar.eclipse.EclipseSensor;

public class TestEclipseSensor {

    @Test
    public void testscanEclipseDirectory() {
        EclipseSensor sensor = new EclipseSensor();
        // SensorContext sensorContext = mock(SensorContext.class);
        // Metric metric = mock(Metric.class);
        File file =
            new File(File.separator + "home" + File.separator + "hjonsson" + File.separator + "svn" + File.separator
                + "ebitool-3.1" + File.separator + "plugins" + File.separator + "com.bombardier.compare");

        assertEquals(sensor.findEclipseRootDirectory(file).toString(), File.separator + "home" + File.separator + "hjonsson" + File.separator + "svn" + File.separator + "ebitool-3.1");
    }

}
