$(document).ready(function(){
	var valid_title = new LiveValidation('tb_title');
	valid_title.add( Validate.Presence, { failureMessage: message.book.title.required } );
	var valid_cost = new LiveValidation('tb_cost', {insertAfterWhatNode : 'div_cost'});
	valid_cost.add( Validate.Presence, { failureMessage: message.book.cost.required } );
	
	$("#tb_cost").keypress(function (e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57))
		               return false;
	});
	
	$('#tb_startDate').focus(function(e){
		$('#tb_startDate').parent().parent().parent().removeClass('error');
		$('#span_date_error').html('');
	});
	
	$('#tb_endDate').focus(function(e){
		$('#tb_endDate').parent().parent().parent().removeClass('error');
		$('#span_date_error').html('');
	});
	
	$('#form_createBook').ajaxForm({
		dataType : 'json',
		beforeSerialize: function($form, options) {
			if ($('#tb_startDate').val() == ''){
				$('#tb_startDate').parent().parent().parent().addClass('error');
				$('#span_date_error').html(message.book.date.required);
				return false;
			} 
			if ($('#tb_endDate').val() == ''){
				$('#tb_endDate').parent().parent().parent().addClass('error');
				$('#span_date_error').html(message.book.date.required);
				return false;
			}
			
			if (Date.parse($('#tb_endDate').val()) < Date.parse($('#tb_startDate').val())){
				$('#tb_startDate').parent().parent().parent().addClass('error');
				$('#span_date_error').html(message.book.date.startEndDate);
				return false;
			}
			
			return true;
		},
		success : function(data){
			if (data.status == 'ok'){
				window.location.href = '/book/edit/' + data.bookId;
			} else {
				var div_error = $('#div_error').removeClass('hide').empty();
				var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
				var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
				$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
				$('<span />').attr({id : 'span_error'}).html(data.error.displayMessage).appendTo(div);
				$('<a />').addClass('pull-right').click(function(e){
					postToErrorPage(data.error);
				}).attr({href : '#'}).html('Details').appendTo(div);
			}
		}, error : function(data){
			var div_error = $('#div_error').removeClass('hide').empty();;
			var div = $('<div />').addClass('alert alert-error').appendTo(div_error);
			var a_close = $('<a data-dismiss="alert"/>').attr({href : '#'}).addClass('close').html('x').appendTo(div);
			$('<strong />').attr({id : 'strong_error'}).html(SERVER_ERROR_TITLE).appendTo(div);
			$('<span />').attr({id : 'span_error'}).html(SERVER_ERROR).appendTo(div);
			$('#tb_password, #tb_confirmPassword').val('');
		}
	});
});