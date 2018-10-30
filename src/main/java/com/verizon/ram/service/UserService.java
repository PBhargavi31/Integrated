package com.verizon.ram.service;

import java.util.List;

import com.verizon.ram.model.Users;

public interface UserService {
	
	List<Users> getAllUsers();
	Users getUserById(Long uid);
	Users addUser(Users user);
	List<Users> getUserByMid(Long mid);
	
	List<Users> findAllByMid(long mid);

}
