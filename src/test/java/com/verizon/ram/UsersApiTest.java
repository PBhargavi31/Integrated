package com.verizon.ram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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


import com.verizon.ram.model.Role;

import com.verizon.ram.model.Users;
import com.verizon.ram.restapi.ResourceApi;
import com.verizon.ram.restapi.UsersApi;
import com.verizon.ram.service.ResourceService;
import com.verizon.ram.service.UserService;
import com.verizon.ram.test.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersApi.class)
public class UsersApiTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private UserService usmock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		assertThat(this.usmock).isNotNull();

		List<Users> planList = new ArrayList<>();
		planList.add(new Users());

		when(usmock.getAllUsers()).thenReturn(planList);

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andDo(print());

	}
	
//	@Test
//	public void testAddUserAction() throws Exception {
//		assertThat(this.usmock).isNotNull();
//		Role r =null;
//		
//		Users rs = new Users();
//		rs.setUid(123);
//		rs.setEmailid("satish@gmail.com");
//		rs.setName("Firefox");
//		rs.setPwd("roger");
//		rs.setRole(r.ADMIN);
//		rs.setMid(1);
//		//rs.setRstatus(r1.AVAILABLE);
//		System.out.println(rs.getMid());
//		
//		Users rs1 = new Users();
//		rs.setUid(222);
//		rs.setEmailid("satish@gmail.com");
//		rs.setName("Firefox");
//		rs.setPwd("roger");
//		rs.setRole(r.EMPLOYEE);
//		rs.setMid(625);
//		//rs1.setRstatus(r1.AVAILABLE);
//		System.out.println(rs1.getMid());
//		
//		when(usmock.addUser(Mockito.any(Users.class))).thenReturn(rs1);
//
//		mockMvc.perform(post("/users").contentType(TestUtil.APPLICATION_JSON_UTF8)
//				.content(TestUtil.convertObjectToJsonBytes(rs))).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
//		
//		
//		
//		
//	}
	
	
	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.usmock).isNotNull();
		long uid = 28;
		Role r= null;

		Users u1 = new Users();

		u1.setUid(28);
		u1.setName("Error");
		u1.setEmail("dumma@gmail.com");
		u1.setRole(r.EMPLOYEE);
		u1.setMid(15);
		when(usmock.getUserById(uid)).thenReturn(u1);

		mockMvc.perform(get("/users/employee/28")).andExpect(status().isOk()).andDo(print());

	}
	

}
