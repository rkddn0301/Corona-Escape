function doUpdateCheck(f) {
		if (f.user_reg_no.value == "") {
			alert("세션이 만료되었습니다. 다시 로그인해 주세요");
			location.href="/login.do";
			return false;
		}
	
	 
		if (f.notice_up_title.value == "") {
			alert("제목을 입력해주세요.");
			f.notice_up_title.focus();
			return false;
		}

		if (f.notice_up_content.value == "") {
			alert("내용을 입력해주세요.");
			f.notice_up_content.focus();
			return false;
		}

		return true;
	
}

function updateNt() {
	if (doUpdateCheck(document.getElementById('notice_update'))) {
		document.getElementById('notice_update').submit();
	}
	
}