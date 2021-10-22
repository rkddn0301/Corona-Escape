<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.UserDTO" %>
<%
	String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
	String url = CmmUtil.nvl((String)request.getAttribute("url"));
	UserDTO rDTO = (UserDTO)request.getAttribute("rDTO");
	UserDTO pDTO = (UserDTO)request.getAttribute("pDTO");

	
		// 기존 회원 세션
		String SS_USER_NO = CmmUtil.nvl((String) session.getAttribute("SS_USER_NO"));
		String SS_USER_ID = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
		String SS_USER_NAME = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));
		String SS_YEAR = CmmUtil.nvl((String) session.getAttribute("SS_YEAR"));
		String SS_MONTH = CmmUtil.nvl((String) session.getAttribute("SS_MONTH"));
		String SS_DAY = CmmUtil.nvl((String) session.getAttribute("SS_DAY"));
		String SS_EMAIL = CmmUtil.nvl((String) session.getAttribute("SS_EMAIL"));
		String SS_NICK_NAME = CmmUtil.nvl((String) session.getAttribute("SS_NICK_NAME"));
		String SS_GENDER = CmmUtil.nvl((String) session.getAttribute("SS_GENDER"));
		String SS_ADDR1 = CmmUtil.nvl((String) session.getAttribute("SS_ADDR1"));
		String SS_ADDR2 = CmmUtil.nvl((String) session.getAttribute("SS_ADDR2"));
		String SS_AUTHOR = CmmUtil.nvl((String) session.getAttribute("SS_AUTHOR"));
		
		// 카카오 회원 세션
		String access_Token = CmmUtil.nvl((String) session.getAttribute("access_Token"));

%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
alert("<%=msg%>");
location.href = "<%=url%>"
</script>
<meta charset="UTF-8">
<title>회원가입 결과</title>
</head>
<body>

</body>

<!-- 카카오 로그아웃 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("3d554838e744a4e7d04bb8af264d9794");
</script>
</html>