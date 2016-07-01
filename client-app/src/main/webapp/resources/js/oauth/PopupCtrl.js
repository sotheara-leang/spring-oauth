(function(window, document, $) {
	'use strict';
	
	$(document).ready(function () {
		page.init();
	});
	
	var page = {
		init: function() {
			window.opener.$("#state").val(state);
			if (code !== '') {
				window.opener.$("#code").val(code).change();
			} else if (accessToken !== '') {
				window.opener.$("#accessToken").val(accessToken).change();
			}
			window.close();
		}
	};
})(window, document, window.jQuery);