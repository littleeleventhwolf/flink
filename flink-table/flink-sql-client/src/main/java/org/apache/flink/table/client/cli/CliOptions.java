/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.client.cli;

import org.apache.flink.configuration.Configuration;

import javax.annotation.Nullable;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Command line options to configure the SQL client. Arguments that have not been specified by the
 * user are null.
 */
public class CliOptions {

    private final boolean isPrintHelp;
    private final String sessionId;
    private final URI initFile;
    private final URI sqlFile;
    private final String historyFilePath;
    private final Properties sessionConfig;

    private CliOptions(
            boolean isPrintHelp,
            String sessionId,
            URI initFile,
            URI sqlFile,
            String historyFilePath,
            Properties sessionConfig) {
        this.isPrintHelp = isPrintHelp;
        this.sessionId = sessionId;
        this.initFile = initFile;
        this.sqlFile = sqlFile;
        this.historyFilePath = historyFilePath;
        this.sessionConfig = sessionConfig;
    }

    public boolean isPrintHelp() {
        return isPrintHelp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public @Nullable URI getInitFile() {
        return initFile;
    }

    public @Nullable URI getSqlFile() {
        return sqlFile;
    }

    public String getHistoryFilePath() {
        return historyFilePath;
    }

    public Properties getSessionConfig() {
        return sessionConfig;
    }

    /** Command option lines to configure SQL Client in the embedded mode. */
    public static class EmbeddedCliOptions extends CliOptions {

        private final List<URI> jars;
        private final List<URI> libraryDirs;

        private final Configuration pythonConfiguration;

        public EmbeddedCliOptions(
                boolean isPrintHelp,
                String sessionId,
                URI initFile,
                URI sqlFile,
                String historyFilePath,
                List<URI> jars,
                List<URI> libraryDirs,
                Configuration pythonConfiguration,
                Properties sessionConfig) {
            super(isPrintHelp, sessionId, initFile, sqlFile, historyFilePath, sessionConfig);
            this.jars = jars;
            this.libraryDirs = libraryDirs;
            this.pythonConfiguration = pythonConfiguration;
        }

        public List<URI> getJars() {
            return jars;
        }

        public List<URI> getLibraryDirs() {
            return libraryDirs;
        }

        public Configuration getPythonConfiguration() {
            return pythonConfiguration;
        }
    }

    /** Command option lines to configure SQL Client in the gateway mode. */
    public static class GatewayCliOptions extends CliOptions {

        private final @Nullable URL gatewayAddress;

        GatewayCliOptions(
                boolean isPrintHelp,
                String sessionId,
                URI initFile,
                URI sqlFile,
                String historyFilePath,
                @Nullable URL gatewayAddress,
                Properties sessionConfig) {
            super(isPrintHelp, sessionId, initFile, sqlFile, historyFilePath, sessionConfig);
            this.gatewayAddress = gatewayAddress;
        }

        public Optional<URL> getGatewayAddress() {
            return Optional.ofNullable(gatewayAddress);
        }
    }
}
