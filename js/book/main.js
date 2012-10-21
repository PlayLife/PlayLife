function initFB(){
	FB.init({appId: '288454414516535', status: true, cookie: true, xfbml: true, user_photos: 1});
}

function cufon(){
	Cufon.replace('.topic', { fontFamily: 'Needlework Good'} );
}

$(document).ready(function(){
	cufon();
});