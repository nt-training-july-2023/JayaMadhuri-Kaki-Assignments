package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  @Override
  public final String studentRegistration(final Users users) {
    Optional<Users> existingUser = usersRepo
          .findUserByEmailId(users.getEmailId());
    String name = users.getFirstName() + users.getLastName();
    if (existingUser.isPresent()) {
      throw new EmailAlreadyExistsException();
    } else {
      usersRepo.save(users);
    }
    return name;
  }
  @Override
  public final Map<String, String> authenticateUser(final Users users) {
    Optional<Users> existingUser = usersRepo.findUserByEmailId(users
             .getEmailId());
    Map<String, String> userDetails = new HashMap<String, String>();
    if (existingUser.isPresent()) {
      String existingUserPassword = existingUser.get().getPassword();
      if (users.getPassword().equals(existingUserPassword)) {
        userDetails.put("UserType: ", existingUser.get().getUserType());
        userDetails.put("Name: ", existingUser.get().getFirstName()
              + " " + existingUser.get().getLastName());
        userDetails.put("Email Id: ", existingUser.get().getEmailId());
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
  public final Users updateStudentDetails(final Long studentId,
                 final Users users) {
    Users existinguser = usersRepo.findById(studentId).orElse(null);
    if (existinguser == null) {
      throw new NoSuchElementException();
    } else {
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
      return usersRepo.save(existinguser);
    }
  }
  @Override
  public final Users getStudentById(final Long studentId) {
    return usersRepo.findById(studentId).orElseThrow(
               () -> new NoSuchElementException(
               "Cannot find User with id: " + studentId));
  }
}
