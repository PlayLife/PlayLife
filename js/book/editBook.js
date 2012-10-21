var facebook_page = 0;

$(document).ready(function(){
	$('#div_photo_facebook_login').click(fbLogin);
	window.fbAsyncInit = function() {
		initFB();
		fbLogin();
	}
});

function fbLogin(){
	FB.login(function(user) {
		if (user.authResponse != undefined && user.authResponse != null){
			$('#div_photo_facebook_login').addClass('hide');
			$('#div_photo_facebook_list').removeClass('hide');
			
			$('#div_photoContainer_facebook').block({message: null});
			FB.api('/me/albums', function(albums) {
				parseAlbums(albums);
			});
		}
	});
}

function parseAlbums(albums){
	if (albums.data != undefined && albums.data != null){
		$('#div_photo_facebook_list').empty();
		var div_album_container = $('<div />').addClass('div_photo_facebook_album_container').appendTo($('#div_photo_facebook_list'));
		/* Step 1 : Add Prev button if any */
		if (albums.paging != undefined && albums.paging != null && albums.paging.previous != undefined && albums.paging.previous != null){
			var div_album = $('<div />').addClass('round shadow div_photo_facebook_album div_photo_facebook_album_next').appendTo(div_album_container);
			var div_album_inner = $('<div />').addClass('div_photo_facebook_album_inner').appendTo(div_album);
			var img_album = $('<img />').attr({'src': '/img/previous.png'}).appendTo(div_album_inner);
			$('<span />').html('Previous').addClass('white verySmall').appendTo(div_album);
			
			div_album.click(function(e){
				$('#div_photoContainer_facebook').block({message: null});
				FB.api(albums.paging.previous, function(albums) {
					parseAlbums(albums);
				});
			});
		}
		
		/* Step 2 : Add Album */
		$(albums.data).each(function(album_idx){
			var div_album = $('<div />').addClass('round shadow div_photo_facebook_album').appendTo(div_album_container);
			var div_album_inner = $('<div />').addClass('div_photo_facebook_album_inner').appendTo(div_album);
			var img_album = $('<img />').appendTo(div_album_inner);
			$('<span />').html(this.name).addClass('white verySmall').appendTo(div_album);
			
			FB.api('/'+this.id+'/picture', function(photo){
				if (photo.data != undefined && photo.data != null){
					img_album.attr('src', photo.data.url);
				}
			});
		});
		
		/* Step 3 : Add Next button if any */
		if (albums.paging != undefined && albums.paging != null && albums.paging.next != undefined && albums.paging.next != null){
			var div_album = $('<div />').addClass('round shadow div_photo_facebook_album div_photo_facebook_album_next').appendTo(div_album_container);
			var div_album_inner = $('<div />').addClass('div_photo_facebook_album_inner').appendTo(div_album);
			var img_album = $('<img />').attr({'src': '/img/next.png'}).appendTo(div_album_inner);
			$('<span />').html('Next').addClass('white verySmall').appendTo(div_album);
			
			div_album.click(function(e){
				$('#div_photoContainer_facebook').block({message: null});
				$('#div_photo_facebook_list').scrollLeft(0);
				FB.api(albums.paging.next, function(albums) {
					parseAlbums(albums);
				});
			});
		}
		$('<div />').addClass('div_photo_facebook_album_padding').appendTo(div_album_container);
	}
	$('#div_photoContainer_facebook').unblock();
}
