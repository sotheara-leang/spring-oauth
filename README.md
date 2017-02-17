# spring-oauth

+ Authorization Code
	
		oauth/authorize?response_type=code&client_id=&scope=&redirect_uri=
		
		oauth/token?grant_type=authorization_code&code=&redirect_uri=&client_id=&client_secret=
		
+ Implicit

		oauth/authorize?response_type=token&client_id=&scope=&redirect_uri
		
		No refresh token
	
+ Resource Owner Password Credentials
	
		oauth/token?grant_type=password&username=&password=&client_id=&client_secret=
		
+ Client Credentials
	
		oauth/token?grant_type=client_credentials&client_id=&client_secret=
		
		No refresh token
	
+ Refresh Token
	
		oauth/token?grant_type=refresh_token&refresh_token=&client_id=&client_secret=
