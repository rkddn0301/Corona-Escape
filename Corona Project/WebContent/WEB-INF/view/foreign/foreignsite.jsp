<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="static poly.util.CmmUtil.nvl"%>
<%@ page import="poly.dto.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="poly.util.DateUtil"%>
<%@ page import="poly.dto.ForeignCountryDTO" %>

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
<title>국외발생현황</title>


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


<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="/bootstrap/css/demo.css" rel="stylesheet" />

<link href="/bootstrap/css/light-bootstrap-dashboard.css?v=2.0.0"
	rel="stylesheet" />

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
							<li class="nav-item active"><a class="nav-link"
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
					<li class="nav-item"><a class="nav-link" href="/main.do">
							<i class="nc-icon nc-icon nc-bank"></i> 메인
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
							<li class="nav-item active"><a class="nav-link"
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
							<li class="nav-item"><a class="nav-link"
								href="/inquiry/inquirylist.do">문의내역</a></li>
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
			<br /> <br />

			<div class="content">

				<div class="container-fluid">
					<div class="col-lg-20">
						<div class="card">
							<div class="card-header">
								<h1 class="card-title">
									<b>국외발생현황</b>
								</h1>
							</div>
							<div class="card-body">
								<div class="row" style="text-align: center;">
						<div class="col-md-4">
							<div class="card">
								<div class="card-header card-header-primary">
									<h3 class="card-title" style="color: #f07575;">
										<b>전세계 확진자</b>
									</h3>

								</div>
								<div class="card-body">
									<h2 class="card-title" id="TotalCase"></h2>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title" style="color: #f07575;">
										<b>전세계 완치자</b>
									</h3>
								</div>
								<div class="card-body">
									<h2 class="card-title" id="totalRecovered"></h2>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title" style="color: #f07575;">
										<b>전세계 사망자</b>
									</h3>
								</div>
								<div class="card-body">
									<h2 class="card-title" id="TotalDeath"></h2>
								</div>
							</div>
						</div>

					</div>
								<div id="chartdiv"></div>
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
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="/bootstrap/js/light-bootstrap-dashboard.js?v=2.0.0 "
	type="text/javascript"></script>
<!--  Notifications Plugin    -->
<script src="/bootstrap/js/bootstrap-notify.js"></script>

<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="/bootstrap/js/demo.js"></script>

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


<!-- 차트 -->
<!-- Styles -->
<style>
#chartdiv {
  width: 100%;
  height: 600px
}

</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/maps.js"></script>
<script src="https://cdn.amcharts.com/lib/4/geodata/worldLow.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/dataviz.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>

//ajax 국외발생현황
$(function() {
	getForeignCountryInformation();
});

