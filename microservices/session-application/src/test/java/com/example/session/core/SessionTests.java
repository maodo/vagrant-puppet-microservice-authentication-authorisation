package com.example.session.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionTests {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private static final String NULL_ERROR_MESSAGE = "may not be null";

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void serializesToJson() throws Exception {
        final Session session = getSession();
        String json = MAPPER.writeValueAsString(session);
        assertTrue(json.contains("\"uuid\":\""));
        assertTrue(json.contains("\"user\":\"testUser\""));
        assertTrue(json.contains("\"created\":"));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Session session = MAPPER.readValue(fixture("fixtures/session.json"), Session.class);
        assertEquals("80d1e97d-b46b-418b-8165-223ff493c85a", session.getUuid());
        assertEquals("testUser", session.getUser());
        assertTrue(session.getCreated() != null);
    }

    public static Session getSession() {
        return new Session("testUser");
    }
}
