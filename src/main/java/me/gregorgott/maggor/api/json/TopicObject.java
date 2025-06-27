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

    @JsonbProperty("password")
    private String password;

    public TopicObject() {
    }

    public TopicObject(String name, String id) {
        this(name, id, null);
    }

    public TopicObject(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
