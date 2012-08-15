$(document).ready(function(e){
	/* Set up validation */
	var valid_password = new LiveValidation('tb_new_password');
	valid_password.add( Validate.Presence, { failureMessage: message.user.password.required } );
	valid_password.add( Validate.Length, { minimum: 4, tooShortMessage: message.user.password.minimum} );
	
	var valid_confirmPassword = new LiveValidation('tb_confirm_new_password');
	valid_confirmPassword.add( Validate.Presence, { failureMessage: message.user.confirmPassword.required } );
	valid_confirmPassword.add( Validate.Confirmation, { match: 'tb_new_password', failureMessage: message.user.confirmPassword.matches } );
	
	var valid_username = new LiveValidation('tb_username');
	valid_username.add( Validate.Presence, { failureMessage: message.user.username.required } );
	valid_username.add( Validate.Length, { minimum: 3, tooShortMessage: message.user.username.minimum} );
	valid_username.add( Validate.Length, { maximum: 20, tooLongMessage: message.user.username.maximum} );
	
	$('#form_accountSetting').ajaxForm({
		dataType : 'json',
		beforeSerialize: function($form, options) { 
			if ($('#tb_new_password').val() != '')
				$('#tb_new_password').val(MD5($('#tb_new_password').val()));
			if ($('#tb_old_password').val() != '')
				$('#tb_old_password').val(MD5($('#tb_old_password').val()));
		},
		success : function(data){
			if (data.status == 'ok'){
				$('#div_success').addClass('div_center');
				$('#form_accountSetting').flipContent($('#div_success'));
				setTimeout('window.location = "/"', 3000);
			} else {
				var div_error = $('#div_error').removeClass('hide').empty();;
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
				$('<a />').addClass('pull-right').click(function(e){
					postToErrorPage(data.error);
				}).attr({href : '#'}).html('Details').appendTo(div);
				$('#tb_old_password, #tb_new_password, #tb_new_confirm_password').val('');
			}
		},
		error : function(data){
			var div_error = $('#div_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			$('#tb_old_password, #tb_new_password, #tb_new_confirm_password').val('');
		}
	});
});