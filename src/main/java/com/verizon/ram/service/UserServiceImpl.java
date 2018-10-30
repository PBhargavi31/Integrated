package com.verizon.ram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.ram.dao.UsersDao;
import com.verizon.ram.model.Users;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersDao userdao;
	
	@Override
	public List<Users> getAllUsers(){
		return  userdao.findAll();
	}

	@Override
	public Users getUserById(Long uid) {
		Users user = null;
		Optional<Users> optUser =userdao.findById(uid);
		if(optUser.isPresent()) {
			user = optUser.get();
		}
		return user;	
		}

	
	@Override
	public Users addUser(Users user) {
		return userdao.save(user);
	}

	@Override
	public List<Users> getUserByMid(Long mid) {
		return userdao.findAllByMid(mid);
	}

	@Override
	public List<Users> findAllByMid(long mid) {
		
		return userdao.findAllByMid(mid);
	}

}
