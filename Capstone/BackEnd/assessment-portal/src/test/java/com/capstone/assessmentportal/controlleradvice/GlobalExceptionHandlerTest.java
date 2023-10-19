package com.capstone.assessmentportal.controlleradvice;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.exception.UserNotFoundException;
import com.capstone.assessmentportal.response.CustomResponse;

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
        
        CustomResponse<MethodArgumentNotValidException> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(400);
        expectedResponse.setMessage("Field1Error");
        
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        CustomResponse<MethodArgumentNotValidException> errorMap = globalhandler.handleEmptyInput(methodArgumentNotValidException);
        assertEquals(expectedResponse,errorMap);
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
        CustomResponse<UserNotFoundException> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(401);
        expectedResponse.setMessage("User not found");
        UserNotFoundException noSuch = new UserNotFoundException("User not found");
        CustomResponse<UserNotFoundException> response = globalhandler.handleUserNotFound(noSuch);
        assertEquals(expectedResponse,response);
    }
    
    @Test 
    void testDataIntegrityException() {
        CustomResponse<DataIntegrityViolationException> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(409);
        expectedResponse.setMessage("Category Name already exists");
        DataIntegrityViolationException noSuch = new DataIntegrityViolationException("Category Name already exists");
        CustomResponse<DataIntegrityViolationException> response = globalhandler.handleConflict(noSuch);
        assertEquals(expectedResponse,response);
    }
    
    @Test 
    void testHttpMessageNotReadableException() {
        HttpMessageNotReadableException noSuch = new HttpMessageNotReadableException("Correct Answer should contain optionA or optionB or optionC or optionD");
        CustomResponse<HttpMessageNotReadableException> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(409);
        expectedResponse.setMessage(noSuch.getLocalizedMessage());
        CustomResponse<HttpMessageNotReadableException> response = globalhandler.handleHttpMessageNotReadableException(noSuch);
        assertEquals(expectedResponse,response);
    }
    
    @Test
    void testHandleNoSuchElement() {
       NoSuchElementException noSuch = new NoSuchElementException("No such element found");
       CustomResponse<NoSuchElementException> expectedResponse = new CustomResponse<>();
       expectedResponse.setStatusCode(404);
       expectedResponse.setMessage("No such element found");
       CustomResponse<NoSuchElementException> response = globalhandler.handleNoSuchElement(noSuch);
       assertEquals(expectedResponse,response);
    }
    
    @Test
    void testHandleAlreadyExistsExceptionEmailAlreadyExistsException() {
        AlreadyExistsException noSuch = new AlreadyExistsException("Element already exists");
        CustomResponse<AlreadyExistsException> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(409);
        expectedResponse.setMessage("Element already exists");
        CustomResponse<AlreadyExistsException> response = globalhandler.handleAlreadyExistsException(noSuch);
        assertEquals(expectedResponse,response);
    }
}