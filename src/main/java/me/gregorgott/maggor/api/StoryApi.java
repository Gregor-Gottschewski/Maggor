package me.gregorgott.maggor.api;

import com.mongodb.client.MongoClient;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import me.gregorgott.maggor.api.json.StoryObject;
import me.gregorgott.maggor.db.AppDatabaseConnector;
import me.gregorgott.maggor.db.StoryUtils;
import me.gregorgott.maggor.utils.AppConfiguration;

@RequestScoped
@Path("/stories")
@BasicAuthenticationMechanismDefinition(realmName = "maggor")
public class StoryApi {

    @Inject
    private AppConfiguration appConfiguration;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStory(
            @Context SecurityContext securityContext,
            @NotNull @Valid StoryObject storyObject
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            StoryUtils.createStory(storyObject, mongoClient);
        } catch (Exception e) {
            return Response.serverError().build();
        }

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStories(
            @Context SecurityContext securityContext,
            @NotBlank @Size(min = 1, max = 64) @QueryParam("topic") String topic
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            return Response.accepted(StoryUtils.getStories(topic, mongoClient)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    public Response deleteStory(
            @Context SecurityContext securityContext,
            @QueryParam("title") String title,
            @QueryParam("topic") String topic
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            StoryUtils.deleteStory(title, topic, mongoClient);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
