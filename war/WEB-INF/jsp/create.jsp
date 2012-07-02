<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Create New Model</title>
    <%@include file="/template/header.jsp"%> 
    <script type="text/javascript" src="http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject.js"></script>
    <script type='text/javascript' src='/js/create.js'></script>
    <link rel="stylesheet" type="text/css" href="/css/create.css" />
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