package com.example.session.resources;

import com.example.session.core.Session;
import com.example.session.dao.SessionDAO;
import com.sun.jersey.api.client.UniformInterfaceException;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class SessionResourceTests {

    private static final SessionDAO sessionDAO = mock(SessionDAO.class);

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new SessionResource(sessionDAO))
            .build();

    @Test
    public void get() throws Exception {
        Session findSession = new Session();
        findSession.setUuid("1234");
        findSession.setUser("user1");
        findSession.setCreated(new Date());
        when(sessionDAO.findByUUID("1234")).thenReturn(findSession);

        Session session = resources.client().resource("/sessions/1234").get(Session.class);

        assertEquals("user1", session.getUser());
    }

    @Test
    public void get_returns404whenNotFound() throws Exception {
        when(sessionDAO.findByUUID("1234")).thenReturn(null);

        try {
            resources.client().resource("/sessions/1234").get(Session.class);
        } catch (UniformInterfaceException ex) {
            if (ex.getResponse().getStatus() != 404) {
                fail("Expected 404");
            }
        }
    }

    @Test()
    public void add() throws Exception {
        String user = "user1";

        String uuid = resources.client().resource("/sessions")
                .type(MediaType.APPLICATION_JSON)
                .post(String.class, user);

        assertNotNull(uuid);
        verify(sessionDAO, times(1)).insert(any(Session.class));
    }

    @Test()
    public void delete() throws Exception {
        resources.client().resource("/sessions/1234").delete();
        verify(sessionDAO, times(1)).deleteByUUID("1234");
    }
}
