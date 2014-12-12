package com.example.addressfinder.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class AddressTests {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJson() throws Exception {
        final Address address = getAddress();
        assertEquals(fixture("fixtures/address.json").replace(", ", ","), MAPPER.writeValueAsString(address));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Address address = getAddress();
        assertEquals(address, MAPPER.readValue(fixture("fixtures/address.json"), Address.class));
    }

    public Address getAddress() {
        return new Address("BT11 1BT", "1 Test Road", "Testville", "Testcounty", "BT11 1BT", "UK");
    }
}
