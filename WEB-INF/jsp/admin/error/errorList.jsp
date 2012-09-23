<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin Panel</title>
	<%@include file="/template/header.jsp"%>
	<script type='text/javascript' src='/js/admin/main.js'></script>
    <script type='text/javascript' src='/js/admin/error/errorList.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/admin/error/errorList.css" />
</head>
<body>
	<div class='container'>
	<jsp:include page="/template/userHeader" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<jsp:include page="/admin/nav" />
				</div>
				<div class="span9">
					<div class='well' style=''>
						<h1 class='topic red big'><spring:message code="admin.error.list.title"/></h1>
						<hr />
						<div id='div_error' class='span10 hide'></div>
						<input type='text' id='tb_search' class='pull-right'/>
						<div class='control pull-left pull-down'>
							<spring:message code="admin.error.list.pageSize.prefix"/> 
							<select class='span2' style='display:inline-block; width:60px' id='select_pageSize'>
								<option value='10'>10</option>
								<option value='20'>20</option>
								<option value='30'>30</option>
								<option value='40'>40</option>
								<option value='50'>50</option>
							</select>
							<spring:message code="admin.error.list.pageSize.postfix"/>
						</div>
						
						<table class='table table-striped' id='table_error'>
							<thead>
								<tr><th><spring:message code="admin.error.list.ip"/></th><th><spring:message code="admin.error.list.date"/></th><th><spring:message code="admin.error.list.displayMessage"/></th><th></th></tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						
						<spring:message code="admin.error.list.total.prefix"/>  <span id='span_total'></span> <spring:message code="admin.error.list.total.postfix"/>
						<div class='pagination pull-right'>
							<ul id='ul_page'>
							</ul>
						</div>
						<br /><br /><br /><br />
					</div>
				</div>
			</div>
		</div>
    <%@include file="/template/footer.jsp"%>
    </div>
</body>
</html>
