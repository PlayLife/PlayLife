<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns:fb="https://www.facebook.com/2008/fbml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play Life</title>
<link rel="stylesheet" type="text/css" href="css/jquery/jquery-ui-1.8.16.custom.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<script type="text/javascript" src="script/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="script/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="script/jquery/jquery.updnWatermark.js"></script>
<script type="text/javascript" src="script/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="script/main.js"></script>
<script type="text/javascript" src="script/home.js"></script>
<body>
<div id='div_container'>
    <div id='div_header'>
    	<div id='div_header_banner'><div id="div_functionList"><c:out value="${functionList}" escapeXml="false" /></div></div>
    </div>
    <div id="div_mainContent">
    </div>

    <div id="div_footer"></div>
</div>
</body>
</html>