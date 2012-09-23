$('#form_sendCode').ajaxForm({
	dataType : 'json',
	success : function(data){
		if (data.status == 'ok'){
			$('#tb_checkCode_email').val($('#tb_sendCode_email').val());
			$('#hidden_checkCode_email').val($('#tb_sendCode_email').val());
			$('#form_sendCode').flipContent($('#form_checkCode'));
		} else {
			var div_error = $('#div_sendCode_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
			$('<a />').addClass('pull-right').click(function(e){
				postToErrorPage(data.error);
			}).attr({href : '#'}).html('Details').appendTo(div);
		}
	},
	error : function(data){
		var div_error = $('#div_sendCode_error').removeClass('hide').empty();;
		var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
		var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
		$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
		$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
	}
});

$('#form_checkCode').ajaxForm({
	dataType : 'json',		
	beforeSerialize: function($form, options) { 
		$('#tb_checkCode_password').val(MD5($('#tb_checkCode_password').val()));
	},
	success : function(data){
		if (data.status == 'ok'){
			$('#div_forgot_success').addClass('div_center');
			$('#form_checkCode').flipContent($('#div_forgot_success'));
			setTimeout('window.location = "/"', 3000);
		} else {
			var div_error = $('#div_checkCode_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
			$('<a />').addClass('pull-right').click(function(e){
				postToErrorPage(data.error);
			}).attr({href : '#'}).html('Details').appendTo(div);
		}
	},
	error : function(data){
		var div_error = $('#div_checkCode_error').removeClass('hide').empty();;
		var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
		var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
		$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
		$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
	}
	
});