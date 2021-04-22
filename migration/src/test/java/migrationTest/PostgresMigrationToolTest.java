package migrationTest;

import config.PostgresMigrationTool;
import org.junit.Assert;
import org.junit.Test;

public class PostgresMigrationToolTest {

    private static final String UPDATE_SCHEMA = "update_schema";
    private static final String MIGRATION_INFO = "info";
    private static final String MIGRATION_VALIDATE = "validate";

    @Test
    public void testPostgresSchemaUpdate() {
        final config.PostgresMigrationTool updateSchema = new config.PostgresMigrationTool(new String[]{UPDATE_SCHEMA});
        Assert.assertEquals(0, updateSchema.runTool());
        //Validate after migration
        final config.PostgresMigrationTool validateMigration = new config.PostgresMigrationTool(new String[]{MIGRATION_VALIDATE});
        Assert.assertEquals(0, validateMigration.runTool());
    }

    @Test
    public void testPostgresGetMigrationInfo() {
        final PostgresMigrationTool migrationTool = new PostgresMigrationTool(new String[]{MIGRATION_INFO});
        Assert.assertEquals(0, migrationTool.runTool());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMigrationAction() {
        new PostgresMigrationTool(new String[]{"invalidation"});
    }
}
