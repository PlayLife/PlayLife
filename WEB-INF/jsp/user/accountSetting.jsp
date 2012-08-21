<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Account Setting</title>
	<%@include file="/template/header.jsp"%> 
	<script type='text/javascript' src='/js/user/accountSetting.js'></script>
</head>
<body>
	<div class='container'>
    	<jsp:include page="/template/userHeader" />
    	<form action='/user/accountSetting.json' method='POST' class='well form-horizontal' id='form_accountSetting'>
			<a class='close pull-right' href='/'><spring:message code="accountSetting.cancel"/></a>
			<legend><h1 class='topic red big'><spring:message code="accountSetting.title"/></h1></legend>
			
			<!-- Old Password -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_old_password"><spring:message code="accountSetting.oldPassword"/> : </label>
					<div class="controls"><input type="password" class="input-xlarge" id="tb_old_password" name='old_password' placeholder="<spring:message code="accountSetting.oldPassword"/>"/></div>
				</div>
			</fieldset>
			
			<hr />
			
			<!-- New Password -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_new_password"><spring:message code="accountSetting.newPassword"/> : </label>
					<div class="controls"><input type="password" class="input-xlarge" id="tb_new_password" name='new_password' placeholder="<spring:message code="accountSetting.newPassword"/>"/></div>
				</div>
			</fieldset>
			
			<!-- Confirm New Password -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_confirm_new_password"><spring:message code="accountSetting.confirmPassword"/> : </label>
					<div class="controls"><input type="password" class="input-xlarge" id="tb_confirm_new_password" placeholder="<spring:message code="accountSetting.confirmNewPassword"/>"/></div>
				</div>
			</fieldset>
			
			<hr />
			
			<!-- Username -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_username"><spring:message code="user.username"/> : </label>
					<div class="controls"><input type="text" class="input-xlarge" id="tb_username" name='username' value='${user.username}'/></div>
				</div>
			</fieldset>
			
			<fieldset>
				<div id='div_error' class='span6 hide'>
				</div>
				<button type="submit" class="btn btn-primary btn-large span3 offset8"><spring:message code="accountSetting.submit"/></button>
			</fieldset>
		</form>
		
		<div id='div_success' class='well hide pull-center'>
			<h1 class='topic red big'><spring:message code="accountSetting.success"/></h1>
			<span><spring:message code="accountSetting.redirect"/></span>
		</div>
		
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>