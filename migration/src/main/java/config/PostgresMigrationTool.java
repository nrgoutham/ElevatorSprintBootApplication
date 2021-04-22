package config;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.System.err;

public class PostgresMigrationTool {

    private static final List<String> COMMAND_SET = List.of("info", "validate", "update_schema", "repair");
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String command;
    private final PostgresProperties postgresProperties;

    public PostgresMigrationTool(final String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Must specify a migration command, it can be \"info\", \"validate\" or \"update_schema\"");
        }
        command = args[0];
        if (!COMMAND_SET.contains(command.toLowerCase())) {
            throw new IllegalArgumentException("Unrecognized command: " + command + "\nChoose one of possible commands: " + COMMAND_SET.toString());
        }
        this.postgresProperties = new PostgresProperties();
        log.info("Recognized command=" + command);
    }

    public static void main(final String[] args) {
        System.exit(new PostgresMigrationTool(args).runTool());
    }

    private int run() {
        switch (command.toLowerCase()) {
            case "info":
                return doInfo();
            case "update_schema":
                return doUpdateSchema();
            case "validate":
                return doValidate();
            case "repair":
                return doRepair();
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    public int runTool() {
        try {
            return run();
        } catch (final Exception e) {
            err.println("Migration failed: " + e.getMessage() + "\n");
            return 1;
        }
    }

    private int doInfo() {
        final MigrationInfoService info = postgresProperties.getInfo();
        log.info("Current migration:");
        printMigrationInfo(info.current());
        log.info("Pending migrations:");
        printMigrationInfos(info.pending());
        log.info("Applied migration:");
        printMigrationInfos(info.applied());
        log.info("Get migration info successfully completed.");
        return 0;
    }

    private int doValidate() {
        log.info("Validating schema migration");
        postgresProperties.validate();
        log.info("Schema Validation completed successfully.");
        return 0;
    }

    private int doRepair() {
        log.info("Validating schema migration:");
        postgresProperties.repair();
        log.info("Schema repair completed successfully.");
        return 0;
    }

    private int doUpdateSchema() {
        postgresProperties.schemaUpdate();
        log.info("Schema migration successfully completed.");
        return 0;
    }

    private void printMigrationInfos(final MigrationInfo[] migrationInfos) {
        if (migrationInfos == null || migrationInfos.length == 0) {
            log.info("No matched migrations found.\n");
            return;
        }

        for (final MigrationInfo migrationInfo : migrationInfos) {
            printMigrationInfo(migrationInfo);
        }
    }

    private void printMigrationInfo(final MigrationInfo migrationInfo) {
        if (migrationInfo == null) {
            return;
        }

        final String sb = "\n=====================================" +
                "\nType:" +
                migrationInfo.getType() +
                "\nChecksum:" +
                migrationInfo.getChecksum() +
                "\nVersion:" +
                migrationInfo.getVersion() +
                "\nDescription:" +
                migrationInfo.getDescription() +
                "\nScript:" +
                migrationInfo.getScript() +
                "\nState:" +
                migrationInfo.getState() +
                "\nInstalledOn:" +
                migrationInfo.getInstalledOn() +
                "\nInstalledBy:" +
                migrationInfo.getInstalledBy() +
                "\nInstalledRank:" +
                migrationInfo.getInstalledRank() +
                "\nExecutionTime:" +
                migrationInfo.getExecutionTime() +
                "\n=====================================\n";
        log.info(sb);
    }
}
