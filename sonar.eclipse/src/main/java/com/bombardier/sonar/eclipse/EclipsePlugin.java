package com.bombardier.sonar.eclipse;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.SonarPlugin;

/**
 * This class is the entry point for all extensions
 */
public class EclipsePlugin extends SonarPlugin {

    // This is where you're going to declare all your Sonar extensions
    public List<Class<? extends Extension>> getExtensions() {
        return Arrays.asList(EclipseMetrics.class, EclipseSensor.class, EclipseDashboardWidget.class);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
