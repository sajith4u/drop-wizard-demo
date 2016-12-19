package dev.innova.wizard;

import dev.innova.wizard.api.StudentManagementService;
import dev.innova.wizard.db.StudentsDAO;
import dev.innova.wizard.health.StudentRestHealth;
import dev.innova.wizard.service.StudentDatabaseService;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


public class RestApplication extends Application<RestStubConfig> {

    public static void main(String[] args) throws Exception {
        new RestApplication().run(args);
    }

    @Override
    public void run(RestStubConfig config, Environment env) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");
        final StudentsDAO dao = jdbi.onDemand(StudentsDAO.class);
        final StudentDatabaseService studentDatabaseService = new StudentDatabaseService(dao);
        env.jersey().register(studentDatabaseService);

        final StudentManagementService studentManagementService = new StudentManagementService(studentDatabaseService);

        env.jersey().register(studentManagementService);


        final StudentRestHealth healthCheck = new StudentRestHealth(config.getVersion(), studentDatabaseService);
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(healthCheck);
    }
}
