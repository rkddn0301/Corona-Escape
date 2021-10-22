<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="static poly.util.CmmUtil.nvl"%>
<%@ page import="poly.dto.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="poly.dto.CoronaMainDTO"%>

<%
//기존 회원 세션
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
<meta charset="utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>마이페이지</title>


<!-- 하이퍼링크 밑줄 없애기 -->
<style type="text/css">
a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
</style>



<meta name="viewport" content="width=device-width,initial-scale=1">
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
<!-- CSS Files -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="bootstrap/css/light-bootstrap-dashboard.css?v=2.0.0 "
	rel="stylesheet" />

<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="bootstrap/css/demo.css" rel="stylesheet" />
</head>

<body>
	<div class="wrapper">
		<div class="sidebar" data-color="red" data-image="image/corona19.PNG">
			<!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
			<div class="sidebar-wrapper" style="height: 850px;">
				<div class="logo">
					<a href="/docs.do" class="simple-text"> Corona Escape </a>
				</div>
				<%
					if (SS_AUTHOR.equals("0")) {
				%>
				<!-- 사용자 메뉴 -->
				<ul class="nav">

					<!-- a 클래스를 클릭하면 숨김기능인 'collapse'가 data-toggle로 인해 활성화되어,
					data-target을 통해 div id를 호출해낸다. 그렇게해서 해당 div가 메뉴식으로 나오는 모션을 만듦.
					
					 -->


					<li class="nav-item"><a class="nav-link" href="/main.do">
							<i class="nc-icon nc-icon nc-bank"></i> 메인
					</a></li>
					<br />

					<li class="nav-item"><a href="#"
						class="dropdown-toggle nav-link collapsed" data-toggle="collapse"
						data-target="#collapseTwo" aria-expanded="false"
						aria-controls="collapseTwo"> <i
							class="nc-icon nc-icon nc-single-02"></i> 회원
					</a></li>
					<div id="collapseTwo" class="collapse">
						<ul class="nav" style="text-align: center">

							<li class="nav-item active"><a class="nav-link"
								href="/mypage.do">마이페이지</a></li>
							<li class="nav-item"><a class="nav-link"
								href="#" onclick="Logoutbtn()">로그아웃</a></li>

						</ul>
					</div>
					<br />
					<li class="nav-item"><a href="#"
						class="dropdown-toggle nav-link collapsed" data-toggle="collapse"
						data-target="#collapseThree" aria-expanded="false"
						aria-controls="collapseThree"> <i
							class="nc-icon nc-icon nc-circle"></i> 발생동향
					</a></li>
					<div id="collapseThree" class="collapse">
						<ul class="nav" style="text-align: center">
							<li class="nav-item"><a class="nav-link" href="/country/countrysite.do">시도별발생동향</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/foreign/foreignsite.do">국외발생현황</a></li>
							<li class="nav-item"><a class="nav-link" href="/gubun/gubunsite.do">연령별·성별현황</a></li>
							<li class="nav-item"><a class="nav-link" href="/route/routesite.do">확진자이동경로</a></li>

						</ul>
					</div>

					<br />
					<li class="nav-item"><a href="#"
						class="dropdown-toggle nav-link collapsed" data-toggle="collapse"
						data-target="#collapseFour" aria-expanded="false"
						aria-controls="collapseFour"> <i
							class="nc-icon nc-icon nc-headphones-2"></i> 고객지원
					</a></li>
					<div id="collapseFour" class="collapse">
						<ul class="nav" style="text-align: center">
							<li class="nav-item"><a class="nav-link" href="/notice/noticelist.do">공지사항</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/hospital/hospital.do">선별진료소</a></li>
							<%if (access_Token == "") {  %>
							<li class="nav-item"><a class="nav-link" href="#"
							data-toggle="modal" data-target="#myModal">문의하기</a></li>
							<li class="nav-item"><a class="nav-link" href="#" onclick="mvIQ()">문의내역</a></li>
							<form id="form-data" name="form" method="get" action="/chat/intro.do">
							<input type="hidden" id="room_name" name="room_key" />
							<li class="nav-item"><a class="nav-link"
								href="#" onclick="chatopen()">실시간 채팅 문의</a></li>
							</form>
							<%} %>
							
							
						</ul>
						
					</div>
					
					<form name="inquiry">
						<input type="hidden" name="i_user_no" value="<%=SS_USER_NO%>"/>
					</form>

				</ul>			
				<%
					}
				%>

			</div>
		</div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav class="navbar navbar-expand-lg " color-on-scroll="500">
				<div class="container-fluid">
					<a class="navbar-brand" href="#pablo"></a>
					<button href="" class="navbar-toggler navbar-toggler-right"
						type="button" data-toggle="collapse"
						aria-controls="navigation-index" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-bar burger-lines"></span> <span
							class="navbar-toggler-bar burger-lines"></span> <span
							class="navbar-toggler-bar burger-lines"></span>
					</button>

				</div>

			</nav>

			<!-- End Navbar -->
			<br /> <br />

			<div class="content">
				<div class="container-fluid">

					<div class="col-lg-20">

						<div class="card">
							<div class="card-header">
								<h1 class="card-title">
									<b>마이페이지</b>
									<input type="hidden" name="user_no" id="user_reg_no" value="<%=SS_USER_NO %>"/>
								</h1>
							</div>
							
							<% if (access_Token != "") {
							%>
							<div class="card-body">
								<form id="mypage_kakao" method="post" name="f">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="user_id">아이디</label> <input
													class="form-control py-4" type="text" id="user_update_id"
													name="user_id" value="<%=SS_USER_ID%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="user_name">이름</label> <input
													class="form-control py-4" type="text" name="user_name"
													value="<%=SS_USER_NAME%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="nick_name">별명</label> <input
													class="form-control py-4" type="text" name="nick_name"
													value="<%=SS_NICK_NAME%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="email">이메일</label> <input
													class="form-control py-4" type="text" name="email"
													value="<%=SS_EMAIL%>" style="width: 300px; height: 50px"
													readonly />
											</div>
										</div>

									</div>
										<div class="col-lg-12">
											<div class="form-group">
									
												<h2 class="card-category">* 카카오 계정은 수정할 수 없습니다.</h2>
											</div>
										
										</div>
									</form>

									<%} else { %>
							
	
							<div class="card-body">
								<form id="mypage_form" method="post" name="f">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="user_id">아이디</label> <input
													class="form-control py-4" type="text" id="user_update_id"
													name="user_id" value="<%=SS_USER_ID%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="user_name">이름</label> <input
													class="form-control py-4" type="text" name="user_name"
													value="<%=SS_USER_NAME%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="nick_name">별명</label> <input
													class="form-control py-4" type="text" name="nick_name"
													value="<%=SS_NICK_NAME%>"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="birthday">출생년도</label> <input
													class="form-control py-4" type="text" name="birthday"
													value="<%=SS_YEAR%>년 <%=SS_MONTH%>월<%=SS_DAY%>일"
													style="width: 300px; height: 50px" readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="gender">성별</label> <input
													class="form-control py-4" type="text" name="gender"
													value="<%=SS_GENDER%>" id="gender"
													style="width: 100px; height: 50px" readonly />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="email">이메일</label> <input
													class="form-control py-4" type="text" name="email"
													value="<%=SS_EMAIL%>" style="width: 300px; height: 50px"
													readonly />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="password">비밀번호 변경</label> <input
													class="form-control py-4" type="password" name="password"
													id="user_update_password" placeholder="변경할 비밀번호 입력"
													oninput="checkPw()" style="width: 300px; height: 50px" />
												<div id="wrongPw"
													style="display: none; color: red; font-size: 11px; margin-left: 20px;">5~16자의
													비밀번호를 사용해주세요.</div>

												<input class="form-control py-4" type="password"
													name="password2" id="repeat_password"
													placeholder="비밀번호 재입력" oninput="checkPw2()"
													style="width: 300px; height: 50px" />
												<div id="wrongPw2"
													style="display: none; color: red; font-size: 11px; margin-left: 20px;">비밀번호가
													일치하지 않습니다.</div>
													
												<br/>
													
												<button class="btn-dark btn-simple caret" type="button"
													onclick="updatePw()" id="passwordbtn">변경</button>

											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="small mb-1" for="addr1">주소</label> <input
													class="form-control py-4" type="text" name="addr1"
													id="user_reg_addr1" value="<%=SS_ADDR1%>"
													style="width: 300px; height: 50px" /> <input
													class="form-control py-4" type="text" name="addr2"
													id="user_reg_addr2" value="<%=SS_ADDR2%>"
													style="width: 300px; height: 50px" />
												<br/>

												<button type="button" class="btn-dark btn-simple caret"
													onclick="sample2_execDaumPostcode()">주소 찾기</button>
												&nbsp;&nbsp;&nbsp;
												<button class="btn-dark btn-simple caret" type="button"
													onclick="updateAddr()" id="Addrbtn">수정</button>
												&nbsp;&nbsp;&nbsp;
												<button class="btn-dark btn-simple caret" type="button"
													onclick="deleteUser()" id="deletebtn">회원탈퇴</button>
											</div>
										</div>



									</div>







								</form>

								<br />

							</div>
							
							<%} %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog" data-backdrop="static">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header" style="justify-content: center;">
					<h2 class="modal-title">
						<b>문의하기</b>
					</h2>
				</div>
				<form id="inquiry_regist" method="post" name="f">
					<input type="hidden" name="i_user_no" id="inquiry_user_no"
						value="<%=SS_USER_NO%>" /> <input type="hidden" name="i_user_id"
						id="inquiry_user_id" value="<%=SS_USER_ID%>" /> <input type="hidden"
						name="i_nick_name" id="inquiry_nick_name" value="<%=SS_NICK_NAME%>" />
					<input type="hidden" name="i_author" id="inquiry_author"
						value="<%=SS_AUTHOR%>" />


					<div class="modal-body">

						<div class="row" style="justify-content: center;">
							<div class="form-group">

								<input type="text" id="inquiry_reg_title" name="inquiry_title"
									placeholder="문의 제목" style="width: 300px; height: 50px;"
									autocomplete="off" />
							</div>
						</div>

						<div class="row" style="justify-content: center;">
							<div class="form-group">

								<textarea id="inquiry_reg_content" name="inquiry_content"
									placeholder="문의 내용" cols="50"
									style="resize: none; height: 350px;"></textarea>
							</div>
						</div>
					</div>

					<div class="modal-footer" style="justify-content: flex-end; margin-right: 0px;">
						<div class="row"
							>
							<div class="form-group">
								<button class="btn-dark btn-simple caret" type="button"
									onclick="insertIQ()">보내기</button>
								<button class="btn-dark btn-simple caret" type="button"
									onclick="cancelIQ()">취소</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="jquery/jquery.min.js"></script>
