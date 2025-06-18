package me.gregorgott.maggor.api;

import com.mongodb.client.MongoClient;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import me.gregorgott.maggor.TopicNotFoundException;
import me.gregorgott.maggor.api.json.TopicObject;
import me.gregorgott.maggor.api.queries.TopicPostQuery;
import me.gregorgott.maggor.db.AppDatabaseConnector;
import me.gregorgott.maggor.db.TopicUtils;
import me.gregorgott.maggor.utils.AppConfiguration;

@Path("/topics")
@RequestScoped
@BasicAuthenticationMechanismDefinition(realmName = "maggor")
public class TopicsApi {

    @Inject
    private AppConfiguration appConfiguration;

    @POST
    @RolesAllowed({"users"})
    public Response addTopic(
            @Context SecurityContext securityContext,
            @Valid @BeanParam TopicPostQuery topicPostQuery
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            TopicUtils.addTopic(new TopicObject(topicPostQuery.getName(), securityContext.getUserPrincipal().getName()), mongoClient);
        } catch (Exception e) {
            return Response.serverError().build();
        }

        return Response.accepted().build();
    }

    @GET
    @RolesAllowed({"users"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopics(
            @Context SecurityContext securityContext
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            return Response.ok(TopicUtils.getTopics(mongoClient), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{topicId}")
    @RolesAllowed({"users"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopicInformation(
            @Context SecurityContext securityContext,
            @PathParam("topicId") String id
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            return Response.ok(TopicUtils.getTopicInformation(id, mongoClient)).build();
        } catch (TopicNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{topicId}")
    public Response deleteTopic(
            @Context SecurityContext securityContext,
            @PathParam("topicId") String id
    ) {
        if (securityContext.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (MongoClient mongoClient = AppDatabaseConnector.getAppMongoClient(appConfiguration.getDbName(), appConfiguration.getDbPort(), appConfiguration.getDatabaseRootUser(), appConfiguration.getDatabaseRootPassword())) {
            TopicUtils.deleteTopic(id, securityContext.getUserPrincipal().getName(), mongoClient);
            return Response.ok().build();
        } catch (TopicNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
