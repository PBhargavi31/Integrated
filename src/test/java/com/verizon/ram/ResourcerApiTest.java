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

import com.verizon.ram.model.RStatus;
import com.verizon.ram.model.Resourcer;
import com.verizon.ram.model.Rtype;
import com.verizon.ram.restapi.ResourceApi;
import com.verizon.ram.service.ResourceService;
import com.verizon.ram.test.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ResourceApi.class)
public class ResourcerApiTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ResourceService rsmock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllResources() throws Exception {
		assertThat(this.rsmock).isNotNull();

		List<Resourcer> planList = new ArrayList<>();
		planList.add(new Resourcer());

		when(rsmock.getAllResources()).thenReturn(planList);

		mockMvc.perform(get("/resource")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testAddResourceAction() throws Exception {
		assertThat(this.rsmock).isNotNull();
		Rtype r = null;
		RStatus r1 = null;
		
		Resourcer rs = new Resourcer();
		rs.setRid(123);
		rs.setRdesc("8gb");
		rs.setRname("Mouse");
		rs.setRtype(r.HARDWARE);
		//rs.setRstatus(r1.AVAILABLE);
		System.out.println(rs);
		
		Resourcer rs1 = new Resourcer();
		rs1.setRid(423);
		rs1.setRdesc("8gb");
		rs1.setRname("Mouse");
		//rs1.setUid(234);
		rs1.setRtype(r.HARDWARE);
		//rs1.setRstatus(r1.AVAILABLE);
		System.out.println(rs1);
		
		when(rsmock.addResource(Mockito.any(Resourcer.class))).thenReturn(rs1);

		mockMvc.perform(post("/resource").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(rs))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
		
		
		
		
	}
	
	
	@Test
	public void testUpdateResourceAction() throws Exception {
		assertThat(this.rsmock).isNotNull();
		Rtype r = null;
		RStatus r1 = null;
		
		Resourcer rs = new Resourcer();
		rs.setRid(123);
		rs.setRdesc("8gb");
		rs.setRname("Mouse");
		rs.setRtype(r.HARDWARE);
		//rs.setRstatus(r1.AVAILABLE);
		System.out.println(rs);
		
		long rid = 123;
		
		Resourcer rs1 = new Resourcer();
		rs1.setRid(123);
		rs1.setRdesc("8gb");
		rs1.setRname("Mouse");
		rs1.setUid(234);
		rs1.setRtype(r.HARDWARE);
		//rs1.setRstatus(r1.AVAILABLE);
		System.out.println(rs1);
		
		when(rsmock.getResourceByRid(rid)).thenReturn(rs1);

		when(rsmock.updateResource(Mockito.any(Resourcer.class))).thenReturn(rs);

		mockMvc.perform(put("/resource").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(rs))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	
	
	}
	
	@Test
	public void testDeleteResource() throws Exception {
		assertThat(this.rsmock).isNotNull();

		int rid = 123;

		when(rsmock.deleteResource(rid)).thenReturn(true);

		mockMvc.perform(delete("/resource/123")).andExpect(status().isOk()).andDo(print());

	}
	
}
