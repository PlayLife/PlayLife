<link rel="stylesheet" type="text/css" href="css/login.css">

<script type="text/javascript" src="script/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="script/login.js"></script>

Login <br/><br/>
<div id="div_login">
	<form action="user.do?action=login" id="fm_login" method="post">
        Email : <input type="text" id="tb_email" name="email"/><br />
        Password : <input type="password" id="tb_password" name="password"/><br />
        <input type="hidden" value='${accessToken}' id="hn_accessToken" name="accessToken"/><br />
		<div id="div_err"><c:out value="${error}" escapeXml="false" /></div><br />
        <input type="submit" id="bn_submit"/>
    </form>
</div>