package com.example.addressfinder.resources;

import com.example.addressfinder.core.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/addresses")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AddressResource {

    @GET
    @Path("/search")
    public List<Address> search(@QueryParam("postcode") String postcode){
        return getFakeResults(postcode);
    }

    private List<Address> getFakeResults(String postcode) {
        List<Address> addresses = new ArrayList<>();

        if (postcode != null && postcode.length() == 8) {
            addresses.add(new Address(postcode, "1 Test Road", "Testville", "Testcounty", postcode, "UK"));
            addresses.add(new Address(postcode, "2 Test Road", "Testville", "Testcounty", postcode, "UK"));
            addresses.add(new Address(postcode, "5 Test Road", "Testville", "Testcounty", postcode, "UK"));
            addresses.add(new Address(postcode, "88 Test Road", "Testville", "Testcounty", postcode, "UK"));
            addresses.add(new Address(postcode, "101 Test Road", "Testville", "Testcounty", postcode, "UK"));
        }

        return addresses;
    }
}
