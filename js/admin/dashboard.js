$(document).ready(function(e){
	$('#btn_updateLikeCount').click(function(e){
		$.ajax({
			traditional : true,
			url : '/do/drawing/updateLikeCount',
			type: 'POST',
			dataType: 'json',
			success : function(data){
				if (data.status == 'ok'){
					var div_error = $('#div_error').removeClass('hide').empty();;
					var div = $('<div />').addClass('alert alert-success').appendTo(div_error);
					var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
					$('<strong />').attr({id : 'strong_error'}).html('Success ').appendTo(div);
					$('<span />').attr({id : 'span_error'}).html('Like Count Updated.').appendTo(div);
				} else {
					var div_error = $('#div_error').removeClass('hide').empty();
					var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
					var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
					$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
					$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
				}
			}, 
			error: function (){
				var div_error = $('#div_error').removeClass('hide').empty();;
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			}
		});
	});
});