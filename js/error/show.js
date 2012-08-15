$(document).ready(function(e){
	$('#btn_report').click(function(e){
		$.ajax({
			url : '/error/log.json',
			type : 'POST',
			data : {s_log : s_log},
			dataType : 'JSON',
			success : function(data){
				if (data.status == 'ok'){
					$('#div_success').addClass('div_center');
					$('#div_show').flipContent($('#div_success'));
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
				}
			},
			error : function(data){
				var div_error = $('#div_error').removeClass('hide').empty();;
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			}
		});
		
	});
});

function postToErrorPage(error){
	var s_persistence = JSON.stringify(error.persistence);
	var s_logic = JSON.stringify(error.logic);
	var s_presentation = JSON.stringify(error.presentation);
	var s_validation = JSON.stringify(error.validation);
	var s_exception = JSON.stringify(error.exception);
	var s_displayMessage = JSON.stringify(error.displayMessage);
	
	var form = $('<form />').addClass('hide').attr({action: '/error/show', method : 'POST'}).appendTo($('body'));
	$('<input />').addClass('hide').attr({name : 's_persistence'}).val(s_persistence).appendTo(form);
	$('<input />').addClass('hide').attr({name : 's_logic'}).val(s_logic).appendTo(form);
	$('<input />').addClass('hide').attr({name : 's_presentation'}).val(s_presentation).appendTo(form);
	$('<input />').addClass('hide').attr({name : 's_validation'}).val(s_validation).appendTo(form);
	$('<input />').addClass('hide').attr({name : 's_exception'}).val(s_exception).appendTo(form);
	$('<input />').addClass('hide').attr({name : 's_displayMessage'}).val(s_displayMessage).appendTo(form);
	form.submit();
}