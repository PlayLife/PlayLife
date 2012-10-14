$(document).ready(function(){
	window.fbAsyncInit = function() {
		initFB();
		FB.login(function(response) {
			if (response.authResponse) {
				FB.api('/me/albums', function(response) {
					alert(response.data[0].name);
				});
			} else {
			
			}
		});
	}
});