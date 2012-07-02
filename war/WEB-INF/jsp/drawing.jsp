<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Drawing - ${drawing.modelname}</title>
	<%@include file="/template/header.jsp"%> 
    <script type='text/javascript' src='/js/drawing.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/drawing.css" />
    <script>
	var userId = -1;
	var drawingId = ${drawing.drawingId};
	</script>
</head>
<body>
	<div id='container'>
		<jsp:include page="/template/userHeader" />
        <hr/>
        <div id='div_info'>
            <table>
                <tr><td>ID : </td><td>${drawing.drawingId}</td></tr>
                <tr><td>Model Name : </td><td>${drawing.modelname}</td></tr>
                <tr><td>Date : </td><td>${drawing.dateText}</td></tr>
				<tr><td>Like : </td><td><div id='div_likeCount'></div></td>
			</table>
			<a href='/model/${drawing.drawingId}' ><img src="/do/drawing/image?key=${drawing.drawingId}" /></a>    
        </div>
        <div id='div_commentList'></div>
        
        <div id='div_loadMore'><a id='a_loadMore'>Load More</a></div>
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>
