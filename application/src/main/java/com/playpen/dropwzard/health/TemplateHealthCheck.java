package com.playpen.dropwzard.health;

import com.codahale.metrics.health.HealthCheck;
import com.playpen.dropwzard.configuration.HelloWorldConfiguration;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides health checks for the template field of the {@link HelloWorldConfiguration} class.
 */
public class TemplateHealthCheck extends HealthCheck
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateHealthCheck.class);

    private final String template;

    public TemplateHealthCheck(String template)
    {
        LOGGER.debug("Created TemplateHealthCheck with template: {}", template);
        this.template = template;
    }

    /**
     * An example health check that tests:
     * - That the given template is well-formatted.
     * - That the template contains the 'TEST' String.
     * @return A {@link Result} object, indicating whether this check passed or failed.
     */
    @Override
    protected Result check()
    {
        String saying = String.format(template, "TEST");
        Result result;
        if (saying.contains("TEST"))
        {
            result = Result.healthy();
        }
        else
        {
            result = Result.unhealthy("template doesn't include a name");
        }
        LOGGER.debug("Returning health check result: {}", result);
        return result;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}