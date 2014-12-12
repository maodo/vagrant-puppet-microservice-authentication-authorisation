package com.example.person;

import com.example.person.dao.PersonDAO;
import com.example.person.resources.PersonResource;
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
        return "person-application";
    }

    @Override
    public void initialize(Bootstrap<PersonConfiguration> bootstrap) {
    }

    @Override
    public void run(PersonConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final PersonDAO personDAO = jdbi.onDemand(PersonDAO.class);
        setupDB(personDAO);

        final PersonResource personResource = new PersonResource(personDAO);

        environment.jersey().register(personResource);
    }

    private void setupDB(PersonDAO personDAO) throws Exception {
        try {
            personDAO.createTable();
            personDAO.insertTestData();
        } catch (Exception ex) {
            if (!ex.getMessage().contains("Table \"PERSON\" already exists")) {
                throw ex;
           }
        }
    }
}