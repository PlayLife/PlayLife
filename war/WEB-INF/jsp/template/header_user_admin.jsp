<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
    	<a class="brand" href='/'>My Cube Drawing</a>
    	<ul class='nav pull-left'> 
    		<li><a class='userFunction' href='/create'><spring:message code="userFunction.create"/></a></li>
    		<li><a class='userFunction' href='/admin/'><spring:message code="userFunction.admin"/></a></li>
    	</ul><!-- /.nav .pull-left -->
    	<ul class='nav pull-right'> 
    		<li><a href='/logout' class='userFunction'><spring:message code="userFunction.logout"/></a></li>
    	</ul><!-- /.nav .pull-right -->
    </div><!-- /.container -->
   </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->