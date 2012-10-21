<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Play Life</title>
	<%@include file="/template/bookHeader.jsp"%> 
    <script>
    var bookId = ${bookId};
    </script>
    <script type='text/javascript' src='/js/book/editBook.js'></script>
	<link rel="stylesheet" type="text/css" href="/css/book/editBook.css" />
</head>
<body>
<div id="fb-root"></div>
<div id='wrapper'>
	<div id='div_photoContainer'>
		<div class="tabbable tabs-left">
			<ul class='nav nav-tabs'>
				<li class='active'><a href='#div_photoContainer_facebook' data-toggle="tab">Facebook</a></li>
				<li><a href='#div_photoContainer_playlife' data-toggle="tab">PlayLife</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="div_photoContainer_facebook">
					<div id='div_photo_facebook_list' class='div_photo_facebook_list hide'>
					
					</div>
					<div id='div_photo_facebook_login'>
						<img src='/img/icon_facebook.png'/><span class='topic medium blue'> Login with Facebook</span>
					</div>
				</div>
				<div class="tab-pane" id="div_photoContainer_playlife">
				</div>
			</div>
		</div>
	</div>
	<div id='div_sheetContainer'>
	
	</div>
</div>
<script>
// Load the SDK Asynchronously
(function(d){
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {return;}
	js = d.createElement('script'); js.id = id; js.async = true;
	js.src = "//connect.facebook.net/en_US/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));
</script>
</body>
</html>
