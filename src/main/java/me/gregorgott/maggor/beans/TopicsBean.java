package me.gregorgott.maggor.beans;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.gregorgott.maggor.api.json.TopicObject;
import me.gregorgott.maggor.db.AppDatabaseConnector;
import me.gregorgott.maggor.db.TopicUtils;
import me.gregorgott.maggor.utils.AppConfiguration;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TopicsBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private LoginBean loginBean;

    @Inject
    private AppConfiguration appConfiguration;

    private List<TopicObject> topics;
    private String newTopicName;
    private TopicObject selectedTopic;

    @PostConstruct
    public void init() {
        topics = new ArrayList<>();
        loadTopics();
    }

    public String loadTopics() {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            topics = TopicUtils.getTopics(mongoClient);
        }

        return "";
    }

    public void createTopic() {
        if (!loginBean.isLoggedIn() || newTopicName == null || newTopicName.trim().isEmpty()) {
            return;
        }

        TopicObject topic = new TopicObject(newTopicName, loginBean.getUsername());

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            TopicUtils.addTopic(topic, mongoClient);
        }

        newTopicName = "";
        topics.add(topic);
    }

    public String viewStories(TopicObject topic) {
        this.selectedTopic = topic;
        return "stories";
    }

    public void deleteTopic(TopicObject topic) {
        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            TopicUtils.deleteTopic(topic.getName(), loginBean.getUsername(), mongoClient);
        }
        topics.remove(topic);
    }

    public List<TopicObject> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicObject> topics) {
        this.topics = topics;
    }

    public String getNewTopicName() {
        return newTopicName;
    }

    public void setNewTopicName(String newTopicName) {
        this.newTopicName = newTopicName;
    }

    public TopicObject getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(TopicObject selectedTopic) {
        this.selectedTopic = selectedTopic;
    }
}