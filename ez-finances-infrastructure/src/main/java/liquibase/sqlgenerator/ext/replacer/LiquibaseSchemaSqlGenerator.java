package liquibase.sqlgenerator.ext.replacer;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.exception.Warnings;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGenerator;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.statement.core.RawSqlStatement;

/**
 * Liquibase schema configuration.
 */
public class LiquibaseSchemaSqlGenerator implements SqlGenerator<RawSqlStatement> {
    @Override
    public int getPriority() {
        return 15;
    }

    @Override
    public boolean supports(RawSqlStatement statement, Database database) {
        return true;
    }

    @Override
    public boolean generateStatementsIsVolatile(Database database) {
        return false;
    }

    @Override
    public boolean generateRollbackStatementsIsVolatile(Database database) {
        return false;
    }


    @Override
    public ValidationErrors validate(RawSqlStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        return null;
    }

    @Override
    public Warnings warn(RawSqlStatement replacerStatement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        return null;
    }

    @Override
    public Sql[] generateSql(RawSqlStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        String sql = statement.getSql();
        String schemaName = database.getDefaultSchemaName();
        sql = sql.replace("{{schema}}", schemaName);
        return new Sql[]{
                new UnparsedSql(sql, statement.getEndDelimiter())
        };
    }
}
