package com.example.repository_poc;

import org.testcontainers.containers.PostgreSQLContainer;

public class SingletonPostgreSQLContainer extends PostgreSQLContainer<SingletonPostgreSQLContainer> {

    private static SingletonPostgreSQLContainer container;

    private SingletonPostgreSQLContainer() {
        super(Constants.IMAGE_VERSION);
    }

    public static SingletonPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new SingletonPostgreSQLContainer()
                    .withDatabaseName(Constants.DATABSE_NAME)
                    .withUsername(Constants.DATABASE_USERNAME)
                    .withPassword(Constants.DATABASE_PASSWORD)
                    .withInitScript(Constants.INIT_SCRIPT_PATH)
                    .withReuse(true);
        }
        return container;
    }

    @Override
    public void start() {
        if (!isRunning()) {
            super.start();
        }
    }

    @Override
    public void stop() {
    }
}
