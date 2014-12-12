package com.example.addressfinder;

import com.example.addressfinder.resources.AddressResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AddressFinderApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new AddressFinderApplication().run(args);
    }

    @Override
    public String getName() {
        return "addressfinder-application";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        final AddressResource addressResource = new AddressResource();

        environment.jersey().register(addressResource);
    }
}