package poly.controller;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.ChatDTO;
import poly.service.IChatService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Controller
public class chatController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "ChatService")
	private IChatService ChatService;

	/**
	 * 채팅방 생성
	 */
	@RequestMapping(value = "chat/intro", method = RequestMethod.GET)
	public String intro(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".intro Start!");
		
		// 기존 세션에 저장된 값 삭제하기
		session.setAttribute("SS_ROOM_NAME", "");
		
		String nick_name = CmmUtil.nvl((String)session.getAttribute("SS_NICK_NAME"));
		String room_key = CmmUtil.nvl((String)request.getParameter("room_key"));
		
		log.info("nick_name : " + nick_name);
		log.info("room_key : " + room_key);
		
		// 세션에 값 저장하기
		session.setAttribute("SS_ROOM_NAME", room_key);
		
		// 입장 환영 멘트 저장하기
		ChatDTO pDTO = new ChatDTO();
		
		pDTO.setRoom_key(room_key);
		pDTO.setNick_name(nick_name);
		pDTO.setMsg(nick_name + "님이 입장하셨습니다.");
		pDTO.setDatetime(DateUtil.getDateTime("yyyy.MM.dd HH:mm:ss"));
		
		// 채팅 멘트 저장하기
		ChatService.insertChat(pDTO);

		log.info(this.getClass().getName() + ".intro End!");
		
		return "/chat/chatroom";
	}
	
	/**
	 * 채팅방 전체 리스트 가져오기
	 */
	@RequestMapping(value = "chat/roomList", method=RequestMethod.POST)
	@ResponseBody
	public Set<String> roomList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info(this.getClass().getName() + ".roomList Start!");
		
		Set<String> rSet = ChatService.getRoomList();
		
		if (rSet == null) {
			rSet = new LinkedHashSet<String>();
		}
		
		log.info(this.getClass().getName() + ".roomList End!");
		
		return rSet;
		
	}
	
	/**
	 * 대화 저장
	 */
	@RequestMapping(value = "chat/msg")
	@ResponseBody
	public List<ChatDTO> msg(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	throws Exception {
		log.info(this.getClass().getName() + ".msg Start!");
		
		String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
		String nick_name = CmmUtil.nvl((String) session.getAttribute("SS_NICK_NAME"));
		String user_no = CmmUtil.nvl((String) session.getAttribute("SS_USER_NO"));
		
		String msg = CmmUtil.nvl(request.getParameter("send_msg"));
		
		log.info("user_no : " + user_no);
		log.info("nick_name : " + nick_name);
		log.info("room_name : " + room_name);
		log.info("msg : " + msg);
		
		List<ChatDTO> rList = null;
		
		// 메시지가 존재하는 경우에만 대화 가져오기
		if (msg.length() > 0) {
			ChatDTO pDTO = new ChatDTO();
			
			pDTO.setRoom_key(room_name);
			pDTO.setNick_name(nick_name);
			pDTO.setUser_no(user_no);
			pDTO.setMsg(msg);
			pDTO.setDatetime(DateUtil.getDateTime("yyyy.MM.dd HH:mm:ss"));

			
			rList = ChatService.insertChat(pDTO);
			
			if (rList == null) {
				rList = new LinkedList<ChatDTO>();
			}
			pDTO = null;
		}
		
		log.info(this.getClass().getName() + ".msg End!");
		
		return rList;
	}
	
	/**
	 * 대화 가져오기
	 */
	@RequestMapping(value = "chat/getMsg")
	@ResponseBody
	public List<ChatDTO> getMsg(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	throws Exception{
		log.info(this.getClass().getName() + ".getMsg Start!");
		
		String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
		
		log.info("room_name : " + room_name);
		
		ChatDTO pDTO = new ChatDTO();
		pDTO.setRoom_key(room_name);
		
		List<ChatDTO> rList = ChatService.getChat(pDTO);
		
		if (rList == null) {
			rList = new LinkedList<ChatDTO>();
		}
		
		pDTO = null;
		
		
		log.info(this.getClass().getName() + ".getMsg End!");
		
		return rList;
	}
	

}