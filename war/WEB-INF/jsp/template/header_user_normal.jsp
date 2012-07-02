<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
    	<a class="brand" href='/'>My Cube Drawing</a>
    	<ul class='nav pull-left'>
    		<li><a class='userFunction' href='/create'><spring:message code="userFunction.create"/></a></li>
    	</ul><!-- /.nav .pull-left -->
    	<ul class='nav pull-right'>
    		<li><a class='userFunction' href='/user/register'><spring:message code="userFunction.register"/></a></li> 
    		<li><a class='userFunction' href='/user/login'><spring:message code="userFunction.login"/></a></li>
    	</ul><!-- /.nav .pull-right -->
    </div><!-- /.container -->
   </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->
