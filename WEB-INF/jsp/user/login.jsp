<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Login</title>
	<%@include file="/template/header.jsp"%> 
    <script type='text/javascript' src='/js/user/login.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/user/login.css" />
</head>
<body>
	<div class='container'>
    	<jsp:include page="/template/userHeader" />
    	<form action='/user/login.json' method='POST' class='well form-horizontal' id='form_login'>
			<a class='close pull-right' href='/'><spring:message code="login.cancel"/></a>
			<legend><h1 class='topic red big'><spring:message code="login.title"/></h1></legend>
			<!-- Email -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_email"><spring:message code="user.email"/> : </label>
					<div class="controls"><input type="text" class="input-xlarge" id="tb_email" name='email' placeholder="<spring:message code="user.email"/>"/></div>
				</div>
			</fieldset>
			
			<!-- Password -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_password"><spring:message code="user.password"/> : </label>
					<div class="controls"><input type="password" class="input-xlarge" id="tb_password" name='password' placeholder="<spring:message code="user.password"/>"/> <a href='/user/forgot'><spring:message code="login.forgotpassword"/></a></div>
				</div>
			</fieldset>
			
			<fieldset>
				<div id='div_error' class='span6 hide'>
				</div>
				<button type="submit" class="btn btn-primary btn-large span3 offset8"><spring:message code="login.login"/></button>
			</fieldset>
			
			<hr />
			<!-- Facebook Login Dialog -->
			<div class=''>
				<a href='/user/facebook/login'><img src='/img/icon_facebook.png'/><span class='topic medium blue'> Login with Facebook</span></a>
			</div>
		</form>
		
		<div id='div_login_success' class='well hide pull-center'>
			<h1 class='topic red big'><spring:message code="login.success"/></h1>
			<span><spring:message code="login.redirect"/></span>
		</div>
		
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>