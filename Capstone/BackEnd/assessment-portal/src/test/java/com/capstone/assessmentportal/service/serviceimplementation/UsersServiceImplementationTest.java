package com.capstone.assessmentportal.service.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.capstone.assessmentportal.dto.Gender;
import com.capstone.assessmentportal.dto.LoginRequest;
import com.capstone.assessmentportal.dto.SignUpRequest;
import com.capstone.assessmentportal.dto.UserDetails;
import com.capstone.assessmentportal.dto.UserDetailsForUpdate;
import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.exception.UserNotFoundException;
import com.capstone.assessmentportal.model.Users;
import com.capstone.assessmentportal.repository.UsersRepo;

class UsersServiceImplementationTest {
    @Mock
    UsersRepo usersRepo;
    @InjectMocks
    UsersServiceImplementation usersServiceImpl;
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
        
        Users userDetails = new Users();
        userDetails.setFirstName(users.getFirstName());
        userDetails.setLastName(users.getLastName());
        userDetails.setDateOfBirth(users.getDateOfBirth());
        userDetails.setGender(users.getGender());
        userDetails.setEmailId(users.getEmailId());
        userDetails.setPassword(users.getPassword());
        userDetails.setUserType(users.getUserType());
        
        usersRepo.save(userDetails);
        when(usersRepo.findById(userDetails.getUserId())).thenReturn(Optional.of(userDetails));
        String signUpRequest = usersServiceImpl.studentRegistration(users);
        assertEquals(userDetails.getFirstName()+ " " + userDetails.getLastName(),
                signUpRequest);
    }
    
    @Test
    void testStudentRegistrationIfFails() {
        SignUpRequest users = new SignUpRequest();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender(Gender.female);
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
        
        when(usersRepo.findUserByEmailId(userDetails.getEmailId())).thenReturn(Optional.of(userDetails));
        assertThrows(AlreadyExistsException.class, () -> usersServiceImpl.studentRegistration(users));
    }

    @Test
    void testAuthenticateUser() {
        LoginRequest login = new LoginRequest();
        login.setEmailId("jayamadhuri@nucleusteq.com");
        login.setPassword("Madhu@123");
        
        Users users = new Users();
        users.setEmailId(login.getEmailId());
        users.setPassword(login.getPassword());
        users.setFirstName("Madhu");
        users.setLastName("Kaki");
        Map<String,String> expectedMap = new HashMap<>();
        expectedMap.put("EmailId", "jayamadhuri@nucleusteq.com");
        expectedMap.put("UserType", "Student");
        expectedMap.put("Name", "Madhu Kaki");
        
        when(usersRepo.findUserByEmailId(login.getEmailId())).thenReturn(Optional.of(users));
        Map<String,String> map = usersServiceImpl.authenticateUser(login);
        assertNotNull(map);
        assertEquals(expectedMap,map);
    }
    
    @Test
    void testAuthenticateUserIfPasswordWrong() {
        LoginRequest login = new LoginRequest();
        login.setEmailId("jayamadhuri@nucleusteq.com");
        login.setPassword("Madhu@123");
        
        Users users = new Users();
        users.setEmailId(login.getEmailId());
        users.setPassword("Madhu");
        
        when(usersRepo.findUserByEmailId(login.getEmailId())).thenReturn(Optional.of(users));
        assertNotEquals(users.getPassword(),login.getPassword());
        assertThrows(UserNotFoundException.class, () -> usersServiceImpl.authenticateUser(login));
    }
    
    @Test
    void testAuthenticateUserIfUserNotPresent() {
        LoginRequest login = new LoginRequest();
        login.setEmailId("jayamadhuri@nucleusteq.com");
        login.setPassword("Madhu@123");
        
        Users users = new Users();
        users.setEmailId(login.getEmailId());
        users.setPassword("Madhu");
        
        when(usersRepo.findUserByEmailId(login.getEmailId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> usersServiceImpl.authenticateUser(login));
    }
    
    @Test
    void testDeleteStudent() {
        Long userId = 1L;
        Users users = new Users(1L,"Jaya","kaki","23-01-2001",Gender.female,"jayamadhuri@nucleusteq.com");
        when(usersRepo.findById(userId)).thenReturn(Optional.of(users));
        usersServiceImpl.deleteStudent(userId);
        assertFalse(usersRepo.existsById(userId));
    }
    
    @Test
    void testDeleteStudentIfUserNotExists() {
        Long userId = 1L;
        when(usersRepo.existsById(userId)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> usersServiceImpl.deleteStudent(userId));
    }

    @Test
    void testUpdateStudentDetailsIfUserNotFound() {
        Long userId = 1L;
        UserDetailsForUpdate users = new UserDetailsForUpdate();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender(Gender.female);
        
        when(usersRepo.existsById(userId)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> usersServiceImpl.updateStudentDetails(userId,users));
    }
    
    @Test
    void updateDetails() {
        Long userId = 1L;
        UserDetailsForUpdate users = new UserDetailsForUpdate();
        users.setFirstName("Jaya");
        users.setLastName("kaki");
        users.setDateOfBirth("23-01-2001");
        users.setGender(Gender.female);
        
        Users user = new Users();
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setDateOfBirth(users.getDateOfBirth());
        user.setGender(users.getGender());
        
        UserDetailsForUpdate updatedusers = new UserDetailsForUpdate();
        updatedusers.setFirstName("JayaMadhuri");
        updatedusers.setLastName("kaki");
        updatedusers.setDateOfBirth("23-01-2001");
        updatedusers.setGender(Gender.female);
        
        Users updateduser = new Users();
        updateduser.setFirstName(updatedusers.getFirstName());
        updateduser.setLastName(updatedusers.getLastName());
        updateduser.setDateOfBirth(updatedusers.getDateOfBirth());
        updateduser.setGender(updatedusers.getGender());
        when(usersRepo.findById(userId)).thenReturn(Optional.of(user));
        when(usersRepo.save(user)).thenReturn(updateduser);
        UserDetailsForUpdate updatedDetails = usersServiceImpl.updateStudentDetails(userId, updatedusers);
        assertNotNull(updatedDetails);
        assertEquals(updatedusers,updatedDetails);
    }
    
    @Test
    void testGetStudentById() {
       Long userId = 13L;
       SignUpRequest users = new SignUpRequest();;
       users.setFirstName("JayaMadhuri");
       users.setLastName("kaki");
       users.setDateOfBirth("23-01-2001");
       users.setGender(Gender.female);
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
       
       UserDetails expectedResult = new UserDetails();
       expectedResult.setFirstName(users.getFirstName());
       expectedResult.setLastName(users.getLastName());
       expectedResult.setDateOfBirth(users.getDateOfBirth());
       expectedResult.setGender(users.getGender());
       expectedResult.setEmailId(users.getEmailId());
       expectedResult.setUserType(users.getUserType());
       
       when(usersRepo.findById(userId)).thenReturn(Optional.of(userDetails));
       UserDetails result = usersServiceImpl.getStudentById(userId);
       assertEquals(expectedResult,result);
    }
    
    @Test
    void testGetStudentByIdNotExists() {
       Long userId = 13L;
       when(usersRepo.findById(userId)).thenReturn(Optional.empty());
       assertThrows(NoSuchElementException.class, () -> usersServiceImpl.getStudentById(userId));
    }
    
    @Test
    void testGetStudentByEmailNotExists() {
       String email = "jaya@nucleusteq.com";
       when(usersRepo.findUserByEmailId(email)).thenReturn(Optional.empty());
       assertThrows(NoSuchElementException.class, () -> usersServiceImpl.getStudentDetailsByEmail(email));
    }
    
    @Test
    void testGetStudentByEmailId() {
       SignUpRequest users = new SignUpRequest();;
       users.setFirstName("Jaya");
       users.setLastName("kaki");
       users.setDateOfBirth("23-01-2001");
       users.setGender(Gender.female);
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
       
       UserDetails expectedResult = new UserDetails();
       expectedResult.setFirstName(users.getFirstName());
       expectedResult.setLastName(users.getLastName());
       expectedResult.setDateOfBirth(users.getDateOfBirth());
       expectedResult.setGender(users.getGender());
       expectedResult.setEmailId(users.getEmailId());
       expectedResult.setUserType(users.getUserType());
       
       when(usersRepo.findUserByEmailId(userDetails.getEmailId())).thenReturn(Optional.of(userDetails));
       UserDetails result = usersServiceImpl.getStudentDetailsByEmail(users.getEmailId());
       assertEquals(expectedResult,result);
    }
    
    @Test
    void testGetUserByEmailNotExists() {
       String email = "jaya@nucleusteq.com";
       when(usersRepo.findUserByEmailId(email)).thenReturn(Optional.empty());
       String message = usersServiceImpl.getUsersDetailsByEmail(email);
       assertEquals("Cannot find user with email id",message);
      
    }
    
    @Test
    void testGetUserByEmailId() {
       String email = "jaya@nucleusteq.com";
       Users users = new Users();
       when(usersRepo.findUserByEmailId(email)).thenReturn(Optional.of(users));
       assertThrows(AlreadyExistsException.class, () -> usersServiceImpl.getUsersDetailsByEmail(email));
    }
}
