var message = {
	user : {
		email : {
			required : "Email is required.",
			notValid : "This is not an valid email.",
			exists : "Email already exists. Try to <a href='/user/login'>login</a>. "
		}, 
		password : {
			required : "Password is required.",
			minimum : "Password must not less than 4 characters long."
		},
		confirmPassword : {
			required : "Confirm Password is required.",
			matches : "Passwords do not match."
		}, 
		username : {
			required : "Username is required.",
			minimum : "Username must not less than 3 characters long.",
			maximum : "Username must not more than 20 characters long."
		}
	}
};