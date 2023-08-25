package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.UsersRepo;
import com.capstone.assessmentPortal.service.UsersService;

@Service
public class UsersServiceImplementation implements UsersService{
  @Autowired
  UsersRepo usersRepo;

  @Override
  public Users studentRegistration(Users users) {
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	String encryptedPassword = bcrypt.encode(users.getPassword());
	users.setPassword(encryptedPassword);
    return usersRepo.save(users);
  }

  @Override
  public Users authenticateUser(Users users) {
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	Optional<Users> existingUser = usersRepo.findUserByEmailId(users.getEmailId());
	if(existingUser.isPresent()) {
	  Users existingUserInDb = existingUser.get();
	  if(bcrypt.matches(users.getPassword(), existingUserInDb.getPassword())) {
		return users;
	  }else {
		return null;
	  }
	}else {
		return null;
	}
  }

  @Override
  public void deleteStudent(Long studentId) {
	usersRepo.deleteById(studentId);
  }

  @Override
  public Users updateStudentDetails(Long studentId, Users users) {
    Users existinguser = usersRepo.findById(studentId).orElse(null);
	if(existinguser != null) {
		existinguser.setFirstName(users.getFirstName());
		existinguser.setLastName(users.getLastName());
		existinguser.setDateOfBirth(users.getDateOfBirth());
		existinguser.setGender(users.getGender());
		return usersRepo.save(existinguser);
	}
	return existinguser;
  }

  @Override
  public Optional<Users> getStudentById(Long studentId) {
    return usersRepo.findById(studentId);
  }
}
