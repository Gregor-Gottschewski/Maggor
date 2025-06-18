package me.gregorgott.maggor.api;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.gregorgott.maggor.base.StatusCode;

/**
 * This class represents the status of the application.
 *
 * @author Gregor Gottschewski
 */
@ApplicationScoped
@Path("/status")
public class StatusApi {
    private StatusCode statusCode;

    public void setStatusCode(final StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    @PostConstruct
    public void init() {
        statusCode = StatusCode.TEMPORARY_NOT_AVAILABLE;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStatus() {
        return Response.status(statusCode.getCode())
                .entity(statusCode.getMessage())
                .build();
    }
}
