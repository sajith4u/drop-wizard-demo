package dev.innova.wizard;

import dev.innova.wizard.health.StudentRestHealth;
import dev.innova.wizard.service.StudentManagementService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class RestApplication extends Application<RestStubConfig> {
    public static void main(String[] args) throws Exception {
        new RestApplication().run(args);
    }

    @Override
    public void run(RestStubConfig config, Environment env) {
        final StudentManagementService studentManagementService = new StudentManagementService();
        env.jersey().register(studentManagementService);

        final StudentRestHealth healthCheck = new StudentRestHealth(config.getVersion());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(healthCheck);
    }
}
