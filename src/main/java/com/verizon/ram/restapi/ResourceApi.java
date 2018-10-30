package com.verizon.ram.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.ram.model.RStatus;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Rtype;
import com.verizon.ram.service.ResourceService;

@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceApi {

	@Autowired
	private ResourceService rs;
	
	@GetMapping
	public ResponseEntity<List<Resourcer>> getAllResources() {
		return new ResponseEntity<>(rs.getAllResources(), HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Resourcer> getResourcerByRefid(@PathVariable("id") long rid) {
		ResponseEntity<Resourcer> resp;
		Resourcer c = rs.getResourceByRid(rid);
		if (c == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(c, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/User/{srchValue}")
	public ResponseEntity<List<Resourcer>> getAllResourcers (
		@PathVariable("srchValue") long uid)
	{
		ResponseEntity<List<Resourcer>> resp;
			
			
				List<Resourcer> results =rs.findAllByUid(uid);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	@GetMapping("/Rname/{srchValue}")
	public ResponseEntity<List<Resourcer>> getAllRnames (
		@PathVariable("srchValue") String rname)
	{
		ResponseEntity<List<Resourcer>> resp;
			
			
				List<Resourcer> results =rs.findAllByRname(rname);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	@GetMapping("/Type/{srchValue}")
	public ResponseEntity<List<Resourcer>> getAllResourcers (
		
		@PathVariable("srchValue") Rtype searchValue)
	{
		ResponseEntity<List<Resourcer>> resp;
			
			
				List<Resourcer> results =rs.findAllByRtype(searchValue);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	@GetMapping("/Status/{srchValue}")
	public ResponseEntity<List<Resourcer>> getAllResourcers (
		
		@PathVariable("srchValue") RStatus searchValue)
	{
		ResponseEntity<List<Resourcer>> resp;
			
			
				List<Resourcer> results =rs.findAllByRstatus(searchValue);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	
		
		return resp;
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Resourcer> addCustomer(@RequestBody Resourcer resource) {
		ResponseEntity<Resourcer> resp = null;
		RStatus r=null;
		
		if (resp == null) {
			Resourcer c = rs.addResource(resource);
			c.setUid(0);
			c.setRstatus(r.NA);
			if (c == null)
				resp = new ResponseEntity<Resourcer>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Resourcer>(c, HttpStatus.OK);
		}
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<Resourcer> updateResourcer (@RequestBody Resourcer Resourcer){
		ResponseEntity<Resourcer> resp = null;
		Resourcer c= rs.getResourceByRid(Resourcer.getRid());
		RStatus r = null;
		
		if(resp==null){
			c= rs.updateResource(Resourcer);
			if(c==null)
				resp=new ResponseEntity<Resourcer>(HttpStatus.BAD_REQUEST);
			
			
			else if(c.getUid()==0){
				c.setRstatus(r.NA);
				resp=new ResponseEntity<Resourcer>(c,HttpStatus.OK);
			System.out.println(c.getUid());
			}
			
			else {
				c.setRstatus(r.A);
				resp=new ResponseEntity<Resourcer>(c,HttpStatus.OK);
			System.out.println(c.getUid());
			
			}
		}
		return resp;
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<Void> deleteResourceAction(@PathVariable("id") long rid) 
	{		
		ResponseEntity<Void> resp = null;		
	boolean isDeleted = rs.deleteResource(rid);	
	if (!isDeleted)			
		resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	else			
		resp = new ResponseEntity<>(HttpStatus.OK);		
	return resp;
	}
}
