package com.capstone.assessmentPortal.controllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmailAlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;

/**
 *Global exception handler controller class.
*/

@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   *Exception handles when arguments does not contain valid input.
   *@return errorMap
   *@param exception methodArgumentNotValidException
  */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final Map<String, String> handleEmptyInput(
      final MethodArgumentNotValidException exception) {
      Map<String, String> errorMap = new HashMap<>();
      exception.getBindingResult().getFieldErrors().forEach(error -> {
        errorMap.put(error.getField(), error.getDefaultMessage());
      });
      errorMap.put("StatusCode", "400");
      return errorMap;
  }
  /**
   *Exception handles when no element is present with id.
   *@return responseEntity
   *@param exception noSuchElementException
  */
  @ExceptionHandler(NoSuchElementException.class)
  public final ResponseEntity<String> handleNoSuchElement(
              final NoSuchElementException exception) {
    return new ResponseEntity<String>("No value is present in Db with this ID,"
            + " please change your request", HttpStatus.NOT_FOUND);
  }
  /**
   *Exception handles when user credentials are not valid.
   *@return responseEntity
   *@param exception userNotFoundException
  */
  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<String> handleUserNotFound(final
                 UserNotFoundException exception) {
    return new ResponseEntity<String>("Invalid user credentials",
                 HttpStatus.NOT_FOUND);
  }
  /**
   * Exception handles when Existed email again to register.
   * @return responseEntity
   * @param exception emailAlreadyExistsException
  */
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public final ResponseEntity<String> handleEmailAlreadyExistsException(
            final EmailAlreadyExistsException exception) {
    return new ResponseEntity<String>("An account is already registered "
            + "with this Email", HttpStatus.CONFLICT);
  }
  /**
   *Exception handles when when data is already exists in db.
   *@return responseEntity
   *@param exception alreadyExitsException
  */
  @ExceptionHandler(AlreadyExistsException.class)
  public final ResponseEntity<String> handleAlreadyExistsException(final
           AlreadyExistsException exception) {
    return new ResponseEntity<String>("Data already exists",
           HttpStatus.CONFLICT);
  }
  /**
   *Exception handles when list is empty.
   *@return responseEntity
   *@param exception emptyListxception
  */
  @ExceptionHandler(EmptyListException.class)
  public final ResponseEntity<String> handleEmptyListException(final
          EmptyListException exception) {
    return new ResponseEntity<String>("List is Empty", HttpStatus.OK);
  }
  /**
   *Exception handles when data with given id is not found.
   *@return responseEntity
   *@param exception notFoundException
  */
  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<String> handleNotFoundException(final
           NotFoundException exception) {
    return new ResponseEntity<String>("Super class Id Not exists",
           HttpStatus.NOT_FOUND);
  }
}
