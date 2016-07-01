function OAuth(clientId, clientSecret, redirectUri) {
	this.clientId = clientId || undefined;
	this.clientSecret = clientSecret || undefined;
	this.redirectUri = redirectUri || undefined;
};

OAuth.prototype.createAuthCodeUrl = function(authEndpoint, data) {
	var params = $.extend({}, data);
	params.response_type = "code";
	params.client_id = this.clientId;
	params.redirect_uri = this.redirectUri;
	return authEndpoint + "?" + $.param(params);
};

OAuth.prototype.createImplicitUrl = function(authEndpoint, data) {
	var params = $.extend({}, data);
	params.response_type = "token";
	params.client_id = this.clientId;
	params.redirect_uri = this.redirectUri;
	return authEndpoint + "?" + $.param(params);
};

OAuth.prototype.getAuthCodeAccessToken = function(tokenEndpoint, code) {
	var param = {};
	param.grant_type = "authorization_code";
	param.client_id = this.clientId;
	param.client_secret = this.clientSecret;
	param.code = code;
	param.redirect_uri = this.redirectUri;
	
	return $.post(tokenEndpoint + "?" + $.param(param));
};