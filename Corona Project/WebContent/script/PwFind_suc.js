function doRegUserCheck(f) {
	if (f.user_id.value == "") {
		alert("아이디를 입력하세요.");
		f.user_id.focus();
		return false;
	}

	if (f.user_name.value == "") {
		alert("이름을 입력하세요.");
		f.user_name.focus();
		return false;
	}

	if (f.email.value == "") {
		alert("이메일을 입력하세요.");
		f.email.focus();
		return false;
	}

}
