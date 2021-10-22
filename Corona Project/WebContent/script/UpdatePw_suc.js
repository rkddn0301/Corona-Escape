function reset_reg_form() {
	$("#mypage_form")[0].reset();
	$('#wrongPw').hide();
	$('#wrongPw2').hide();


}

function checkPw() {
	var inputed = user_update_password.value;
	if (inputed.length < 5) {
		$('#wrongPw').show();
		$('#wrongPw2').hide();
	} else {
		$('#wrongPw').hide();
		$('#wrongPw2').hide();
	}
}
function checkPw2() {
	var inputed = user_update_password.value;
	var inputed2 = repeat_password.value;
	if (inputed != inputed2) {
		$('#wrongPw2').show();
	} else {
		$('#wrongPw2').hide();
	}
}

function doupdatePwcheck(f) {
	if (f.user_update_password.value == ""){
		alert("비밀번호를 입력해주세요.")
		return false;
	}
	if (f.user_update_password.value.length < 5) {
		alert("5~16자의 비밀번호를 사용해주세요.")
		return false;
	}
	if (f.user_update_password.value.length > 16) {
		alert("5~16자의 비밀번호를 사용해주세요.")
		return false;
	} 
	if (f.repeat_password.value == ""){
		alert("비밀번호 재입력을 입력해주세요.")
		return false;
	} 
	if (f.user_update_password.value != f.repeat_password.value){
		alert("비밀번호가 같지 않습니다.")
		return false;
	}
	return true;
}


function updatePw() {
	if (doupdatePwcheck(document.getElementById('mypage_form'))) {
		$.ajax({
		url : "/updatePw.do",
		type : "post",
		data : {
			"user_id" : $('#user_update_id').val(),
			"password" : $('#user_update_password').val()
		},
		success : function(a){
			console.log(a);
			reset_reg_form();
		
			if (a == 0) {
				alert('비밀번호 변경에 실패하였습니다. 잠시 후 다시 시도하여 주십시오.');
				reset_reg_form();
				} else {
				alert('변경이 완료되었습니다. 다시 로그인 해주세요.');
				location.href = "/login.do";
				}
			}
			
			
		})
		
	}
}

