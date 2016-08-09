package com.playpen.dropwzard.application;

import com.playpen.dropwzard.cli.ExampleCommand;
import com.playpen.dropwzard.configuration.HelloWorldConfiguration;
import com.playpen.dropwzard.health.TemplateHealthCheck;
import com.playpen.dropwzard.resource.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.cli.Command;
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

    /**
     * Called to bootstrap the application.
     *
     * Note:
     * We would register the paths to static assets (e.g. JavaScript, CSS, HTML etc.) here.
     * @param bootstrap An object onto which {@link Command}s or {@link AssetsBundle}s can be added.
     */
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap)
    {
        super.initialize(bootstrap);
        LOGGER.debug("Initializing application with bootstrap = {}", bootstrap);

        // Register static web content.
        bootstrap.addBundle(new AssetsBundle("/webapp", "/", "index.html"));
        bootstrap.addBundle(new AssetsBundle("/webapp/assets/css/", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/webapp/assets/img/", "/img", null, "img"));
        bootstrap.addBundle(new AssetsBundle("/webapp/assets/js/", "/js", null, "js"));

        // Register custom Commands.
        bootstrap.addCommand(new ExampleCommand());
    }

    /**
     * When called registers the various elements of the application with DropWizard.
     *
     * Note:
     * Any complicated to construct classes should be extracted out of the run method and build in a factory class
     * which can then be called here in the run method.
     *
     * @param configuration The configuration class this application is set to use.
     * @param environment The environment to register application elements against.
     */
    @SuppressWarnings ("FeatureEnvy")
    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment)
    {
        LOGGER.debug("Running application with configuration = {}", configuration);
        LOGGER.debug("Running application with environment = {}", environment);

        // Register the HelloWorldResource with DropWizard.
        HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                configuration.getPhrase()
        );
        environment.jersey().register(resource);

        // Register the health check for the template.
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}