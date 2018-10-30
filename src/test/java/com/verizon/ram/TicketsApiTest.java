package com.verizon.ram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.ram.model.Priority;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.TStatus;
import com.verizon.ram.model.Tickets;
import com.verizon.ram.model.Trtype;
import com.verizon.ram.restapi.ticketApi;
import com.verizon.ram.service.ResourceService;
import com.verizon.ram.service.TicketService;
import com.verizon.ram.test.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ticketApi.class)
public class TicketsApiTest {
	private MockMvc mockMvc;
	
	@MockBean
	private TicketService tmock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	@Test
	public void testGetAllTickets() throws Exception {
		assertThat(this.tmock).isNotNull();

		List<Tickets> ticketList = new ArrayList<>();
		ticketList.add(new Tickets());

		when(tmock.getAllTickets()).thenReturn(ticketList);

		mockMvc.perform(get("/tickets")).andExpect(status().isOk()).andDo(print());

	}
	
//@Test
//public void addTicket() throws Exception{
//	assertThat(this.tmock).isNotNull();
//	Tickets t1 = new Tickets();
//	Tickets t2 = new Tickets();
//	//Setters
//	t1.setTid(101);
//	t1.setAcmnt("a");
//	t1.setMcmnt("a");
//	t1.setEnd("a");
//	t1.setUid(101);
//	t1.setmtime("a");
//	t1.setRid(101);
//	t1.setTdesc("a");
//	t1.setStart("a");
//	t1.setTsub("a");
//	t1.setTtype(Trtype.HARDWARE);
//	t1.setTpriority(Priority.HIGH);
//	t1.setTstatus(TStatus.APPROVED);
//	//sachinam
//	
//	t2.setTid(202);
//	t2.setAcmnt("a");
//	t2.setMcmnt("a");
//	t2.setEnd("a");
//	t2.setUid(101);
//	t2.setmtime("a");
//	t2.setRid(101);
//	t2.setTdesc("a");
//	t2.setStart("a");
//	t2.setTsub("a");
//	t2.setTtype(Trtype.HARDWARE);
//	t2.setTpriority(Priority.HIGH);
//	t2.setTstatus(TStatus.APPROVED);
//	
//	
//	when(tmock.addTicket(Mockito.any(Tickets.class))).thenReturn(t2);
//	
//
//	mockMvc.perform(post("/addTicket").contentType(TestUtil.APPLICATION_JSON_UTF8)
//			.content(TestUtil.convertObjectToJsonBytes(t1))).andDo(print()).andExpect(status().isOk())
//			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
//	
//}
	
	

}
