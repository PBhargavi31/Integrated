package com.verizon.ram.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.ram.model.Priority;

import com.verizon.ram.model.TStatus;
import com.verizon.ram.model.Tickets;
import com.verizon.ram.model.Trtype;
import com.verizon.ram.service.TicketService;


@RestController
//@CrossOrigin
@RequestMapping("/tickets")


public class ticketApi {
	
	@Autowired
	private TicketService tService;

	
	@GetMapping
	public ResponseEntity<List<Tickets>> listTickets(){
		ResponseEntity<List<Tickets>> resp=null;
		
		List<Tickets> tickets = tService.getAllTickets();
		if(tickets != null && tickets.size() >0)
			resp = new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Tickets>>(tickets,HttpStatus.NOT_FOUND);
		return resp;
		
	}
@PostMapping("/addTicket")
public ResponseEntity<Tickets> addTicketsAction(@RequestBody Tickets ticket) {
	ticket.setTstatus(TStatus.CREATED);//Default Status
	ResponseEntity<Tickets> resp=new ResponseEntity<Tickets>(ticket, HttpStatus.NOT_ACCEPTABLE);
	if(ticket!=null && tService.existsByTid(ticket.getTid()))return resp;
	tService.addTicket(ticket);
	resp = new ResponseEntity<Tickets>(ticket, HttpStatus.OK);
	return resp;

}
	
	@GetMapping("/tid/{id}")
	public ResponseEntity <Tickets> getTicketId(@PathVariable("id") long tid) {
		
		ResponseEntity<Tickets> resp=null;
		Tickets tickets =tService.getTicketById(tid);
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(tickets, HttpStatus.OK);
		return resp;
		
	}
	
	@PutMapping("/{tid}/{tstatus}")
	public ResponseEntity <Tickets> updateTicketState(@PathVariable("tstatus") String tstatus,@PathVariable("tid") long tid){
		tService.setStatusById(tstatus, tid);
		Tickets t = tService.getTicketById(tid);
		ResponseEntity<Tickets> resp=null;
		Tickets tickets =tService.getTicketById(tid);
		resp = new ResponseEntity<>(HttpStatus.OK);
		return resp;
	}
	
	

	 @PutMapping("/{tid}")
	public ResponseEntity<Tickets> updateTicket(@RequestBody Tickets tickets){


	ResponseEntity<Tickets> resp;
	if(tickets!=null && tService.existsByTid(tickets.getTid())){
	tService.updateTicket(tickets);

	resp = new ResponseEntity<Tickets>(tickets, HttpStatus.OK);
	}
	else 
	resp = new ResponseEntity<Tickets>(HttpStatus.NOT_FOUND);
	return resp;
	}
	
	@GetMapping("/status/{tstatus}")
	public ResponseEntity<List<Tickets>> getTicketStatus(@PathVariable("tstatus") String tstatus) {
		ResponseEntity<List<Tickets>> resp = null;
		List<Tickets> tickets = tService.getTicketsByTstatus(tstatus);
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
		return resp;
	}
	
	
	@GetMapping("/priority/{tpriority}")
	

	public ResponseEntity<List<Tickets>> getTicketPriority(@PathVariable("tpriority") Priority tpriority) {
		ResponseEntity<List<Tickets>> resp = null;
		List<Tickets> tickets = tService.getTicketsByTpriority(tpriority);
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
		return resp;
	}	
	//TODO: to be changed to be 
	@GetMapping("/uid/{uid}")
	public ResponseEntity <Tickets> getTicketuId(@PathVariable("uid") long uid) {
		
		ResponseEntity<Tickets> resp=null;
		Tickets tickets =tService.getTicketByUid(uid);
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else
			resp = new ResponseEntity<>(tickets, HttpStatus.OK);
		return resp;
		
	}
//	@GetMapping("/TicketByManagerId/{mid}")
//	public ResponseEntity <Tickets> ticketByManagerId(@PathVariable("mid") long mid){
//		ResponseEntity<List<Tickets>> resp=null;
//		List<Users> ul=uService.findAllByMid(mid);
//		List<Tickets> t = null;
//		ul.forEach(i->{
//			System.out.println(tService.getTicketByUid(i.getUid()).gettId());
//			});
//		resp = new ResponseEntity<>(t, HttpStatus.OK);
//		return resp;
//	}

	
	//API for retrieving values based on Ticket type: hardware/softare
	
	@GetMapping("/Trtype/{ttype}")
	public ResponseEntity<List<Tickets>> ticketByType(@PathVariable("ttype") Trtype ttype){
		ResponseEntity<List<Tickets>> resp = null;
		List<Tickets> tickets = tService.getTicketsByTtype(ttype);
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
		return resp;
	}
	
//API for retrieving values based on TSUB( subcategory of enums:hardware, software)
	
	@GetMapping("/Tsub/{sub}")
	public ResponseEntity<List<Tickets>> getTicketByTsub(@PathVariable("sub") String tsub){
		ResponseEntity<List<Tickets>> resp = null;
		
	
		List<Tickets> tickets = tService.getTicketsByTsub(tsub);
		
		if (tickets == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<List<Tickets>>(tickets, HttpStatus.OK);
		return resp;
	}
	@GetMapping("/TicketByManagerId/{mid}")
	public ResponseEntity <List<Tickets>> ticketByManagerId(@PathVariable("mid") long mid){
		ResponseEntity<java.util.List<Tickets>> resp=null;
		List<Tickets> t=tService.getTicketsByMid(mid);
		resp = new ResponseEntity<>(t, HttpStatus.OK);
		return resp;
	}

}

















































