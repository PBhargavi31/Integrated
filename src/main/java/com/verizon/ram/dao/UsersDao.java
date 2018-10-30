package com.verizon.ram.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verizon.ram.model.Role;
import com.verizon.ram.model.Users;

public interface UsersDao extends JpaRepository<Users, Long>{
	
	List<Users> findAllByMid(long mid);
	
	List<Users> findAllByRole(Role role);
	boolean existsByUid(long uid);
}
