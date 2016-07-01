(function(window, document, $) {
	'use strict';

	var authServer = "http://localhost:8080/auth-server";
	var resourceServer = "http://localhost:8080/resource-server";
	var oAuth = new OAuth("1111", "1111", "http://localhost:8080/client-app/oauth");
	
	var page = {
		init: function() {
			this.initView();
			this.bindView();
		},
		initView: function () {},
		bindView: function() {
			
			$("#btnClear").click(function() {
				var tableBody = $("#articleTable tbody");
				$(tableBody).html("");
			});
			
			// Authorization Code
			$("#btnAuthCode").click(function() {
				var url = oAuth.createAuthCodeUrl(authServer + "/oauth/authorize", {scope: "article", state: "access_article"});
				var popupWindow = window.open(url, "", "width=500, height=600");
			});
			
			$("#code").change(function() {
				console.log("\n\n===== Authorization Code =====");
				
				var code = $(this).val();
				var promise = oAuth.getAuthCodeAccessToken(authServer + "/oauth/token", code);
				promise.then(function(token) {
					console.log("===== Access Token =====", token);
					return page.getArticles(token.access_token);
					
				}).then(function(articles) {
					console.log("===== Articles =====", articles);
					page.initArticleTable(articles);
				});
			});
			
			// Implicit - Spring redirect with wrong url starting with # instead of ?
			// http://localhost:8080/client-app/oauth#access_token=0cdce860-4973-41a2-a197-7cae479333f3&token_type=bearer&expires_in=2204 
			
			$("#btnImplicit").click(function() {
				console.log("\n\n===== Implicit =====");
				var url = oAuth.createImplicitUrl(authServer + "/oauth/authorize", {scope: "article", state: "access_article"});
				var popupWindow = window.open(url, "", "width=500, height=600");
			});
			
			
			$("#accessToken").change(function() {
				var accessToken = $(this).val();
				getArticles(accessToken).then(function(articles) {
					console.log("===== Articles =====", articles);
					page.initArticleTable(articles);
				});
			});
			
		},
		getArticles: function(accessToken) {
			var url = resourceServer + "/api/articles";
			return $.ajax({
			    url: url,
			    beforeSend: function (xhr) {
			        xhr.setRequestHeader ("Authorization", "Bearer " + accessToken);
			    }
			});
		},
		initArticleTable: function(articles) {
			var tr;
			var tableBody = $("#articleTable tbody");
			$(tableBody).html("");
			
			$.each(articles, function(index, article) {
				tr = 
					"<tr>"
						+ "<td>" + article.id +  "</td>"
						+ "<td>" + article.title + "</td>"
						+ "<td>" + article.content + "</td>"
					"</tr>";
				tableBody.append(tr);
			});
		}
	};	
	
	$(document).ready(function () {
		page.init();
	});
	
})(window, document, window.jQuery);