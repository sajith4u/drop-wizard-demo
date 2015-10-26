package dev.innova.wizard.health;

import com.codahale.metrics.health.HealthCheck;
import dev.innova.wizard.db.StudentsDB;


public class StudentRestHealth extends HealthCheck {

    private final String version;

    public StudentRestHealth(String version) {
        this.version = version;
    }

    @Override
    protected Result check() throws Exception {
        if (StudentsDB.getCount() == 0) {
            return Result.unhealthy("No persons in DB! Version: " +
                    this.version);
        }
        return Result.healthy("OK with version: " + this.version +
                ". Persons count: " + StudentsDB.getCount());
    }
}
