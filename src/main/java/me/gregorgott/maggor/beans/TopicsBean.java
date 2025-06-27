package me.gregorgott.maggor.beans;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.gregorgott.maggor.api.json.TopicObject;
import me.gregorgott.maggor.db.AppDatabaseConnector;
import me.gregorgott.maggor.db.TopicUtils;
import me.gregorgott.maggor.utils.AppConfiguration;
import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class TopicsBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(TopicsBean.class.getName());
    @Inject
    private LoginBean loginBean;

    @Inject
    private AppConfiguration appConfiguration;

    private List<TopicObject> topics;
    private String newTopicName;
    private TopicObject selectedTopic;
    private TopicObject listTopic;
    private String newTopicPassword;
    private String topicPassword;

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

        if (!newTopicPassword.isBlank()) {
            topic.setPassword(BCrypt.hashpw(newTopicPassword, BCrypt.gensalt()));
            logger.log(Level.INFO, "New password:", topic.getPassword());
            newTopicName = "";
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            TopicUtils.addTopic(topic, mongoClient);
        }

        topics.add(topic);
    }

    public String viewStories(TopicObject topic) {
        this.listTopic = topic;

        if (topic.getPassword() == null) {
            this.selectedTopic = topic;
            return "stories";
        }

        PrimeFaces.current().executeScript("PF('password-topic-dialog').show();");
        return "";
    }

    public String viewSecretStories() {
        if (BCrypt.checkpw(topicPassword, listTopic.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwort ist falsch", "Probiere es nochmal :)")
            );

            this.selectedTopic = listTopic;
            this.topicPassword = "";
            return "stories";
        }

        this.topicPassword = "";
        return "";
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

    public String getNewTopicPassword() {
        return newTopicPassword;
    }

    public void setNewTopicPassword(String newTopicPassword) {
        this.newTopicPassword = newTopicPassword;
    }

    public String getTopicPassword() {
        return topicPassword;
    }

    public void setTopicPassword(String topicPassword) {
        this.topicPassword = topicPassword;
    }
}