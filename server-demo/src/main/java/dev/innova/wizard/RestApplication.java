package dev.innova.wizard;

import dev.innova.wizard.db.StudentsDAO;
import dev.innova.wizard.health.StudentRestHealth;
import dev.innova.wizard.service.StudentDatabaseService;
import dev.innova.wizard.service.StudentManagementService;
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
        final StudentManagementService studentManagementService = new StudentManagementService();
        env.jersey().register(studentManagementService);

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, config.getDataSourceFactory(), "mysql");
        final StudentsDAO dao = jdbi.onDemand(StudentsDAO.class);
        env.jersey().register(new StudentDatabaseService(dao));

        final StudentRestHealth healthCheck = new StudentRestHealth(config.getVersion());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(healthCheck);
    }
}
