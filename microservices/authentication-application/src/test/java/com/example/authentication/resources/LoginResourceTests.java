package com.example.authentication.resources;

import com.example.authentication.core.Login;
import com.sun.jersey.api.client.UniformInterfaceException;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginResourceTests {

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new LoginResource())
            .build();

    @Test
    public void login() throws Exception {
        String username = resources.client().resource("/login")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(String.class, new Login("user", "password"));

        assertEquals("user", username);
    }

    @Test
    public void login_invalid() throws Exception {
        try {
            String username = resources.client().resource("/login")
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .post(String.class, new Login("notAUser", "password"));
        } catch (UniformInterfaceException ex) {
            if (ex.getResponse().getStatus() != 401) {
                fail("Expected 401");
            }
        }
    }
}
