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
package org.flywaydb.core.internal.database.hive.driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Proxy of HiveDriver which provided an implementation for some methods which throw exceptions
 */
public class HiveProxyDriver implements Driver {

    private final Driver delegate;

    public HiveProxyDriver() {
        try {
            delegate = (Driver)Class.forName("org.apache.hive.jdbc.HiveDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot initialize HiveDriver", e);
        }
    }

    public Connection connect(String url, Properties info) throws SQLException {
        return new HiveProxyConnection(delegate.connect(url, info));
    }

    public boolean acceptsURL(String url) throws SQLException {
        return delegate.acceptsURL(url);
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return delegate.getPropertyInfo(url, info);
    }

    public int getMajorVersion() {
        return delegate.getMajorVersion();
    }

    public int getMinorVersion() {
        return delegate.getMinorVersion();
    }

    public boolean jdbcCompliant() {
        return delegate.jdbcCompliant();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return delegate.getParentLogger();
    }


}
