(function(window, document, $) {
	'use strict';

	var code, accessToken, state;
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
			$("#btnGetArticles").click(function() {
				var url = oAuth.createAuthCodeUrl(authServer + "/oauth/authorize", {scope: "article", state: "access_article"});
				var popupWindow = window.open(url, "", "width=500, height=600");
				popupWindow.onbeforeunload = function() {
					if (code !== null) {
						page.requestAuthCodeAccessToken();
					}
				};
			});
		},
		requestAuthCodeAccessToken: function() {
			oAuth.getAuthCodeAccessToken(authServer + "/oauth/token", code, function(response) {
				console.log("Get Access Token: " + response);
				page.getArticles(response.access_token);
			});
		},
		getArticles: function(accessToken) {
			var url = resourceServer + "/api/articles";
			$.ajax({
			    url: url,
			    beforeSend: function (xhr) {
			        xhr.setRequestHeader ("Authorization", "Bearer " + accessToken);
			    }
			})
			.done(function(articles) {
				code = null;
				accessToken = null;
				state = null;
				initArticleTable(articles)
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
						+ "<td>" + article+ content + "</td>"
					"</tr>";
				
				tableBody.append(tr);
			});
		}
	};	
	
	$(document).ready(function () {
		page.init();
	});
	
	window.initCallbackData = function (_Code, _AccessToken, _State) {
		code = _Code;
		accessToken =  _AccessToken;
		state = _State;
	};
	
})(window, document, window.jQuery);