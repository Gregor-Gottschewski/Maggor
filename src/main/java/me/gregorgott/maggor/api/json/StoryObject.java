package me.gregorgott.maggor.api.json;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StoryObject {

    @NotNull
    @JsonbProperty("title")
    private String title;

    @NotNull
    @JsonbProperty("body")
    private String body;

    @NotNull
    @JsonbProperty("topic")
    private String topic;

    @JsonbProperty("rating")
    private int rating;

    @NotNull
    @JsonbProperty("date")
    private LocalDate date;

    public StoryObject() {
    }

    public StoryObject(String title, String body, String topic) {
        this.title = title;
        this.body = body;
        this.topic = topic;
        this.rating = 0;
        this.date = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }
}
