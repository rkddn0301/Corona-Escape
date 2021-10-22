var checkId = 0;
var pwCheck = 0;
var emailCheck = 0;
var nickCheck = 0;
function reset_reg_form() {
	$("#reg_form")[0].reset();
	$('#wrongId').hide();
	$('#failId').hide();
	$('#successId').hide();
	$('#wrongPw').hide();
	$('#wrongPw2').hide();
	$('#successMail').hide();
	$('#failMail').hide();
	$('#wrongMail').hide();
	$('#wrongNick').hide();
	$('#failNick').hide();
	$('#successNick').hide();

}
function idCheck() {
	var inputed = f.user_id.value;
	var CheckForm = /^[a-z0-9]{5,16}$/;
	if (!CheckForm.test(inputed)) {
		$('#wrongId').show();
		$('#failId').hide();
		$('#successId').hide();
		checkId = 0;
	} else {
		$.ajax({
			data : {
				user_id : inputed
			},
			url : '/idCheck.do',
			success : function(data) {
				if (inputed == "" && data == '0') {
					$('#wrongId').hide();
					$('#failId').show();
					$('#successId').hide();
					checkId = 0;
				} else if (data == '0') {
					$('#wrongId').hide();
					$('#failId').hide();
					$('#successId').show();
					checkId = 1;
				} else if (data == '1') {
					$('#wrongId').hide();
					$('#failId').show();
					$('#successId').hide();
					checkId = 0;
				}
			}
		})
	}
}
function checkPw() {
	var inputed = f.user_reg_password.value;
	if (inputed.length < 5) {
		$('#wrongPw').show();
		$('#wrongPw2').hide();
	} else {
		$('#wrongPw').hide();
		$('#wrongPw2').hide();
	}
}
function checkPw2() {
	var inputed = f.user_reg_password.value;
	var inputed2 = f.RepeatPassword.value;
	if (inputed != inputed2) {
		$('#wrongPw2').show();
	} else {
		$('#wrongPw2').hide();
	}
}
function checkMail() {
	var inputed = f.user_reg_email.value;
	var CheckForm = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	// 정규식 표현 ^xy(xy를 제외한) (:이상 /$/끝내기 i : 대소문자 구별

	if (!CheckForm.test(inputed)) {
		$('#successMail').hide();
		$('#failMail').hide();
		$('#wrongMail').show();
		emailCheck = 0;
	} else {
		$.ajax({
			data : {
				email : inputed
			},
			url : '/checkMail.do',
			success : function(data) {
				if (inputed == "" && data == '0') {
					$('#successMail').hide();
					$('#failMail').show();
					$('#wrongMail').hide();
					emailCheck = 0;
				} else if (data == '0') {
					$('#successMail').show();
					$('#failMail').hide();
					$('#wrongMail').hide();
					emailCheck = 1;
				} else if (data == '1') {
					$('#successMail').hide();
					$('#failMail').show();
					$('#wrongMail').hide();
					emailCheck = 0;
				}
			}
		})
	}
}

function checkNick() {
	var inputed = f.user_reg_nick.value;
	var CheckForm = /^[A-Za-z0-9가-힣]{2,14}$/;
	if (!CheckForm.test(inputed)) {
		$('#wrongNick').show();
		$('#failNick').hide();
		$('#successNick').hide();
		nickCheck = 0;
	} else {
		$.ajax({
			data : {
				nick_name : inputed
			},
			url : '/checkNick.do',
			success : function(data) {
				if (inputed == "" && data == '0') {
					$('#wrongNick').hide();
					$('#failNick').show();
					$('#successNick').hide();
					nickCheck = 0;
				} else if (data == '0') {
					$('#wrongNick').hide();
					$('#failNick').hide();
					$('#successNick').show();
					nickCheck = 1;
				} else if (data == '1') {
					$('#wrongNick').hide();
					$('#failNick').show();
					$('#successNick').hide();
					nickCheck = 0;
				}
			}
		})
	}
}

