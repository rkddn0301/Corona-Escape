function deleteUser(){
	if (confirm("탈퇴 시 복구할 수 없습니다. 그래도 하시겠습니까?") == true){ //확인
		$.ajax({
			url : "/deleteUser.do",
			type : "post",
			dataType : "json",
			data : {
				"user_no" : $('#user_reg_no').val()
			},
			success : function(a) {
				if(a == 0) {
					alert('탈퇴 도중 오류가 발생했습니다. 잠시 후 다시 시도하여 주십시오.');
					return false;
				} else {
					alert('탈퇴가 완료되었습니다. 그동안 이용해 주셔서 감사합니다.');
					location.href="/docs.do";
				}
			}
		})
		return true;
	} else {//취소 
		return
	}

	
	
}
