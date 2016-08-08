package com.playpen.dropwzard;

import com.playpen.dropwzard.configuration.HelloWorldConfiguration;
import com.playpen.dropwzard.resources.HelloWorldResource;
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

    /**
     * Entry point for this DropWizard application.
     * @param args A list of command line arguments to run.
     * @throws Exception Thrown if any Exception was thrown by the underlying calls made by the application.
     */
    public static void main(String... args) throws Exception
    {
        LOGGER.info("Running Hello World Application.");

        if (args.length == 0)
        {
            LOGGER.debug("No arguments provided.");
        }
        else
        {
            LOGGER.debug("Application running with arguments:");
        }

        for (int index = 0 ; index < args.length ; index++)
        {
            LOGGER.debug("Argument {}: {}", index, args[index]);
        }

        new HelloWorldApplication().run(args);
    }

    /**
     * Returns the projects name.
     * @return The name of the project.
     */
    @Override
    public String getName()
    {
        String projectName = super.getName();
        LOGGER.debug("Returning project name: {}", projectName);
        return projectName;
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
    {
        super.initialize(bootstrap);
        LOGGER.debug("Initializing application with bootstrap = {}", bootstrap);
    }

    /**
     * When called registers the various elements of the application with DropWizard.
     * @param configuration The configuration class this application is set to use.
     * @param environment The environment to register application elements against.
     */
    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment)
    {
        LOGGER.debug("Running application with configuration = {}", configuration);
        LOGGER.debug("Running application with environment = {}", environment);

        // Register the HelloWorldResource with DropWizard.
        HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}