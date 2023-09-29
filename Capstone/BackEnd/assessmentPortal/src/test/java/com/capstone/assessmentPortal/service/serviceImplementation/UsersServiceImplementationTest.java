package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
import com.capstone.assessmentPortal.model.Results;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.UsersRepo;


@SpringBootTest
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
        usersRepo.save(userDetails);
        when(usersRepo.findById(userDetails.getUserId())).thenReturn(Optional.of(userDetails));
        String signUpRequest = usersServiceImpl.studentRegistration(users);
        assertEquals(userDetails.getFirstName()+userDetails.getLastName(),
                signUpRequest);
    }
    
    @Test
    void testStudentRegistrationIfFails() {
        SignUpRequest users = new SignUpRequest();
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
        when(usersRepo.findUserByEmailId(login.getEmailId())).thenReturn(Optional.of(users));
        Map<String,String> map = usersServiceImpl.authenticateUser(login);
        assertNotNull(map);
        assertEquals("Student", map.get("UserType"));
        assertEquals("null null", map.get("Name"));
        assertEquals("jayamadhuri@nucleusteq.com", map.get("EmailId"));
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
    void testDeleteStudent() {
        Long userId = 1L;
        Users users = new Users(1L,"Jaya","kaki","23-01-2001","female","jayamadhuri@nucleusteq.com");
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
        users.setGender("female");
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
        users.setGender("female");
        Users user = new Users();
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setDateOfBirth(users.getDateOfBirth());
        user.setGender(users.getGender());
        user.getResults();
        UserDetailsForUpdate updatedusers = new UserDetailsForUpdate();
        updatedusers.setFirstName("JayaMadhuri");
        updatedusers.setLastName("kaki");
        updatedusers.setDateOfBirth("23-01-2001");
        updatedusers.setGender("female");
        Users updateduser = new Users();
        updateduser.setFirstName(updatedusers.getFirstName());
        updateduser.setLastName(updatedusers.getLastName());
        updateduser.setDateOfBirth(updatedusers.getDateOfBirth());
        updateduser.setGender(updatedusers.getGender());
        when(usersRepo.findById(userId)).thenReturn(Optional.of(user));
        when(usersRepo.save(user)).thenReturn(updateduser);
        UserDetailsForUpdate updatedDetails = usersServiceImpl.updateStudentDetails(userId, updatedusers);
        assertNotNull(updatedDetails);
        assertEquals(updateduser.getFirstName(),updatedDetails.getFirstName());
    }
    
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
       List<Results> listOfResults = new ArrayList<>();
       userDetails.setResults(listOfResults);
       when(usersRepo.findById(userId)).thenReturn(Optional.of(userDetails));
       UserDetails result = usersServiceImpl.getStudentById(userId);
       assertEquals(userDetails.getEmailId(), result.getEmailId());
       assertEquals(userDetails.getFirstName(), result.getFirstName());
       assertEquals(userDetails.getLastName(), result.getLastName());
       assertEquals(userDetails.getDateOfBirth(),result.getDateOfBirth());
       assertEquals(userDetails.getGender(),result.getGender());
       assertEquals(userDetails.getUserType(),result.getUserType());
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
       when(usersRepo.findUserByEmailId(userDetails.getEmailId())).thenReturn(Optional.of(userDetails));
       UserDetails result = usersServiceImpl.getStudentDetailsByEmail(users.getEmailId());
       assertEquals(userDetails.getUserId(), result.getUserId());
       assertEquals(userDetails.getEmailId(), result.getEmailId());
       assertEquals(userDetails.getFirstName(), result.getFirstName());
       assertEquals(userDetails.getLastName(), result.getLastName());
       assertEquals(userDetails.getDateOfBirth(),result.getDateOfBirth());
       assertEquals(userDetails.getGender(),result.getGender());
       assertEquals(userDetails.getUserType(),result.getUserType());
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
