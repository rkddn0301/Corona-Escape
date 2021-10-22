package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NoticeDTO;
import poly.service.INoticeService;
import poly.util.CmmUtil;

@Controller
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());

	String msg = "";
	String url = "";

	@Resource(name = "NoticeService")
	private INoticeService NoticeService;

	// 공지사항 사이트
	@RequestMapping(value = "notice/noticelist")
	public String noticelist(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
		log.info(this.getClass().getName() + ".noticelist start!");

		NoticeDTO pDTO = new NoticeDTO();

		// 공지사항 현재 페이지 수 가져오기
		String number = request.getParameter("no");
		int cnt = NoticeService.NoticeCounter(pDTO);

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
			cnt = NoticeService.SearchCounter(pDTO);

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
			List<NoticeDTO> rList = NoticeService.SearchList(pDTO);
			if (rList == null) {
				rList = new ArrayList<NoticeDTO>();
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

			log.info(this.getClass().getName() + ".noticelist end!");

			return "/notice/noticelist";
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

			// 게시판 리스트 가져오기
			List<NoticeDTO> rList = NoticeService.getNoticeList(pDTO);

			if (rList == null) {
				rList = new ArrayList<NoticeDTO>();
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

			log.info(this.getClass().getName() + ".noticelist end!");
			return "/notice/noticelist";
		}
	}

	// 공지사항 등록 사이트
	@RequestMapping(value = "notice/noticeregist")
	public String noticeregist() {
		log.info(this.getClass().getName() + ".noticeregist start!");

		log.info(this.getClass().getName() + ".noticeregist end!");

		return "/notice/noticeregist";
	}

	// 공지사항 등록 로직
	@RequestMapping(value = "notice/registsuccess")
	public String registsuccess(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		// jsp에 기록된 내용을 전달
		String notice_title = CmmUtil.nvl(request.getParameter("notice_title"));
		String notice_content = CmmUtil.nvl(request.getParameter("notice_content"));
		String n_user_no = CmmUtil.nvl(request.getParameter("user_no"));
		String n_user_id = CmmUtil.nvl(request.getParameter("user_id"));
		String n_nick_name = CmmUtil.nvl(request.getParameter("nick_name"));
		String n_author = CmmUtil.nvl(request.getParameter("author"));

		// DTO에 그 값을 저장
		NoticeDTO pDTO = new NoticeDTO();
		pDTO.setNotice_title(notice_title);
		pDTO.setNotice_content(notice_content);
		pDTO.setN_user_no(n_user_no);
		pDTO.setN_user_id(n_user_id);
		pDTO.setN_nick_name(n_nick_name);
		pDTO.setN_author(n_author);

		// Service로 DTO에 저장된 갑승ㄹ 전달
		int res = NoticeService.insertNotice(pDTO);
		log.info("res : " + res);

		url = "/notice/noticelist.do";

		// 등록
		if (res == 0) {
			msg = "등록에 실패했습니다.";
		} else {
			msg = "등록이 완료되었습니다.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/redirect";

	}

	// 공지사항 상세
	@RequestMapping(value = "notice/noticedetail")
	public String noticedetail(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".noticedetail Start!");

		// 게시글 번호 받아오기
		String notice_no = request.getParameter("no");

		// 게시글 번호를 DTO에 저장
		NoticeDTO pDTO = new NoticeDTO();
		int res = 0;
		pDTO.setNotice_no(notice_no);

		// 저장한 DTO를 서비스로 전달 및 res에 조회수 기능 추가
		res = NoticeService.insertReadCnt(pDTO);
		NoticeDTO rDTO = NoticeService.NoticeDetail(pDTO);

		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트함
		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/notice/noticelist.do");
			return "/redirect";
		}
		model.addAttribute("rDTO", rDTO);
		log.info(this.getClass().getName() + ".noticedetail End!");

		return "/notice/noticedetail";

	}

	// 게시글 삭제
	@RequestMapping(value = "notice/NoticeDelete")
	public String NoticeDelete(HttpServletRequest request, ModelMap model) {
		log.info(this.getClass().getName() + ".NoticeDelete Start!");

		// 게시글 번호 받아오기
		String notice_no = request.getParameter("no");

		NoticeDTO pDTO = new NoticeDTO();
		pDTO.setNotice_no(notice_no);

		int res = NoticeService.NoticeDelete(pDTO);

		url = "/notice/noticelist.do";

		// 게시글 등록에 성공할 경우 res에 0보다 큰 수가 저장됨
		if (res > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제에 실패했습니다. 잠시 후 다시 시도하여 주십시오.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass().getName() + ".NoticeDelete End!");
		return "/redirect";
	}

	// 게시글 수정 사이트
	@RequestMapping(value = "notice/editnotice")
	public String editnotice(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// 게시글 번호 받아오기
		String notice_no = request.getParameter("no");

		// 게시글 번호를 DTO에 저장
		NoticeDTO pDTO = new NoticeDTO();
		pDTO.setNotice_no(notice_no);

		// 저장한 DTO를 서비스로 전달
		NoticeDTO rDTO = NoticeService.NoticeDetail(pDTO);

		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트함
		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/notice/noticelist.do");
			return "/redirect";
		}
		model.addAttribute("rDTO", rDTO);

		return "/notice/editnotice";

	}

	// 게시글 수정 로직
	@RequestMapping(value = "notice/editsuccess")
	public String editsuccess(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".editsuccess Start!");

		// 게시글 관련 정보를 jsp에서 가져오기
		String notice_no = request.getParameter("no");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");

		log.info("notice_no : " + notice_no);
		log.info("notice_title : " + notice_title);

		// 게시글 정보를 DTO에 저장
		NoticeDTO pDTO = new NoticeDTO();

		pDTO.setNotice_no(notice_no);
		pDTO.setNotice_title(notice_title);
		pDTO.setNotice_content(notice_content);

		// 게시글 정보를 서비스로 전달하여 처리함
		int res = NoticeService.Noticeupdate(pDTO);
		log.info("res : " + res);

		url = "/notice/noticelist.do";

		// 수정
		if (res == 0) {
			msg = "수정에 실패했습니다.";
		} else {
			msg = "수정이 완료되었습니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass().getName() + ".editsuccess End!");

		return "/redirect";
	}

}
