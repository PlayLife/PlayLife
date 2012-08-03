$(document).ready(function(e) {
	$('#bn_submit').button();
	
	$('#fm_login').submit(function(){
		$.ajax({
			url : $(this).attr('action'),
			type : $(this).attr('method'),
			data : {email: $('#tb_email').val(), password: MD5($('#tb_password').val()), username: $('#tb_username').val(), accessToken: $('#hn_accessToken').val()},
			success : function(data){
				data = jQuery.parseJSON(data);
				if (data.status == 'error'){
					/* Status Error */			
					$('#div_err').html(data.error.displayMessage);		
					if (data.error.displayMessage == 'Access token expired.'){
						$('<div />').attr('id', 'div_refresh').html("Page will be refresh in 3 seconds").appendTo($('#div_err'));
						setTimeout("javascript:$('#div_refresh').html('Page will be refresh in 2 seconds')", 1000);
						setTimeout("javascript:$('#div_refresh').html('Page will be refresh in 1 seconds')", 2000);
						setTimeout("javascript:location.reload(true)", 3000);
					}
				} else {
					/* Status OK */
					if (parent != null || parent != undefined)
						parent.location.href = "home";
					else
						location.href = "home";
				}
			}
		});
		
		return false;
	});
});