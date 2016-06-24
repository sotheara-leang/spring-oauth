package com.mcnc.example.authserver;

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
		"classpath:com/mcnc/example/authserver/appcontext-root.xml",
		"classpath:com/mcnc/example/authserver/appcontext-datasource.xml",
		"classpath:com/mcnc/example/authserver/appcontext-oauth.xml",
		"classpath:com/mcnc/example/authserver/appcontext-security.xml",
		"classpath:com/mcnc/example/authserver/appcontext-mvc.xml"
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
		return content;
	}
	
	@Test
	public void doTest() throws Exception {
		System.out.println(getAccessToken("user", "123"));
	}
}
