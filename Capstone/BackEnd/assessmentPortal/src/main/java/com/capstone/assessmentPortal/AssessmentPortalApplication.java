package com.capstone.assessmentPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main class.
*/
@SpringBootApplication
public class AssessmentPortalApplication {
  /**
   * run method.
   * @param args arguments
  */
  private void run(final String[] args) {
    SpringApplication.run(AssessmentPortalApplication.class, args);
  }
  /**
   * main class.
   * @param args arguments
   */
  public static void main(final String[] args) {
    new AssessmentPortalApplication().run(args);
  }
}
