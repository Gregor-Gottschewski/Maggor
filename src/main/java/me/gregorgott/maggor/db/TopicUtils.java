package me.gregorgott.maggor.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import me.gregorgott.maggor.TopicNotFoundException;
import me.gregorgott.maggor.api.json.TopicObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TopicUtils {
    public static void addTopic(TopicObject topic, MongoClient mongoClient) {
        mongoClient.getDatabase("maggor").getCollection("topics").insertOne(
                new Document("topic", topic.getName())
                        .append("password", topic.getPassword())
        );
    }

    public static List<TopicObject> getTopics(MongoClient mongoClient) {
        ArrayList<TopicObject> topics = new ArrayList<>();

        mongoClient.getDatabase("maggor")
                .getCollection("topics")
                .find()
                .into(new ArrayList<>())
                .forEach(topic -> topics.add(new TopicObject(topic.getString("topic"),
                        topic.getObjectId("_id").toHexString(), topic.getString("password"))
                ));

        return topics;
    }

    public static TopicObject getTopicInformation(String id, MongoClient mongoClient) throws TopicNotFoundException {
        Document topic = mongoClient.getDatabase("maggor").getCollection("topics").find(
                new Document("_id", id)
        ).first();

        if (topic == null) {
            throw new TopicNotFoundException(id);
        }

        return new TopicObject(topic.getString("name"), topic.getObjectId("_id").toHexString());
    }

    public static void deleteTopic(String topicName, String user, MongoClient mongoClient) throws TopicNotFoundException {
        mongoClient.getDatabase("maggor").getCollection("topics")
                .deleteOne(new Document("topic", topicName).append("user", user));
        mongoClient.getDatabase("maggor").getCollection("stories")
                .deleteMany(Filters.eq("topic", topicName));
    }
}
