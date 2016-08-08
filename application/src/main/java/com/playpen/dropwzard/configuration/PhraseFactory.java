package com.playpen.dropwzard.configuration;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A simple class to hold the content of a phrase.
 */
public class PhraseFactory
{
    @NotEmpty
    private String content;

    // Simple getters and setters.

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}