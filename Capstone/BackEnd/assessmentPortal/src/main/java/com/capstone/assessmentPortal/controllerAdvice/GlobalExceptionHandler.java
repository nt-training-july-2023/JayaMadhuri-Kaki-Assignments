package com.capstone.assessmentPortal.controllerAdvice;

import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
import com.capstone.assessmentPortal.response.CustomResponse;

/**
 *Global exception handler controller class.
*/

@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   *Exception handles when arguments does not contain valid input.
   *@return responseEntity
   *@param exception methodArgumentNotValidException
  */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<CustomResponse<MethodArgumentNotValidException>>
  handleEmptyInput(final MethodArgumentNotValidException exception) {
      final int status = 400;
      CustomResponse<MethodArgumentNotValidException> customResponse =
              new CustomResponse<MethodArgumentNotValidException>(status,
                      exception.getBindingResult().getFieldErrors()
                      .get(0).getDefaultMessage());
      return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
  }
  /**
   *Exception handles when name already exists.
   *@return responseEntity
   *@param exception DataIntegrityViolationException
  */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public final ResponseEntity<CustomResponse<DataIntegrityViolationException>>
  handleConflict(final DataIntegrityViolationException exception) {
      final int status = 409;
      CustomResponse<DataIntegrityViolationException> customResponse =
              new CustomResponse<DataIntegrityViolationException>(status,
                      "Name already exists");
      return new ResponseEntity<>(customResponse, HttpStatus.CONFLICT);
  }
  /**
   *Exception handles when name already exists.
   *@return responseEntity
   *@param exception DataIntegrityViolationException
  */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public final ResponseEntity<CustomResponse<HttpMessageNotReadableException>>
  handleHttpMessageNotReadableException(final HttpMessageNotReadableException
          exception) {
      final int status = 409;
      CustomResponse<HttpMessageNotReadableException> customResponse =
              new CustomResponse<HttpMessageNotReadableException>(status,
                      "Correct Answer should contain optionA or"
                      + " optionB or optionC or optionD");
      return new ResponseEntity<>(customResponse, HttpStatus.CONFLICT);
  }
  /**
   *Exception handles when no element is present with id.
   *@return responseEntity
   *@param exception noSuchElementException
  */
  @ExceptionHandler(NoSuchElementException.class)
  public final ResponseEntity<CustomResponse<NoSuchElementException>>
  handleNoSuchElement(final NoSuchElementException exception) {
    final int status = 404;
    CustomResponse<NoSuchElementException> customResponse = new
        CustomResponse<NoSuchElementException>(status, exception.getMessage());
    return new ResponseEntity<>(customResponse,
                                    HttpStatus.NOT_FOUND);
  }
  /**
   *Exception handles when user credentials are not valid.
   *@return responseEntity
   *@param exception userNotFoundException
  */
  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<CustomResponse<UserNotFoundException>>
  handleUserNotFound(final UserNotFoundException exception) {
      final int status = 401;
      CustomResponse<UserNotFoundException> customResponse = new CustomResponse
              <UserNotFoundException>(status, exception.getMessage());
      return new ResponseEntity<>(customResponse, HttpStatus.UNAUTHORIZED);
  }
  /**
   *Exception handles when when data is already exists in db.
   *@return responseEntity
   *@param exception alreadyExitsException
  */
  @ExceptionHandler(AlreadyExistsException.class)
  public final ResponseEntity<CustomResponse<AlreadyExistsException>>
  handleAlreadyExistsException(final AlreadyExistsException exception) {
    final int status = 409;
    CustomResponse<AlreadyExistsException> customResponse = new
              CustomResponse<AlreadyExistsException>(status,
                      exception.getMessage());
    return new ResponseEntity<>(customResponse, HttpStatus.CONFLICT);
  }
}
