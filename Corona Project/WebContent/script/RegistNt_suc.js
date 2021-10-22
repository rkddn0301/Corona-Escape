function doNoticeCheck(f) {
		if (f.user_reg_no.value == "") {
			alert("세션이 만료되었습니다. 다시 로그인해 주세요");
			location.href="/login.do";
			return false;
		}
	
	 
		if (f.notice_reg_title.value == "") {
			alert("제목을 입력해주세요.");
			f.notice_reg_title.focus();
			return false;
		}

		if (f.notice_reg_content.value == "") {
			alert("내용을 입력해주세요.");
			f.notice_reg_content.focus();
			return false;
		}

		return true;
	
}

function insertNt() {
	if (doNoticeCheck(document.getElementById('notice_regist'))) {
		document.getElementById('notice_regist').submit();
	}
	
}