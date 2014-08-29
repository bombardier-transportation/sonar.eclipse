package com.bombardier.sonar.eclipse;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class EclipseMetrics implements Metrics {

    public static final Metric NO_PLUGINS  = new Metric.Builder("no_plugins", "Plugins",
                                               Metric.ValueType.INT)
                                               .setDescription("Number of plugins")
                                               .setQualitative(false)
                                               .setDomain(CoreMetrics.DOMAIN_GENERAL)
                                               .create();

    public static final Metric NO_FEATURES = new Metric.Builder("no_features", "Features",
                                               Metric.ValueType.INT)
                                               .setDescription("Number of features")
                                               .setQualitative(false)
                                               .setDomain(CoreMetrics.DOMAIN_GENERAL)
                                               .create();

    // getMetrics() method is defined in the Metrics interface and is used by
    // Sonar to retrieve the list of new Metric
    public List<Metric> getMetrics() {
        return Arrays.asList(NO_FEATURES, NO_PLUGINS);
    }
}
