package com.verizon.ram.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.ram.model.Hardware;
import com.verizon.ram.model.Priority;
import com.verizon.ram.model.TStatus;
import com.verizon.ram.model.Tickets;
import com.verizon.ram.model.Trtype;


@Repository
public interface TicketDao extends JpaRepository<Tickets, Long> {


	/*
	 * to add and get entire list of tickets without any condition, we can use
	 * save() and findAll() methods of Jparepository, hence we need not declare explicitly here.
	 */


	boolean existsByTid(long tid); // to check availability of tickets

	
	boolean existsByUid(long uid);
	
	Optional<Tickets> findByTid(long tid); // to find ticket using ticket Id

	
	Optional<Tickets> findByUid(long uid);
	
	
	List<Tickets> findAllByTstatus(TStatus tstatus);   // finding tickets based on status

	List<Tickets> findAllByTpriority(Priority tpriority);
	
	List<Tickets> findAllByTtype(Trtype ttype);
	
	List<Tickets> findAllByTsub(String tsub);
	
	List<Tickets> findByUidIn(List<Long> users);
	
	


	
	


}
