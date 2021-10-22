<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="poly.util.CmmUtil"%>
<%@ page import="poly.dto.UserDTO"%>

<%

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
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>코로나19 분석 홈페이지에 오신 것을 환영합니다.</title>
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
<script
	src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
<style>
pre.prettyprint {
	background-color: #eee;
	border: 0px;
	margin-bottom: 60px;
	margin-top: 30px;
	padding: 20px;
	text-align: left;
}

.atv, .str {
	color: #05AE0E;
}

.tag, .pln, .kwd {
	color: #3472F7;
}

.atn {
	color: #2C93FF;
}

.pln {
	color: #333;
}

.com {
	color: #999;
}

.space-top {
	margin-top: 50px;
}

.btn-primary .caret {
	border-top-color: #3472F7;
	color: #3472F7;
}

.area-line {
	border: 1px solid #999;
	border-left: 0;
	border-right: 0;
	color: #666;
	display: block;
	margin-top: 20px;
	padding: 8px 0;
	text-align: center;
}

.area-line a {
	color: #666;
}

.container-fluid {
	padding-right: 15px;
	padding-left: 15px;
}

.table-shopping .td-name {
	min-width: 130px;
}

.pick-class-label {
	border-radius: 8px;
	border: 1px solid #E3E3E3;
	color: #ffffff;
	cursor: pointer;
	display: inline-block;
	font-size: 75%;
	font-weight: bold;
	line-height: 1;
	margin-right: 10px;
	padding: 23px;
	text-align: center;
	vertical-align: baseline;
	white-space: nowrap;
}
</style>
</head>

<body class="documentation">
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg  navbar-transparent "
		color-on-scroll="600">

		<div class="container-fluid ">

			<div class="collapse navbar-collapse justify-content-end"
				id="navigation">

				<ul class="navbar-nav">
					<li class="nav-item">
						<p class="nav-link"></p>
					</li>
					<li class="nav-item">
						<p class="nav-link"></p>
					</li>
					<li class="nav-item">
						<p class="nav-link"></p>
					</li>
				</ul>

			</div>

		</div>

	</nav>
	<!-- End Navbar -->
	<div class="wrapper">
		<div class="page-header clear-filter" filter-color="red">
			<div class="page-header-image" data-parallax="true"
				style="background-image: url('image/corona19.PNG')">
				<div class="filter"></div>
				<div class="title-container text-center">
					<h1></h1>
					<h1>Corona Escape</h1>
					<h3 class="description text-center">코로나로 지겨운 나날 이제 끝냅시다.</h3>
					<br /><br />
					
					
					<%
						if (SS_USER_ID == null || SS_USER_ID == "") {
					%>
					<button type="button" class="btn-dark btn-fill caret"
						target="_blank" onclick="mvuserinfo()">회원가입</button>
					<button type="button" class="btn-dark btn-fill caret"
						target="_blank" onclick="mvlogin()">로그인</button>
					<%
						} else {
					%>


					<button type="button" class="btn-dark btn-fill caret"
						target="_blank" onclick="Logoutbtn()">로그아웃</button>
					
					<br /> <br />
					<button type="button" class="btn-dark btn-round btn-fill caret"
						target="_blank" onclick="mvmain()">메인으로 들어가기</button>
					<%
						}
					%>				
				</div>
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
<!--  Chartist Plugin  -->
<script src="bootstrap/js/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="bootstrap/js/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="bootstrap/js/light-bootstrap-dashboard.js?v=2.0.0 "
	type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="bootstrap/js/demo.js"></script>
<script>
	var header_height;
	var fixed_section;
	var floating = false;

	$(document).ready(function() {
		suggestions_distance = $("#suggestions").offset();
		pay_height = $('.fixed-section').outerHeight();

	});
</script>



<!-- 회원가입 가기버튼 -->
<script>
	function mvuserinfo() {
		location.href = "/userinfo.do";
	}
</script>

<!-- 로그인 가기버튼 -->
<script>
	function mvlogin() {
		location.href = "/login.do";
	}
</script>

<!-- 로그아웃 버튼 -->
<script type="text/javascript" src="/script/Logout_suc.js"></script>

<!-- 메인홈페이지 가기버튼 -->
<script>
	function mvmain() {
		location.href = "/main.do";
	}
</script>

<!-- 카카오 로그아웃 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("3d554838e744a4e7d04bb8af264d9794");
</script>


</html>