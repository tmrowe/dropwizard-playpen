package com.playpen.dropwzard.configuration;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Primary configuration class for the project.
 *
 * Note:
 * Primitive fields defined in the primary configuration class will be at the root level of the associated
 * configuration file.
 *
 * Classes stored as fields will need to be prefaced with the fields name in the configuration file to map as expected.
 *
 * The @NotEmpty annotations will cause an exception to be thrown if the values for the marked fields are not included
 * in the configuration class.
 *
 * The mapping between this class and the configuration file is conducted by Jackson. Those we can use the Jackson
 * validation annotations here.
 */
public class HelloWorldConfiguration extends Configuration
{
    @NotEmpty
    private String template;

    // Default value displayed for the users name if no name is given.
    @NotEmpty
    private String defaultName = "Stranger";

    @Valid
    @NotNull
    private final PhraseFactory phrase = new PhraseFactory();

    // Simple getters and setters.

    @JsonProperty
    public String getTemplate()
    {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template)
    {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName()
    {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name)
    {
        this.defaultName = name;
    }

    @JsonProperty
    public String getPhrase()
    {
        return phrase.getContent();
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}