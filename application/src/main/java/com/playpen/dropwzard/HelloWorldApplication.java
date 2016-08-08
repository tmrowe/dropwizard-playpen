package com.playpen.dropwzard;

import com.playpen.dropwzard.configuration.HelloWorldConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Starting point for the application.
 *
 * Brings together the various bundles and commands that provide the projects functionality.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{
    public static void main(String... args) throws Exception
    {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "hello-world";
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