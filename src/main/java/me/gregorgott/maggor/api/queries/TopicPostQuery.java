package me.gregorgott.maggor.api.queries;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.QueryParam;

public class TopicPostQuery {

    @QueryParam("name")
    @NotBlank(message = "Topic name must not be blank")
    @Size(min = 1, max = 64, message = "Topic name must not be between 1 and 64")
    private String name;

    public String getName() {
        return name;
    }
}
