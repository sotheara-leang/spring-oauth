package com.mcnc.example.clientapp;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/mcnc/example/clientapp/appcontext-root.xml")
public class OAuth2RestTemplateTest {

	@Test
	public void doTest() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setUsername( "user" );
		resourceDetails.setPassword( "123" );
		resourceDetails.setAccessTokenUri( "http://localhost:8080/auth-server/oauth/token" );
		resourceDetails.setClientId( "1111" );
		resourceDetails.setClientSecret( "1111" );
		resourceDetails.setAuthenticationScheme( AuthenticationScheme.header );
		resourceDetails.setClientAuthenticationScheme( AuthenticationScheme.query );
		resourceDetails.setId( "resource-server" );
		resourceDetails.setScope( Arrays.asList( "article" ) );
		
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate( resourceDetails );
		
		ResponseEntity<String> forEntity = null;
		try {
			forEntity = restTemplate.getForEntity( "http://localhost:8080/resource-server/api/articles", String.class );
			System.out.println( forEntity );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