function getForeignCountryInformation() {
	
	$.ajax({
		url : "/foreign/getForeignCountry.do",
		type : "post",
		dataType : "JSON",
		contentType : "application/json; charset=UTF-8",
		success : function(json) {
			
			if(json[190].totalDeaths == ""){
				json[190].totalDeaths = "0";
			}
			
			var TotalCase = (json[227].totalCases + "명");
			var TotalRecover = (json[227].totalRecovered + "명");
			var TotalDeath = (json[227].totalDeaths + "명");
			
			$('#TotalCase').html(TotalCase);
			$('#totalRecovered').html(TotalRecover);
			$('#TotalDeath').html(TotalDeath);

	am4core.ready(function() {

	// Themes begin
	am4core.useTheme(am4themes_dataviz);
	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create map instance
	var chart = am4core.create("chartdiv", am4maps.MapChart);




		var mapData = [
  { "id":"AF", "name":"아프가니스탄", "value":json[1].totalCases, "value2":json[1].totalDeaths, "value3":json[1].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"AL", "name":"알바니아", "value":json[3].totalCases, "value2":json[3].totalDeaths, "value3":json[3].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"DZ", "name":"알제리", "value":json[4].totalCases, "value2":json[4].totalDeaths, "value3":json[4].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"AO", "name":"앙골라", "value":json[6].totalCases, "value2":json[6].totalDeaths, "value3":json[6].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"AR", "name":"아르헨티나", "value":json[9].totalCases, "value2":json[9].totalDeaths, "value3":json[9].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"AM", "name":"아르메니아", "value":json[10].totalCases, "value2":json[10].totalDeaths, "value3":json[10].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"AU", "name":"호주", "value":json[13].totalCases, "value2":json[13].totalDeaths, "value3":json[13].totalRecovered, "color":"#8aabb0" },
  { "id":"AT", "name":"오스트리아", "value":json[14].totalCases, "value2":json[14].totalDeaths, "value3":json[14].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"AZ", "name":"아제르바이잔", "value":json[15].totalCases, "value2":json[15].totalDeaths, "value3":json[15].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"BH", "name":"바레인", "value":json[17].totalCases, "value2":json[17].totalDeaths, "value3":json[17].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"BD", "name":"방글라데시", "value":json[18].totalCases, "value2":json[18].totalDeaths, "value3":json[18].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"BY", "name":"벨라루스", "value":json[20].totalCases, "value2":json[20].totalDeaths, "value3":json[20].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"BE", "name":"벨기에", "value":json[21].totalCases, "value2":json[21].totalDeaths, "value3":json[21].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"BJ", "name":"베냉", "value":json[23].totalCases, "value2":json[23].totalDeaths, "value3":json[23].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"BT", "name":"부탄", "value":json[25].totalCases, "value2":json[25].totalDeaths, "value3":json[25].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"BO", "name":"볼리비아", "value":json[26].totalCases, "value2":json[26].totalDeaths, "value3":json[26].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"BA", "name":"보스니아 헤르체고비나", "value":json[27].totalCases, "value2":json[27].totalDeaths, "value3":json[27].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"BW", "name":"보츠와나", "value":json[28].totalCases, "value2":json[28].totalDeaths, "value3":json[28].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"BR", "name":"브라질", "value":json[29].totalCases, "value2":json[29].totalDeaths, "value3":json[29].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"BN", "name":"브루나이", "value":json[31].totalCases, "value2":json[31].totalDeaths, "value3":json[31].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"BG", "name":"불가리아", "value":json[32].totalCases, "value2":json[32].totalDeaths, "value3":json[32].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"BF", "name":"부르키나파소", "value":json[33].totalCases, "value2":json[33].totalDeaths, "value3":json[33].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"BI", "name":"부룬디", "value":json[34].totalCases, "value2":json[34].totalDeaths, "value3":json[34].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"CV", "name":"카보베르데", "value":json[35].totalCases, "value2":json[35].totalDeaths, "value3":json[35].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"KH", "name":"캄보디아", "value":json[36].totalCases, "value2":json[36].totalDeaths, "value3":json[36].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"CM", "name":"카메룬", "value":json[37].totalCases, "value2":json[37].totalDeaths, "value3":json[37].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"CA", "name":"캐나다", "value":json[38].totalCases, "value2":json[38].totalDeaths, "value3":json[38].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"CF", "name":"중앙아프리카공화국", "value":json[39].totalCases, "value2":json[39].totalDeaths, "value3":json[39].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"TD", "name":"차드", "value":json[42].totalCases, "value2":json[42].totalDeaths, "value3":json[42].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"CL", "name":"칠레", "value":json[44].totalCases, "value2":json[44].totalDeaths, "value3":json[44].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"CN", "name":"중국", "value":json[45].totalCases, "value2":json[45].totalDeaths, "value3":json[45].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"CO", "name":"콜롬비아", "value":json[46].totalCases, "value2":json[46].totalDeaths, "value3":json[46].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"KM", "name":"코모로", "value":json[47].totalCases, "value2":json[47].totalDeaths, "value3":json[47].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"CG", "name":"콩고", "value":json[48].totalCases, "value2":json[48].totalDeaths, "value3":json[48].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"CR", "name":"코스타리카", "value":json[49].totalCases, "value2":json[49].totalDeaths, "value3":json[49].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"HR", "name":"크로아티아", "value":json[50].totalCases, "value2":json[50].totalDeaths, "value3":json[50].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"CU", "name":"쿠바", "value":json[51].totalCases, "value2":json[51].totalDeaths, "value3":json[51].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"CY", "name":"키프로스", "value":json[53].totalCases, "value2":json[53].totalDeaths, "value3":json[53].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"CZ", "name":"체코", "value":json[54].totalCases, "value2":json[54].totalDeaths, "value3":json[54].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"DK", "name":"덴마크", "value":json[55].totalCases, "value2":json[55].totalDeaths, "value3":json[55].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"DJ", "name":"지부티", "value":json[57].totalCases, "value2":json[57].totalDeaths, "value3":json[57].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"DO", "name":"도미니카공화국", "value":json[59].totalCases, "value2":json[59].totalDeaths, "value3":json[59].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"CD", "name":"콩고민주공화국", "value":json[60].totalCases, "value2":json[60].totalDeaths, "value3":json[60].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"EC", "name":"에콰도르", "value":json[61].totalCases, "value2":json[61].totalDeaths, "value3":json[61].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"EG", "name":"이집트", "value":json[62].totalCases, "value2":json[62].totalDeaths, "value3":json[62].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"SV", "name":"엘살바도르", "value":json[63].totalCases, "value2":json[63].totalDeaths, "value3":json[63].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"GQ", "name":"적도기니", "value":json[64].totalCases, "value2":json[64].totalDeaths, "value3":json[64].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"ER", "name":"에리트레아", "value":json[65].totalCases, "value2":json[65].totalDeaths, "value3":json[65].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"EE", "name":"에스토니아", "value":json[66].totalCases, "value2":json[66].totalDeaths, "value3":json[66].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"SZ", "name":"에스와티니", "value":json[67].totalCases, "value2":json[67].totalDeaths, "value3":json[67].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"ET", "name":"에티오피아", "value":json[68].totalCases, "value2":json[68].totalDeaths, "value3":json[68].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"FJ", "name":"피지", "value":json[72].totalCases, "value2":json[72].totalDeaths, "value3":json[72].totalRecovered, "color":"#8aabb0" },
  { "id":"FI", "name":"핀란드", "value":json[73].totalCases, "value2":json[73].totalDeaths, "value3":json[73].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"FR", "name":"프랑스", "value":json[74].totalCases, "value2":json[74].totalDeaths, "value3":json[74].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"GA", "name":"가봉", "value":json[77].totalCases, "value2":json[77].totalDeaths, "value3":json[77].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GM", "name":"감비아", "value":json[78].totalCases, "value2":json[78].totalDeaths, "value3":json[78].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GE", "name":"조지아", "value":json[79].totalCases, "value2":json[79].totalDeaths, "value3":json[79].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"DE", "name":"독일", "value":json[80].totalCases, "value2":json[80].totalDeaths, "value3":json[80].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"GH", "name":"가나", "value":json[81].totalCases, "value2":json[81].totalDeaths, "value3":json[81].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GR", "name":"그리스", "value":json[83].totalCases, "value2":json[83].totalDeaths, "value3":json[83].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"GT", "name":"과테말라", "value":json[87].totalCases, "value2":json[87].totalDeaths, "value3":json[87].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"GN", "name":"기니", "value":json[88].totalCases, "value2":json[88].totalDeaths, "value3":json[88].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GW", "name":"기니비사우", "value":json[89].totalCases, "value2":json[89].totalDeaths, "value3":json[89].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GY", "name":"가이아나", "value":json[90].totalCases, "value2":json[90].totalDeaths, "value3":json[90].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"HT", "name":"아이티", "value":json[91].totalCases, "value2":json[91].totalDeaths, "value3":json[91].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"HN", "name":"온두라스", "value":json[92].totalCases, "value2":json[92].totalDeaths, "value3":json[92].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"HK", "name":"홍콩", "value":json[93].totalCases, "value2":json[93].totalDeaths, "value3":json[93].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"HU", "name":"헝가리", "value":json[94].totalCases, "value2":json[94].totalDeaths, "value3":json[94].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"IS", "name":"아이슬란드", "value":json[95].totalCases, "value2":json[95].totalDeaths, "value3":json[95].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"IN", "name":"인도", "value":json[96].totalCases, "value2":json[96].totalDeaths, "value3":json[96].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"ID", "name":"인도네시아", "value":json[97].totalCases, "value2":json[97].totalDeaths, "value3":json[97].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"IR", "name":"이란", "value":json[98].totalCases, "value2":json[98].totalDeaths, "value3":json[98].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"IQ", "name":"이라크", "value":json[99].totalCases, "value2":json[99].totalDeaths, "value3":json[99].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"IE", "name":"아일랜드", "value":json[100].totalCases, "value2":json[100].totalDeaths, "value3":json[100].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"IL", "name":"이스라엘", "value":json[102].totalCases, "value2":json[102].totalDeaths, "value3":json[102].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"IT", "name":"이탈리아", "value":json[103].totalCases, "value2":json[103].totalDeaths, "value3":json[103].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"CI", "name":"코트디부아르", "value":json[104].totalCases, "value2":json[104].totalDeaths, "value3":json[104].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"JM", "name":"자메이카", "value":json[105].totalCases, "value2":json[105].totalDeaths, "value3":json[105].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"JP", "name":"일본", "value":json[106].totalCases, "value2":json[106].totalDeaths, "value3":json[106].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"JO", "name":"요르단", "value":json[107].totalCases, "value2":json[107].totalDeaths, "value3":json[107].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"KZ", "name":"카자흐스탄", "value":json[108].totalCases, "value2":json[108].totalDeaths, "value3":json[108].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"KE", "name":"케냐", "value":json[109].totalCases, "value2":json[109].totalDeaths, "value3":json[109].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"KW", "name":"쿠웨이트", "value":json[110].totalCases, "value2":json[110].totalDeaths, "value3":json[110].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"KG", "name":"키르기스스탄", "value":json[111].totalCases, "value2":json[111].totalDeaths, "value3":json[111].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"LA", "name":"라오스", "value":json[112].totalCases, "value2":json[112].totalDeaths, "value3":json[112].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"LV", "name":"라트비아", "value":json[113].totalCases, "value2":json[113].totalDeaths, "value3":json[113].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"LB", "name":"레바논", "value":json[114].totalCases, "value2":json[114].totalDeaths, "value3":json[114].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"LS", "name":"레소토", "value":json[115].totalCases, "value2":json[115].totalDeaths, "value3":json[115].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"LR", "name":"라이베리아", "value":json[116].totalCases, "value2":json[116].totalDeaths, "value3":json[116].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"LY", "name":"리비아", "value":json[117].totalCases, "value2":json[117].totalDeaths, "value3":json[117].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"LT", "name":"리투아니아", "value":json[119].totalCases, "value2":json[119].totalDeaths, "value3":json[119].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"LU", "name":"룩셈부르크", "value":json[120].totalCases, "value2":json[120].totalDeaths, "value3":json[120].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"MG", "name":"마다가스카르", "value":json[122].totalCases, "value2":json[122].totalDeaths, "value3":json[122].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MW", "name":"말라위", "value":json[123].totalCases, "value2":json[123].totalDeaths, "value3":json[123].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MY", "name":"말레이시아", "value":json[124].totalCases, "value2":json[124].totalDeaths, "value3":json[124].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"ML", "name":"말리", "value":json[126].totalCases, "value2":json[126].totalDeaths, "value3":json[126].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MR", "name":"모리타니", "value":json[130].totalCases, "value2":json[130].totalDeaths, "value3":json[130].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MU", "name":"모리셔스", "value":json[131].totalCases, "value2":json[131].totalDeaths, "value3":json[131].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MX", "name":"멕시코", "value":json[133].totalCases, "value2":json[133].totalDeaths, "value3":json[133].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"MD", "name":"몰도바", "value":json[135].totalCases, "value2":json[135].totalDeaths, "value3":json[135].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"MN", "name":"몽골", "value":json[137].totalCases, "value2":json[137].totalDeaths, "value3":json[137].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"ME", "name":"몬테네그로", "value":json[138].totalCases, "value2":json[138].totalDeaths, "value3":json[138].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"MA", "name":"모로코", "value":json[140].totalCases, "value2":json[140].totalDeaths, "value3":json[140].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MZ", "name":"모잠비크", "value":json[141].totalCases, "value2":json[141].totalDeaths, "value3":json[141].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MM", "name":"미얀마", "value":json[143].totalCases, "value2":json[143].totalDeaths, "value3":json[143].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"NA", "name":"나미비아", "value":json[144].totalCases, "value2":json[144].totalDeaths, "value3":json[144].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"NP", "name":"네팔", "value":json[145].totalCases, "value2":json[145].totalDeaths, "value3":json[145].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"NL", "name":"네덜란드", "value":json[146].totalCases, "value2":json[146].totalDeaths, "value3":json[146].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"NZ", "name":"뉴질랜드", "value":json[148].totalCases, "value2":json[148].totalDeaths, "value3":json[148].totalRecovered, "color":"#8aabb0" },
  { "id":"NI", "name":"니카라과", "value":json[149].totalCases, "value2":json[149].totalDeaths, "value3":json[149].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"NE", "name":"니제르", "value":json[150].totalCases, "value2":json[150].totalDeaths, "value3":json[150].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"NG", "name":"나이지리아", "value":json[151].totalCases, "value2":json[151].totalDeaths, "value3":json[151].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"MK", "name":"마케도니아", "value":json[153].totalCases, "value2":json[153].totalDeaths, "value3":json[153].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"NO", "name":"노르웨이", "value":json[154].totalCases, "value2":json[154].totalDeaths, "value3":json[154].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"OM", "name":"오만", "value":json[156].totalCases, "value2":json[156].totalDeaths, "value3":json[156].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"PK", "name":"파키스탄", "value":json[157].totalCases, "value2":json[157].totalDeaths, "value3":json[157].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"PS", "name":"팔레스타인", "value":json[159].totalCases, "value2":json[159].totalDeaths, "value3":json[159].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"PA", "name":"파나마", "value":json[160].totalCases, "value2":json[160].totalDeaths, "value3":json[160].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"PG", "name":"파푸아뉴기니", "value":json[161].totalCases, "value2":json[161].totalDeaths, "value3":json[161].totalRecovered, "color":"#8aabb0" },
  { "id":"PY", "name":"파라과이", "value":json[162].totalCases, "value2":json[162].totalDeaths, "value3":json[162].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"PE", "name":"페루", "value":json[163].totalCases, "value2":json[163].totalDeaths, "value3":json[163].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"PH", "name":"필리핀", "value":json[164].totalCases, "value2":json[164].totalDeaths, "value3":json[164].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"PL", "name":"폴란드", "value":json[165].totalCases, "value2":json[165].totalDeaths, "value3":json[165].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"PT", "name":"포르투갈", "value":json[166].totalCases, "value2":json[166].totalDeaths, "value3":json[166].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"QA", "name":"카타르", "value":json[167].totalCases, "value2":json[167].totalDeaths, "value3":json[167].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"RO", "name":"루마니아", "value":json[168].totalCases, "value2":json[168].totalDeaths, "value3":json[168].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"RU", "name":"러시아", "value":json[169].totalCases, "value2":json[169].totalDeaths, "value3":json[169].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"RW", "name":"르완다", "value":json[170].totalCases, "value2":json[170].totalDeaths, "value3":json[170].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"KR", "name":"대한민국", "value": json[172].totalCases, "value2":json[172].totalDeaths, "value3":json[172].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"SA", "name":"사우디아라비아", "value":json[181].totalCases, "value2":json[181].totalDeaths, "value3":json[181].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"SN", "name":"세네갈", "value":json[182].totalCases, "value2":json[182].totalDeaths, "value3":json[182].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"RS", "name":"세르비아", "value":json[183].totalCases, "value2":json[183].totalDeaths, "value3":json[183].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"SL", "name":"시에라리온", "value":json[185].totalCases, "value2":json[185].totalDeaths, "value3":json[185].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"SG", "name":"싱가포르", "value":json[186].totalCases, "value2":json[186].totalDeaths, "value3":json[186].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"SK", "name":"슬로바키아", "value":json[188].totalCases, "value2":json[188].totalDeaths, "value3":json[188].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"SI", "name":"슬로베니아", "value":json[189].totalCases, "value2":json[189].totalDeaths, "value3":json[189].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"SB", "name":"솔로몬제도", "value":json[190].totalCases, "value2":json[190].totalDeaths, "value3":json[190].totalRecovered, "color":"#8aabb0" },
  { "id":"SO", "name":"소말리아", "value":json[191].totalCases, "value2":json[191].totalDeaths, "value3":json[191].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"ZA", "name":"남아프리카공화국", "value":json[192].totalCases, "value2":json[192].totalDeaths, "value3":json[192].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"ES", "name":"스페인", "value":json[195].totalCases, "value2":json[195].totalDeaths, "value3":json[195].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"LK", "name":"스리랑카", "value":json[196].totalCases, "value2":json[196].totalDeaths, "value3":json[196].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"SD", "name":"수단", "value":json[199].totalCases, "value2":json[199].totalDeaths, "value3":json[199].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"SR", "name":"수리남", "value":json[200].totalCases, "value2":json[200].totalDeaths, "value3":json[200].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"SE", "name":"스웨덴", "value":json[201].totalCases, "value2":json[201].totalDeaths, "value3":json[201].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"CH", "name":"스위스", "value":json[202].totalCases, "value2":json[202].totalDeaths, "value3":json[202].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"SY", "name":"시리아", "value":json[203].totalCases, "value2":json[203].totalDeaths, "value3":json[203].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"TW", "name":"대만", "value":json[204].totalCases, "value2":json[204].totalDeaths, "value3":json[204].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"TJ", "name":"타지키스탄", "value":json[205].totalCases, "value2":json[205].totalDeaths, "value3":json[205].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"TZ", "name":"탄자니아", "value":json[206].totalCases, "value2":json[206].totalDeaths, "value3":json[206].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"TH", "name":"태국", "value":json[207].totalCases, "value2":json[207].totalDeaths, "value3":json[207].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"TG", "name":"토고", "value":json[209].totalCases, "value2":json[209].totalDeaths, "value3":json[209].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"TT", "name":"트리니다드토바고", "value":json[210].totalCases, "value2":json[210].totalDeaths, "value3":json[210].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"TN", "name":"튀니지", "value":json[211].totalCases, "value2":json[211].totalDeaths, "value3":json[211].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"TR", "name":"터키", "value":json[212].totalCases, "value2":json[212].totalDeaths, "value3":json[212].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"AE", "name":"아랍에미리트", "value":json[214].totalCases,"value2":json[214].totalDeaths, "value3":json[214].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"UG", "name":"우간다", "value":json[215].totalCases, "value2":json[215].totalDeaths, "value3":json[215].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"GB", "name":"영국", "value":json[216].totalCases, "value2":json[216].totalDeaths, "value3":json[216].totalRecovered, "color":chart.colors.getIndex(1) },
  { "id":"UA", "name":"우크라이나", "value":json[217].totalCases, "value2":json[217].totalDeaths, "value3":json[217].totalRecovered, "color":chart.colors.getIndex(1) }, 
  { "id":"UY", "name":"우루과이", "value":json[218].totalCases, "value2":json[218].totalDeaths, "value3":json[218].totalRecovered, "color":chart.colors.getIndex(3) },
  { "id":"US", "name":"미국", "value":json[219].totalCases, "value2":json[219].totalDeaths, "value3":json[219].totalRecovered, "color":chart.colors.getIndex(4) },
  { "id":"UZ", "name":"우즈베키스탄", "value":json[220].totalCases, "value2":json[220].totalDeaths, "value3":json[220].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"VE", "name":"베네수엘라", "value":json[223].totalCases, "value2":json[223].totalDeaths, "value3":json[223].totalRecovered, "color":chart.colors.getIndex(3) }, 
  { "id":"VN", "name":"베트남", "value":json[224].totalCases, "value2":json[224].totalDeaths, "value3":json[224].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"YE", "name":"예멘", "value":json[228].totalCases, "value2":json[228].totalDeaths, "value3":json[228].totalRecovered, "color": chart.colors.getIndex(0) },
  { "id":"ZM", "name":"잠비아", "value":json[229].totalCases, "value2":json[229].totalDeaths, "value3":json[229].totalRecovered, "color":chart.colors.getIndex(2) },
  { "id":"ZW", "name":"짐바브웨", "value":json[230].totalCases, "value2":json[230].totalDeaths, "value3":json[230].totalRecovered, "color":chart.colors.getIndex(2) }
				];
		
// Set map definition
chart.geodata = am4geodata_worldLow;

// Set projection
chart.projection = new am4maps.projections.Miller();

// Create map polygon series
var polygonSeries = chart.series.push(new am4maps.MapPolygonSeries());
polygonSeries.exclude = ["AQ"];
polygonSeries.useGeodata = true;
polygonSeries.nonScalingStroke = true;
polygonSeries.strokeWidth = 0.5;
polygonSeries.calculateVisualCenter = true;

var imageSeries = chart.series.push(new am4maps.MapImageSeries());
imageSeries.data = mapData;
imageSeries.dataFields.value = "value";

var imageTemplate = imageSeries.mapImages.template;
imageTemplate.nonScaling = true


var circle = imageTemplate.createChild(am4core.Circle);
circle.fillOpacity = 0.7;
circle.propertyFields.fill = "color";
circle.tooltipText = "[bold font-size: 20]{name}[/]\n 확진자 : [bold]{value}명[/]\n 사망자 : [bold]{value2}명[/]\n 완치자 : [bold]{value3}명[/]";




imageSeries.heatRules.push({
  "target": circle,
  "property": "radius",
  "min": 4,
  "max": 30,
  "dataField": "value"
})

imageTemplate.adapter.add("latitude", function(latitude, target) {
  var polygon = polygonSeries.getPolygonById(target.dataItem.dataContext.id);
  if(polygon){
    return polygon.visualLatitude;
   }
   return latitude;
})

imageTemplate.adapter.add("longitude", function(longitude, target) {
  var polygon = polygonSeries.getPolygonById(target.dataItem.dataContext.id);
  if(polygon){
    return polygon.visualLongitude;
   }
   return longitude;
   
   
})



}); // end am4core.ready()

		}
	})

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
