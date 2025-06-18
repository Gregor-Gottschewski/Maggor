package me.gregorgott.maggor.api.json;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

public class TopicObject {
    @NotNull
    @JsonbProperty("name")
    private String name;

    @NotNull
    @JsonbProperty("id")
    private String id;

    public TopicObject() {
    }

    public TopicObject(String name) {
        this(name, null);
    }

    public TopicObject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
