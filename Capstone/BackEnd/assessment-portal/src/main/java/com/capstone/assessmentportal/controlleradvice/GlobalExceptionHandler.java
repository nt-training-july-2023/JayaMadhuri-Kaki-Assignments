package com.capstone.assessmentportal.controlleradvice;

import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.exception.UserNotFoundException;
import com.capstone.assessmentportal.response.CustomResponse;

/**
 *Global exception handler controller class.
*/

@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   *Exception handles when arguments does not contain valid input.
   *@return customResponse
   *@param exception methodArgumentNotValidException
  */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final CustomResponse<MethodArgumentNotValidException>
  handleEmptyInput(final MethodArgumentNotValidException exception) {
      final int status = 400;
      CustomResponse<MethodArgumentNotValidException> customResponse =
              new CustomResponse<MethodArgumentNotValidException>(status,
                      exception.getBindingResult().getFieldErrors()
                      .get(0).getDefaultMessage());
      return customResponse;
  }
  /**
   *Exception handles when name already exists.
   *@return customResponse
   *@param exception DataIntegrityViolationException
  */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public final CustomResponse<DataIntegrityViolationException>
  handleConflict(final DataIntegrityViolationException exception) {
      final int status = 409;
      CustomResponse<DataIntegrityViolationException> customResponse =
              new CustomResponse<DataIntegrityViolationException>(status,
                      "Category Name already exists");
      return customResponse;
  }
  /**
   *Exception handles when name already exists.
   *@return customResponse
   *@param exception DataIntegrityViolationException
  */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public final CustomResponse<HttpMessageNotReadableException>
  handleHttpMessageNotReadableException(final HttpMessageNotReadableException
          exception) {
      final int status = 409;
      CustomResponse<HttpMessageNotReadableException> customResponse =
              new CustomResponse<HttpMessageNotReadableException>(status,
                      exception.getLocalizedMessage());
      return customResponse;
  }
  /**
   *Exception handles when no element is present with id.
   *@return customResponse
   *@param exception noSuchElementException
  */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public final CustomResponse<NoSuchElementException>
        handleNoSuchElement(final NoSuchElementException exception) {
    final int status = 404;
    CustomResponse<NoSuchElementException> customResponse = new
        CustomResponse<NoSuchElementException>(status, exception.getMessage());
    return customResponse;
  }
  /**
   *Exception handles when user credentials are not valid.
   *@return customResponse
   *@param exception userNotFoundException
  */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UserNotFoundException.class)
  public final CustomResponse<UserNotFoundException>
  handleUserNotFound(final UserNotFoundException exception) {
      final int status = 401;
      CustomResponse<UserNotFoundException> customResponse = new CustomResponse
              <UserNotFoundException>(status, exception.getMessage());
      return customResponse;
  }
  /**
   *Exception handles when when data is already exists in db.
   *@return customResponse
   *@param exception alreadyExitsException
  */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(AlreadyExistsException.class)
  public final CustomResponse<AlreadyExistsException>
  handleAlreadyExistsException(final AlreadyExistsException exception) {
    final int status = 409;
    CustomResponse<AlreadyExistsException> customResponse = new
              CustomResponse<AlreadyExistsException>(status,
                      exception.getMessage());
    return customResponse;
  }
}
