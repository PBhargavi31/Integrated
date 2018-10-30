package com.verizon.ram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.ram.dao.ResourcerDao;
import com.verizon.ram.model.RStatus;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Rtype;

@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourcerDao rd;

	@Override
	public Resourcer addResource(Resourcer resources) {
		
		return rd.save(resources);
	}

	@Override
	public Resourcer findByRid(long rid) {
		
		return rd.findByRid(rid);
	}

	@Override
	public List<Resourcer> getAllResources() {
		return rd.findAll();
	}

	@Override
	public Resourcer getResourceByRid(long rid) {
		Resourcer resource=null;
//		if(rd.existsById(rid)){  // Data base is hit twice while searching and retrieving
//			Customer= rd.findById(rid).get();
//		}
		Optional<Resourcer> optCustomer=rd.findById(rid);
		if(optCustomer.isPresent()){// checking if data is available
			resource=optCustomer.get();// retrieve the data using .get
		}
		return resource;
	}
	
//	@Override
//	public List<Resourcer> getResourceByRtype(Rtype rtype) {
//		Resourcer resource=null;
////		if(rd.existsById(rid)){  // Data base is hit twice while searching and retrieving
////			Customer= rd.findById(rid).get();
////		}
//		List<Resourcer> optCustomer=rd.findAllByRtype(rtype);
//		if(optCustomer != null){// checking if data is available
//			resource=optCustomer.sort(c);;// retrieve the data using .get
//		}
//		return resource;
//	}

	@Override
	public List<Resourcer> findAllByRtype(Rtype rtype) {
		
		return rd.findAllByRtype(rtype);
	}

	@Override
	public List<Resourcer> findAllByRstatus(RStatus rstatus) {
		
		return rd.findAllByRstatus(rstatus);
	}

	@Override
	public List<Resourcer> findAllByUid(long uid) {
		return rd.findAllByUid(uid);
	}

	@Override
	public Resourcer updateResource(Resourcer resources) {
		
		return rd.save(resources);
	}

	@Override
	public boolean deleteResource(long rid) {
		boolean isdeleted=false; 
		if(rd.existsById(rid)){
			
			rd.deleteById(rid);
			isdeleted=true;}
			return isdeleted;
	}

	@Override
	public boolean existsByRid(long rid) {
		
		return rd.existsByRid(rid);
	}

	@Override
	public List<Resourcer> findAllByRname(String rname) {
		
		return rd.findAllByRname(rname);
	}


}
