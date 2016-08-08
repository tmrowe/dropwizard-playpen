package com.playpen.dropwzard.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple object to hold the id and content of a Hello World message.
 *
 * This class will be serialized into a JSON object in the form:
 *  {
 *      "id": 1,
 *      "content": "Hi!"
 *  }
 */
public class Saying
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Saying.class);

    private long id;

    @Length(max = 3)
    private String content;

    public Saying()
    {
        LOGGER.debug("Saying object created with no arguments.");
    }

    public Saying(long id, String content)
    {
        LOGGER.debug("Saying object created with id: {}, and content: {}", id, content);
        this.id = id;
        this.content = content;
    }

    // Simple getters and setters.

    @JsonProperty
    public long getId()
    {
        return id;
    }

    @JsonProperty
    public String getContent()
    {
        return content;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}