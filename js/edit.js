var panelHeight = 0;
$('document').ready(function(){
	window.fbAsyncInit = function(){
		initFB();
		addAlbum('div_albumList');
	}
	bindClick();
	$('#div_editPanel').droppable({
		accept : '.img_photo',
		activeClass: 'ui-state-hover',
        hoverClass: 'ui-state-active',
		drop : function(event, ui){
			var ratio = $(ui.draggable).data('photo').images[0].width / $(ui.draggable).data('photo').images[0].height;
			var img = $('<img />').attr('src', $(ui.draggable).data('photo').images[0].source).appendTo($(this)).resizable({aspectRatio : ratio, containment: 'parent',
				stop : function(event, ui){					
					thisHeight = parseInt(img.css('top').replace("px", "")) + (img.height()/2);
					if (thisHeight > panelHeight)
						panelHeight = thisHeight;
					alert(thisHeight);		
					alert(panelHeight);						
				}}).parent().draggable({
				stop: function(event, ui){
					alert(img.css('top'));
					thisHeight = parseInt(img.css('top').replace("px", "")) + (img.height()/2);
					if (thisHeight > panelHeight)
						panelHeight = thisHeight;
						
					alert(thisHeight);
					alert(panelHeight);						
				}
			});
			img.hide();
			img.show('slow');
		}
	});
});

function addAlbum(target) {
	var ul_album = $('<ul />').attr('id', 'ul_album').appendTo($('#'+target));
	FB.api('/me/albums', function(response) {
		if (response.error != null)
			$("#div_albumList").html('error');	
		else {
			$.each(response.data, function(data_cnt, data){
				var li_album = $('<li />').attr('id','li_album'+data_cnt).addClass('li_album').appendTo(ul_album);
				var div_album = $('<div />').attr('id', 'div_album'+data_cnt).addClass('div_album').data({'aid':data.id}).appendTo(li_album);
				var img_album = $('<img />').addClass('img_album').prependTo(div_album);
				FB.api('/'+data.id+'/photos', function(response){
					if (response.error == null){
						img_album.attr('src', response.data[0].images[1].source);
						div_album.hide();
						div_album.show('slow');
					}
				});
				$('<span />').html(data.name).addClass('span_album','slow').appendTo(li_album);
			});
			$('#ul_album').hoverscroll({
				vertical:false,
				width : 1000,
				height : 150,
				fixedArrows: false,
			});				
		}
	});
}

function bindClick(){
	$('div .div_album').live('click', function(){
		$('#ul_album').children().hide('slow');
		var aid = $(this).data('aid');

		FB.api('/'+aid+'/photos', function(response){
			if (response.data != null){
				$.each(response.data, function(photo_cnt, photo){
					var li_photo = $('<li />').attr('id','li_photo'+photo_cnt).addClass('li_photo').appendTo($('#ul_album'));
					var img_photo = $('<img />').attr({'src': photo.images[1].source}).addClass('img_photo').data({photo : photo}).prependTo(li_photo);
					img_photo.draggable({ appendTo: 'body', containment: 'window', scroll: false, helper: 'clone', stop : function(){
						$(this).css({'left' : 0});
						$(this).css({'top' : panelHeight});
						panelHeight = $(this).height();
					}});
				});
				var div_btn_photo = $('<div />').addClass('div_btn_photo').appendTo($('#ul_album'));	
				var btn_photo = $('<button />').addClass('btn_photo').appendTo(div_btn_photo).click(function(){
					$('#div_albumList').empty();
					addAlbum('div_albumList');	
				}).button({
					icons: {
						primary: "ui-icon-arrowreturnthick-1-w"
					},
					text: false
				});
				$('#ul_album').hide();
				$('#ul_album').show('slow');				
			}			
			$('#ul_album').hoverscroll({
				vertical:false,
				width : 1000,
				height : 150,
				fixedArrows: false
			});				
		});			
	});
}