package com.mcnc.example.resourceserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:com/mcnc/example/resourceserver/appcontext-root.xml",
		"classpath:com/mcnc/example/resourceserver/appcontext-datasource.xml",
		"classpath:com/mcnc/example/resourceserver/appcontext-oauth.xml",
		"classpath:com/mcnc/example/resourceserver/appcontext-security.xml",
		"classpath:com/mcnc/example/resourceserver/appcontext-mvc.xml"
})
@WebAppConfiguration
public class MyTest {
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}
	
	private String getAccessToken(String username, String password) throws Exception {
		//String authorization = "Basic " + new String(Base64Utils.encode("clientapp:123456".getBytes()));

		String content = mvc
				.perform(
						post("/oauth/token")
							//	.header("Authorization", authorization)
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", username)
								.param("password", password)
								.param("grant_type", "password")
								.param("client_id", "1111")
								.param("client_secret", "1111"))
				
				
				
				.andReturn().getResponse().getContentAsString();
		return content.substring(17, 53);
	}
	
	@Test
	public void doTest() throws Exception {
		String accessToken = "428bf800-b9de-4a44-92af-5965e527b2fc";
		
		String result = mvc.perform(
				get("/api/employees")
				.header("Authorization", "Bearer " + accessToken))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
}
