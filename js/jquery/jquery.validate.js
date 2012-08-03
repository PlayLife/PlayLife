$.fn.validate = function(settings) {
    var _defaultSettings = {
		msg_required : "* This field is required",
		msg_email : "* This is not a valid email",
		msg_minLength : "* This should not be less than ",
		msg_equalTo : "* This is not match",
		msg_emptyOrMinLength : "* This should not be less than ",
		msg_isRegistered : "X This Email has already registered",
		msg_node : $("<span />")
    };
	
    var _settings = $.extend(_defaultSettings, settings);
	
    var isWrong = false;
	var onKeyUpMethod = function(e){
		isWrong = false;
		if (_settings.required != undefined && _settings.required != null && _settings.required == true && !isWrong)
			required_validate($(this));
		if (_settings.email != undefined && _settings.email != null && _settings.email == true && !isWrong)
			email_validate($(this));
		if (_settings.minLength != undefined && _settings.minLength != null && !isWrong)
			minLength_validate($(this));
		if (_settings.equalTo != undefined && _settings.equalTo != null && !isWrong)
			equalTo_validate($(this), _settings.equalTo);
		if (_settings.emptyOrMinLength != undefined && _settings.emptyOrMinLength != null && !isWrong)
			emptyOrMinLength_validate($(this));
		
		if (_settings.isRegistered != undefined && _settings.isRegistered != null && _settings.isRegistered == true && !isWrong)
			isRegistered_validate($(this));		
	}
	
	/****************************
	 *							*	
	 *	Required Validation 	*
	 *							*	
	 ****************************/
	function required_validate($this){
		if ($this.val().trim().length == 0)
			append_msg($this, _settings.msg_required);
		else
			remove_msg($this);
	}
	
	/****************************
	 *							*	
	 *	Email Validation 		*
	 *							*	
	 ****************************/
	function email_validate($this){
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		if( !emailReg.test( $this.val() ) )
			append_msg($this, _settings.msg_email);
		else
			remove_msg($this);
	}
	
	/****************************
	 *							*	
	 *	MinLength Validation 	*
	 *							*	
	 ****************************/
	function minLength_validate($this){
		if ($this.val().trim().length <= _settings.minLength)
			append_msg($this, _settings.msg_minLength + _settings.minLength);
		else
			remove_msg($this);
	}
	
	/****************************
	 *							*	
	 *	Equal To Validation 	*
	 *							*	
	 ****************************/
	function equalTo_validate($this, $target){
		if ($this.val().trim() != $target.val().trim())
			append_msg($this, _settings.msg_equalTo);
		else
			remove_msg($this);
	}
	
	/************************************
	 *									*	
	 *	Empty or MinLength Validation 	*
	 *									*	
	 ************************************/
	function emptyOrMinLength_validate($this){
		if ($this.val().trim().length > 0 && $this.val().trim().length <= _settings.emptyOrMinLength)
			append_msg($this, _settings.msg_emptyOrMinLength + _settings.emptyOrMinLength);
		else
			remove_msg($this);
	}
	
	/********************************
	 *								*	
	 *	Is Registered Validation 	*
	 *								*	
	 ********************************/
	function isRegistered_validate($this){
		$.ajax({
			url : 'user.do?action=check',
			data : {email : $this.val().trim()},
			success : function (data){
				data = jQuery.parseJSON(data);
				if (data.status != 'ok')
					append_msg($this, _settings.msg_isRegistered);
				else
					remove_msg($this);
			}
		});
	}
	
	function append_msg($this, msg){
		remove_msg($this);
		isWrong = true;
		var span_msg = _settings.msg_node.html(msg).addClass("errorMsg");
		if (_settings.msg_node.parent().size() <= 0)
			span_msg.appendTo($($this).parent());
	}
	
	function remove_msg($this){
		$this.parent().find(".errorMsg").remove();
	}
	
	return this.each(function() {
        $(this).keyup(onKeyUpMethod);
    });
};

$.fn.isValid = function(settings) {
    var _defaultSettings = {
    };
    	
    var _settings = $.extend(_defaultSettings, settings);
        
	return ($(this).find(".errorMsg").size() <= 0);
};