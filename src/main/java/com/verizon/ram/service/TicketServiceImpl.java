package com.verizon.ram.service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.verizon.ram.dao.TicketDao;
import com.verizon.ram.model.Hardware;
import com.verizon.ram.model.Priority;
import com.verizon.ram.model.TStatus;
import com.verizon.ram.model.Tickets;
import com.verizon.ram.model.Trtype;
import com.verizon.ram.model.Users;

@Component
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao tdao;

	@Autowired
	private UserService uService;

	@Override
	public List<Tickets> getAllTickets() {
		return tdao.findAll();// findAll is inbuilt in jpa repo
	}

	@Override
	public void addTicket(Tickets ticket) {
		tdao.save(ticket);// save is inbuilt, to insert or add values
	}

	@Override
	public Tickets getTicketById(long tid) {

		Optional<Tickets> opt = tdao.findById(tid);
		return opt.isPresent() ? opt.get() : null;

	}

	@Override
	public List<Tickets> getTicketsByTstatus(String tstatus) {
		tstatus = tstatus.toUpperCase();
		TStatus ts = TStatus.valueOf(tstatus);
		return tdao.findAllByTstatus(ts);
	}

	@Override
	public List<Tickets> getTicketsByTpriority(Priority tpriority) {

		return tdao.findAllByTpriority(tpriority);
	}

	@Override
	public void setStatusById(String tstatus, long tid) {
		tstatus = tstatus.toUpperCase();
		TStatus ts = TStatus.valueOf(tstatus);
		Tickets t = tdao.getOne(tid);
		t.setTstatus(ts);
		tdao.save(t);
	}

	@Override
	public boolean existsByUid(long uid) {

		return tdao.existsByUid(uid);
	}

	@Override
	public Tickets getTicketByUid(long uid) {

		Optional<Tickets> opt = tdao.findByUid(uid);
		return opt.isPresent() ? opt.get() : null;

	}

	@Override
	public boolean existsByTid(long tid) {
		return tdao.existsByTid(tid);
	}

	@Override
	public List<Tickets> getTicketsByTtype(Trtype ttype) {
		// type=type.toUpperCase();
		// Trtype trtype = Trtype.valueOf(type);
		return tdao.findAllByTtype(ttype);

	}

	@Override
	public List<Tickets> getTicketsByTsub(String tsub) {
		return tdao.findAllByTsub(tsub);
	}
	@Override
	public List<Tickets> getTicketsByMid(long mid) {
		List<Users> users=uService.findAllByMid(mid);
		Vector<Long> uids=new Vector();
		for( Users u : users)uids.addElement(u.getUid());
		List<Tickets> t=tdao.findByUidIn(uids);
		return t;
	}
	
	
	@Override
	public void updateTicket(Tickets tickets) {
	tdao.save(tickets);
	}
	
	
	
	
	
	
	
	////
	// @Override
	// public List<Tickets> findAllBySwtype(Software swtype) {
	// return tdao.findAllBySwType(swtype);
	// }

	// @Override
	// public List<Tickets> findAllBySwtype(String stype) {
	// stype=stype.toUpperCase();
	// Software sw = Software.valueOf(stype);
	// return tdao.findAllBySwType(sw);
	//
	// }

	// @Override
	// public Tickets getTicketByMid(long mid) {
	// List<Users> ul= uService.findAllByMid(mid);
	// return null;
	// }
	//
	//
	//

}
