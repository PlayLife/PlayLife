<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Language</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
	<%@include file="/template/header.jsp"%> 
	<link rel="stylesheet" type="text/css" href="/css/language.css" />
</head>
<body>
	<div class='container'>
		<jsp:include page="/template/userHeader" />
		<h1 class='topic big red'>Please choose your language: </h1>
        <br />

		<ul class="thumbnails">
			<li class="span3">
				<a href="/?locale=en" class="thumbnail well shadow">
			      <img src="/img/locale/uk.png" class='locale_image' alt="">
			      <p>English</p>
			    </a>
		  	</li>
			<li class="span3">
				<a href="/?locale=zh_TW" class="thumbnail well shadow">
			      <img src="/img/locale/hk.png" class='locale_image' alt="">
			      <p>繁體中文</p>
			    </a>
		  	</li>		  	 
		</ul>
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>
