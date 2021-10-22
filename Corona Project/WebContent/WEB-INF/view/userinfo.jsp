<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<head>


<link href="bootstrap/css/login.css" rel="stylesheet">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/styles.css" />
<link rel="stylesheet" href="/bootstrap/js/bootstrap.js" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>회원가입</title>

<!-- 하이퍼링크 밑줄 없애기 / 라디오 태그 정렬 -->
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

.radio {
	background: none;
	height: 15px;
}

.va_m {
	vertical-align: middle;
}

img {
	border: 0;
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
						style="margin-top: 50px; height: 900px;">
						<form id="reg_form" name="f" class="form" method="post">
							<br />
							<h3 class="text-center text-dark">회원가입</h3>
							<p class="text-right text-dark">* : 필수입력값</p>

							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="user_id" class="text-dark">*아이디</label><br /> <input
											class="form-control" type="text" name="user_id"
											id="user_reg_id" oninput="idCheck()" maxlength="16"
											autocomplete="off" />

										<div id="wrongId"
											style="display: none; color: red; font-size: 11px;">5~16자의
											영문 소문자, 숫자만 사용 가능합니다.</div>
										<div id="failId"
											style="display: none; color: red; font-size: 11px;">사용하실
											수 없는 아이디입니다.</div>
										<div id="successId"
											style="display: none; color: dodgerblue; font-size: 11px;">사용하실
											수 있는 아이디입니다.</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="user_name" class="text-dark">*이름</label><br /> <input
											class="form-control" type="text" name="user_name"
											id="user_reg_name" maxlength="20" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="password" class="text-dark">*비밀번호</label><br /> <input
											class="form-control" type="password" name="password"
											id="user_reg_password" oninput="checkPw()" maxlength="16"
											autocomplete="off" style="ime-mode: disabled;" />

										<div id="wrongPw"
											style="display: none; color: red; font-size: 11px; margin-left: 20px;">5~16자의
											비밀번호를 사용해주세요.</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="user_name" class="text-dark">*비밀번호 확인</label><br />
										<input class="form-control" type="password" name="password2"
											id="RepeatPassword" oninput="checkPw2()" maxlength="16"
											autocomplete="off" style="ime-mode: disabled;" />

										<div id="wrongPw2"
											style="display: none; color: red; font-size: 11px; margin-left: 20px;">비밀번호가
											일치하지 않습니다.</div>
									</div>
								</div>

							</div>

							<label for="birthday" class="text-dark">*생년월일</label><br />

							<div class="form-row">

								<div class="col-md-4">
									<div class="form-group">
										<select class="form-control" name="year" id="user_reg_year"
											value="출생년도">
											<%
												for (int y = 1899; y < 2021; y++) {
											%>
											<%
												if (y == 1899) {
											%>
											<option value="">출생년도</option>
											<%
												}
											%>
											<option value="<%=y + 1%>"><%=y + 1%></option>

											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<select class="form-control" name="month" id="user_reg_month"
											value="월">
											<%
												for (int i = 0; i < 12; i++) {
											%>
											<%
												if (i == 0) {
											%>
											<option value="">월</option>
											<%
												}
											%>
											<option value="<%=i + 1%>"><%=i + 1%></option>

											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<input class="form-control" type="text" name="day"
											id="user_reg_day" maxlength="2" autocomplete="off"
											placeholder="일" />
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="email" class="text-dark">*이메일</label><br /> <input
											class="form-control" type="email" name="email"
											id="user_reg_email" oninput="checkMail()" maxlength="50"
											autocomplete="off" style="ime-mode: disabled;" />

										<div id="wrongMail"
											style="display: none; color: red; font-size: 11px;">잘못된
											이메일 형식입니다.</div>
										<div id="successMail"
											style="display: none; color: dodgerblue; font-size: 11px">사용가능한
											Email입니다.</div>
										<div id="failMail"
											style="display: none; color: red; font-size: 11px;">이미
											등록된 Email입니다.</div>
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="nick_name" class="text-dark">*닉네임</label><br /> <input
											class="form-control" type="text" name="nick_name"
											id="user_reg_nick" oninput="checkNick()" maxlength="20"
											autocomplete="off" />

										<div id="wrongNick"
											style="display: none; color: red; font-size: 11px;">특수문자를
											제외한 2~14자까지 사용 가능합니다.</div>
										<div id="failNick"
											style="display: none; color: red; font-size: 11px;">사용하실
											수 없는 닉네임입니다.</div>
										<div id="successNick"
											style="display: none; color: dodgerblue; font-size: 11px;">사용하실
											수 있는 닉네임입니다.</div>
									</div>
								</div>


								<div class="col-md-6">
									<div class="form-group">
										<label for="gender" class="text-dark">*성별</label><br /> <span
											class="va_m radio" style="height: 15px"><input
											type="radio" class="radio va_m" name="gender" value="남"
											checked="checked" /></span> <span class="va_m radio">남</span> <span
											class="va_m radio" style="height: 15px"><input
											type="radio" class="radio va_m" name="gender" value="여" /></span> <span
											class="va_m radio">여</span>
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="addr1" class="text-dark">*주소</label>
										<button type="button" class="btn btn-dark btn-md"
											onclick="sample2_execDaumPostcode()">주소 찾기</button>
										<br /> <input class="form-control" type="text" name="addr1"
											id="user_reg_addr1" maxlength="50" autocomplete="off" />
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="addr2" class="text-dark">상세주소</label><br /> <input
											class="form-control" type="text" name="addr2"
											id="user_reg_addr2" maxlength="50" autocomplete="off" />
									</div>
								</div>
							</div>



							<div class="form-group" style="text-align: center;">
								<button type="button" id="submitbtn" onclick="userReg()"
									name="submit" class="btn btn-dark btn-md">회원가입</button>

							</div>
							<div style="width: 100%" align="center">
								<a href="/login.do" class="text-dark">로그인으로 돌아가기</a>

							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<!-- 회원가입 js -->
<script type="text/javascript" src="/script/Insertuser_suc.js"></script>
<script src="/jquery/jquery.js"></script>
<script src="/bootstrap/js/bootstrap.bundle.js"></script>
<script src="/jquery/jquery.min.js"></script>

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
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/script/daumAPIaddr.js"></script>

</html>