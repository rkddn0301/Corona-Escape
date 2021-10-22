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

	List<CoronaMainDTO> rList = (List<CoronaMainDTO>) request.getAttribute("rList");

	if (rList == null) {
		rList = new ArrayList<CoronaMainDTO>();
	}

	List<CoronaMainDTO> cList = (List<CoronaMainDTO>) request.getAttribute("cList");

	if (cList == null) {
		cList = new ArrayList<CoronaMainDTO>();
	}

	CoronaMainDTO mDTO = (CoronaMainDTO) request.getAttribute("mDTO");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>메인</title>


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


					<li class="nav-item active"><a class="nav-link"
						href="/main.do"> <i class="nc-icon nc-icon nc-bank"></i> 메인
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

							<li class="nav-item"><a class="nav-link" href="/mypage.do">마이페이지</a></li>
							<li class="nav-item"><a class="nav-link" href="#"
								onclick="Logoutbtn()">로그아웃</a></li>

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
							<li class="nav-item"><a class="nav-link"
								href="/country/countrysite.do">시도별발생동향</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/foreign/foreignsite.do">국외발생현황</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/gubun/gubunsite.do">연령별·성별현황</a></li>
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
							<li class="nav-item"><a class="nav-link"
								href="/notice/noticelist.do">공지사항</a></li>
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
						<input type="hidden" name="i_user_no" value="<%=SS_USER_NO%>" />
					</form>


				</ul>
				<%
					} else if (SS_AUTHOR.equals("1")) {
				%>
				<!-- 사용자 메뉴 끝 -->

				<!-- 관리자 메뉴 -->
				<ul class="nav">
					<li class="nav-item active"><a class="nav-link"
						href="/main.do"> <i class="nc-icon nc-icon nc-bank"></i> 메인
					</a></li>
					<br />

					<li class="nav-item"><a href="#"
						class="dropdown-toggle nav-link collapsed" data-toggle="collapse"
						data-target="#collapseTwo" aria-expanded="false"
						aria-controls="collapseTwo"> <i
							class="nc-icon nc-icon nc-key-25"></i> 관리자
					</a></li>
					<div id="collapseTwo" class="collapse">
						<ul class="nav" style="text-align: center">

							<li class="nav-item"><a class="nav-link" href="/manage.do">회원관리</a></li>
							<li class="nav-item"><a class="nav-link" href="#"
								onclick="Logoutbtn()">로그아웃</a></li>

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
							<li class="nav-item"><a class="nav-link"
								href="/country/countrysite.do">시도별발생동향</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/foreign/foreignsite.do">국외발생현황</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/gubun/gubunsite.do">연령별·성별현황</a></li>
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
							<li class="nav-item"><a class="nav-link"
								href="/notice/noticelist.do">공지사항</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/hospital/hospital.do">선별진료소</a></li>
							<li class="nav-item"><a class="nav-link" href="#" onclick="mvIQ()">문의내역</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/chat/chats.do">실시간 채팅 문의</a></li>

						</ul>
					</div>
				</ul>
				<!-- 관리자 메뉴 끝 -->
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


			<div class="content">
				<div class="container-fluid">
					<div class="row" style="text-align: center;">
						<div class="col-md-4">
							<div class="card">
								<div class="card-header card-header-primary">
									<h3 class="card-title" style="color: #f07575;">
										<b>확진자</b>
									</h3>

								</div>
								<div class="card-body">
									<h2 class="card-title" id="TotalCase"></h2>
									<br />
									<h4 class="card-title" id="newCase"></h4>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title" style="color: #f07575;">
										<b>완치자</b>
									</h3>
								</div>
								<div class="card-body">
									<h2 class="card-title" id="TotalRecovered"></h2>
									<br />
									<h4 class="card-title" id="TodayRecovered"></h4>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title" style="color: #f07575;">
										<b>사망자</b>
									</h3>
								</div>
								<div class="card-body">
									<h2 class="card-title" id="TotalDeath"></h2>
									<br />
									<h4 class="card-title" id="TodayDeath"></h4>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title" id="socialDistancing">
									</h4>
								</div>
								<div class="card-body">
									<h2 class="card-category">거리두기 단계 수칙</h2>
									<br>
									<pre class="card-category" id="detail">
									</pre>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">전일대비 확진환자 증감비율</h4>
								</div>
								<div class="card-body ">
									<div id="chartdiv"></div>
								</div>

							</div>
						</div>
					</div>
					<div style="text-align: center;">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">백신접종현황</h4>
								<br />
								<b><span id="vaccine1"></span></b>&nbsp;&nbsp;&nbsp;&nbsp;
								<b><span id="vaccine2"></span></b>
								<br />
								<br />
								<button type="button" class="btn-light btn-simple carat"
									onclick="getTodayVaccine()">당일 현황</button>
								<button type="button" class="btn-light btn-simple carat"
									onclick="getTotalVaccine()">누계 현황</button>
								
							</div>
							<div class="card-body">
								<div id="vaccinediv"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog"
		data-backdrop="static">
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
											id="inquiry_user_id" value="<%=SS_USER_ID%>" /> <input
											type="hidden" name="i_nick_name" id="inquiry_nick_name"
											value="<%=SS_NICK_NAME%>" /> <input type="hidden"
											name="i_author" id="inquiry_author" value="<%=SS_AUTHOR%>" />


										<div class="modal-body">

											<div class="row" style="justify-content: center;">
												<div class="form-group">

													<input type="text" id="inquiry_reg_title"
														name="inquiry_title" placeholder="문의 제목"
														style="width: 300px; height: 50px;" autocomplete="off" />
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

										<div class="modal-footer"
											style="justify-content: flex-end; margin-right: 0px;">
											<div class="row">
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

<!-- 로그아웃 -->
<script type="text/javascript" src="/script/Logout_suc.js"></script>

<!-- 확진자 수, 거리두기 단계 -->
<script type="text/javascript">
	$(function() {
		var check = "<%=SS_USER_NO%>";
		if(check == "") {
		alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
		location.href = "/login.do";
		}	
		getInformation();
		getTodayVaccine();
	});

	function getInformation() {
		var socialDistancing = "";
		var detail = "";

		$.ajax({
			url : "/corona/coronaMain.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {
				var TotalCase = (json[0].totalCase + "명");
				var TotalRecovered = (json[0].totalRecovered + "명");
				var TotalDeath = (json[0].totalDeath + "명");
				var TodayRecovered = ("+" + json[0].todayRecovered + "명");
				var TodayDeath = ("+" + json[0].todayDeath + "명");
				var newCase = ("+" + json[0].newCase + "명");

				$('#TotalCase').html(TotalCase);
				$('#TotalRecovered').html(TotalRecovered);
				$('#TotalDeath').html(TotalDeath);
				$('#TodayRecovered').html(TodayRecovered);
				$('#TodayDeath').html(TodayDeath);
				$('#newCase').html(newCase);
			}
		})
		
		$.ajax({
			url : "/corona/getSocialDistancing.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {
				<%
				if (SS_ADDR1.substring(0,2).equals("서울")) {%> socialDistancing = ("내 지역 거리두기 단계 : " + json[0].stage + "단계");
				<%} else if(SS_ADDR1.substring(0,2).equals("부산")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[1].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("대구")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[2].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("인천")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[3].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("광주")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[4].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("대전")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[5].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("울산")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[6].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("세종")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[7].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("경기")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[8].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("강원")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[9].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("충북")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[10].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("충남")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[11].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("전북")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[12].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("전남")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[13].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("경북")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[14].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("경남")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[15].stage + "단계");
				<% } else if(SS_ADDR1.substring(0,2).equals("제주")) {%>socialDistancing = ("내 지역 거리두기 단계 : " + json[16].stage + "단계");
				<% } else { %>
					socialDistancing = ("주소를 찾을 수 없습니다.");
				<% } %> 
				
				
				<%
				if (SS_ADDR1.substring(0,2).equals("서울")){%>
					if (json[0].stage == 1) {
						detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
					} else if (json[0].stage == 2) {
						detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
					} else if (json[0].stage == 4) {
						detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
					}   else if (json[0].stage == 3) {
						detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
					} 
				
				<%} else if (SS_ADDR1.substring(0,2).equals("부산")){%>
				if (json[1].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[1].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[1].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[1].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("대구")){%>
				if (json[2].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[2].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[2].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[2].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("인천")){%>
				if (json[3].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[3].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[3].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[3].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("광주")){%>
				if (json[4].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[4].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[4].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[4].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("대전")){%>
				if (json[5].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[5].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[5].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[5].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("울산")){%>
				if (json[6].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[6].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[6].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[6].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("세종")){%>
				if (json[7].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[7].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[7].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[7].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("경기")){%>
				if (json[8].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[8].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[8].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[8].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("강원")){%>
				if (json[9].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[9].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[9].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[9].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("충북")){%>
				if (json[10].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[10].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[10].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[10].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("충남")){%>
				if (json[11].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[11].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[11].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[11].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("전북")){%>
				if (json[12].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[12].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[12].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[12].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("전남")){%>
				if (json[13].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[13].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[13].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[13].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("경북")){%>
				if (json[14].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[14].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[14].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[14].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("경남")){%>
				if (json[15].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[15].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[15].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[15].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%} else if (SS_ADDR1.substring(0,2).equals("제주")){%>
				if (json[16].stage == 1) {
					detail = ("단계 명칭 : 지속적 억제상태 유지 \n\n기준 : 인구 10만명당 1명 미만\n(주간 평균)\n▸ 전국: 500명 미만\n▸ 수도권: 250명 미만\n\n모임 : 방역수칙 준수\n\n행사 : 500인 이상 행사 시 지자체 사전 신고\n\n집회 : 500인 이상 집회 금지");
				} else if (json[16].stage == 2) {
					detail = ("단계 명칭 : 지역 유행/인원 제한 \n\n기준 : 인구 10만명당 1명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 500명 이상\n▸ 수도권: 250명 이상\n\n모임 : 8명까지 모임 가능(9인 이상 사적모임 금지)\n\n행사 : 100인 이상 행사 금지\n\n집회 : 100인 이상 집회 금지");
				} else if (json[16].stage == 4) {
					detail = ("단계 명칭 : 대유행/외출 금지 \n\n기준 : 인구 10만명당 4명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 2,000명 이상\n▸ 수도권: 1,000명 이상\n\n모임 : 18시 이후 2명까지 모임 가능(3인 이상 사적모임 금지)\n\n행사 : 행사 금지\n\n집회 : 1인 시위 외 집회 금지");
				}   else if (json[16].stage == 3) {
					detail = ("단계 명칭 : 권역 유행/모임 금지 \n\n기준 : 인구 10만명당 2명 이상\n(주간 평균이 3일 이상 기준 초과)\n▸ 전국: 1,000명 이상\n▸ 수도권: 500명 이상\n\n모임 : 4명까지 모임 가능(5인 이상 사적모임 금지)\n\n행사 : 50인 이상 행사 금지\n\n집회 : 50인 이상 집회 금지");
				}
				<%}%>


					
					
			
				
				

				$('#socialDistancing').html(socialDistancing);
				$('#detail').html(detail);

			}
		})
	}
</script>
					



<!-- 전일대비 그래프 -->
<!-- Styles -->
<style>
#chartdiv {
	width: 100%;
	height: 500px;
}
</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/material.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_material);
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("chartdiv", am4charts.XYChart);

		// ajax 코로나 전일대비
		$(function() {
			getCountryInformation();
		});

		function getCountryInformation() {

			$.ajax({
				url : "/corona/countryMain.do",
				type : "post",
				dataType : "JSON",
				contentType : "application/json; charset=UTF-8",
				success : function(json) {

					// Add data
					chart.data = [ {
						"country" : json[1].countryName,
						"visits" : json[1].newCase
					}, {
						"country" : json[2].countryName,
						"visits" : json[2].newCase
					}, {
						"country" : json[3].countryName,
						"visits" : json[3].newCase
					}, {
						"country" : json[4].countryName,
						"visits" : json[4].newCase
					}, {
						"country" : json[5].countryName,
						"visits" : json[5].newCase
					}, {
						"country" : json[6].countryName,
						"visits" : json[6].newCase
					}, {
						"country" : json[7].countryName,
						"visits" : json[7].newCase
					}, {
						"country" : json[8].countryName,
						"visits" : json[8].newCase
					}, {
						"country" : json[9].countryName,
						"visits" : json[9].newCase
					}, {
						"country" : json[10].countryName,
						"visits" : json[10].newCase
					}, {
						"country" : json[11].countryName,
						"visits" : json[11].newCase
					}, {
						"country" : json[12].countryName,
						"visits" : json[12].newCase
					}, {
						"country" : json[13].countryName,
						"visits" : json[13].newCase
					}, {
						"country" : json[14].countryName,
						"visits" : json[14].newCase
					}, {
						"country" : json[15].countryName,
						"visits" : json[15].newCase
					}, {
						"country" : json[16].countryName,
						"visits" : json[16].newCase
					}, {
						"country" : json[17].countryName,
						"visits" : json[17].newCase
					}, {
						"country" : json[18].countryName,
						"visits" : json[18].newCase
					}

					];

				}
			})

		}

		// Create axes

		var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "country";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.minGridDistance = 30;

		categoryAxis.renderer.labels.template.adapter.add("dy", function(dy,
				target) {
			if (target.dataItem && target.dataItem.index & 2 == 2) {
				return dy + 25;
			}
			return dy;
		});

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

		// Create series
		var series = chart.series.push(new am4charts.ColumnSeries());
		series.dataFields.valueY = "visits";
		series.dataFields.categoryX = "country";
		series.name = "Visits";
		series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/]";
		series.columns.template.fillOpacity = .8;

		var columnTemplate = series.columns.template;
		columnTemplate.strokeWidth = 2;
		columnTemplate.strokeOpacity = 1;

	}); // end am4core.ready()
</script>
<!-- 전일대비 그래프 끝 -->

<!-- 백신예방접종 현황 그래프 시작 -->
<style>
#vaccinediv{
	width: 100%;
  	height: 1000px;
}
</style>
<script type="text/javascript">
function getTodayVaccine() {
	
	$.ajax({
		url : "vaccine/getVaccination.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			
			var vaccine1 = ("당일 1차 접종 실적 : " + json[0].todayVaccine1 + "명");
			var vaccine2 = ("당일 접종완료 실적 : " + json[0].todayVaccine2 + "명");
			
			$('#vaccine1').html(vaccine1);
			$('#vaccine2').html(vaccine2);
			
	am4core.ready(function() {

		// Themes begin
		am4core.unuseTheme(am4themes_material);
		am4core.useTheme(am4themes_animated);
		// Themes end

		 // Create chart instance
		var chart = am4core.create("vaccinediv", am4charts.XYChart);

		chart.legend = new am4charts.Legend()
		chart.legend.position = 'bottom'
		chart.legend.paddingBottom = 20
		chart.legend.labels.template.maxWidth = 95
		// Add data
		chart.data = [{	
		  "city": json[1].city,
		  "1차 접종": json[1].todayVaccine1,
		  "접종완료": json[1].todayVaccine2
		},{
		  "city": json[2].city,
		  "1차 접종": json[2].todayVaccine1,
		  "접종완료": json[2].todayVaccine2  
		},{
		  "city": json[3].city,
		  "1차 접종": json[3].todayVaccine1,
		  "접종완료": json[3].todayVaccine2
		},{
		  "city": json[4].city,
		  "1차 접종": json[4].todayVaccine1,
		  "접종완료": json[4].todayVaccine2
		},{
		  "city": json[5].city,
		  "1차 접종": json[5].todayVaccine1,
		  "접종완료": json[5].todayVaccine2
		},{
		  "city": json[6].city,
		  "1차 접종": json[6].todayVaccine1,
		  "접종완료": json[6].todayVaccine2
		},{
		  "city": json[7].city,
		  "1차 접종": json[7].todayVaccine1,
		  "접종완료": json[7].todayVaccine2
		},{
		  "city": json[8].city,
		  "1차 접종": json[8].todayVaccine1,
		  "접종완료": json[8].todayVaccine2
		},{
		  "city": json[9].city,
		  "1차 접종": json[9].todayVaccine1,
		  "접종완료": json[9].todayVaccine2
		},{
		  "city": json[10].city,
		  "1차 접종": json[10].todayVaccine1,
		  "접종완료": json[10].todayVaccine2
		},{
		  "city": json[11].city,
		  "1차 접종": json[11].todayVaccine1,
		  "접종완료": json[11].todayVaccine2
		},{
		  "city": json[12].city,
		  "1차 접종": json[12].todayVaccine1,
		  "접종완료": json[12].todayVaccine2
		},{
		  "city": json[13].city,
		  "1차 접종": json[13].todayVaccine1,
		  "접종완료": json[13].todayVaccine2
		},{
		  "city": json[14].city,
		  "1차 접종": json[14].todayVaccine1,
		  "접종완료": json[14].todayVaccine2
		},{
		  "city": json[15].city,
		  "1차 접종": json[15].todayVaccine1,
		  "접종완료": json[15].todayVaccine2
		},{
		  "city": json[16].city,
	      "1차 접종": json[16].todayVaccine1,
		  "접종완료": json[16].todayVaccine2
		},{
		  "city": json[17].city,
		  "1차 접종": json[17].todayVaccine1,
		  "접종완료": json[17].todayVaccine2
		}];

		// Create axes
		var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
		categoryAxis.dataFields.category = "city";
		categoryAxis.renderer.inversed = true;
		categoryAxis.numberFormatter.numberFormat = "#";
		categoryAxis.renderer.grid.template.location = 0;
		categoryAxis.renderer.cellStartLocation = 0.1;
		categoryAxis.renderer.cellEndLocation = 0.9;

		var  valueAxis = chart.xAxes.push(new am4charts.ValueAxis()); 
		valueAxis.renderer.opposite = true;

		// Create series
		function createSeries(field, name) {
		  var series = chart.series.push(new am4charts.ColumnSeries());
		  series.dataFields.valueX = field;
		  series.dataFields.categoryY = "city";
		  series.name = name;
		  series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
		  series.columns.template.height = am4core.percent(100);
		  series.sequencedInterpolation = true;

		  var valueLabel = series.bullets.push(new am4charts.LabelBullet());
		  valueLabel.label.text = "{valueX}";
		  valueLabel.label.horizontalCenter = "left";
		  valueLabel.label.dx = 10;
		  valueLabel.label.hideOversized = false;
		  valueLabel.label.truncate = false;

		  var categoryLabel = series.bullets.push(new am4charts.LabelBullet());
		  categoryLabel.label.text = "{name}";
		  categoryLabel.label.horizontalCenter = "right";
		  categoryLabel.label.dx = -10;
		  categoryLabel.label.fill = am4core.color("#fff");
		  categoryLabel.label.hideOversized = false;
		  categoryLabel.label.truncate = false;
		}

		createSeries("1차 접종", "1차 접종");
		createSeries("접종완료", "접종완료");

		}); // end am4core.ready()
		}
	})
}
</script>

<script type="text/javascript" src="/script/Vaccine_suc.js"></script>

<!-- 백신예방접종 현황 그래프 끝 -->

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
