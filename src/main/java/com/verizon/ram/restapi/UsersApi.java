package com.verizon.ram.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Role;
import com.verizon.ram.model.Rtype;
import com.verizon.ram.model.Users;
import com.verizon.ram.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersApi {
	@Autowired
	private UserService us;
	
	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);

	}
	
	@GetMapping("/Role/{srchValue}")
	public ResponseEntity<List<Users>> getEmpByRole (
		
		@PathVariable("srchValue") Role searchValue)
	{
		ResponseEntity<List<Users>> resp;
			
			
				List<Users> results =us.findAllByRole(searchValue);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Users> getAdminByUid(@PathVariable("id") long uid) {
		ResponseEntity<Users> resp = null;
		Role r=null;
		
		Users u = us.getUserById(uid);
		
			
		if (u == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else{ 
			if (u.getRole()==r.ADMIN)
			resp = new ResponseEntity<>(u, HttpStatus.OK);}
		
		return resp;
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Users> getEmployeeByUid(@PathVariable("id") long uid) {
		ResponseEntity<Users> resp = null;
		Role r=null;
		
		Users u = us.getUserById(uid);
		
			
		if (u == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else{ 
			
			resp = new ResponseEntity<>(u, HttpStatus.OK);}
		
		return resp;
	}
	
	
	
//	@GetMapping("/manager/{id}")//Search employees under a manager
//	public ResponseEntity<List<Users>> getUserByMgrId(@PathVariable("id") long mid) {
//		ResponseEntity<List<Users>> resp;
//		List<Users> u = us.getUserByMid(mid);
//		if (u == null)
//			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		else
//			resp = new ResponseEntity<>(u, HttpStatus.OK);
//		return resp;
//	}
	
	@GetMapping("/Emgr/{srchValue}")
	public ResponseEntity<List<Users>> getAllRnames (
		@PathVariable("srchValue") long mid)
	{
		ResponseEntity<List<Users>> resp;
			
			
				List<Users> results =us.findAllByMid(mid);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Users> getMgrById(@PathVariable("uid") long uid){
		ResponseEntity<Users> resp;
		Users rst= us.getUserById(uid);
		long mid=rst.getMid();
		Users rst1=us.getUserById(mid);
		
		if(rst1!=null )
			resp=new ResponseEntity<>(rst1,HttpStatus.OK);
		else
			resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return resp;	
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<Users> addUser(@RequestBody Users user) {
		ResponseEntity<Users> resp = null;
		Role r=null;
		
		
			
			if(user.getRole()==r.MANAGER ){
				user.setMid(0);
			}
			if( user.getRole()==r.ADMIN){user.setMid(1);}
			
			
				
				if(user.getRole()==r.EMPLOYEE)
				{
					if(!us.existsByUid(user.getMid()))
						resp = new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
					else
				 {
					Users u= us.addUser(user);
					resp = new ResponseEntity<>(u, HttpStatus.OK);}
				}
			
		
		return resp;
	}
}
