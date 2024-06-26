package com.example.repository_poc;

public class Constants {
    // Number of iterations for the tests
    public static final int ITERATIONS = 100_000;

    // PostgresSQL container
    public static final String INIT_SCRIPT_PATH = "schema.sql";
    public static final String IMAGE_VERSION = "postgres:latest";
    public static final String DATABSE_NAME = "testdb";
    public static final String DATABASE_USERNAME = "testuser";
    public static final String DATABASE_PASSWORD = "testpassword";

    // Properties for the Spring datasource
    public static final String SPRING_DATASOURCE_URL_PROPERTY = "spring.datasource.url";
    public static final String SPRING_DATASOURCE_USERNAME_PROPERTY = "spring.datasource.username";
    public static final String SPRING_DATASOURCE_PASSWORD_PROPERTY = "spring.datasource.password";
    public static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME_PROPERTY = "spring.datasource.driver-class-name";
}
