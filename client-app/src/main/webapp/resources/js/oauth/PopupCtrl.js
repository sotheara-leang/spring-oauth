(function(window, document, $) {
	'use strict';
	
	$(document).ready(function () {
		page.init();
	});
	
	var page = {
		init: function() {
			window.opener.initCallbackData(code, accessToken, state);
			window.close();
		}
	};
})(window, document, window.jQuery);