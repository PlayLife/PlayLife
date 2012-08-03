$('document').ready(function(){
	$('.date').datepicker();
	
	$('#bn_submit').button().click(function(){
		$.ajax({
			url : $('#fm_create').attr('action'),
			data : $('#fm_create').serialize(),
			success : function(data){
				data = jQuery.parseJSON(data);
				if (data.status != 'error')
					parent.location.href = 'edit?bid=' + data.book.bid;
			}
		});	
	});
	
	$('#fm_create').submit(function(){return false;});	
});

