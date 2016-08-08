package com.playpen.dropwzard;

import com.playpen.dropwzard.configuration.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starting point for the application.
 *
 * Brings together the various bundles and commands that provide the projects functionality.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class);

    private static final String PROJECT_NAME = "hello-world";

    /**
     * Entry point for this DropWizard application.
     * @param args Arguments to pass into the application. For instance the configuration file to start with.
     * @throws Exception Thrown if any Exception was thrown by the underlying calls made by the application.
     */
    public static void main(String... args) throws Exception
    {
        LOGGER.info("Running Hello World Application.");
        LOGGER.debug("Application running with arguments = {}", args);
        new HelloWorldApplication().run(args);
    }

    /**
     * Returns the projects name.
     * @return The name of the project.
     */
    @Override
    public String getName()
    {
        LOGGER.debug("Returning project name: {}", PROJECT_NAME);
        return PROJECT_NAME;
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
    {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment)
    {
        // nothing to do yet
    }
}