package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.CoronaMainDTO;
import poly.dto.UserDTO;
import poly.service.ICoronaMainService;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.service.impl.KakaoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;
import poly.util.RandomPassword;

@Controller
public class MainController {

	// 회원 로직 서비스
	@Resource(name = "UserService")
	private IUserService UserService;

	// 메일 서비스
	@Resource(name = "MailService")
	private IMailService MailService;

	// 코로나 서비스
	@Resource(name = "CoronaMainService")
	private ICoronaMainService CoronaMainService;
	
	// 카카오 서비스
	@Autowired
	private KakaoService kakao;

	// 리다이렉트용 변수
	String msg = "";
	String url = "";

	// 로그
	private Logger log = Logger.getLogger(this.getClass());
	


	// 첫 페이지
	@RequestMapping(value = "docs")
	public String docs() {
		return "/docs";
	}

	// 메인페이지(확진자현황)
	@RequestMapping(value = "main")
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<CoronaMainDTO> rList = CoronaMainService.getInformation();

		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}

		List<CoronaMainDTO> cList = CoronaMainService.getCountryInformation();
		if (cList == null) {
			cList = new ArrayList<CoronaMainDTO>();
		}

		return "/main";
	}

	// 회원가입페이지
	@RequestMapping(value = "userinfo")
	public String userinfo() {
		return "/userinfo";
	}

	// 회원가입 로직
	@RequestMapping(value = "InsertUser", method = RequestMethod.POST)
	@ResponseBody
	public String InsertUser(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".InsertUser Control Start");

		// 웹에서 받는 정보를 저장할 변수
		UserDTO pDTO = null;

		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장

		String user_id = CmmUtil.nvl(request.getParameter("user_id"));
		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		String password = CmmUtil.nvl(request.getParameter("password"));
		String year = CmmUtil.nvl(request.getParameter("year"));
		String month = CmmUtil.nvl(request.getParameter("month"));
		String day = CmmUtil.nvl(request.getParameter("day"));
		String email = CmmUtil.nvl(request.getParameter("email"));
		String nick_name = CmmUtil.nvl(request.getParameter("nick_name"));
		String gender = CmmUtil.nvl(request.getParameter("gender"));
		String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
		String addr2 = CmmUtil.nvl(request.getParameter("addr2"));

		// 값을 받는 경우 반드시 로그를 찍어서 값이 들어오는지 확인
		log.info("user_id : " + user_id);
		log.info("user_name : " + user_name);
		log.info("password : " + password);
		log.info("year : " + year);
		log.info("month : " + month);
		log.info("day : " + day);
		log.info("email : " + email);
		log.info("nick_name : " + nick_name);
		log.info("gender : " + gender);
		log.info("addr1 : " + addr1);
		log.info("addr2 : " + addr2);

		// 웹에서 받는 정보를 DTO에 저장하기 시작. 무조건 웹으로 받는 정보는 DTO에 저장

		// 받는 정보를 저장할 변수를 메모리에 올리기
		pDTO = new UserDTO();
		pDTO.setUser_id(user_id);
		pDTO.setUser_name(user_name);
		pDTO.setYear(year);
		pDTO.setMonth(month);
		pDTO.setDay(day);
		pDTO.setNick_name(nick_name);
		pDTO.setGender(gender);
		pDTO.setAddr1(addr1);
		pDTO.setAddr2(addr2);

		// 비밀번호는 절대적으로 복호화되지 않도록 해시 알고리즘으로 암호화함
		pDTO.setPassword(EncryptUtil.encHashSHA256(password));

		// 민감한 정보인 이메일은 AES128-CBC로 암호화함
		pDTO.setEmail(EncryptUtil.encAES128CBC(email));

		// 웹에서 받는 정보를 DTO에 저장하기 끝.

		// 회원가입
		int res = UserService.InsertUser(pDTO);

		if (res == 1) {
			msg = "회원가입 되었습니다.";
			return "1";
		} else if (res == 2) {
			msg = "이미 가입된 회원입니다.";
			return "2";
		} else {
			msg = "오류로 인해 회원가입에 실패하였습니다.";
			return "0";
		}
	}

	// ID 체크
	@RequestMapping(value = "idCheck")
	@ResponseBody
	public int idCheck(HttpServletRequest request) throws Exception {

		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		String user_id = request.getParameter("user_id");

		// 웹에서 받는 정보를 DTO에 저장하기 시작. 무조건 웹으로 받는 정보는 DTO에 저장

		// 받는 정보를 저장할 변수를 메모리에 올리기
		UserDTO pDTO = new UserDTO();
		pDTO.setUser_id(user_id);

		return UserService.idCheck(pDTO);

	}

	// Email 체크
	@RequestMapping(value = "checkMail")
	@ResponseBody
	public int checkMail(HttpServletRequest request) throws Exception {

		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		String email = request.getParameter("email");

		// 웹에서 받는 정보를 DTO에 저장하기 시작. 무조건 웹으로 받는 정보는 DTO에 저장

		// 받는 정보를 저장할 변수를 메모리에 올리기
		UserDTO pDTO = new UserDTO();
		pDTO.setEmail(EncryptUtil.encAES128CBC(email));

		return UserService.mailCheck(pDTO);

	}

	// 닉네임 체크
	@RequestMapping(value = "checkNick")
	@ResponseBody
	public int checkNick(HttpServletRequest request) throws Exception {

		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		String nick_name = request.getParameter("nick_name");

		// 웹에서 받는 정보를 DTO에 저장하기 시작. 무조건 웹으로 받는 정보는 DTO에 저장

		// 받는 정보를 저장할 변수를 메모리에 올리기
		UserDTO pDTO = new UserDTO();
		pDTO.setNick_name(nick_name);

		return UserService.checkNick(pDTO);

	}

	// 로그인페이지
	@RequestMapping(value = "login")
	public String login() {
		return "/login";
	}

	// 로그인 로직
	@RequestMapping(value = "Loginbtn", method = RequestMethod.POST)
	@ResponseBody
	public String Loginbtn(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {

		// 웹에서 받는 정보를 String 변수에 저장 시작 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		log.info("user_id : " + user_id);
		log.info("password : " + password);

		// 받는 정보를 저장 할 변수를 메모리에 올리기
		UserDTO rDTO = new UserDTO();
		rDTO.setUser_id(user_id);
		rDTO.setPassword(EncryptUtil.encHashSHA256(password));

		rDTO = UserService.getLogin(rDTO);

		if (rDTO == null) {
			return "0";
		} else if (rDTO.getAuthor().equals("1")) {
			/*
			 * 관리자
			 */
			// 이메일 암호화 풀기
			String email_lock = EncryptUtil.decAES128CBC(rDTO.getEmail());
			log.info("email_lock : " + email_lock);

			log.info("author : " + rDTO.getAuthor());
			session.setAttribute("SS_USER_NO", rDTO.getUser_no());
			session.setAttribute("SS_USER_ID", rDTO.getUser_id());
			session.setAttribute("SS_USER_NAME", rDTO.getUser_name());
			session.setAttribute("SS_YEAR", rDTO.getYear());
			session.setAttribute("SS_MONTH", rDTO.getMonth());
			session.setAttribute("SS_DAY", rDTO.getDay());
			session.setAttribute("SS_EMAIL", email_lock);
			session.setAttribute("SS_NICK_NAME", rDTO.getNick_name());
			session.setAttribute("SS_GENDER", rDTO.getGender());
			session.setAttribute("SS_ADDR1", rDTO.getAddr1());
			session.setAttribute("SS_ADDR2", rDTO.getAddr2());
			session.setAttribute("SS_AUTHOR", rDTO.getAuthor());
			return "1";
		} else {
			/*
			 * 사용자
			 */
			// 이메일 암호화 풀기
			String email_lock = EncryptUtil.decAES128CBC(rDTO.getEmail());
			log.info("email_lock : " + email_lock);

			log.info("author : " + rDTO.getAuthor());
			session.setAttribute("SS_USER_NO", rDTO.getUser_no());
			session.setAttribute("SS_USER_ID", rDTO.getUser_id());
			session.setAttribute("SS_USER_NAME", rDTO.getUser_name());
			session.setAttribute("SS_YEAR", rDTO.getYear());
			session.setAttribute("SS_MONTH", rDTO.getMonth());
			session.setAttribute("SS_DAY", rDTO.getDay());
			session.setAttribute("SS_EMAIL", email_lock);
			session.setAttribute("SS_NICK_NAME", rDTO.getNick_name());
			session.setAttribute("SS_GENDER", rDTO.getGender());
			session.setAttribute("SS_ADDR1", rDTO.getAddr1());
			session.setAttribute("SS_ADDR2", rDTO.getAddr2());
			session.setAttribute("SS_AUTHOR", rDTO.getAuthor());
			return "2";
		}

	}

	// 로그아웃
	@RequestMapping(value = "Logoutbtn")
	public String Logoutbtn(HttpSession session, ModelMap model) throws Exception {

	    
	    session.invalidate();

		model.addAttribute("msg", "로그아웃 하였습니다.");
		model.addAttribute("url", "/docs.do");
		return "/redirect";
	}

	// 아이디찾기 페이지
	@RequestMapping(value = "idfind")
	public String idfind() {
		return "/idfind";
	}

	// 아이디 찾기 구현
	@RequestMapping(value = "idFindCheck")
	public String idFindCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ". idFindCheck start!");

		// 값 초기화
		int res = 0;
		UserDTO pDTO = null;

		try {

			// 값 압룍
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String email = CmmUtil.nvl(request.getParameter("email"));

			log.info("user_name : " + user_name);
			log.info("email : " + email);

			// DTO에 값 저장
			pDTO = new UserDTO();

			pDTO.setUser_name(user_name);
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));

			// Service에서 아이디 찾기에 제한된 중복기능 활성화
			res = UserService.idFindCheck(pDTO);
			log.info("res : " + res);

			if (res == 1) {
				UserDTO rDTO = UserService.idFind(pDTO);
				msg = user_name + " 님의 아이디는 " + CmmUtil.nvl(rDTO.getUser_id()) + " 입니다.";
				url = "/login.do";
			} else if (res == 0) {
				msg = "아이디 찾기에 실패했습니다. 내용을 다시 확인해 주십시오.";
				url = "/idfind.do";
			}
		} catch (Exception e) {

			log.info(e.toString());
			e.printStackTrace();
		} finally {
			// redirect로 MSG와 url을 보내기 위해 model에 저장
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			model.addAttribute("res", String.valueOf(res));
			pDTO = null;
			log.info(this.getClass().getName() + ".idFindCheck end!");
		}
		return "/redirect";
	}

	// 비밀번호 찾기 페이지
	@RequestMapping(value = "pwfind")
	public String pwfind() {
		return "/pwfind";
	}

	// 비밀번호 찾기 로직
	@RequestMapping(value = "pwFindCheck")
	public String pwFindCheck(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
		log.info(this.getClass().getName() + ".pwFindCheck start!");

		// 값 초기화
		int res, ures, mres = 0;
		UserDTO pDTO = null;

		// JSP에서 값을 입력 받음
		String user_id = CmmUtil.nvl(request.getParameter("user_id"));
		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		String email = CmmUtil.nvl(request.getParameter("email"));

		log.info("user_id : " + user_id);
		log.info("user_name : " + user_name);
		log.info("email : " + email);

		// DTO에 입력받은 값을 저장함
		pDTO = new UserDTO();

		pDTO.setUser_id(user_id);
		pDTO.setUser_name(user_name);
		pDTO.setEmail(EncryptUtil.encAES128CBC(email));

		// Service에서 DTO 값을 처리하여 결과를 가져옴
		res = UserService.pwFindCheck(pDTO);

		// res가 1일 경우 임시비밀번호 발송 및 변경되고, 아닐 경우 실행 안됨
		if (res == 1) {
			// 해당 비밀번호를 랜덤함수로 6자리 변경
			String password = RandomPassword.setPassword(6);

			// 비밀번호가 나주엥 해쉬함수로 인해 바뀌기 때문에 임시비밀번호를 따로 저장함
			String tpassword = password;

			// DTO에 원래 비밀번호는 해쉬함수로, 이메일로 보낼 임시비밀번호는 기존에 있던 비밀번호로 저장함
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			pDTO.setTpassword(tpassword);

			// 비밀번호를 자동으로 변경함
			ures = UserService.pwAutoUpdate(pDTO);
			log.info("ures : " + ures);

			// 이메일로 보냄
			mres = UserService.pwmail(pDTO);
			log.info("mres : " + mres);

			msg = "임시 비밀번호가 이메일로 발송되었습니다.";
			url = "/login.do";
		} else {
			msg = "비밀번호 찾기에 실패했습니다. 내용을 다시 확인하여 주십시오.";
			url = "/pwfind.do";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		pDTO = null;

		log.info(this.getClass().getName() + ".pwFindCheck end!");
		return "/redirect";
	}

	// 마이페이지
	@RequestMapping(value = "mypage")
	public String mypage() {
		return "/mypage";
	}

	// 마이페이지 비밀번호 변경
	@RequestMapping(value = "/updatePw", method = RequestMethod.POST)
	@ResponseBody
	public String updatePw(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {

		// DTO에 저장하기 위해 String 변수에 JSP 입력 값을 삽입
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		// 받는 정보를 저장할 변수를 메모리에 올리기
		UserDTO pDTO = new UserDTO();
		int res = 0;
		pDTO.setUser_id(user_id);
		pDTO.setPassword(EncryptUtil.encHashSHA256(password));
		log.info("password : " + password);

		// 비밀번호 변경
		res = UserService.updatePw(pDTO);
		if (res == 0) {
			return "0";
		} else
			return "1";

	}

	// 마이페이지 주소 변경
	@RequestMapping(value = "/updateAddr", method = RequestMethod.POST)
	@ResponseBody
	public String updateAddr(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {

		// DTO에 저장하기 위해 String 변수에 JSP 입력 값을 삽입
		String user_id = request.getParameter("user_id");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");

		// 받는 정보를 저장할 변수를 메모리에 올리기
		UserDTO pDTO = new UserDTO();
		int res = 0;
		pDTO.setUser_id(user_id);
		pDTO.setAddr1(addr1);
		pDTO.setAddr2(addr2);
		log.info("addr1 : " + addr1);
		log.info("addr2 : " + addr2);

		// 비밀번호 변경
		res = UserService.updateAddr(pDTO);
		if (res == 0) {
			return "0";
		} else {
			session.setAttribute("SS_ADDR1", pDTO.getAddr1());
			session.setAttribute("SS_ADDR2", pDTO.getAddr2());
			return "1";

		}

	}

	// 마이페이지 회원 탈퇴
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) {
		log.info(this.getClass().getName() + " .deleteUser start!");

		// JSP에 세션으로 기록되어있는 유저의 번호를 전달
		String user_no = CmmUtil.nvl(request.getParameter("user_no"));

		log.info("user_no : " + user_no);

		// DTO에 그 값을 저장
		UserDTO pDTO = new UserDTO();
		pDTO.setUser_no(user_no);

		// Service로 DTO에 저장된 값을 전달
		int res = UserService.deleteUser(pDTO);
		log.info("res : " + res);

		// 탈퇴 결정
		if (res == 0) {
			return "0";
		} else {
			session.invalidate();
			return "1";
		}
	}

	// 회원관리
	@RequestMapping(value = "/manage")
	public String manage(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session)
			throws Exception {
		log.info(this.getClass().getName() + ".manage start!");

		UserDTO pDTO = new UserDTO();
		// 회원의 현재 페이지 수 가져오기
		String number = request.getParameter("no");
		int cnt = UserService.UserCounter(pDTO);

		// 검색
		String search = request.getParameter("search");
		log.info("search : " + search);
		// 페이징 할 떄 필요
		if (search == "" && number != null) {
			search = request.getParameter("research");
			log.info("search : " + search);

		} // 검색할 때 필요
		else if ((search == "" || search == null) && number == null) {
			session.removeAttribute("SS_SEARCH");
			search = "";

		}
		log.info("search : " + search);

		// 이 페이지에 처음 접속할 때 number는 값을 입력받지 않는다.따라서 null을 1로 변환해준다.
		if (number == null) {
			number = "1";
		}
		log.info("number : " + number);

		// 현재 페이지 수를 숫자로 나타냄
		int nowPage = Integer.parseInt(number);

		// 검색 로직 -----------------------------------
		if (search != "") {
			session.setAttribute("SS_SEARCH", search);

			search = "%" + search + "%";
			pDTO.setSearch(search);
			cnt = UserService.SearchCounter(pDTO);

			// 페이지의 끝번호를 나타냄
			int totalPage = cnt / 10;
			if (cnt % 10 > 0) {
				totalPage++;
			}

			// 페이지 수의 처음 번호
			int first = 1;

			// 페이지 수의 마지막 번호
			int last = first + 9;
			if (last > cnt / 10) {
				last = cnt / 10;
				if (cnt % 10 > 0) {
					last = last + 1;
				}
			}

			// SQL의 OFFSET에 환산되는 변수
			int offsetnum = (Integer.parseInt(number) - 1) * 10; // 10씩 건너 뛸 때마다 다른 게시물을 보여줌.

			if (last != 0) {
				if (nowPage > last) {
					if(Integer.parseInt(number) % 10 == 0) { // number가 10, 20, 30일 때는 first 번호가 바뀌면 안된다.
						first = first + ((Integer.parseInt(number)/10 - 1)*10);
					} else { // number가 0 단위가 아닌 1 ~ 9 일 때는 first 번호가 바뀌어야 한다.
					first = first + ((Integer.parseInt(number)/10)*10);
					}
					last = first + 9;
					if (last > cnt / 10) {
						last = cnt / 10;
						if (cnt % 10 > 0) {
							last = last + 1;
						}
					}
				}
			}

			log.info("cnt : " + cnt);
			log.info("offsetnum " + offsetnum);
			log.info("nowPage " + nowPage);
			log.info("first " + first);
			log.info("last " + last);
			log.info("totalPage : " + totalPage);

			pDTO.setCnt(cnt);
			pDTO.setOffsetnum(offsetnum);
			pDTO.setNowPage(nowPage);

			// 회원 수 게시판 가져오기
			List<UserDTO> rList = UserService.SearchList(pDTO);
			if (rList == null) {
				rList = new ArrayList<UserDTO>();
			}

			// 조회된 리스트 결과값 넣어주기
			model.addAttribute("cnt", cnt);
			model.addAttribute("offsetnum", offsetnum);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("first", first);
			model.addAttribute("last", last);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("rList", rList);

			// 변수 초기화(메모리 효율화 시키기 위해 사용)
			rList = null;

			log.info(this.getClass().getName() + ".manage end!");

			return "/manage";
			// 검색 로직 끝 ---------------------
		} else {

			// 페이지의 끝번호를 나타냄
			int totalPage = cnt / 10;
			if (cnt % 10 > 0) {
				totalPage++;
			}

			// 페이지 수의 처음 번호
			int first = 1;

			// 페이지 수의 마지막 번호
			int last = first + 9;
			if (last > cnt / 10) {
				last = cnt / 10;
				if (cnt % 10 > 0) {
					last = last + 1;
				}
			}

			// SQL의 OFFSET에 환산되는 변수
			int offsetnum = (Integer.parseInt(number) - 1) * 10; // 10씩 건너 뛸 때마다 다른 게시물을 보여줌.

			// 페이지 수 측정
			if (last != 0) {
				if (nowPage > last) {
					if(Integer.parseInt(number) % 10 == 0) { // number가 10, 20, 30일 때는 first 번호가 바뀌면 안된다.
						first = first + ((Integer.parseInt(number)/10 - 1)*10);
					} else { // number가 0 단위가 아닌 1 ~ 9 일 때는 first 번호가 바뀌어야 한다.
					first = first + ((Integer.parseInt(number)/10)*10);
					}
					last = first + 9;
					if (last > cnt / 10) {
						last = cnt / 10;
						if (cnt % 10 > 0) {
							last = last + 1;
						}
					}
				}
			}

			log.info("cnt : " + cnt);
			log.info("offsetnum " + offsetnum);
			log.info("nowPage " + nowPage);
			log.info("first " + first);
			log.info("last " + last);
			log.info("totalPage : " + totalPage);

			pDTO.setCnt(cnt);
			pDTO.setOffsetnum(offsetnum);
			pDTO.setNowPage(nowPage);

			// 회원 수 게시판 가져오기
			List<UserDTO> rList = UserService.getUserList(pDTO);
			if (rList == null) {
				rList = new ArrayList<UserDTO>();
			}

			// 조회된 리스트 결과값 넣어주기
			model.addAttribute("cnt", cnt);
			model.addAttribute("offsetnum", offsetnum);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("first", first);
			model.addAttribute("last", last);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("rList", rList);

			// 변수 초기화(메모리 효율화 시키기 위해 사용)
			rList = null;

			log.info(this.getClass().getName() + ".manage end!");

			return "/manage";
		}
	}

	// 회원관리 삭제
	@RequestMapping(value = "/dropUser")
	public String dropUser(HttpServletRequest request, ModelMap model) {
		log.info(this.getClass().getName() + ".dropUser Start!");

		// 게시글 번호 받아오기
		String user_no = request.getParameter("user_no");

		log.info("user_no : " + user_no);

		UserDTO pDTO = new UserDTO();
		pDTO.setUser_no(user_no);

		int res = UserService.dropUser(pDTO);

		url = "/manage.do";

		// 게시글 등록에 성공할 경우 res에 0보다 큰 수가 저장됨
		if (res > 0) {
			msg = "탈퇴가 완료되었습니다.";
		} else {
			msg = "탈퇴에 실패했습니다. 잠시 후 다시 시도하여 주십시오.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass().getName() + ".dropUser End!");
		return "/redirect";
	}
	
	// 카카오 로그인
	@RequestMapping (value = "kakao/redirect")
	public String redirect(@RequestParam("code") String code, HttpSession session, ModelMap model) {
		log.info(this.getClass().getName() + ".redirect start!");
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);
		
		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		if (userInfo.get("email") != null) {
			// 카카오 정보
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("SS_USER_NO", userInfo.get("user_no"));
			session.setAttribute("SS_USER_ID",  userInfo.get("user_id"));
			session.setAttribute("SS_USER_NAME", userInfo.get("nickname"));
			session.setAttribute("SS_NICK_NAME", userInfo.get("nickname"));
			session.setAttribute("SS_EMAIL", userInfo.get("email"));
			
			// 코로나 사이트 토큰 제공
			session.setAttribute("SS_ADDR1", "서울");
			session.setAttribute("SS_AUTHOR", "0");
			
			
			
		}
		
		model.addAttribute("msg", "카카오 로그인이 되었습니다.");
		model.addAttribute("url", "/docs.do");
		return "/redirect";
	}
	
	/**
	 * 채팅방 페이지 
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="chat/chats")
	public String chats() throws Exception {
		log.info(this.getClass());
		
		return "/chat/chats";
	}
	

	// test
	@RequestMapping(value = "test")
	public String test() {
		return "/test";
	}
	
	// test2
	@RequestMapping(value = "test2")
	public String test2() {
		return "/test2";
	}

	// 음성인식
	@RequestMapping(value = "TTS")
	public String TTS() {
		return "/TTS";
	}

}
