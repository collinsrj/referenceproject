package com.collinsrj.referenceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Reference Project.
 *
 * <p>This class serves as the entry point for the Spring Boot application and enables
 * auto-configuration through the {@link SpringBootApplication} annotation.
 *
 * @author collinsrj
 * @version 0.0.1-SNAPSHOT
 * @since 1.0
 */
@SpringBootApplication
public class ReferenceprojectApplication {

  /**
   * The main method that bootstraps and launches the Spring Boot application.
   *
   * @param args command line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(ReferenceprojectApplication.class, args);
  }
}
