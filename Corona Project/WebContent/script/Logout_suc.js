function Logoutbtn() {
	if (confirm("로그아웃 하시겠습니까?") == true){ //확인
		
		    
		location.href="/Logoutbtn.do";

	} else {//취소 
		return
	}
	
	
}