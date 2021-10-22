<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일전송폼</title>
</head>
<body>

<form action = "/mail/sendMailPost.do" method = "post">
<table border = '1'>
<tr>
<td>받는사람</td>
<td><input type = "text" name = "toMail" placeholder = "이메일" style = "width:300px;"><br></td>
</tr>
<tr>
<td>메일제목</td>
<td><input type="text" name = "title" placeholder = "제목" style = "width:300px;"> <br></td>
</tr>

<tr>
<td>메일내용</td>
<td><input type="text" name = "contents" placeholder="내용 입력" style = "width:300px; height:300px"><br></td>
</tr>

</table>

<input type="submit" value = "[메일전송]">
<input type="reset" value = "[내용초기화]">
</form>



</body>
</html>