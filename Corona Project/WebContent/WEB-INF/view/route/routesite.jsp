<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="static poly.util.CmmUtil.nvl"%>
<%@ page import="poly.dto.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="poly.util.DateUtil"%>

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
<title>확진자이동경로</title>


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
							<li class="nav-item"><a class="nav-link"
								href="/foreign/foreignsite.do">국외발생현황</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/gubun/gubunsite.do">연령별·성별현황</a></li>
							<li class="nav-item active"><a class="nav-link" href="/route/routesite.do">확진자이동경로</a></li>

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
							<li class="nav-item"><a class="nav-link"
								href="/foreign/foreignsite.do">국외발생현황</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/gubun/gubunsite.do">연령별·성별현황</a></li>
							<li class="nav-item active"><a class="nav-link" href="/route/routesite.do">확진자이동경로</a></li>

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
									<b>확진자이동경로</b>
								</h1>
							</div>
							<form id="route_select" method="post" name="a">
								<div class="card-body">
									<div class="row" style="justify-content: flex-end; margin-left: 0px;" align="center">
										<div class="col-md-3">
											<div class="form-group">
												<select class="form-control" name="city" id="reg_city"
													value="시도별 선택" onchange="cityclick(this)">
													<option value="">시도별 선택</option>
													<option value="seoul">서울특별시</option>
													<option value="busan">부산광역시</option>
													<option value="daegu">대구광역시</option>
													<option value="incheon">인천광역시</option>
													<option value="gwangju">광주광역시</option>
													<option value="daejeon">대전광역시</option>
													<option value="ulsan">울산광역시</option>
													<option value="gyeonggi">경기도</option>
													<option value="gangwon">강원도</option>
													<option value="northcc">충청북도</option>
													<option value="southcc">충청남도</option>
													<option value="northjl">전라북도</option>
													<option value="southjl">전라남도</option>
													<option value="northgs">경상북도</option>
													<option value="southgs">경상남도</option>
													<option value="jeju">제주도</option>
													<option value="sejong">세종특별자치시</option>

												</select>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<select class="form-control" name="choice" id="reg_choice"
													value="시군구별 선택">
													<option value="">시군구별 선택</option>
												</select>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group">
												<button class="btn-dark btn-simple caret" type="button"
												onclick="panTo()">조회</button>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div id="map" style="width: 100%; height: 50vh;"></div>
									</div>
										
								</div>
							</form>
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

