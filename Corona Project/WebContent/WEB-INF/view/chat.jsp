<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="static poly.util.CmmUtil.nvl"%>
<%@ page import="poly.dto.UserDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
//모든 페이지에 필수로 있어야하는 것들

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

%>
<link rel="stylesheet" href="/bootstrap/css/chat.css">
<script  src="/bootstrap/js/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/7f70eb1ead.js" crossorigin="anonymous"></script>
<!-- 채팅 박스 -->
<div class="floating-chat" style="z-index: 100;">
	<i class="fa fa-comments" aria-hidden="true"></i>
	<div class="chat">
		<div class="header">
			<span class="title"> 실시간 문의 </span>
			<button>
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>

		</div>
		<ul id="messages" class="messages">
		</ul>
		<div class="footer">
			<div class="text-box" id="text-box" contenteditable="true"
				disabled="true"></div>
			<button id="sendMessage">전송</button>
		</div>
	</div>
</div>


<!-- 웹소켓 채팅 -->
<script>
	var element = $('.floating-chat');
	var myStorage = localStorage;
	var ws;
	var messages = $('.messages');
	
	var tema_logo = "";
	var member_id = "<%=SS_NICK_NAME%>";
	
	setTimeout(function() {
		element.addClass('enter');
	}, 1000);
	element.click(openSocket);
	function openSocket() {
		var messages = element.find('.messages');
		var textInput = element.find('.text-box');
		element.find('>i').hide();
		element.addClass('expand');
		element.find('.chat').addClass('enter');
		var strLength = textInput.val().length * 2;
		textInput.keydown(onMetaAndEnter).prop("disabled", false).focus();
		element.off('click', openSocket);
		element.find('.header button').click(closeSocket);
		element.find('#sendMessage').click(send);
		messages.scrollTop(messages.prop("scrollHeight"));
		if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
			writeResponse("WebSocket is already opened.");
			return;
		}
		// 웹소켓 객체 만드는 코드
		ws = new WebSocket("ws://localhost:8080/echo.do");/* localhost:9005 *//* 15.164.51.243 */
		ws.onopen = function(event) {
			if (event.data === undefined)
				return;
			writeResponse(event.data);
		};
		ws.onmessage = function(event) {
			writeResponse(event.data);
		};
		ws.onclose = function(event) {
			writeResponse(<%=SS_NICK_NAME%> + "님이 퇴장하셨습니다.");
		}
	}
	function send() {
		var userInput = $('.text-box');
		var newMessage = userInput.html().replace(/\<div\>|\<br.*?\>/ig, '\n')
				.replace(/\<\/div\>/g, '').trim().replace(/\n/g, '<br>');
		if (newMessage.replaceAll("&nbsp;", "").replaceAll("<br>", "").trim() == "") {
			return;
		}
		var text = member_id + "<br>" +  newMessage;
		console.log(text)
		ws.send(text);
		text = "";
		var messagesContainer = $('.messages');
		// clean out old message
		userInput.html('');
		// focus on input
		userInput.focus();
		messagesContainer.finish().animate({
			scrollTop : messagesContainer.prop("scrollHeight")
		}, 250);
	}
	
	function closeSocket() {
		element.find('.chat').removeClass('enter').hide();
		element.find('>i').show();
		element.removeClass('expand');
		element.find('.header button').off('click', closeSocket);
		element.find('#sendMessage').off('click', send);
		element.find('.text-box').off('keydown', onMetaAndEnter).prop(
				"disabled", true).blur();
		setTimeout(function() {
			element.find('.chat').removeClass('enter').show()
			element.click(openSocket);
		}, 500);
		ws.close();
	}
	function writeResponse(text) {
		var messagesContainer = $('.messages');
		
		
		// member_id와 message 순서대로 넣기
		
		var message = text.split("<br>")[1];
		var member = text.split("<br>")[0];
		
		
			
			
		//캐릭터 이미지 나 이외의 다른사람들, 메시지 보내는 우선순위(그림 -> 아이디 -> 메시지)
		if (member != member_id) {
			messagesContainer.append([ '<li class="self">','<img class="chat_logo" src="/image/me.jpg">', member, '<br>', message, '</li>' ].join(''));
			
			
		} else {
			messagesContainer.append([ '<li class="other">','<img class="chat_logo" src="/image/other.jpg">', member, '<br>', message, '</li>' ].join(''));
		}
		messagesContainer.finish().animate({
			scrollTop : messagesContainer.prop("scrollHeight")
		}, 250);
	}
	// 메시지 입력 키
	function onMetaAndEnter(event) {
		if ((event.metaKey || event.ctrlKey) && event.keyCode == 13) {
			send();
		}
	}
</script>