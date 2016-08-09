package com.playpen.dropwzard.cli;

import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example command that can be executed against this DropWizard application.
 */
public class ExampleCommand extends Command
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleCommand.class);

    /**
     * Create a new command with the name 'hello' and the description 'Prints a greeting'.
     * The name is what will be typed on the command line to execute this command.
     */
    public ExampleCommand()
    {
        super("hello", "Prints a greeting");
        LOGGER.debug("Create new command with name: Hello, and description: Prints a greeting.");
    }

    /**
     * Configures this command to have a command required line argument -u/--user.
     * @param subparser This object is used to add a sub command.
     */
    @Override
    public void configure(Subparser subparser)
    {
        // Add a command line option
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("The user of the program");
    }

    /**
     * This code is executed when the user runs the command.
     * @param bootstrap An object onto which {@link Command}s or {@link AssetsBundle}s can be added.
     * @param namespace An object which holds the values provided to the application as arguments.
     * @throws Exception If the Command encountered an error when attempting to run.
     */
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception
    {
        System.out.println("Hello " + namespace.getString("user"));
    }
}