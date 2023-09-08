package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.exception.EmailAlreadyExistsException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.UsersRepo;
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
   * users service implementation class.
   * @param usersRepo2 usersRepo2
  */
  public UsersServiceImplementation(final UsersRepo usersRepo2) {
    this.usersRepo = usersRepo2;
  }
@Override
  public final String studentRegistration(final SignUpRequest signUpRequest) {
    Optional<Users> existingUser = usersRepo
          .findUserByEmailId(signUpRequest.getEmailId());
    String name = signUpRequest.getFirstName() + signUpRequest.getLastName();
    if (existingUser.isPresent()) {
      throw new EmailAlreadyExistsException();
    } else {
      Users users = new Users();
      users.setFirstName(signUpRequest.getFirstName());
      users.setLastName(signUpRequest.getLastName());
      users.setDateOfBirth(signUpRequest.getDateOfBirth());
      users.setGender(signUpRequest.getGender());
      users.setEmailId(signUpRequest.getEmailId());
      users.setPassword(signUpRequest.getPassword());
      users.setUserType(signUpRequest.getUserType());
      usersRepo.save(users);
    }
    return name;
  }
  @Override
  public final Map<String, String> authenticateUser(final
          LoginRequest loginRequest) {
    Optional<Users> existingUser = usersRepo.findUserByEmailId(loginRequest
             .getEmailId());
    Map<String, String> userDetails = new HashMap<String, String>();
    if (existingUser.isPresent()) {
      String existingUserPassword = existingUser.get().getPassword();
      if (loginRequest.getPassword().equals(existingUserPassword)) {
        userDetails.put("UserType", existingUser.get().getUserType());
        userDetails.put("Name", existingUser.get().getFirstName()
              + " " + existingUser.get().getLastName());
        userDetails.put("EmailId", existingUser.get().getEmailId());
        return userDetails;
      } else {
        throw new UserNotFoundException();
      }
    } else {
      throw new UserNotFoundException();
    }
  }
  @Override
  public final void deleteStudent(final Long studentId) {
    Optional<Users> existingUser = usersRepo.findById(studentId);
    if (existingUser.isPresent()) {
      usersRepo.deleteById(studentId);
    } else {
      throw new NoSuchElementException();
    }
  }
  @Override
  public final UserDetailsForUpdate updateStudentDetails(final Long studentId,
                 final UserDetailsForUpdate users) {
    Users existinguser = usersRepo.findById(studentId).orElseThrow(() ->
    new NoSuchElementException());
    existinguser.setFirstName(users.getFirstName());
    existinguser.setLastName(users.getLastName());
    existinguser.setDateOfBirth(users.getDateOfBirth());
    existinguser.setGender(users.getGender());
    if (existinguser.getFirstName().isEmpty()
       || existinguser.getLastName().isEmpty()
       || existinguser.getDateOfBirth().isEmpty()
       || existinguser.getGender().isEmpty()) {
      throw new InputEmptyException();
    }
    Users updatedUser = usersRepo.save(existinguser);
    UserDetailsForUpdate updatedDetails = new UserDetailsForUpdate();
    updatedDetails.setFirstName(updatedUser.getFirstName());
    updatedDetails.setLastName(updatedUser.getLastName());
    updatedDetails.setDateOfBirth(updatedUser.getDateOfBirth());
    updatedDetails.setGender(updatedUser.getGender());
    return updatedDetails;
  }
  @Override
  public final UserDetails getStudentById(final Long studentId) {
    Users userDetails = usersRepo.findById(studentId).orElseThrow(
            () -> new NoSuchElementException(
            "Cannot find User with id: " + studentId));
    UserDetails userDetailsDto = new UserDetails();
    userDetailsDto.setFirstName(userDetails.getFirstName());
    userDetailsDto.setLastName(userDetails.getLastName());
    userDetailsDto.setDateOfBirth(userDetails.getDateOfBirth());
    userDetailsDto.setGender(userDetails.getGender());
    userDetailsDto.setEmailId(userDetails.getEmailId());
    userDetailsDto.setUserType(userDetails.getUserType());
    return userDetailsDto;
  }
}
