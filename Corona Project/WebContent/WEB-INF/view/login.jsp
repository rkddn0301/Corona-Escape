<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>로그인</title>



<link href="bootstrap/css/login.css" rel="stylesheet">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/styles.css" />
<link rel="stylesheet" href="/bootstrap/js/bootstrap.js" />
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

</head>


<body>

	<div id="login">
		<h3 class="text-center text-white pt-5">
			<a href="/docs.do" class="text-white pt-5">Corona Escape</a>
		</h3>
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12"
					style="margin-top: 50px; height: 400px;">
						<form id="login-form" name="f" class="form" method="post">
							<h3 class="text-center text-dark">로그인</h3>
							<div class="form-group">
								<br /> <input class="form-control" type="text" name="user_id"
									id="login_id" placeholder="아이디" maxlength="20"
									autocomplete="off" />


								<div id="notId"
									style="display: none; color: red; font-size: 11px;">아이디를
									입력해주세요.</div>
							</div>
							<div class="form-group">
								<br /> <input class="form-control" type="password"
									name="password" id="login_password" placeholder="비밀번호"
									maxlength="20" style="ime-mode: disabled;" />
									
									<div id="notPw"
									style="display: none; color: red; font-size: 11px;">비밀번호를
									입력해주세요.</div>
									
							</div>

							<div class="form-group" style="text-align: center;">
								<button type="button" id="submitbtn" onclick="LoginUser()"
									name="submit" class="btn btn-dark btn-md">로그인</button>
								<button type="button" id="kakaobtn" onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=c8383f77b5e928527317d40b67ea998a&redirect_uri=http://localhost:8080/kakao/redirect.do&response_type=code'"
									name="kakaoLogin" class="btn btn-warning btn-md">카카오 로그인</button>

							</div>
							<div style="width: 100%;" align="center">
								<a href="/idfind.do" class="text-dark">아이디 찾기</a>&nbsp;|&nbsp; <a
									href="/pwfind.do" class="text-dark">비밀번호 찾기</a>&nbsp;|&nbsp; <a
									href="/userinfo.do" class="text-dark">회원가입</a>&nbsp;&nbsp;
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<!-- 로그인 js -->
<script type="text/javascript" src="/script/Login_suc.js"></script>
<script src="/jquery/jquery.js"></script>
<script src="/bootstrap/js/bootstrap.bundle.js"></script>
<script src="/jquery/jquery.min.js"></script>

<!-- 카카오 로그인 js -->
<script type="text/javascript" src="/script/KakaoLogin_suc.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("3d554838e744a4e7d04bb8af264d9794");
</script>
</html>