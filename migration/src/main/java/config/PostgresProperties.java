package config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfoService;

public class PostgresProperties {
    private Flyway flyway;

    public PostgresProperties() {
        initFlyway();
    }

    private void initFlyway() {
        this.flyway = new Flyway();
        flyway.setDataSource(getJdbcUrl(), getUserName(), getPassword());
        flyway.setLocations("migration");
        flyway.setSchemas(getSchemaName());
    }

    public void schemaUpdate() {
        flyway.repair();
        flyway.migrate();
    }

    public MigrationInfoService getInfo() {
        return flyway.info();
    }

    public void validate() {
        flyway.validate();
    }

    public void repair() {
        flyway.repair();
    }


    public String getUserName() {
        return "elevator";
    }

    public String getPassword() {
        return "password";
    }

    // change localhost to aws RDS instance URL
    public String getJdbcUrl() {
        return "jdbc:postgresql://elevator.c6oxrngzouaa.eu-west-1.rds.amazonaws.com:5432/" + getDbName();
    }

    public String getDbName() {
        return "elevator";
    }

    public String getSchemaName() {
        return getDbName() + "_schema";
    }

}
