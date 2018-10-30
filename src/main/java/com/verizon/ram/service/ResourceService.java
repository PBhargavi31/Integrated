package com.verizon.ram.service;

import java.util.List;

import com.verizon.ram.model.RStatus;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Rtype;

public interface ResourceService {
	
	List<Resourcer> getAllResources();
	
	Resourcer addResource(Resourcer resources);
	
	Resourcer updateResource(Resourcer resources);
	
	boolean deleteResource(long rid);
	
	Resourcer findByRid(long rid);
	
	Resourcer getResourceByRid(long rid);
	
	
	
//	List<Resourcer> getResourceByRtype(Rtype rtype);
	
	List<Resourcer> findAllByRtype(Rtype rtype);
	
	List<Resourcer> findAllByRstatus(RStatus rstatus);
	
	List<Resourcer> findAllByUid(long uid);
	
	List<Resourcer> findAllByRname(String rname);
	
	boolean existsByRid(long rid);
	
	
}
