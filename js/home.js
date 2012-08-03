$(document).ready(function(e) {
	var default_page = 'search';
	$('<div />').load(default_page).css({opacity : 0}).addClass('div_content').appendTo($('#div_mainContent')).fadeTo("slow", 1)
	
	$('#div_functionList a, #div_logo a').click(function(){
		animateContent($(this));
		return false;
	});
});
