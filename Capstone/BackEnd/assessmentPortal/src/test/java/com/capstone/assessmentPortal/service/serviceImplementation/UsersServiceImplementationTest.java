package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.UsersRepo;

@SpringBootTest
class UsersServiceImplementationTest {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UsersServiceImplementation usersServiceImpl;
    @BeforeEach
    void setUp() {
      usersRepo = mock(UsersRepo.class);
      usersServiceImpl = new UsersServiceImplementation(usersRepo);
    }
//    @Test
//    void testStudentRegistration() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testAuthenticateUser() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testDeleteStudent() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testUpdateStudentDetails() {
//        fail("Not yet implemented");
//    }

    @Test
    void testGetStudentById() {
       Long userId = 13L;
       SignUpRequest users = new SignUpRequest();;
       users.setFirstName("Jaya");
       users.setLastName("kaki");
       users.setDateOfBirth("23-01-2001");
       users.setGender("female");
       users.setEmailId("jayamadhuri@nucleusteq.com");
       users.setPassword("Madhu@123");
       users.setUserType("Student");
       Users userDetails = new Users();
       userDetails.setFirstName(users.getFirstName());
       userDetails.setLastName(users.getLastName());
       userDetails.setDateOfBirth(users.getDateOfBirth());
       userDetails.setGender(users.getGender());
       userDetails.setEmailId(users.getEmailId());
       userDetails.setPassword(users.getPassword());
       userDetails.setUserType(users.getUserType());
       when(usersRepo.findById(userId)).thenReturn(Optional.of(userDetails));
       UserDetails result = usersServiceImpl.getStudentById(userId);
       assertTrue(result != null);
       assertEquals(userDetails.getEmailId(), result.getEmailId());
    }
//    
//    @Test
//    void testGetStudentByIdNotExists() {
//       
//    }

}
