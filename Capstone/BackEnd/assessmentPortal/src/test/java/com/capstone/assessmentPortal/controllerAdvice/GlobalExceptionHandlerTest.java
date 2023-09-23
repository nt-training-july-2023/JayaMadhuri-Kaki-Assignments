package com.capstone.assessmentPortal.controllerAdvice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmailAlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
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
        Map<String, String> errorMap = globalhandler.handleEmptyInput(methodArgumentNotValidException);
        assertEquals("400", errorMap.get("StatusCode"));
    }
    private BindingResult mockBindingResult() {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "field1", "Field1Error"));
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        return bindingResult;
    }
    @Test 
    void testEmailAlreadyExistsException() {
        EmailAlreadyExistsException noSuch = new EmailAlreadyExistsException("email already exists in db");
        ResponseEntity<String> response = globalhandler.handleEmailAlreadyExistsException(noSuch);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    @Test
    void testHandleNoSuchElement() {
       NoSuchElementException noSuch = new NoSuchElementException("No such element found");
       ResponseEntity<String> response = globalhandler.handleNoSuchElement(noSuch);
       assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void testHandleUserNotFound() {
        UserNotFoundException noSuch = new UserNotFoundException("User not found");
        ResponseEntity<String> response = globalhandler.handleUserNotFound(noSuch);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void testHandleAlreadyExistsExceptionEmailAlreadyExistsException() {
        AlreadyExistsException noSuch = new AlreadyExistsException("Element already exists");
        ResponseEntity<String> response = globalhandler.handleAlreadyExistsException(noSuch);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    void testHandleAlreadyExistsExceptionAlreadyExistsException() {
        AlreadyExistsException noSuch = new AlreadyExistsException("Element already exists");
        ResponseEntity<String> response = globalhandler.handleAlreadyExistsException(noSuch);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    void testHandleEmptyListException() {
        EmptyListException noSuch = new EmptyListException("List is empty");
        ResponseEntity<String> response = globalhandler.handleEmptyListException(noSuch);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testHandleNotFoundException() {
        NotFoundException noSuch = new NotFoundException("Element Not found");
        ResponseEntity<String> response = globalhandler.handleNotFoundException(noSuch);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

}
