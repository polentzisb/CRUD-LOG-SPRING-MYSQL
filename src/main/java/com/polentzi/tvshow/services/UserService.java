package com.polentzi.tvshow.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.polentzi.tvshow.models.User;
import com.polentzi.tvshow.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User registerUser(User user, BindingResult resultado) {
		User userRegistrado = userRepo.findByEmail(user.getEmail());
		if (userRegistrado != null) {
			resultado.rejectValue("email", "Matches", "email already exist");
		}
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			resultado.rejectValue("password", "Matches", "the password must be the same");
		}
		if(resultado.hasErrors()) {
        	return null;
        }

		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);		
		return userRepo.save(user);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public User findUserById(Long id) {
		Optional<User> u = userRepo.findById(id);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}


	public boolean authenticateUser(String email, String password, BindingResult resultado) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			resultado.rejectValue("email", "Matches", "invalid email");
			return false;
		} else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				resultado.rejectValue("password", "Matches", "invalid password");
				return false;
			}
		}
	}

}
