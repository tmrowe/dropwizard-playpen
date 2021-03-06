package com.playpen.dropwzard.resource;

import com.codahale.metrics.annotation.Timed;
import com.playpen.dropwzard.api.Saying;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

/**
 * The Resource class maps a data representation object in this case {@link Saying} to an end point that
 * can be called by a client.
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    private final String template;
    private final String defaultName;
    private final String phrase;
    private final AtomicLong counter;

    /**
     * Build a new instance of the this class with the given template and defaultName.
     * @param template The template containing the message content used to build the {@link Saying} object.
     * @param defaultName THe default name to use if none is given.
     */
    public HelloWorldResource(String template, String defaultName, String phrase)
    {
        LOGGER.debug("Created new HelloWorldResource with template: {}, and defaultName: {}", template, defaultName);
        this.template = template;
        this.defaultName = defaultName;
        this.phrase = phrase;
        this.counter = new AtomicLong();
    }

    /**
     * Exposes the functionality to display the Hello World message to the user.
     * @param name The name of the user.
     * @return A JSON object containing the users id and name.
     */
    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name)
    {
        LOGGER.info("Say Hello called.");
        String value = String.format(template, name.orElse(defaultName));
        Saying saying = new Saying(counter.incrementAndGet(), value, phrase);

        LOGGER.debug("Returning saying: {}", saying);
        return saying;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}