function reset_inquiry_regist() {
	$("#inquiry_regist")[0].reset();
}

function doRegInquiryCheck(f) {
	if (f.inquiry_user_no.value == "") {
		alert("세션이 만료되었습니다. 다시 로그인해 주세요");
		location.href="/login.do";
		return false;
	}
	
	if (f.inquiry_title.value == "") {
		alert("제목을 입력해주세요.");
		f.inquiry_title.focus();
		return false;
	}
	if (f.inquiry_content.value == "") {
		alert("내용을 입력해주세요.");
		f.inquiry_content.focus();
		return false;
	}
	
	return true;
}



function insertIQ() {
	if (doRegInquiryCheck(document.getElementById('inquiry_regist'))) {
		
		$.ajax({
			url : '/inquiry/registinquiry.do',
			type : 'post',
			data : {
				'inquiry_title' : $('#inquiry_reg_title').val(),
				'inquiry_content' : $('#inquiry_reg_content').val(),
				'i_user_no' : $('#inquiry_user_no').val(),
				'i_user_id' : $('#inquiry_user_id').val(),
				'i_nick_name' : $('#inquiry_nick_name').val(),
				'i_author' : $('#inquiry_author').val()
				
			},
			success : function(a) {
				console.log(a);
				if (a == 1) {
					$('#inquiry_reg_content').val().replace(/\n/g, "<br>")
					alert("문의등록이 완료되었습니다.");
					reset_inquiry_regist();
					$('#myModal').modal("hide");
					location.reload();
					

				} else if (a == 0) {
					alert("문의 등록에 실패했습니다. 잠시 후 다시 시도하여 주십시오.");
				}
			}
		})
	}
}


