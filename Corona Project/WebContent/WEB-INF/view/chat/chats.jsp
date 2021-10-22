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
<title>채팅 목록</title>


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
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/bootstrap/css/light-bootstrap-dashboard.css?v=2.0.0 "
	rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="/bootstrap/css/demo.css" rel="stylesheet" />




</head>

<body>
	<div class="wrapper">
		<div class="sidebar" data-color="red" data-image="/image/corona19.PNG">
			<!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
			<div class="sidebar-wrapper" style="height: 850px;">
				<div class="logo">
					<a href="/docs.do" class="simple-text"> Corona Escape </a>
				</div>
				
				<%
					if (SS_AUTHOR.equals("1")) {
				%>


				<!-- 관리자 메뉴 -->
				<ul class="nav">
					<li class="nav-item"><a class="nav-link"
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
							<li class="nav-item"><a class="nav-link"
								href="/route/routesite.do">확진자이동경로</a></li>

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
							<li class="nav-item"><a class="nav-link" href="#"
								onclick="mvIQ()">문의내역</a></li>
							<li class="nav-item active"><a class="nav-link"
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
						<div class="col-md-12">
							<div class="card">
								<div class="card-header card-header-primary">
									<h3 class="card-title" style="color: #f07575;">
										<b>채팅방 목록</b>
									</h3>

								</div>
								<div class="card-body">
								<hr/>
								
									<b><h4 id="resContainer" class="card-title room_list"></h4></b>
									

								</div>
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
						value="<%=SS_NICK_NAME%>" /> <input type="hidden" name="i_author"
						id="inquiry_author" value="<%=SS_AUTHOR%>" />


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
<script src="/jquery/jquery.min.js"></script>
<!--   Core JS Files   -->
<script src="/bootstrap/js/popper.min.js" type="text/javascript"></script>
<script src="/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="/bootstrap/js/bootstrap-switch.js"></script>

<!--  Notifications Plugin    -->
<script src="/bootstrap/js/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="/bootstrap/js/light-bootstrap-dashboard.js?v=2.0.0 "
	type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="/bootstrap/js/demo.js"></script>

<!-- 로그아웃 -->
<script type="text/javascript" src="/script/Logout_suc.js"></script>

<!-- 세션 -->
<script type="text/javascript">
	$(function() {
		var check = "<%=SS_USER_NO%>";
		if (check == "") {
			alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
			location.href = "/login.do";
		}
	});

</script>

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


<!-- 채팅 -->
<script type="text/javascript">
	$(window).on('load', function() {
		//전체 채팅방 리스트 가져오기
		getRoomList();

		setInterval('getRoomList()', 1000);

	});
	//전체 채팅방 리스트 가져오기
	function getRoomList() {
		//Ajax 호출
		$.ajax({
					url : '/chat/roomList.do',
					type : 'post',
					dataType : "JSON",
					contentType : "application/json; charset=UTF-8",
					success : function(json) {
						var roomList = "";
						for (var i = 0; i < json.length; i++) {

							roomList += "<div >";
							roomList += "<a href='#' onclick='goRoom(this)' id='"
							+ json[i] +"'>";
							roomList += "<h4>"
									+ json[i] +"님의 문의</h4>";
							roomList += "<hr />"
							roomList += "</a>";

							roomList += "</div>";

						}
						$('.room_list').html(roomList);
					}
				})
	}

	function goRoom(title) {
		var roomtitle = $(title).attr('id');
		console.log(roomtitle);
		location.href = '/chat/intro.do?room_key=' + roomtitle;
	}
</script>



</html>
