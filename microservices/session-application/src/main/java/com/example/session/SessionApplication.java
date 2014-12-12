package com.example.session;

import com.example.session.dao.SessionDAO;
import com.example.session.resources.SessionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class SessionApplication extends Application<SessionConfiguration> {
    public static void main(String[] args) throws Exception {
        new SessionApplication().run(args);
    }

    @Override
    public String getName() {
        return "session-application";
    }

    @Override
    public void initialize(Bootstrap<SessionConfiguration> bootstrap) {
    }

    @Override
    public void run(SessionConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final SessionDAO sessionDAO = jdbi.onDemand(SessionDAO.class);
        setupDB(sessionDAO);

        final SessionResource sessionResource = new SessionResource(sessionDAO);

        environment.jersey().register(sessionResource);
    }

    private void setupDB(SessionDAO sessionDAO) throws Exception {
        try {
            sessionDAO.createTable();
        } catch (Exception ex) {
            if (!ex.getMessage().contains("Table \"SESSION\" already exists")) {
                throw ex;
           }
        }
    }
}