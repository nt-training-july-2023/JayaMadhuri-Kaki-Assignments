package com.capstone.assessmentPortal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssessmentPortalApplicationTests {
    @Test
    public void testMain() {
      assertDoesNotThrow(() -> AssessmentPortalApplication.main(new String[]{}));
    }
}
