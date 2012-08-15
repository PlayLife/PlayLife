var message = {
	user : {
		email : {
			required : "電郵是必須填寫",
			notValid : "這不是正確電郵",
			exists : "電郵已經被使用. 請嘗試 <a href='/user/login'>登入</a>"
		}, 
		password : {
			required : "密碼是必須填寫",
			minimum : "密碼不可少於 4 字元"
		},
		confirmPassword : {
			required : "確認密碼是必須填寫",
			matches : "密碼並不相同"
		}, 
		username : {
			required : "匿稱是必須填寫",
			minimum : "匿稱不可少於 3 字元",
			maximum : "匿稱不可多於 20 字元"
		}
	}
};