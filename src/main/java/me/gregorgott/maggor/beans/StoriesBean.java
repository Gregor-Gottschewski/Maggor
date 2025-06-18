package me.gregorgott.maggor.beans;

import com.mongodb.client.MongoClient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import me.gregorgott.maggor.api.json.StoryObject;
import me.gregorgott.maggor.db.AppDatabaseConnector;
import me.gregorgott.maggor.db.StoryUtils;
import me.gregorgott.maggor.utils.AppConfiguration;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class StoriesBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private AppConfiguration appConfiguration;

    @Inject
    private LoginBean loginBean;

    @Inject
    private TopicsBean topicsBean;

    private List<StoryObject> stories;
    private StoryObject newStory;
    private StoryObject selectedStory;

    public StoriesBean() {
        stories = new ArrayList<>();
        selectedStory = new StoryObject();
        newStory = new StoryObject();
    }

    public String loadStories() {
        if (!loginBean.isLoggedIn() || topicsBean.getSelectedTopic() == null) {
            return "login?faces-redirect=true";
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            stories = StoryUtils.getStories(topicsBean.getSelectedTopic().getName(), mongoClient);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error loading stories", e.getMessage())
            );
        }

        return "";
    }

    public void createStory() {
        if (!loginBean.isLoggedIn() || topicsBean.getSelectedTopic() == null ||
                newStory.getTitle() == null || newStory.getTitle().trim().isEmpty() ||
                newStory.getBody() == null || newStory.getBody().trim().isEmpty() ||
                newStory.getDate() == null) {
            return;
        }

        newStory.setTopic(topicsBean.getSelectedTopic().getName());

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            StoryUtils.createStory(newStory, mongoClient);
            stories.add(newStory);
            resetNewStory();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating story", e.getMessage())
            );
        }
    }

    public void resetNewStory() {
        newStory = new StoryObject();
    }

    public void deleteStory(StoryObject story) {
        if (!loginBean.isLoggedIn() || story == null) {
            return;
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            StoryUtils.deleteStory(story.getTitle(), story.getTopic(), mongoClient);
            stories.remove(story);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting story", e.getMessage())
            );
        }
    }

    public String backToTopics() {
        return "topics";
    }

    public List<StoryObject> getStories() {
        return stories;
    }

    public void setStories(List<StoryObject> stories) {
        this.stories = stories;
    }

    public StoryObject getNewStory() {
        return newStory;
    }

    public void setNewStory(StoryObject newStory) {
        this.newStory = newStory;
    }

    public StoryObject getSelectedStory() {
        return selectedStory;
    }

    public void setSelectedStory(StoryObject selectedStory) {
        this.selectedStory = selectedStory;
    }
}