package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.UsersRepo;
import com.capstone.assessmentPortal.response.ValidationMessage;
import com.capstone.assessmentPortal.service.UsersService;

/**
 * Users service implementation class.
*/

@Service
public class UsersServiceImplementation implements UsersService {
  /**
   * users service implementation class.
  */
  @Autowired
  private UsersRepo usersRepo;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          UsersServiceImplementation.class);
@Override
  public final String studentRegistration(final SignUpRequest signUpRequest) {
    Optional<Users> user = usersRepo
          .findUserByEmailId(signUpRequest.getEmailId());
    String name = signUpRequest.getFirstName() + signUpRequest.getLastName();
    if (user.isPresent()) {
      logger.error(ValidationMessage.USER_EMAILALREADYEXISTS);
      throw new AlreadyExistsException(ValidationMessage
              .USER_EMAILALREADYEXISTS);
    }
    logger.info("User successfully registered");
    Users users = new Users();
    users.setFirstName(signUpRequest.getFirstName());
    users.setLastName(signUpRequest.getLastName());
    users.setDateOfBirth(signUpRequest.getDateOfBirth());
    users.setGender(signUpRequest.getGender());
    users.setEmailId(signUpRequest.getEmailId());
    users.setPassword(signUpRequest.getPassword());
    users.setUserType(signUpRequest.getUserType());
    usersRepo.save(users);
    return name;
  }
  @Override
  public final Map<String, String> authenticateUser(final
          LoginRequest loginRequest) {
    Optional<Users> user = usersRepo.findUserByEmailId(loginRequest
             .getEmailId());
    Map<String, String> userDetails = new HashMap<String, String>();
    if (user.isPresent()) {
      logger.info("Login successful");
      String userPassword = user.get().getPassword();
      if (loginRequest.getPassword().equals(userPassword)) {
        userDetails.put("UserType", user.get().getUserType());
        userDetails.put("Name", user.get().getFirstName()
              + " " + user.get().getLastName());
        userDetails.put("EmailId", user.get().getEmailId());
        return userDetails;
      }
    }
    logger.error(ValidationMessage.USER_NOTFOUND);
    throw new UserNotFoundException(ValidationMessage.USER_NOTFOUND);
  }
  @Override
  public final void deleteStudent(final Long studentId) {
    usersRepo.findById(studentId).orElseThrow(
            () -> new NoSuchElementException(ValidationMessage
                    .USER_NOSUCHELEMENT));
    logger.info("User deleted");
    usersRepo.deleteById(studentId);
  }
  @Override
  public final UserDetailsForUpdate updateStudentDetails(final Long studentId,
                 final UserDetailsForUpdate users) {
    Users user = usersRepo.findById(studentId).orElseThrow(() ->
                 new NoSuchElementException(ValidationMessage
                         .USER_NOSUCHELEMENT));
    user.setFirstName(users.getFirstName());
    user.setLastName(users.getLastName());
    user.setDateOfBirth(users.getDateOfBirth());
    user.setGender(users.getGender());
    Users usersObj = usersRepo.save(user);
    UserDetailsForUpdate usersDetails = new UserDetailsForUpdate();
    usersDetails.setFirstName(usersObj.getFirstName());
    usersDetails.setLastName(usersObj.getLastName());
    usersDetails.setDateOfBirth(usersObj.getDateOfBirth());
    usersDetails.setGender(usersObj.getGender());
    logger.info("User updated");
    return usersDetails;
  }
  @Override
  public final UserDetails getStudentById(final Long studentId) {
    Users user = usersRepo.findById(studentId).orElseThrow(
            () -> new NoSuchElementException(ValidationMessage
                    .USER_NOSUCHELEMENT));
    UserDetails userDetails = new UserDetails();
    userDetails.setFirstName(user.getFirstName());
    userDetails.setLastName(user.getLastName());
    userDetails.setDateOfBirth(user.getDateOfBirth());
    userDetails.setGender(user.getGender());
    userDetails.setEmailId(user.getEmailId());
    userDetails.setUserType(user.getUserType());
    logger.info("Retrieved student details by id");
    return userDetails;
  }
    @Override
    public final UserDetails getStudentDetailsByEmail(final String emailId) {
        Optional<Users> user = usersRepo.findUserByEmailId(emailId);
        if (user.isPresent()) {
            logger.info("Retrieved student details by EmailId");
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(user.get().getUserId());
            userDetails.setFirstName(user.get().getFirstName());
            userDetails.setLastName(user.get().getLastName());
            userDetails.setDateOfBirth(user.get().getDateOfBirth());
            userDetails.setGender(user.get().getGender());
            userDetails.setEmailId(user.get().getEmailId());
            userDetails.setUserType(user.get().getUserType());
            return userDetails;
        }
        logger.error(ValidationMessage.USER_NOSUCHELEMENTEMAIL);
        throw new NoSuchElementException(ValidationMessage
                .USER_NOSUCHELEMENTEMAIL);
    }
    @Override
    public final String getUsersDetailsByEmail(final String emailId) {
        Optional<Users> user = usersRepo.findUserByEmailId(emailId);
        if (user.isPresent()) {
            logger.error(ValidationMessage.USER_EMAILALREADYEXISTS);
            throw new AlreadyExistsException(ValidationMessage
                    .USER_EMAILALREADYEXISTS);
        } else {
            logger.info(ValidationMessage.USER_NOSUCHELEMENTEMAIL);
            return ValidationMessage.USER_NOSUCHELEMENTEMAIL;
        }
    }
}
