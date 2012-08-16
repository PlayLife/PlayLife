<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
    	<a class="brand" href='/'><span class="topic red small">Play Life</span></a>
    	<ul class='nav pull-left'> 
    		<li><a class='userFunction' href='/create'><spring:message code="userFunction.create"/></a></li>
    	</ul><!-- /.nav .pull-left -->
    	<ul class='nav pull-right'>
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">${user.email}<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href='/user/accountSetting'><spring:message code="userFunction.accountSetting"/></a></li>
					<li><a href='/language'><spring:message code="userFunction.language"/></a></li>
					<li><a href='/logout' class='userFunction'><spring:message code="userFunction.logout"/></a></li>
				</ul>
			</li>
    	</ul><!-- /.nav .pull-right -->
    </div><!-- /.container -->
   </div><!-- /.navbar-inner -->
</div><!-- /.navbar -->