<!--   Core JS Files   -->
<script src="bootstrap/js/popper.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="bootstrap/js/bootstrap-switch.js"></script>
<!--  Notifications Plugin    -->
<script src="bootstrap/js/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="bootstrap/js/light-bootstrap-dashboard.js?v=2.0.0 "
	type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="bootstrap/js/demo.js"></script>

<!-- 세션 -->
<script>
$(function() {
	var check = "<%=SS_USER_NO%>";
	if(check == "") {
		alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
		location.href = "/login.do";
	}


});
</script>

<!-- 로그아웃 -->
<script type="text/javascript" src="/script/Logout_suc.js"></script>

<!-- 비밀번호 변경 -->
<script type="text/javascript" src="/script/UpdatePw_suc.js"></script>

<!-- 주소 변경 -->
<script type="text/javascript" src="/script/UpdateAddr_suc.js"></script>

<!-- 회원 탈퇴 -->
<script type="text/javascript" src="/script/DeleteUser_suc.js"></script>

<!-- Daum api -->
<!--  다음 주소 API -->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<div id="layer"
	style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
		id="btnCloseLayer"
		style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
		onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script type="text/javascript" src="/script/daumAPIaddr.js"></script>

<!-- 문의하기  -->
<script type="text/javascript" src="/script/InsertIQ_suc.js"></script>

<!-- 문의하기 취소 버튼 -->
<script type="text/javascript" src="/script/cancelIQ_suc.js"></script>

<!-- 문의내역 이동 -->
<script>
function mvIQ() {
	var f = document.inquiry;
	
	f.action = "/inquiry/inquirylist.do"
	f.method = "post"
	f.submit();
}

</script>

<!-- 카카오 로그아웃 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("3d554838e744a4e7d04bb8af264d9794");
</script>

<!-- 채팅방 생성 -->
<script>
	function chatopen() {
		var title = '<%=SS_NICK_NAME%>';
		if (title != null) {
			$('#room_name').val(title);
			$('#form-data').submit();
		}
	}
</script>

</html>
