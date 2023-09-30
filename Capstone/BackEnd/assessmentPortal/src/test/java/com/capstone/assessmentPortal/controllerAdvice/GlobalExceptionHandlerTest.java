package com.capstone.assessmentPortal.controllerAdvice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
import com.capstone.assessmentPortal.response.CustomResponse;

@SpringBootTest
class GlobalExceptionHandlerTest {
    @InjectMocks
    GlobalExceptionHandler globalhandler;
    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testHandleEmptyInput() {
        BindingResult bindingResult = mockBindingResult();
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        ResponseEntity<CustomResponse<MethodArgumentNotValidException>> errorMap = globalhandler.handleEmptyInput(methodArgumentNotValidException);
        assertEquals(400, errorMap.getBody().getStatusCode());
        assertEquals(null,errorMap.getBody().getResponseData());
    }
    
    private BindingResult mockBindingResult() {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "field1", "Field1Error"));
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        return bindingResult;
    }
    
    @Test 
    void testUserNotFoundException() {
        UserNotFoundException noSuch = new UserNotFoundException("User not found");
        ResponseEntity<CustomResponse<UserNotFoundException>> response = globalhandler.handleUserNotFound(noSuch);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("User not found",response.getBody().getMessage());
    }
    
    @Test 
    void testDataIntegrityException() {
        DataIntegrityViolationException noSuch = new DataIntegrityViolationException("Quiz with same name already exists");
        ResponseEntity<CustomResponse<DataIntegrityViolationException>> response = globalhandler.handleConflict(noSuch);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("Quiz with same name already exists",response.getBody().getMessage());
    }
    
    @Test
    void testHandleNoSuchElement() {
       NoSuchElementException noSuch = new NoSuchElementException("No such element found");
       ResponseEntity<CustomResponse<NoSuchElementException>> response = globalhandler.handleNoSuchElement(noSuch);
       assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
       assertEquals(null,response.getBody().getResponseData());
       assertEquals("No such element found",response.getBody().getMessage());
    }
    
    @Test
    void testHandleAlreadyExistsExceptionEmailAlreadyExistsException() {
        AlreadyExistsException noSuch = new AlreadyExistsException("Element already exists");
        ResponseEntity<CustomResponse<AlreadyExistsException>> response = globalhandler.handleAlreadyExistsException(noSuch);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("Element already exists",response.getBody().getMessage());
    }
}
