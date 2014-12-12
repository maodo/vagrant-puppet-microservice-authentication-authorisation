package com.example.authorisation.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/authorisations")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AuthorisationResource {

    @GET
    @Path("/{user}/{authorisation}")
    public void hasAuthorisation(@PathParam("user") String user, @PathParam("authorisation") String authorisation) {

        if (!user.equals("admin") || !authorisation.equals("edit")) {
            throw new WebApplicationException(404);
        }
    }
}
