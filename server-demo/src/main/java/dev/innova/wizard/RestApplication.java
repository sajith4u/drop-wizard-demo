package dev.innova.wizard;

import dev.innova.wizard.health.RestStubCheck;
import dev.innova.wizard.service.StudentManagementService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class RestApplication extends Application<RestStubConfig> {
    public static void main(String[] args) throws Exception {
        new RestApplication().run(args);
    }

    @Override
    public void run(RestStubConfig config, Environment env) {
        final StudentManagementService personService = new StudentManagementService();
        env.jersey().register(personService);

        final RestStubCheck healthCheck = new RestStubCheck(config.getVersion());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(healthCheck);
    }
}