<!-- 선택 -->
<script>
	function cityclick(a) {
		var city = ["시군구별 선택"];
		var seoul = ["시군구별 선택", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구",
			"서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
		var busan = ["시군구별 선택", "강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구" ,"북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"];
		var daegu = ["시군구별 선택", "남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"];
		var incheon = ["시군구별 선택", "강화군", "계양구", "남동구", "동구", "미추홀구", "부평구", "서구", "연수구", "옹진군", "중구"];
		var gwangju =["시군구별 선택", "광산구", "남구", "동구", "북구", "서구"];
		var daejeon = ["시군구별 선택", "대덕구", "동구", "서구", "유성구", "중구"];
		var ulsan = ["시군구별 선택", "남구", "동구", "북구", "울주군", "중구"];
		var gyeonggi = ["시군구별 선택", "가평군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시",
			"부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "양평군", "여주시", "연천군", "오산시",
			"용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시"];
		var gangwon = ["시군구별 선택", "강릉시", "고성군", "동해시", "삼척시", "속초시", "양구군", "양양군", "영월군", "원주시", "인제군", "정선군", "철원군", "춘천시", "태백시", "평창군", "화천군", "홍천군", "횡성군"];
		var northcc = ["시군구별 선택", "괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "제천시", "증평군", "진천군", "청주시", "충주시"];
		var southcc = ["시군구별 선택", "계룡시", "공주시", "금산군", "논산시", "당진시", "보령시", "부여군", "서산시", "서천군", "아산시", "예산군", "천안시", "청양군", "태안군", "홍성군"];
		var northjl = ["시군구별 선택", "고창군", "군산시", "김제시", "남원시", "무주군", "부안군", "순창군", "완주군", "익산시", "임실군", "장수군", "전주시", "정읍시", "진안군"];
		var southjl = ["시군구별 선택", "강진군", "고흥군", "곡성군", "광양시", "구례군", "나주시", "담양군", "목포시", "무안군", "보성군", "순천시", "신안군", "여수시", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"];
		var northgs = ["시군구별 선택", "경산시", "경주시", "고령군", "구미시", "군위군", "김천시", "문경시", "봉화군", "상주시", "성주군", "안동시", "영덕군", "영양군", "영주시", "영천시", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군", "포항시"];
		var southgs = ["시군구별 선택", "거제시", "거창군", "고성군", "김해시", "남해군", "밀양시", "사천시", "산청군", "양산시", "의령군", "진주시", "창녕군", "창원시", "통영시", "하동군", "함안군", "함양군", "합천군"];
		var jeju = ["시군구별 선택", "제주시", "서귀포시"];
		var sejong = ["세종시"];
		var target = document.getElementById("reg_choice");
		
		if(a.value == "seoul") var d = seoul;
		else if(a.value == "busan") var d = busan;
		else if(a.value == "daegu") var d = daegu;
		else if(a.value == "incheon") var d = incheon;
		else if(a.value == "gwangju") var d = gwangju;
		else if(a.value == "daejeon") var d = daejeon;
		else if(a.value == "ulsan") var d = ulsan;
		else if(a.value == "gyeonggi") var d = gyeonggi;
		else if(a.value == "gangwon") var d = gangwon;
		else if(a.value == "northcc") var d = northcc;
		else if(a.value == "southcc") var d = southcc;
		else if(a.value == "northjl") var d = northjl;
		else if(a.value == "southjl") var d = southjl;
		else if(a.value == "northgs") var d = northgs;
		else if(a.value == "southgs") var d = southgs;
		else if(a.value == "jeju") var d = jeju;
		else if(a.value == "sejong") var d = sejong;
		else if(a.value == "") var d = city;
		
		target.options.length = 0;
		
		for (x in d) {
			var opt = document.createElement("option");
			opt.value = d[x];
			opt.innerHTML = d[x];
			target.appendChild(opt);
		}
	}
</script>



<!-- 지도 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('854494bceb21e36dd44a3719379454cb');

        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());
    </script>

<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=854494bceb21e36dd44a3719379454cb&libraries=clusterer"></script>

<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new window.kakao.maps.LatLng(37.576026, 126.976907), // 지도의 중심좌표
		level : 8, // 지도의 확대 레벨
		mapTypeId : window.kakao.maps.MapTypeId.ROADMAP
	// 지도종류
	};

	// 지도를 생성한다 
	var map = new window.kakao.maps.Map(mapContainer, mapOption);
	
	

	// 마커 클러스터러를 생성합니다 
	var clusterer = new window.kakao.maps.MarkerClusterer({
		map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
		averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
		minLevel : 10
	// 클러스터 할 최소 지도 레벨 
	});

	// 마커 표시용 배열
	var data = [
		// 코로나 공식사이트
			[ 37.630294, 127.023604,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.30~6.10</b><br><b>주소 : 강북구 솔매로45길 99 </b> <br>상호명 : 참빛장로교회  </div>' ],
		// 서울 경로
			// 강남
			// 강동
			[ 37.540510, 127.137577,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~15</b><br><b>주소 : 성안로 200</b> <br>상호명 : 천동초등학교</div>' ],
			[ 37.555189, 127.168684,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~5</b><br><b>주소 : 상일로11길 110</b> <br>상호명 : 고현초등학교</div>' ],
			[ 37.528828, 127.123028,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~5</b><br><b>주소 : 성내로6길 15</b> <br>상호명 : 인애가한방병원</div>' ],
			// 강북
			// 강서
			// 관악
			// 광진
			[ 37.537507, 127.086356,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 아차산로 395</b> <br>상호명 : 당구매니아</div>' ],
			[ 37.535701, 127.082991,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6~9</b><br><b>주소 : 자양로15길 9, 3층</b> <br>상호명 : 빈사무실</div>' ],				
			[ 37.539123, 127.083449,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6~7</b><br><b>주소 : 자양로 126, 성지하이츠 지하1층</b> <br>상호명 : 황지사우나(여탕)</div>' ],
			[ 37.537586, 127.084018,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 자양로18길 9</b> <br>상호명 : 월드 노래방</div>' ],
			[ 37.545517, 127.086049,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 자양로 206, 지층</b> <br>상호명 : 쿨라이브</div>' ],
			[ 37.566056, 127.078198,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~6</b><br><b>주소 : 동일로72길 9</b> <br>상호명 : 이루자식당</div>' ],
			// 구로
			// 금천
			// 노원
			// 도봉
			[ 37.657855, 127.038767,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.16</b><br><b>주소 : 쌍문동</b> <br>상호명 : 도봉구 보건소</div>' ],
			[ 37.654115, 127.038410,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 창동</b> <br>상호명 : 도봉구민회관 광장</div>' ],
			// 동대문
			// 동작
			// 마포
			// 서대문
			// 서초
			[ 37.491980, 127.010053,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10~14</b><br><b>주소 : 서초대로46길 9</b> <br>상호명 : 놀러와노래방</div>' ],
			[ 37.492972, 127.013423,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 서초중앙로 125</b> <br>상호명 : 로이어즈타워</div>' ],				
			[ 37.485659, 127.032963,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.28~6.4</b><br><b>주소 : 강남대로 251 해동빌딩1층</b> <br>상호명 : SC제일은행 양재동지점</div>' ],
			[ 37.480709, 126.983075,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.1~4</b><br><b>주소 : 방배천로4안길 61-3 메종시에뜨 202호</b> <br>상호명 : 금파다경 건강관리</div>' ],
			// 성동
			[ 37.543555, 127.015583,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13~17</b><br><b>주소 : 한림말3길30 (옥수동)</b> <br>상호명 : VIP노래방</div>' ],
			[ 37.541866, 127.049659,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.16</b><br><b>주소 : 뚝섬로3길 18 (성수1가제1동)</b> <br>상호명 : 성수1가1동주민센터</div>' ],
			[ 37.540623, 127.011071,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 독서당로39길 48 （옥수동)</b> <br>상호명 : 이상진돈까스</div>' ],
			[ 37.555620, 127.037717,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.11</b><br><b>주소 : 고산자로2길 67 (행당제1동)</b> <br>상호명 : 필라테스 리버&PT</div>' ],
			[ 37.553639, 127.019589,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 금호산길 62 （금호2.3가동)</b> <br>상호명 : 해마로숯불바베큐</div>' ],
			[ 37.544528, 127.061364,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 아차산로13길 16 (성수2가제3동）</b> <br>상호명 : 서울맛집</div>' ],
			[ 37.561957, 127.057626,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 자동차시장길 7 (용답동)</b> <br>상호명 : 극동타이어</div>' ],
			[ 37.564803, 127.034269,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 왕십리로24길 27 (왕십리도선동)</b> <br>상호명 : 만나떡볶이</div>' ],
			[ 37.543587, 127.064145,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 아차산로17길 14-1（성수2가제3동）</b> <br>상호명 : 뽀식이네부페식당</div>' ],
			[ 37.541607, 127.057617,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8</b><br><b>주소 : 연무장길 76 (성수2가제1동)</b> <br>상호명 : 성동광진 고용복지플러스센터</div>' ],
			[ 37.558068, 127.030985,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8</b><br><b>주소 : 행당로 109 (행당제1동)</b> <br>상호명 : 화미</div>' ],
			[ 37.546800, 127.013309,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 매봉길 50 (옥수동)</b> <br>상호명 : 이지원필라테스 옥수점</div>' ],
			[ 37.559373, 127.033319,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 행당로17길 11 (행당제1동)</b> <br>상호명 : 꼬랑치킨</div>' ],
			[ 37.546078, 127.047002,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 왕십리로8길 21 (성수1가제2동)</b> <br>상호명 : 까까를로</div>' ],
			[ 37.538839, 127.054195,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~5</b><br><b>주소 : 뚝섬로 392 (성수2가제1동)</b> <br>상호명 : 어촌</div>' ],
			[ 37.539494, 127.054826,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4</b><br><b>주소 : 뚝섬로 385-40 (성수2가제1동)</b> <br>상호명 : 명국수</div>' ],
			[ 37.543957, 127.055084,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.3</b><br><b>주소 : 연무장7길 16 (성수2가제3동)</b> <br>상호명 : 한서칼국수</div>' ],
			[ 37.543765, 127.055681,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.3</b><br><b>주소 : 연무장5가길 25（성수2가제3동)</b> <br>상호명 : 밥플러스 성수역점</div>' ],
			[ 37.538406, 127.055539,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.3</b><br><b>주소 : 뚝섬로 408-7 （성수2가제1동)</b> <br>상호명 : 생태한마리</div>' ],
			// 성북
			// 송파
			[ 37.494873, 127.118898,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 중대로9길 21 센터빌딩 2층</b> <br>상호명 : 백마노래연습장</div>' ],
			[ 37.493413, 127.126021,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.1~9</b><br><b>주소 : 송이로27길 9</b> <br>상호명 : 대중베스트이용원</div>' ],
			[ 37.514942, 127.108731,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4</b><br><b>주소 : 오금로 11길 11-8, 5층 501~502호</b> <br>상호명 : OPS방이스타점</div>' ],
			// 양천
			// 영등포
			// 용산
			[ 37.534498, 126.958311,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.22~6.7</b><br><b>주소 : 용산구 원효로41길 15</b> <br>상호명 : 행운노래방</div>' ],
			// 은평
			// 종로
			// 중구
			// 중랑
			[ 37.580192, 127.087467,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 사가정로 52길 11</b> <br>상호명 : 미스터신냉면</div>' ],
			[ 37.578479, 127.085763,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 면목로 285</b> <br>상호명 : 소우가 면목점</div>' ],
			
		
		// 부산 경로
		[ 35.165760, 128.988233,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13~15</b><br><b>주소 : 사상구 사상로 295(괘법동)</b> <br>상호명 : 스마일베이커리</div>' ],
		[ 35.220536, 129.083482,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 동래구 온천장로107번길 16</b> <br>상호명 : 태능연탄갈비</div>' ],
		[ 35.230463, 129.084298,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 금정구 금강로 247-10</b> <br>상호명 : 톤쇼우</div>' ],
		[ 35.210200, 129.035149,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 덕천로281번길 12, 2,4층</b> <br>상호명 : 다비드짐</div>' ],
		[ 35.161072, 129.162966,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 해운대구 중동 해운대해변로 295</b> <br>상호명 : 유투(U2)</div>' ],

		[ 35.220446, 129.081620,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 동래구 금강공원로26번길 32</b> <br>상호명 : 녹천탕(여탕)</div>' ],
		[ 35.103672, 129.020183,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 서구 구덕로 214</b> <br>상호명 : 까페기글링</div>' ],
		[ 35.225591, 129.083669,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4</b><br><b>주소 : 금정구 식물원로44번길 12</b> <br>상호명 : 낙원식당(엑스포코아 내)</div>' ],
		[ 35.231275, 129.087566,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4</b><br><b>주소 : 금정구 부산대학로 34-2(장전동)</b> <br>상호명 : 맥가이버 손칼국수</div>' ],
		
		
		
		// 대구 경로
		[ 35.814421, 128.616673,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~12</b><br><b>주소 : 수성구 파동 153, 시장 안</b> <br>상호명 : 우리이용소</div>' ],
		
		[ 35.860628, 128.506303,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 달서구 선원로 133</b> <br>상호명 : 성서국민체육센터 농구장</div>' ],
		[ 35.829082, 128.710496,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 수성구 유니버시아드로 365</b> <br>상호명 : 모레아장례예식장</div>' ],
		[ 35.867034, 128.633438,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.29~6.5</b><br><b>주소 : 수성구 범어로 199</b> <br>상호명 : 아침에잡은소 범어점</div>' ],
		[ 35.877808, 128.677391,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.20~6.5</b><br><b>주소 : 동구 동촌로 343-3</b> <br>상호명 : 아침에잡은소</div>' ],

		[ 35.869515, 128.591310,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.28~6.4</b><br><b>주소 : 중구 중앙대로 81길 43</b> <br>상호명 : 부림왕소금구이</div>' ],
		[ 35.874376, 128.596383,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.31~6.4</b><br><b>주소 : 중구 태평로 172-6</b> <br>상호명 : 비손골드</div>' ],
		
		// 인천 경로
		[ 37.586826, 126.710109,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7~16</b><br><b>주소 : 서구 당하동 산12-1</b> <br>상호명 : 파라곤2차 건설현장</div>' ],
		[ 37.460686, 126.709715,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.15</b><br><b>주소 : 남동구 석산로 159, 4층(간석 2동)</b> <br>상호명 : 킹덤PC</div>' ],
		[ 37.461198, 126.733722,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 남동구 만수서로 62(만수 2동)</b> <br>상호명 : 메가커피 만수향촌점</div>' ],
		// 광주 경로
		[ 35.229326, 126.861878,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.16</b><br><b>주소 : 북구 첨단과기로 339</b> <br>상호명 : 광주과학기술진흥원 1층 구내식당</div>' ],
		[ 35.115840, 126.857288,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~15</b><br><b>주소 : 서구 매월2로 16</b> <br>상호명 : 서부농수산물도매시장 채소동</div>' ],
		[ 35.167125, 126.883175,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.12</b><br><b>주소 : 서구 죽봉대로 153</b> <br>상호명 : 위더스 웨딩홀 4층 뷔페식당(아델라홀)</div>' ],
		[ 35.175168, 126.914904,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8~11</b><br><b>주소 : 북구 호동로 19-2</b> <br>상호명 : 청년다방 전대점</div>' ],
		[ 35.154331, 126.911642,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 동구 독립로 264번길 4-1</b> <br>상호명 : 김밥OK</div>' ],
		[ 35.179301, 126.894814,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.1~7</b><br><b>주소 : 북구 설죽로217번길 26-1</b> <br>상호명 : 노블레스 바</div>' ],
		[ 35.147375, 126.838121,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 서구 상무누리로 55</b> <br>상호명 : 홀리데이인광주 호텔 별관 웨딩시대 3층 뷔페식당</div>' ],

		[ 35.171414, 126.809052,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~6</b><br><b>주소 : 광산구 사암로 303, 2층</b> <br>상호명 : 스타일 PC</div>' ],
		[ 35.176493, 126.802220,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.23~6.4</b><br><b>주소 : 광산구 용아로 379번길 39</b> <br>상호명 : 세븐스타코인노래연습장 하남2지구점</div>' ],
		
		
		// 대전 경로
		[ 36.327166, 127.395703,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.12~15</b><br><b>주소 : 중구 태평로 120(태평동)</b> <br>상호명 : 복수한우날고기</div>' ],
		[ 36.355224, 127.377238,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 서구 둔산로 31번길 77(둔산동)</b> <br>상호명 : 사리원 본점</div>' ],
		[ 36.357112, 127.433873,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 동구 한밭대로 1230-7(용전동)</b> <br>상호명 : 검은커피로스터스</div>' ],
		[ 36.325404, 127.379846,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~12</b><br><b>주소 : 서구 변동로 59(변동)</b> <br>상호명 : 토종골</div>' ],
		[ 36.363802, 127.438960,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.11</b><br><b>주소 : 대덕구 송촌북로4번길 41-19(송촌동)</b> <br>상호명 : 한박사대패삼겹살</div>' ],
		[ 36.365478, 127.438815,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~11</b><br><b>주소 : 대덕구 계족산로 81번길 60(송촌동)</b> <br>상호명 : 보스톤영수전문학원(강남신수학학원)</div>' ],

		[ 36.300141, 127.319686,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.11</b><br><b>주소 : 서구 관저로 3-8(관저동)</b> <br>상호명 : 명성식당</div>' ],
		[ 36.319713, 127.394098,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~10</b><br><b>주소 : 중구 태평로 26번길33(유천동)</b> <br>상호명 : 썸노래연습장</div>' ],
		[ 36.399958, 127.405674,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7~10</b><br><b>주소 : 유성구 엑스포로 488(전민동)</b> <br>상호명 : 국수사랑(엑스포코아 내)</div>' ],
		[ 36.329368, 127.380469,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10~11</b><br><b>주소 : 서구 동서대로 1077(내동)</b> <br>상호명 : 파티노래연습장</div>' ],
		[ 36.335599, 127.388070,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 서구 괴정로 147(용문동)</b> <br>상호명 : 미샵노래연습장</div>' ],

		[ 36.319626, 127.393901,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 중구 계백로 1713-23(오류동)</b> <br>상호명 : 필노래연습장(오류동)</div>' ],
		[ 36.325454, 127.392246,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 중구 동서대로 1194번길 19(태평동)</b> <br>상호명 : 정가네</div>' ],
		[ 36.320665, 127.398374,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 중구 유천로 86번길 29(유천동)</b> <br>상호명 : 엔돌핀노래연습장</div>' ],
		[ 36.329086, 127.378956,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 서구 변동로 78(변동, 지하)</b> <br>상호명 : 갈채노래연습장</div>' ],
		[ 36.325287, 127.399020,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 중구 동서대로1250번길 30(태평동)</b> <br>상호명 : CF노래방(씨에프노래연습장)</div>' ],

		[ 36.358294, 127.381743,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7</b><br><b>주소 : 서구 한밭대로 745(둔산동)</b> <br>상호명 : 신협중앙회 대전충남지역본부(5, 6, 9~11층)</div>' ],
		
		// 울산 경로
		[ 35.628234, 129.358025,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.1~15</b><br><b>주소 : 북구 동대로 56</b> <br>상호명 : 북구 포시즌유황사우나</div>' ],
		[ 35.530926, 129.327296,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.12~14</b><br><b>주소 : 남구 도산로119번길 7</b> <br>상호명 : 남구 대영사우나(여탕)</div>' ],
		[ 35.525384, 129.331100,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.11~14</b><br><b>주소 : 남구 번영로 4번길 8</b> <br>상호명 : 남구 일월탕</div>' ],
		[ 35.528192, 129.318242,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6~10</b><br><b>주소 : 남구 수암로90번길 16</b> <br>상호명 : 이조주문진막국수</div>' ],
		[ 35.552631, 129.296872,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.3~4</b><br><b>주소 : 중구 신기14길 1</b> <br>상호명 : 스타덤PC방 신기마을점</div>' ],
		
		// 세종 경로
		[ 36.499058, 127.256834,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.17</b><br><b>주소 : 갈매로 387</b> <br>상호명 : 세종시문화재단</div>' ],
		[ 36.478141, 127.290412,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~16</b><br><b>주소 : 호려울로 29 1층</b> <br>상호명 : 바른갈비탕</div>' ],
		[ 36.476684, 127.280359,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~16</b><br><b>주소 : 세종특별자치시 시청대로 78</b> <br>상호명 : 마메종공인중개사무소</div>' ],
		[ 36.505040, 127.262309,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.16</b><br><b>주소 : 도움6로 11</b> <br>상호명 : 국토부 구내식당</div>' ],
		[ 36.473329, 127.290577,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.15</b><br><b>주소 : 금남구즉로 110-3</b> <br>상호명 : 명품한우타운</div>' ],
		[ 36.480256, 127.261863,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 나성동 720 행복타워 1층</b> <br>상호명 : 스타벅스 세종첫마을점</div>' ],
		[ 36.489813, 127.256287,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 나성동 738</b> <br>상호명 : 대손관</div>' ],
		[ 36.477119, 127.286395,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13</b><br><b>주소 : 한누리대로 2165</b> <br>상호명 : 싶빵공장 세종보람점</div>' ],
		[ 36.477505, 127.290261,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13</b><br><b>주소 : 남세종로 454</b> <br>상호명 : 따규</div>' ],
		[ 36.477322, 127.286748,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.12</b><br><b>주소 : 한누리대로 2165</b> <br>상호명 : 역전할머니맥주 세종보람점</div>' ],
		[ 36.477770, 127.287901,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 한누리대로 2149</b> <br>상호명 : 드롭탑 세종시청점</div>' ],
		[ 36.503232, 127.222670,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 장군면 월현윗길 47</b> <br>상호명 : 곤드레말추어탕(장군면점)</div>' ],
		[ 36.495443, 127.312335,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 한누리대로 1820</b> <br>상호명 : 수루배대반점</div>' ],
		[ 36.507649, 127.262855,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8</b><br><b>주소 : 어진동551</b> <br>상호명 : 동카</div>' ],
		
		// 경기도 경로
			// 고양
			[ 37.654454, 126.896376,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~12</b><br><b>주소 : 덕양구 삼원로 3길 73 (삼송동) </b> <br>상호명 : 소문난 식당</div>' ],
			// 구리
			[ 37.632121, 127.118381,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 갈매동 301-7</b> <br>상호명 : LH구리갈매 사업단</div>' ],
			[ 37.604608, 127.145057,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 인창동 건원대로34번길 84</b> <br>상호명 : 구리시보건소</div>' ],
			[ 37.595996, 127.136447,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.12</b><br><b>주소 : 교문1동 체육관로 131</b> <br>상호명 : 구리시체육관</div>' ],
			// 부천
			[ 37.520921, 126.795392,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9~15</b><br><b>주소 : 성곡로 91 송원빌딩 2층</b> <br>상호명 : 정성노래연습장(도당동)</div>' ],
			// 시흥
			[ 37.439730, 126.783559,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6~15</b><br><b>주소 : 경기도 시흥시 삼미시장3길 15 (신천동) </b> <br>상호명 : 고기천국</div>' ],
			// 이천
			[ 37.256770, 127.415222,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.1~3</b><br><b>주소 : 마장면 프리미엄아울렛로 33-27 지하1층</b> <br>상호명 : 아울렛 한식뷔페</div>' ],
			[ 37.277111, 127.445563,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.31~6.3</b><br><b>주소 : 중리동 어재연로8번길 52</b> <br>상호명 : 무스리마 식당</div>' ],
		
			
		// 강원도 경로
			// 강릉
			// 고성
			// 동해
			// 삼척
			// 속초
			// 양구
			[ 38.110007, 127.990099,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.14~16</b><br><b>주소 : 양구읍 관공서로 38</b> <br>상호명 : 양구군청</div>' ],
			[ 38.109753, 127.988959,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8</b><br><b>주소 : 양구읍 관공서로 42</b> <br>상호명 : 양구군 보건소 선별진료소</div>' ],
			
			// 양양
			// 원주
			[ 37.369834, 127.937231,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.11</b><br><b>주소 : 우산동 상지대길 44</b> <br>상호명 : 딱순이 포장마차</div>' ],
			[ 37.321741, 127.948147,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.7~8</b><br><b>주소 : 단구동 구곡길 21-6</b> <br>상호명 : 만남이참좋다</div>' ],
			[ 37.339939, 127.964852,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~7</b><br><b>주소 : 개운동 반곡길 25</b> <br>상호명 : 더본감자탕</div>' ],
			// 영월
			// 인제
			// 정선
			// 철원
			// 춘천
			// 태백
			// 평창
			// 화천
			// 홍천
			// 횡성
			[ 37.495750, 127.985647,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 횡성읍 문화체육로 29</b> <br>상호명 : 키즈마루</div>' ],
		
		// 충북 경로
			// 괴산
			// 단양
			// 보은
			// 영동
			[ 36.172958, 127.787479,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~14</b><br><b>주소 : 영동읍 동정로 72-45</b> <br>상호명 : 제일공업사</div>' ],
			// 옥천
			// 음성
			// 제천
			// 증평
			// 진천
			// 청주
			[ 36.632901, 127.460518,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~14</b><br><b>주소 : 서원구 1순환로 680, 2층</b> <br>상호명 : COSMOS</div>' ],
			// 충주
			[ 37.022250, 127.837042,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.15</b><br><b>주소 : 충주시</b> <br>상호명 : 충주휴게소 창원방향(중앙탑면)</div>' ],
			[ 37.017756, 127.953997,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13~15</b><br><b>주소 : 충주시</b> <br>상호명 : 현대성우캐스팅(용탄동)</div>' ],
			[ 36.980300, 127.914930,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.14</b><br><b>주소 : 충주시</b> <br>상호명 : 서울더블유치과병원(봉방동)</div>' ],
		
		// 충남 경로
			// 계룡
			// 공주
			[ 36.458613, 127.109383,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.12</b><br><b>주소 : 공주시 무령로 77</b> <br>상호명 : 공주의료원 장례식장</div>' ],
			// 금산
			// 논산
			// 당진
			// 보령
			// 부여
			// 서산
			[ 36.766697, 126.461493,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~15</b><br><b>주소 : 서산시 덕지천로 124</b> <br>상호명 : 서산불한증막사우나</div>' ],
			// 서천
			// 아산
			[ 36.776784, 126.932703,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10~15</b><br><b>주소 : 신창면 읍내남길 12</b> <br>상호명 : 매직마켓센터</div>' ],
			[ 36.782405, 127.003950,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.29~6.6</b><br><b>주소 : 온천동 온궁로 33</b> <br>상호명 : 온양온천탕(남탕)</div>' ],
			[ 36.782018, 127.004989,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.2~5</b><br><b>주소 : 온천동 충무로8번길 7</b> <br>상호명 : 고바우식당</div>' ],
			// 예산
			// 천안
			// 청양
			// 태안
			// 홍성
		
		// 전북 경로
		// 전남 경로
			// 순천
			[ 34.950185, 127.528801,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~7</b><br><b>주소 : 해룡면 조례못등2길 4-16</b> <br>상호명 : 샘온천</div>' ],
			[ 34.938882, 127.488729,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~6</b><br><b>주소 : 남정동 우석로 114</b> <br>상호명 : 보금헬스온천</div>' ],
			[ 34.943987, 127.539457,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~6</b><br><b>주소 : 해룡면 상성길 324</b> <br>상호명 : 신대마을</div>' ],
			[ 34.954032, 127.518177,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~6</b><br><b>주소 : 왕조1동 유동1길 30</b> <br>상호명 : 강동스탠드클럽</div>' ],
		
		// 경북 경로
			// 경산
			[ 35.828167, 128.720471,
					'<div style="padding:10px; width:400px;"><b>날짜 : 5.31~6.6</b><br><b>주소 : 펜타힐즈2로57, 2층</b> <br>상호명 : 풀하우스홀덤펍</div>' ],
			// 경주
			// 고령
			// 구미
			// 군위
			// 김천
			// 문경
			[ 36.730023, 128.109117,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 문경읍 온천 2길, 24</b> <br>상호명 : 문경종합온천</div>' ],
			// 봉화		
			// 상주
			[ 36.730023, 128.109117,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.8</b><br><b>주소 : 상주시 경상대로 2559</b> <br>상호명 : 경북대 상주캠퍼스 복지회관 식당ㆍ카페</div>' ],
			// 안동
			// 영덕
			// 영양
			// 영주
			// 영천
			[ 35.973275, 128.934065,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 중앙동 중앙동4길 5</b> <br>상호명 : CU영천문외원룸점</div>' ],
			[ 35.973719, 128.945494,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.10</b><br><b>주소 : 야사동 호국로 70</b> <br>상호명 : CU영천동부점</div>' ],
			[ 35.971810, 128.938526,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~10</b><br><b>주소 : 중앙동 시청로 12</b> <br>상호명 : 파리바게트 영천문외점</div>' ],
			// 예천
			// 울릉
			// 울진
			// 의성
			// 청도
			// 청송
			[ 36.528072, 129.044614,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.13</b><br><b>주소 : 진보면 진안로 15-10</b> <br>상호명 : 다이소 청송진보점</div>' ],
			// 칠곡
			// 포항
			[ 36.064589, 129.371070,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.10~11</b><br><b>주소 : 북구 창포동 640</b> <br>상호명 : GS25뉴창포중앙점</div>' ],
		// 경남 경로
			// 거제
			// 거창
			// 고성
			// 김해
			[ 35.183563, 128.773636,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.13</b><br><b>주소 : 대청계곡길 197-1</b> <br>상호명 : 류디저트카페</div>' ],
			[ 35.220185, 128.733213,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.9</b><br><b>주소 : 진례면 평지길279-65</b> <br>상호명 : 부라더스</div>' ],
						
			// 남해
			// 밀양
			// 사천
			// 산청
			// 양산
			[ 35.333619, 129.004031,
								'<div style="padding:10px; width:400px;"><b>날짜 : 6.2~6</b><br><b>주소 : 물금읍 목화로 84</b> <br>상호명 : 천호탕(여탕)</div>' ],
			// 의령
			// 진주
			[ 35.194926, 128.076630,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.5~8</b><br><b>주소 : 진주성로59 (봉곡동)</b> <br>상호명 : 은주의상실</div>' ],
			[ 35.194935, 128.076673,
								'<div style="padding:10px; width:400px;"><b>날짜 : 5.26~6.7</b><br><b>주소 : 진주성로59 (봉곡동)</b> <br>상호명 : 바바리</div>' ],
			// 창녕
			// 창원
			[ 35.187430, 128.560851,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.6</b><br><b>주소 : 마산합포구 문화북1길 42</b> <br>상호명 : 함흥집</div>' ],
			[ 35.217404, 128.584703,
							'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 마산합포구 산호북3길 23, 2층</b> <br>상호명 : 핌플탁구장</div>' ],
			// 통영
			// 하동
			[ 35.071242, 127.745715,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.4~6</b><br><b>주소 : 하동읍 중앙로 63</b> <br>상호명 : 여우다방</div>' ],
			// 함안
			[ 35.281457, 128.404479,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.5</b><br><b>주소 : 가야읍 도향1길 17</b> <br>상호명 : 함주사우나</div>' ],
			// 함양
			[ 35.526202, 127.770562,
					'<div style="padding:10px; width:400px;"><b>날짜 : 6.10~11</b><br><b>주소 : 함양읍 고운로 476-19</b> <br>상호명 : 함양제일장례식장</div>' ]
			// 합천
							
		
		// 제주도 경로
			
			
			
	];

	var markers = [];
	

	for (var i = 0; i < data.length; i++) {
		console.log(i + "의 "+ data[i][0]);

		// 지도에 마커를 생성하고 표시한다
		var marker = new window.kakao.maps.Marker({
			position : new window.kakao.maps.LatLng(data[i][0], data[i][1]), // 마커의 좌표
			clickable : true,
			map : map
		// 마커를 표시할 지도 객체
		});

		// 인포윈도우를 생성합니다
		var infowindow = new window.kakao.maps.InfoWindow({
			content : data[i][2],
			removable : true
		});

		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		markers.push(marker);
		kakao.maps.event.addListener(marker, 'click', makeClickListener(
				map, marker, infowindow));

	}

	//클러스터러에 마커들을 추가합니다.
	clusterer.addMarkers(markers);
	// 마커에 클릭이벤트를 등록합니다
	function makeClickListener(map, marker, infowindow)
	{
		return function() {
			infowindow.open(map, marker);
		};
	}
	
	
</script>
<!-- 조회버튼 -->
<script type="text/javascript" src="/script/RouteSelect_suc.js"></script>

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
