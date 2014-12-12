package com.example.authentication.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class LoginTests {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJson() throws Exception {
        final Login login = getLogin();
        assertEquals(fixture("fixtures/login.json").replace(", ", ","), MAPPER.writeValueAsString(login));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Login login = getLogin();
        assertEquals(login, MAPPER.readValue(fixture("fixtures/login.json"), Login.class));
    }

    public Login getLogin() {
        return new Login("user1", "password1");
    }
}
