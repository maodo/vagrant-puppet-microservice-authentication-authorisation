package com.example.addressfinder.resources;

import com.example.addressfinder.core.Address;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class AddressResourceTests {

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AddressResource())
            .build();

    @Test
    public void search() throws Exception {
        List<Address> addresses = resources.client().resource("/addresses/search?postcode=BT11%201BT")
                .get(new GenericType<List<Address>>() {});

        assertEquals("BT11 1BT", addresses.get(0).getPostcode());
    }
}
