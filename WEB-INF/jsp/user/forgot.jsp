<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Forgot Password</title>
	<%@include file="/template/header.jsp"%> 
    <script type='text/javascript' src='/js/user/forgot.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/user/forgot.css" />
</head>
<body>
	<div class='container'>
    	<jsp:include page="/template/userHeader" />
    	<form action='/user/sendForgotCode.json' method='POST' class='well form-horizontal' id='form_sendCode'>
			<a class='close pull-right' href='/'><spring:message code="forgot.cancel"/></a>
			<legend><h1 class='topic red big'><spring:message code="forgot.title"/></h1></legend>
			<!-- Email -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_email"><spring:message code="user.email"/> : </label>
					<div class="controls"><input type="text" class="input-xlarge" id="tb_email" name='email' placeholder="<spring:message code="user.email"/>"/></div>
				</div>
			</fieldset>
			
			<fieldset>
				<div id='div_error' class='span6 hide'>
				</div>
				<button type="submit" class="btn btn-primary btn-large span3 offset8"><spring:message code="forgot.forgot"/></button>
			</fieldset>
		</form>
		
		<div id='div_forgot_checkCode' class='well hide pull-center'>
			<form action='/user/changePassword.json' method='POST' class='well form-horizontal' id='form_checkCode'>
				<a class='close pull-right' href='/'><spring:message code="forgot.cancel"/></a>
				<legend><h1 class='topic red big'><spring:message code="forgot.checkCodeTitle"/></h1></legend>
				<!-- Email -->
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="tb_checkCode_email"><spring:message code="user.email"/> : </label>
						<div class="controls"><input type="text" class="input-xlarge" id="tb_checkCode_email" name='email' placeholder="<spring:message code="user.email" disabled='true'/>" /></div>
					</div>
				</fieldset>
				
				<!-- Forgot Code -->
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="tb_checkCode_forgotCode"><spring:message code="forgot.forgotCode"/> : </label>
						<div class="controls"><input type="text" class="input-xlarge" id="tb_checkCode_forgotCode" name='forgotCode' placeholder="<spring:message code="forgot.forgotCode"/>" /></div>
					</div>
				</fieldset>
				
				<!-- Password -->
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="tb_checkCode_password"><spring:message code="user.password"/> : </label>
						<div class="controls"><input type="text" class="input-xlarge" id="tb_checkCode_password" name='password' placeholder="<spring:message code="user.password"/>" /></div>
					</div>
				</fieldset>

				<!-- Confirm Password -->
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="tb_checkCode_confirmPassword"><spring:message code="forgot.confirmPassword"/> : </label>
						<div class="controls"><input type="text" class="input-xlarge" id="tb_checkCode_confirmPassword" placeholder="<spring:message code="forgot.confirmPassword"/>" /></div>
					</div>
				</fieldset>
				
				<fieldset>
					<div id='div_error' class='span6 hide'>
					</div>
					<button type="submit" class="btn btn-primary btn-large span3 offset8"><spring:message code="forgot.resetPassword"/></button>
				</fieldset>
			</form>
		</div>
		
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>