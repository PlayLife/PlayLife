<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Play Life</title>
	<%@include file="/template/header.jsp"%> 
    <script type='text/javascript' src='/js/book/createBook.js'></script>
    
</head>
<body>
<jsp:include page="/template/userHeader" />
<div class='container'>
		<form action='/book/create.json' method='POST' class='well form-horizontal' id='form_createBook' style='min-width: 600px'>
			<a class='close pull-right' href='/'><spring:message code="createBook.cancel"/></a>
			<legend><h1 class='topic red big'><spring:message code="createBook.title"/></h1></legend>
			
			<!-- Title -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_email"><spring:message code="book.title"/> : </label>
					<div class="controls"><input type="text" class="input-xlarge" id="tb_title" name='title' placeholder="<spring:message code="createBook.titleExample"/>"/></div>
				</div>
			</fieldset>
			
			<!-- Date -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_startDate"><spring:message code="createBook.date"/> : </label>
					<div class="controls"><div class="input-append date" id='div_startDate'><input type="text" class="input-medium" id="tb_startDate" name='startDate' placeholder="<spring:message code="book.startDate"/>"/></div> <spring:message code="createBook.to"/> <div class="input-append date" id='div_endDate'><input type="text" class="input-medium" id="tb_endDate" name='endDate' placeholder="<spring:message code="book.endDate"/>"/></div> <span class='help-inline' id='span_date_error'></span></div>
				</div>
			</fieldset>

			<!-- Cost -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="tb_cost"><spring:message code="book.cost"/> : </label>
					<div class="controls"><div class='input-prepend input-append' id='div_cost'><span class="add-on"><spring:message code="createBook.costPrefix"/></span><span class="add-on">$</span><input type="text" class="input-xlarge" id="tb_cost" name='cost' placeholder="<spring:message code="createBook.costExample"/>"/><span class="add-on">.00</span></div></div>
				</div>
			</fieldset>

			<!-- Travel With -->
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="sl_travelWith"><spring:message code="book.travelWith"/> : </label>
					<div class="controls">
						<select name='travelWith' id='sl_travelWith'>
							<option><spring:message code="book.travelWith.single"/></option>
							<option><spring:message code="book.travelWith.couple"/></option>
							<option><spring:message code="book.travelWith.family"/> </option>
							<option><spring:message code="book.travelWith.friends"/></option>
						</select>
					</div>
				</div>
			</fieldset>
						
			<fieldset>
				<div id='div_error' class='span6 hide'>
				</div>
				<button type="submit" class="btn btn-primary btn-large span3 offset8"><spring:message code="createBook.create"/></button>
			</fieldset>
			
		</form>
        <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>
