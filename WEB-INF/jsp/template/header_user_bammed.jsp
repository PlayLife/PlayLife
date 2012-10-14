<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
    	<a class="brand" href='/'><span class="topic white small">Play Life</span></a>
    	<ul class='nav pull-left'> 
    	</ul><!-- /.nav .pull-left -->
    	<ul class='nav pull-right'> 
    		<li style='margin-top: 10px'><i class='icon-remove icon-white'></i><strong><spring:message code="userFunction.accountBammed"/></strong></li>
    		<li><a href='/user/logout' class='userFunction'><spring:message code="userFunction.logout"/></a></li>
    		<li><a href='/language'><spring:message code="userFunction.language"/></a></li>
    	</ul><!-- /.nav .pull-right -->
    </div><!-- /.container -->
   </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->