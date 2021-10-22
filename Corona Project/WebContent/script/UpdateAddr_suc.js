
function doRegUserCheck(f) {
	if (f.user_reg_addr1.value == ""){
		alert("주소를 입력해주세요.")
		return false;
	}
	
	return true;
}

function updateAddr() {
	if (doRegUserCheck(document.getElementById('mypage_form'))) {
		$.ajax({
		url : "/updateAddr.do",
		type : "post",
		dataType : "json",
		data : {
			"user_id" : $('#user_update_id').val(),
			"addr1" : $('#user_reg_addr1').val(),
			"addr2" : $('#user_reg_addr2').val()
		},
		success : function(a){
			console.log(a);
			reset_reg_form();
		
			if (a == 0) {
				alert('주소 변경에 실패하였습니다. 잠시 후 다시 시도하여 주십시오.');
				reset_reg_form();
				} else {
				alert('수정이 완료되었습니다.');
				location.href = "/main.do";
				}
			}
			
			
		})
		
	}
}