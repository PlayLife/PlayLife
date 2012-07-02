<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Model - ${drawing.modelname}</title>
	<%@include file="/template/header.jsp"%> 
    <script>
    var drawingId = ${drawing.drawingId};
    </script>
    <script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
    <script type='text/javascript' src='/js/model.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/model.css" />
</head>
<body>	
	<div id='container'>
    	<jsp:include page="/template/userHeader" />
        <hr/>
		<div class="content">
			<div id="unityPlayer">
				<div class="missing">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">
						<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />
					</a>
				</div>
			</div>
		</div>
        <%@include file="/template/footer.jsp"%>
	</div>
</body>	
</html>
