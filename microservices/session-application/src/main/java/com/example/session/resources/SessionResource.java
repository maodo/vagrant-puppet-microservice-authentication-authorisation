package com.example.session.resources;

import com.example.session.core.Session;
import com.example.session.dao.SessionDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sessions")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SessionResource {

    SessionDAO sessionDAO;

    public SessionResource(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @GET
    @Path("/{uuid}")
    public Session get(@PathParam("uuid") String uuid){
        Session session = sessionDAO.findByUUID(uuid);

        if (session == null) {
            throw new WebApplicationException(404);
        }

        return session;
    }

    @POST
    public String add(String user) {
        Session session = new Session(user);
        sessionDAO.insert(session);

        return session.getUuid();
    }

    @DELETE
    @Path("/{uuid}")
    public void delete(@PathParam("uuid") String uuid) {
        sessionDAO.deleteByUUID(uuid);
    }
}
