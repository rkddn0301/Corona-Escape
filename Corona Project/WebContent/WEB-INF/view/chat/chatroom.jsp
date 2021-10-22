<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="static poly.util.CmmUtil.nvl"%>
<%@ page import="poly.dto.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="poly.dto.CoronaMainDTO"%>
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

	// 채팅방 세션
	String SS_ROOM_NAME = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));

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
<title><%=SS_ROOM_NAME%>님의 문의</title>


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

.balloon_01 {
 position:relative;
 margin: 10px;
 width:40%;
 height:10%;
  background:pink;
  border-radius: 10px;
}
.balloon_01:after {
border-top:15px solid pink;
border-left: 15px solid transparent;
 border-right: 0px solid transparent;
 border-bottom: 0px solid transparent;
 
 content:"";
 position:absolute;
 top:10px;
 left:-15px;
}
 

.balloon_02 {
 position:relative;
 margin: 10px;
 right: -58%;
 width:40%;
 height:10%;
  background:lightblue;
  border-radius: 10px;
}
.balloon_02:after {
 border-top:15px solid lightblue;
 border-left: 0px solid transparent;
 border-right: 15px solid transparent;
 border-bottom: 0px solid transparent;
 content:"";
 position:absolute;
 top:10px;
 right:-15px;
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
							<%
								if (access_Token == "") {
							%>
							<li class="nav-item"><a class="nav-link" href="#"
								data-toggle="modal" data-target="#myModal">문의하기</a></li>
							<li class="nav-item"><a class="nav-link" href="#"
								onclick="mvIQ()">문의내역</a></li>
							<form id="form-data" name="form" method="get" action="/chat/intro.do">
							<input type="hidden" id="room_name" name="room_key" />
							<li class="nav-item"><a class="nav-link"
								href="#" onclick="chatopen()">실시간 채팅 문의</a></li>
							</form>
							<%
								}
							%>


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
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header card-header-primary">
								<%if (SS_AUTHOR.equals("1")) { %>
									<h4 style="margin: 5px;"><a href="/chat/chats.do" style="text-align: left;"><i class="nc-icon nc-icon nc-stre-left"></i></a></h4>

								<% } %>
									<h3 class="card-title" style="color: #f07575; text-align: center;">
										<b><%=SS_ROOM_NAME %>님의 문의</b>
										
									</h3>
									<hr noshade/>

								</div>
								<div class="card-body">

									

									<main class="main-screen main-chat" id="chatresultHTML"
										style="padding-bottom: 50px;"> </main>

									


								</div>
								<form class="reply" name="form" method="post"
										onsubmit="return false;">
									

								<div class="card-footer" style="text-align:center;">
									<hr noshade/>
										<input type="text" name="send_msg" id="send_msg"
											placeholder="메시지를 입력하세요." /> <i
											class="far fa-smile-wink fa-lg apparatusWrap"></i>
										<button id="msgSendBtn"
											style="background-color: #fae100; cursor: pointer;">
											<span>전송</span>
										</button>
											
										</div>
									</form>
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
    	var SS_USER_NO = '<%=SS_USER_NO%>';
    	$(window).on('load',function() {
    		
    		getAllMsg();
    		setInterval('getAllMsg()',500);
    		
    		$('#msgSendBtn').on('click',function() {
    			var send_msg = $('#send_msg').val();
    			
    			if( $('#send_msg').val()==""){
    				$('#send_msg').focus();
    				return false;
    			}
    			
    			$.ajax({
    				url : "/chat/msg.do",
    				type : "post",
    				dataType : "JSON",
    				data : {send_msg:send_msg},
    				success : function(json) {
    					getAllMsg();
    					$('#send_msg').val("");
    					$('#send_msg').focus();
    					var scollheight = document.body.scrollHeight;
    					window.scrollTo(0,scollheight);
    				}
    			})
    		});
    		
    	});
    	
    	//채팅방 전체 대화 가져오기
		function getAllMsg() {
			//Ajax 호출
			$.ajax({
				url : "/chat/getMsg.do",
				type : "post",
				dataType : "JSON",
				//data : $("form").serialize(),
				success : function(json) {
					doOutputMsg(json);
				}
			})
		}
    	
    	function doOutputMsg(json) {
			if(json != null) {
				var msgResult = "";
				var listResult ="";
				var userSet = new Set();
				msgResult += "<div style='color: #3784cc; text-align: center;'>"+'<%=DateUtil.getDateTime("M월 d일 E, yyyy")%>'+"</div>";
				msgResult += "<div style='color: #9c9797; text-align: center;'>『관리자가 근무시간 내에 확인 시 답변해드립니다.』</div><br/>";
				
				for(var i = 0; i<json.length;i++) {
					
				//  유저 셋 유저들 추가.
					userSet.add(json[i].nick_name);

					
					var dateTimevalue = json[i].datetime;
					var resdateTime = dateTimevalue.substring(11,16);	
					if(json[i].author == "admin"){
						msgResult+= json[i].msg+'<br/><br/>';
						
						
					} else if (json[i].user_no == null) // 입장 안내문은 user_no가 null값이다.
					{
						msgResult += "<div style='text-align: center;'>";
						msgResult += 	"<div>";
						msgResult += 		"<div>";
						msgResult += 			"<span>"+ json[i].msg +"&nbsp</span>";
						msgResult += 			"<span>"+resdateTime+"</span>";
						msgResult += 		"</div>";
						msgResult +=		"<hr width='50%'/>"
						msgResult +=		"<br/>"
						msgResult += 	"</div>";
						msgResult += "</div>";
						
						
					} else if(json[i].user_no != SS_USER_NO)// 나 자신말고 다른 사람 메시지
					{
						msgResult += "<div class='balloon_01' style='text-align: left;'>";
						msgResult += 		"<div>";
						msgResult +=			"<b><span>&nbsp&nbsp"+ json[i].nick_name +"</span></b>";
						msgResult += 			"<div>";
						msgResult += 				"<span style='font-size:14pt;'>&nbsp&nbsp"+ json[i].msg +"&nbsp</span>";
						msgResult += 				"<span style='font-size:8pt;'>"+resdateTime+"</span>";
						msgResult += 			"</div>";
						msgResult += 		"</div>";
						msgResult += "</div>";
						
					} else // 나 자신 메시지
					{
						msgResult += "<div class='balloon_02' style='text-align: right;'>";
						msgResult += 	"<div>";
						msgResult += 		"<b><span>"+json[i].nick_name+"&nbsp&nbsp</span></b>";
						msgResult += 		"<div>";
						msgResult += 			"<span style='font-size:8pt;'>"+resdateTime +"&nbsp</span>";
						msgResult += 			"<span style='font-size:14pt;'>"+json[i].msg+"&nbsp&nbsp</span>";
						msgResult += 		"</div>";
						msgResult += 	"</div>";
						msgResult += "</div>";

					}
				}
				
				$('#chatresultHTML').html(msgResult);

				

				
			}
    	}
    	
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
