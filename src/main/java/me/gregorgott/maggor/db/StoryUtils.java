package me.gregorgott.maggor.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import me.gregorgott.maggor.api.json.StoryObject;
import org.bson.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to interact with the database for story-related operations.
 * The methods include creating, retrieving, and deleting stories.
 *
 * @author Gregor Gottschewski
 */
public class StoryUtils {

    /**
     * Creates a new story for the specified user.
     *
     * @param storyObject The {@code StoryObject} containing the details of the story to be added.
     */
    public static void createStory(StoryObject storyObject, MongoClient mongoClient) {
        mongoClient.getDatabase("maggor").getCollection("stories").insertOne(
                new Document("title", storyObject.getTitle())
                        .append("body", storyObject.getBody())
                        .append("topic", storyObject.getTopic())
                        .append("rating", storyObject.getRating())
                        .append("date", storyObject.getDate())
        );
    }

    public static List<StoryObject> getStories(String topic, MongoClient mongoClient) {
        ArrayList<StoryObject> stories = new ArrayList<>();

        mongoClient.getDatabase("maggor").getCollection("stories")
                .find(new Document("topic", topic))
                .forEach(story -> {
                    StoryObject storyObject = new StoryObject(story.getString("title"), story.getString("body"), story.getString("topic"));
                    storyObject.setRating(story.getInteger("rating"));
                    storyObject.setDate(LocalDate.ofInstant(story.getDate("date").toInstant(), ZoneId.systemDefault()));
                    stories.add(storyObject);
                });

        return stories;
    }

    public static void deleteStory(String title, String topic, MongoClient mongoClient) {
        mongoClient.getDatabase("maggor").getCollection("stories").deleteOne(
                Filters.and(Filters.eq("title", title), Filters.eq("topic", topic))
        );
    }
}
