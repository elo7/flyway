/*
 * Copyright 2010-2018 Boxfuse GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.database.hive;

import org.flywaydb.core.internal.database.base.Database;
import org.flywaydb.core.internal.database.base.Schema;
import org.flywaydb.core.internal.database.base.Table;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.sql.SQLException;

public class HiveTable extends Table {

    HiveTable(JdbcTemplate jdbcTemplate, Database<HiveConnection> database, Schema schema, String name) {
        super(jdbcTemplate, database, schema, name);
    }

    @Override
    protected boolean doExists() throws SQLException {
        return exists(null, schema, name);
    }

    @Override
    protected void doLock() throws SQLException {
        return; // lock is not supported
    }

    @Override
    protected void doDrop() throws SQLException {
        // We do not know if it is a table or a view...
        String tableOrView = database.quote(schema.getName(), name);
        try {
            jdbcTemplate.execute("DROP TABLE " + tableOrView);
        } catch (SQLException e) {
            if (e.getMessage().contains("Cannot drop a view with DROP TABLE"))
                jdbcTemplate.execute("DROP VIEW " + tableOrView);
            else
                throw e;
        }
    }


}