function doRegUserCheck(f) {	
	if (f.user_id.value == "") {
		alert("아이디를 입력해주세요.");
		f.user_id.focus();
		return false;
	}
	if (checkId == 0) {
		alert("사용하실수 없는 아이디 입니다.");
		f.user_id.focus();
		return false;
	}

	if (f.user_name.value == "") {
		alert("이름을 입력해주세요.");
		f.user_name.focus();
		return false;
	}

	if (f.user_reg_password.value == "") {
		alert("비밀번호를 입력해주세요.");
		f.user_reg_password.focus();
		return false;
	}
	if (f.user_reg_password.value.length < 5) {
		alert("5~16자의 비밀번호를 사용해주세요.");
		f.user_reg_password.focus();
		return false;
	}
	if (f.user_reg_password.value.length > 16) {
		alert("5~16자의 비밀번호를 사용해주세요.");
		f.user_reg_password.focus();
		return false;
	}
	if (f.RepeatPassword.value == "") {
		alert("비밀번호 재입력을 입력해주세요.");
		f.RepeatPassword.focus();
		return false;
	}
	if (f.password.value != f.RepeatPassword.value) {
		alert("비밀번호가 같지 않습니다.");
		f.user_reg_password.focus();
		return false;
	}

	if (f.user_reg_year.value == "") {
		alert("출생년도를 입력해주세요.")
		f.user_reg_year.focus();
		return false;
	}

	if (f.user_reg_year.value == "") {
		alert("출생년도를 입력해주세요.")
		f.user_reg_year.focus();
		return false;
	}

	if (f.user_reg_month.value == "") {
		alert("출생월을 선택해주세요.")
		f.user_reg_month.focus();
		return false;
	}

	if (f.user_reg_day.value == "") {
		alert("출생일을 입력해주세요.");
		f.user_reg_day.focus();
		return false;
	}
	
	if (f.user_reg_month.value == 2 && f.user_reg_day.value >= 29) {
		alert("출생일을 다시 확인해주세요.");
		f.user_reg_day.focus();
		return false;
	} else if ((f.user_reg_month.value == 1 || f.user_reg_month.value == 3 || f.user_reg_month.value == 5 ||
			f.user_reg_month.value == 7 || f.user_reg_month.value == 8 || f.user_reg_month.value == 10 || 
			f.user_reg_month.value == 12) && f.user_reg_day.value >= 32) {
		alert("출생일을 다시 확인해주세요.");
		f.user_reg_day.focus();
		return false;
	} else if ((f.user_reg_month.value == 4 || f.user_reg_month.value == 6 || f.user_reg_month.value == 9 ||
			f.user_reg_month.value == 11) && f.user_reg_day.value >= 31) {
		alert("출생일을 다시 확인해주세요.");
		f.user_reg_day.focus();
		return false;
	} 
	
	if (f.user_reg_email.value == "") {
		alert("이메일을 입력해주세요.");
		f.user_reg_email.focus();
		return false;
	}

	if (emailCheck == 0) {
		alert("사용하실수 없는 이메일 입니다.");
		f.user_reg_email.focus();
		return false;
	}

	if (f.user_reg_nick.value == "") {
		alert("닉네임을 입력해주세요.");
		f.user_reg_nick.focus();
		return false;
	}

	if (nickCheck == 0) {
		alert("사용하실 수 없는 닉네임 입니다.");
		f.user_reg_nick.focus();
		return false;
	}
	
	if (f.user_reg_addr1.value == "" ){
		alert("주소를 입력해주세요.");
		f.user_reg_addr1.focus();
		return false;
	}

	return true;
}
function userReg() {
	if (doRegUserCheck(document.getElementById('reg_form'))) {

		$.ajax({
			url : '/InsertUser.do',
			type : 'post',
			data : {
				'user_id' : $('#user_reg_id').val(),
				'user_name' : $('#user_reg_name').val(),
				'password' : $('#user_reg_password').val(),
				'year' : $('#user_reg_year').val(),
				'month' : $('#user_reg_month').val(),
				'day' : $('#user_reg_day').val(),
				'email' : $('#user_reg_email').val(),
				'nick_name' : $('#user_reg_nick').val(),
				'gender' : $('input:radio[name="gender"]:checked').val(),
				'addr1' : $('#user_reg_addr1').val(),
				'addr2' : $('#user_reg_addr2').val()			
			},
			success : function(a) {
				console.log(a);
				reset_reg_form();
				if (a == 1) {
					alert("회원가입되었습니다.");
					location.href = "/login.do";
				} else if (a == 2) {
					alert("이미 가입된 회원입니다.");
					reset_reg_form();
				} else if (a == 0) {
					alert("오류로 인해 회원가입이 실패하였습니다.");
					reset_reg_form();
				}
			}
		})
	}

}
