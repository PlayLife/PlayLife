$(document).ready(function(e){
	/* Set up validation */
	var valid_email = new LiveValidation('tb_email');
	valid_email.add( Validate.Presence, { failureMessage: message.user.email.required } );
	valid_email.add( Validate.Format, { pattern: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/, failureMessage: message.user.email.notValid } );
	valid_email.add( Validate.Custom, { against : function(value, args){
		var b_return = true;
		$.ajax({
			url : '/user/checkEmail.json',
			type : 'POST',
			dataType : 'json',
			data: {email : $('#tb_email').val()},
			async: false,
			success: function(data){
				if (data.status == 'error'){
					b_return = false;
				} else 
					b_return = true;
			}
		});
		return b_return;
	}, failureMessage : message.user.email.exists});
	
	var valid_password = new LiveValidation('tb_password');
	valid_password.add( Validate.Presence, { failureMessage: message.user.password.required } );
	valid_password.add( Validate.Length, { minimum: 4, tooShortMessage: message.user.password.minimum} );
	
	var valid_confirmPassword = new LiveValidation('tb_confirmPassword');
	valid_confirmPassword.add( Validate.Presence, { failureMessage: message.user.confirmPassword.required } );
	valid_confirmPassword.add( Validate.Confirmation, { match: 'tb_password', failureMessage: message.user.confirmPassword.matches } );
	
	var valid_username = new LiveValidation('tb_username');
	valid_username.add( Validate.Presence, { failureMessage: message.user.username.required } );
	valid_username.add( Validate.Length, { minimum: 3, tooShortMessage: message.user.username.minimum} );
	valid_username.add( Validate.Length, { maximum: 20, tooLongMessage: message.user.username.maximum} );
	
	$('#form_register').ajaxForm({
		dataType : 'json',
		beforeSerialize: function($form, options) {
			$('#tb_password').val(MD5($('#tb_password').val()));
		},
		success : function(data){
			if (data.status == 'ok'){
				var div_error = $('#div_error').removeClass('hide').empty();;
				var div = $('<div />').addClass('alert alert-success').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html('Success ').appendTo(div);
				$('<span />').attr({id : 'span_error'}).html('Account Created.').appendTo(div);
				form_clear('#form_register');
			} else {
				var div_error = $('#div_error').removeClass('hide').empty();
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
				$('<a />').addClass('pull-right').click(function(e){
					postToErrorPage(data.error);
				}).attr({href : '#'}).html('Details').appendTo(div);
				$('#tb_password, #tb_confirmPassword').val('');
			}
		},
		error : function(data){
			var div_error = $('#div_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			$('#tb_password, #tb_confirmPassword').val('');
		}
	});
});

function form_clear(ele) {
    $(ele).find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });

}
