package com.capstone.assessmentPortal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.response.CustomResponse;
import com.capstone.assessmentPortal.service.serviceImplementation.UsersServiceImplementation;

@SpringBootTest
class UsersControllerTest {
    @InjectMocks
    UsersController usersController;
    @Mock
    UsersServiceImplementation usersService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testStudentRegistration() {
        SignUpRequest users = new SignUpRequest();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender("female");
        users.setEmailId("jayamadhuri@nucleusteq.com");
        users.setPassword("Madhu@123");
        users.setUserType("Student");
        
        String name = users.getFirstName() + users.getLastName();
        when(usersService.studentRegistration(users)).thenReturn(name);
        CustomResponse<SignUpRequest> response = usersController.studentRegistration(users);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("User successfully registered",response.getMessage());
    }

    @Test
    void testUserLogin() {
        LoginRequest login = new LoginRequest();
        login.setEmailId("jayamadhuri@nucleusteq.com");
        login.setPassword("Madhu@123");
        
        Map<String,String> userDetails = new HashMap<>();
        when(usersService.authenticateUser(login)).thenReturn(userDetails);
        CustomResponse<Map<String, String>> response = usersController.userLogin(login);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(userDetails,response.getResponseData());
        assertEquals("User successfully logged in",response.getMessage());
    }

    @Test
    void testGetStudentById() {
        Long studentId = 1L;
        UserDetails users = new UserDetails();
        when(usersService.getStudentById(studentId)).thenReturn(users);
        CustomResponse<UserDetails> response = usersController.getStudentById(studentId);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(users,response.getResponseData());
        assertEquals("Retrieved User details by Id",response.getMessage());
    }
    
    @Test
    void testGetStudentByEmail() {
        String studentEmail = "jaya@nucleusteq.com";
        UserDetails users = new UserDetails();
        when(usersService.getStudentDetailsByEmail(studentEmail)).thenReturn(users);
        CustomResponse<UserDetails> response = usersController.getStudentByEmailId(studentEmail);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(users,response.getResponseData());
        assertEquals("Retrieved student details by EmailId",response.getMessage());
    }
    
    @Test
    void testGetUserByEmail() {
        String email = "jaya@nucleusteq.com";
        when(usersService.getUsersDetailsByEmail(email)).thenReturn("User Not exists with Email");
        CustomResponse<UserDetails> response = usersController.getUserByEmailId(email);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("Successfully Validated",response.getMessage());
    }

    @Test
    void testUpdateStudentDetails() {
        Long userId = 1L;
        UserDetailsForUpdate users = new UserDetailsForUpdate();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender("female");
        
        when(usersService.updateStudentDetails(userId, users)).thenReturn(users);
        CustomResponse<UserDetailsForUpdate> response = usersController.updateStudentDetails(userId,users);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("User Successfully Updated",response.getMessage());
    }

    @Test
    void testDeleteStudent() {
        Long userId = 1L;
        CustomResponse<UserDetails> response = usersController.deleteStudent(userId);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("User Successfully Deleted",response.getMessage());
    }

}
