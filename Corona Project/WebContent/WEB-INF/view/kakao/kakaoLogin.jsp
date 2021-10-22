<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>

<%
	String userId = CmmUtil.nvl((String) session.getAttribute("userId"));
	String access_Token = CmmUtil.nvl((String) session.getAttribute("access_Token"));
	String month = CmmUtil.nvl((String) session.getAttribute("month"));
	String day = CmmUtil.nvl((String) session.getAttribute("day"));
	String gender = CmmUtil.nvl((String) session.getAttribute("gender"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 테스트</title>
</head>
<body>

<% if (userId.equals("")) {
%>



	<a
		href="https://kauth.kakao.com/oauth/authorize?client_id=c8383f77b5e928527317d40b67ea998a&redirect_uri=http://localhost:8080/kakao/redirect.do&response_type=code"><img
		src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png"
		style="height: 60px; width: auto;"></a>
<%} else {%>

	<h1>로그인 성공입니다.</h1>
	<p>Id : <%=userId %></p>
	<p>access_Token : <%=access_Token %></p>
	<p>month : <%=month %>월</p>
	<p>day : <%=day %>일</p>
	<p>gender : <%=gender %></p>
	
	<input type="button" value="로그아웃" onclick="location.href='/kakao/logout.do'">

<%} %>


</body>
</html>