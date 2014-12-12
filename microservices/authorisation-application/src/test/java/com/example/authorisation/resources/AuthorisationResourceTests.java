package com.example.authorisation.resources;

import com.sun.jersey.api.client.UniformInterfaceException;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AuthorisationResourceTests {

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AuthorisationResource())
            .build();

    @Test
    public void hasAuthorisation() throws Exception {
        try {
            resources.client().resource("/authorisations/admin/edit")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class);
        } catch (UniformInterfaceException ex) {
            if (ex.getResponse().getStatus() != 204) {
                fail("Expected 204");
            }
        }
    }

    @Test
    public void hasAuthorisation_returnsNotFound() throws Exception {
        try {
            resources.client().resource("/authorisations/notAUser/edit")
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class);
        } catch (UniformInterfaceException ex) {
            if (ex.getResponse().getStatus() != 404) {
                fail("Expected 404");
            }
        }
    }
}
