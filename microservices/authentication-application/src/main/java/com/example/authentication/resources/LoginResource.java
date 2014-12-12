package com.example.authentication.resources;

import com.example.authentication.core.Login;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class LoginResource {

    @POST
    public String loginAttempt(Login login) {

        if (!isValidLogin(login)) {
            throw new WebApplicationException(401);
        }

        return login.getUsername();
    }

    private Boolean isValidLogin(Login login) {

        if ((login.getUsername().equals("admin") && login.getPassword().equals("password")) ||
            (login.getUsername().equals("user")  && login.getPassword().equals("password"))) {
            return true;
        }

        return false;
    }
}
