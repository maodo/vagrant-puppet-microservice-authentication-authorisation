package com.example;

import com.example.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ExampleApplication extends Application<ExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-seven-one";
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) {
        final UserResource userResource = new UserResource();

        environment.jersey().register(userResource);
    }
}