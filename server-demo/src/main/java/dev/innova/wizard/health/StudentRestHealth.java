package dev.innova.wizard.health;

import com.codahale.metrics.health.HealthCheck;
import dev.innova.wizard.service.StudentDatabaseService;


public class StudentRestHealth extends HealthCheck {

    private final String version;

    private final StudentDatabaseService studentDatabaseService;

    public StudentRestHealth(String version, StudentDatabaseService studentDatabaseService) {
        this.version = version;
        this.studentDatabaseService = studentDatabaseService;
    }


    @Override
    protected Result check() throws Exception {
        if (studentDatabaseService.getCounts() == "") {
            return Result.unhealthy("No persons in DB! Version: " +
                    this.version);
        }
        return Result.healthy("OK with version: " + this.version +
                ". Persons count: " + studentDatabaseService.getCounts());
    }
}
