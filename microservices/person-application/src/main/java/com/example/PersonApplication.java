package com.example;

import com.example.dao.PersonDAO;
import com.example.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class PersonApplication extends Application<PersonConfiguration> {
    public static void main(String[] args) throws Exception {
        new PersonApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-jdbi";
    }

    @Override
    public void initialize(Bootstrap<PersonConfiguration> bootstrap) {
    }

    @Override
    public void run(PersonConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final PersonDAO personDAO = jdbi.onDemand(PersonDAO.class);
        final PersonResource personResource = new PersonResource(personDAO);

        environment.jersey().register(personResource);
    }
}