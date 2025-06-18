package me.gregorgott.maggor;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import me.gregorgott.maggor.api.StatusApi;
import me.gregorgott.maggor.api.StoryApi;
import me.gregorgott.maggor.api.TopicsApi;
import me.gregorgott.maggor.security.ApplicationIdentityStore;

import java.util.Set;

/**
 * This is the entry point of the application.
 *
 * @author Gregor Gottschewski
 */
@ApplicationPath("/api")
public class MaggorMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                StatusApi.class,
                TopicsApi.class,
                StoryApi.class,
                ApplicationIdentityStore.class
        );
    }
}
