<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>아이디 찾기</title>

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
					<div id="login-box" class="col-md-12">
						<form id="login-form" class="form" action="/idFindCheck.do" method="post"
						onsubmit="return doRegUserCheck(this);">
							<h3 class="text-center text-dark">아이디 찾기</h3>
							<div class="form-group">
								<br /> <input class="form-control" type="text" name="user_name"
									id="find_name" placeholder="이름" maxlength="20"
									autocomplete="off" />
							</div>
							<div class="form-group">
								<br /> <input class="form-control" type="email" name="email"
									id="find_email" placeholder="이메일" maxlength="50"
									style="ime-mode: disabled;" autocomplete="off" />
							</div>

							<div class="form-group" style="text-align: center;">
								<input type="submit" name="submit" class="btn btn-dark btn-md"
									value="찾기">

							</div>
							<div style="width: 100%" align="center">
								<a href="/login.do" class="text-dark">로그인</a>&nbsp;|&nbsp; <a
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

<!-- 아이디찾기 js -->
<script type="text/javascript" src="/script/IdFind_suc.js"></script>
<script src="/jquery/jquery.js"></script>
<script src="/bootstrap/js/bootstrap.bundle.js"></script>
<script src="/jquery/jquery.min.js"></script>

<!-- 카카오 로그아웃 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("3d554838e744a4e7d04bb8af264d9794");
</script>
</html>