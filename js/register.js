$(document).ready(function(e) {
	$('#ul_captcha').sortable({placeholder: "ui-state-highlight", forcePlaceholderSize: true, scrollSensitivity: 50 });
	$("#ul_captcha").disableSelection();
	$("#bn_submit").button();
	
	$('.captcha').each(function(idx, captcha){
		$(captcha).data('pos', idx);
	});
	
	$('#div_captcha_refresh').click(function(){
		$.ajax({
			url: 'captcha/refresh',
			success: function(data){
				data = jQuery.parseJSON(data);
				
				if (data.status == 'ok'){
					$('.captcha').each(function(idx, captcha){
						$(captcha).attr('src', $(captcha).attr('src'));
					});
				}
			}
		});
	});
	
	$('#div_captcha_confirm').click(function(){
		var reorder = "";
		$('.captcha').each(function(idx, captcha){
			reorder = reorder + $(captcha).data('pos');
		});
		$.ajax({
			url: 'captcha/verify',
			data: {reorder : reorder},
			success: function(data){
				data = jQuery.parseJSON(data);
				
				if (data.status == 'ok'){
					$('#ul_captcha').sortable("disable");
					$('#div_captcha_confirm').unbind('click');
					$('#div_captcha_refresh').unbind('click');
					$('#div_captcha_confirm').css({'background-color': 'green'});
				} else {
					$('#div_captcha_refresh').trigger('click');
					$('#div_captcha_confirm').css({'background-color': 'red'});
				}
			}
		});
	});

	$('#bn_submit').click(function(){
		if (!$('#fm_register').isValid())
			return false;
		$.ajax({
			url : $('#fm_register').attr('action'),
			data : {email: $('#tb_email').val(), password: MD5($('#tb_password').val()), username: $('#tb_username').val(), accessToken: $('#hn_accessToken').val()},
			success : function(data){
				data = jQuery.parseJSON(data);
				if (data.status != 'error')
					parent.location.href = 'home';
				else {
					$('#div_err').html(data.error.displayMessage);
					if (data.error.displayMessage == 'Access token expired.'){
						$('<div />').attr('id', 'div_refresh').html("Page will be refresh in 3 seconds").appendTo($('#div_err'));
						setTimeout("javascript:$('#div_refresh').html('Page will be refresh in 2 seconds')", 1000);
						setTimeout("javascript:$('#div_refresh').html('Page will be refresh in 1 seconds')", 2000);
						setTimeout("javascript:location.reload(true)", 3000);
					}
				}
			}
		});	
		return false;
	});
	
	$('#bn_back').click(function(){
		location.href = 'home';
	});

	$('#tb_email').validate({minLength : 5, email : true, isRegistered : true});
	$('#tb_password').validate({minLength : 5});
	$('#tb_confirmPassword').validate({equalTo : $('#tb_password')});
	$('#tb_username').validate({emptyOrMinLength : 3});
	
	var email_hint = '<table><tr><td><ul><li>Minimum Length : 5</li><li>In form of email</li><li>should not be registered</li></ul></td>'+
					 '<td>Example : siu_ming@gmail.com </td></tr></table>';
	var password_hint = '<table><tr><td><ul><li>Minimum Length : 5</li><li>Two password must match</li></ul></td>'+
					 '<td>Example : s!u_m1ng </td></tr></table>';
	var username_hint = '<table><tr><td><ul><li>Minimum Length : 3</li><li>Not Required</li></ul></td>'+
					 '<td>Example : siu_ming </td></tr></table>';
	var captcha_hint = 'make it to be playlife';
	
	$('#div_register_hint').html('<div>' + email_hint +'</div>');
	hint_state = 0;
	
	$('#tb_email').focus(function(){
		if (hint_state != 0)
			animateHint(email_hint);	
		hint_state = 0;
	});
	$('#tb_password').focus(function(){
		if (hint_state != 1)
			animateHint(password_hint);
		hint_state = 1;
	});
	$('#tb_confirmPassword').focus(function(){
		if (hint_state != 1)
			animateHint(password_hint);
		hint_state = 1;
	});	
	$('#tb_username').focus(function(){
		if (hint_state != 2)
			animateHint(username_hint);
		hint_state = 2;
	});
	$('#div_register_captcha').hover(function(){
		if (hint_state != 3)
			animateHint(captcha_hint);
		hint_state = 3;
	});
});

var hint_state; 
function animateHint(hint){
	$('#div_register_hint div:first-child').animate({opacity: 0, marginLeft: '-=200px'}, 200, function(){
		var if_target = $('<div />').html(hint).css({opacity : 0}).appendTo($('#div_register_hint')).fadeTo("slow", 1);
		$(this).remove();
	});	
}