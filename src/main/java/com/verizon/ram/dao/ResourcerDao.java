package com.verizon.ram.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.ram.model.RStatus;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Rtype;

@Repository
public interface ResourcerDao extends JpaRepository<Resourcer, Long> {
	
Resourcer findByRid(long rid);
List<Resourcer> findAllByRtype(Rtype rtype);
List<Resourcer> findAllByRstatus(RStatus rstatus);
List<Resourcer> findAllByUid(long uid);
List<Resourcer> findAllByRname(String rname);
boolean existsByRid(long rid);


}
