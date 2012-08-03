<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="script/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="script/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="script/register.js"></script>
<script type="text/javascript" src="script/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="script/jquery/additional-methods.js"></script>

<div id="div_register">
	<div id="div_register_hint">
    
    </div>

	<form action="user.do?action=register" method="post" id="fm_register">
        <table><tr><td width='33%'><input type="button" id="bn_submit"/></td><td width='33%'>Register</td><td width='33%'><input type="button" id="bn_back"/></td></tr></table>
    	<table><tr>
        <td><p>Email : </p></td><td><input type="text" id="tb_email" name="email" class="email"/></td></tr><tr>
        <td><p>Password : </p></td><td><input type="password" id="tb_password" name="password" class="password" maxlength="25"/></td></tr><tr>
        <td><p>Confirm : </p></td><td><input type="password" id="tb_confirmPassword" class="c_password" maxlength="25"/></td></tr><tr>
        <td><p>Username : </p></td><td><input type="text" id="tb_username" name="username" class="username" maxlength="30"/></td></tr></table>
        <div id='div_register_captcha'>
        <p><ul id='ul_captcha'>
            <li><img class='captcha' src="captcha/1.jpg"></li>
            <li><img class='captcha' src="captcha/2.jpg"></li>
            <li><img class='captcha' src="captcha/3.jpg"></li>
            <li><img class='captcha' src="captcha/4.jpg"></li>
            <li><img class='captcha' src="captcha/5.jpg"></li>
            <li><img class='captcha' src="captcha/6.jpg"></li>
            <li><img class='captcha' src="captcha/7.jpg"></li>
            <li><img class='captcha' src="captcha/8.jpg"></li>
        </ul>
		<div id='div_captcha_confirm'>Confirm</div>
        <div id='div_captcha_refresh'>refresh</div></p>
		</div>
	    <div id="div_err"></div><br />
    </form>
</div>
<div id="div_err"></div>