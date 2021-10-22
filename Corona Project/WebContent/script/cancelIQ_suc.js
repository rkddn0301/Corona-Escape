function reset_inquiry_regist() {
	$("#inquiry_regist")[0].reset();
}

function cancelIQ() {
	if (confirm("취소 하시겠습니까?") == true) {//확인
		reset_inquiry_regist();
		$('#myModal').modal("hide");
		
	}
}