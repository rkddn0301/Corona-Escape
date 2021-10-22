function reset_reg_form() {
	$("#login-form")[0].reset();
	$('#notId').hide();
	$('#notPw').hide();
}

function doRegUserCheck(f) {

	if ($("#login_id").val() == "") {
		$('#notId').show();
		$('#notPw').hide();
		return false;
	}
	if ($("#login_password").val() == "") {
		$('#notPw').show();
		$('#notId').hide();
		return false;
	}
	return true;
}


function LoginUser() {
	if (doRegUserCheck(document.getElementById('login-form'))) {
		$.ajax({
			url : "/Loginbtn.do",
			type : "post",
			dataType : "json",
			data : {
				"user_id" : $('#login_id').val(),
				"password" : $('#login_password').val()
			},
			success : function(a) {
				console.log(a);
				reset_reg_form();
				if (a == 0) {
					alert('없는 아이디 또는 비밀번호를 잘못 입력하였습니다.');
				} else if (a == 1) {
					alert('관리자 로그인에 성공하였습니다.');
					location.href = "/docs.do";
				} else if (a == 2) {
					alert('로그인 되었습니다.');
					location.href = "/docs.do";
				} else {
					alert("오류로 인하여 로그인에 실패했습니다. 잠시 후 다시 시도하여 주십시오.");
				}
			}
		})
	}
}


