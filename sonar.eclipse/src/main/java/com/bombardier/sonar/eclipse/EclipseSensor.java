package com.bombardier.sonar.eclipse;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

public class EclipseSensor implements Sensor {
    Logger logger = LoggerFactory.getLogger(getClass());

    public boolean shouldExecuteOnProject(Project project) {
        // this sensor is executed on any type of project
        return true;
    }

    public void analyse(Project project, SensorContext sensorContext) {
        // Only run this sensor on the root project
        // The sub modules are the actual plugins/features
        if (project.isRoot()) {
            List<Project> modules = project.getModules();

            if (!modules.isEmpty()) {
                File moduleBaseDir = modules.get(0).getFileSystem().getBasedir();
                logger.info("Searching for Eclipse root in: " + moduleBaseDir.toString());

                // Can we find the eclipse root directory here?
                File eclipseRootDir = findEclipseRootDirectory(moduleBaseDir);

                if (eclipseRootDir != null) {
                    int numberOffeatures =
                        getNumberOfDirectories(new File(eclipseRootDir + File.separator + "features"));
                    sensorContext.saveMeasure(EclipseMetrics.NO_FEATURES, new Double(numberOffeatures));

                    int numberOfPlugins = getNumberOfDirectories(new File(eclipseRootDir + File.separator + "plugins"));
                    sensorContext.saveMeasure(EclipseMetrics.NO_PLUGINS, new Double(numberOfPlugins));
                }
            }
        }
    }

    public File findEclipseRootDirectory(File moduleBaseDir) {
        if (moduleBaseDir.getAbsolutePath().matches(".*plugins.*")) {
            return getEclipseDirectory(moduleBaseDir);
        }

        return null;
    }

    private File getEclipseDirectory(File moduleBaseDir) {
        File directory = moduleBaseDir;

        while ((directory = directory.getParentFile()) != null) {
            if (directory.getName().equals("plugins")) {
                logger.info("Found eclipse root in " + directory.getParent());
                return directory.getParentFile();
            }
        }

        return null;
    }

    private int getNumberOfDirectories(File directory) {
        if (directory.exists()) {
            int metric = directory.list().length;
            return metric;
        } else {
            logger.warn("Could not find any eclipse directories in " + directory);
            return 0;
        }
    }
}
