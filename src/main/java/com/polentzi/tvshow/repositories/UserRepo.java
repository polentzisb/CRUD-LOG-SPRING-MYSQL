package com.polentzi.tvshow.repositories;


import org.springframework.data.repository.CrudRepository;

import com.polentzi.tvshow.models.User;

public interface UserRepo extends CrudRepository<User, Long>{
	 User findByEmail(String email);
}