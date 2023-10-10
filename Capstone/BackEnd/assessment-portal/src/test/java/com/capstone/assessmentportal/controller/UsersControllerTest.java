package com.capstone.assessmentportal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentportal.dto.Gender;
import com.capstone.assessmentportal.dto.LoginRequest;
import com.capstone.assessmentportal.dto.SignUpRequest;
import com.capstone.assessmentportal.dto.UserDetails;
import com.capstone.assessmentportal.dto.UserDetailsForUpdate;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.service.serviceimplementation.UsersServiceImplementation;

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
        users.setGender(Gender.female);
        users.setEmailId("jayamadhuri@nucleusteq.com");
        users.setPassword("Madhu@123");
        users.setUserType("Student");
        
        CustomResponse<SignUpRequest> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("User successfully registered");
        
        String name = users.getFirstName() + users.getLastName();
        when(usersService.studentRegistration(users)).thenReturn(name);
        CustomResponse<SignUpRequest> response = usersController.studentRegistration(users);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testUserLogin() {
        LoginRequest login = new LoginRequest();
        login.setEmailId("jayamadhuri@nucleusteq.com");
        login.setPassword("Madhu@123");
        
        Map<String,String> userDetails = new HashMap<>();
        
        CustomResponse<Map<String, String>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("User successfully logged in");
        expectedResponse.setResponseData(userDetails);
        
        when(usersService.authenticateUser(login)).thenReturn(userDetails);
        CustomResponse<Map<String, String>> response = usersController.userLogin(login);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testGetStudentById() {
        Long studentId = 1L;
        UserDetails users = new UserDetails();
        CustomResponse<UserDetails> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Retrieved User details by Id");
        expectedResponse.setResponseData(users);
        
        when(usersService.getStudentById(studentId)).thenReturn(users);
        CustomResponse<UserDetails> response = usersController.getStudentById(studentId);
        assertEquals(expectedResponse,response);
    }
    
    @Test
    void testGetStudentByEmail() {
        String studentEmail = "jaya@nucleusteq.com";
        UserDetails users = new UserDetails();
        CustomResponse<UserDetails> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Retrieved student details by EmailId");
        expectedResponse.setResponseData(users);
        
        when(usersService.getStudentDetailsByEmail(studentEmail)).thenReturn(users);
        CustomResponse<UserDetails> response = usersController.getStudentByEmailId(studentEmail);
        assertEquals(expectedResponse,response);
    }
    
    @Test
    void testGetUserByEmail() {
        String email = "jaya@nucleusteq.com";
        CustomResponse<String> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Validated");
        when(usersService.getUsersDetailsByEmail(email)).thenReturn("User Not exists with Email");
        CustomResponse<UserDetails> response = usersController.getUserByEmailId(email);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testUpdateStudentDetails() {
        Long userId = 1L;
        UserDetailsForUpdate users = new UserDetailsForUpdate();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender(Gender.female);
        
        CustomResponse<UserDetailsForUpdate> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("User Successfully Updated");
        
        when(usersService.updateStudentDetails(userId, users)).thenReturn(users);
        CustomResponse<UserDetailsForUpdate> response = usersController.updateStudentDetails(userId,users);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testDeleteStudent() {
        Long userId = 1L;
        CustomResponse<UserDetails> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("User Successfully Deleted");
        
        CustomResponse<UserDetails> response = usersController.deleteStudent(userId);
        assertEquals(expectedResponse,response);
    }

}
