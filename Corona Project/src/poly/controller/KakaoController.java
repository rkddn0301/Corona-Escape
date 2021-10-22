package poly.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.service.impl.KakaoService;

@Controller
public class KakaoController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private KakaoService kakao;
	
	// index
	@RequestMapping (value = "kakao/kakaoLogin")
	public String kakaoLogin() {
		log.info(this.getClass().getName() + ".kakaoLogin start!");
		
		return "/kakao/kakaoLogin";
	}
	
	// login
	@RequestMapping (value = "kakao/redirect2")
	public String redirect(@RequestParam("code") String code, HttpSession session) {
		log.info(this.getClass().getName() + ".redirect start!");
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);
		
		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		if (userInfo.get("email") != null) {
			session.setAttribute("userId",  userInfo.get("email"));
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("month", userInfo.get("month"));
			session.setAttribute("day", userInfo.get("day"));
			session.setAttribute("gender", userInfo.get("gender"));
		}
		
		return "/kakao/kakaoLogin";
	}
	
	// Logout
	@RequestMapping(value="kakao/logout")
	public String logout(HttpSession session) {
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    session.removeAttribute("month");
	    session.removeAttribute("day");
	    session.removeAttribute("gender");
	    return "/kakao/kakaoLogin";
	}

	

}
