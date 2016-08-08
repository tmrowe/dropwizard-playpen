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

    private String phrase;

    public Saying()
    {
        LOGGER.debug("Saying object created with no arguments.");
    }

    public Saying(long id, String content, String phrase)
    {
        LOGGER.debug("Saying object created with id: {}, content: {}, and phrase: {}", id, content, phrase);
        this.id = id;
        this.content = content;
        this.phrase = phrase;
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

    @JsonProperty
    public String getPhrase()
    {
        return phrase;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}