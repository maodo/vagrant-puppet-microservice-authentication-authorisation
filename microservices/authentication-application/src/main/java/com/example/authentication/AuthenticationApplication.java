package com.example.authentication;

import com.example.authentication.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AuthenticationApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new AuthenticationApplication().run(args);
    }

    @Override
    public String getName() {
        return "authentication-application";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        final LoginResource loginResource = new LoginResource();

        environment.jersey().register(loginResource);
    }
}