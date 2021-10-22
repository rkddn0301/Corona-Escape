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
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.InquiryDTO;
import poly.service.ICoronaMainService;
import poly.service.IInquiryService;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.util.CmmUtil;

@Controller
public class InquiryController {

	// 문의
	@Resource(name = "InquiryService")
	private IInquiryService InquiryService;

	// 회원 로직 서비스
	@Resource(name = "UserService")
	private IUserService UserService;

	// 메일 서비스
	@Resource(name = "MailService")
	private IMailService MailService;

	// 코로나 서비스
	@Resource(name = "CoronaMainService")
	private ICoronaMainService CoronaMainService;

	// 리다이렉트용 변수
	String msg = "";
	String url = "";

	// 로그
	private Logger log = Logger.getLogger(this.getClass());

	// 문의내역
	@RequestMapping(value = "inquiry/inquirylist")
	public String inquirylist(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
		log.info(this.getClass().getName() + ".inquirylist start!");

		// 게시글 번호 받아오기
		String i_user_no = request.getParameter("i_user_no");
		if (i_user_no == null) {
			i_user_no = "10";
		}

		// 관리자 시작----------------------
		if (i_user_no.equals("10")) {
			log.info("no : " + i_user_no);
			InquiryDTO pDTO = new InquiryDTO();

			// 문의내역 현재 페이지 수 가져오기
			String number = request.getParameter("no");
			int cnt = InquiryService.InquiryMCounter(pDTO);

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
				cnt = InquiryService.SearchMCounter(pDTO);

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
				List<InquiryDTO> rList = InquiryService.SearchList(pDTO);
				if (rList == null) {
					rList = new ArrayList<InquiryDTO>();
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

				log.info(this.getClass().getName() + ".inquirylist end!");

				return "/inquiry/inquirylist";
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

			// 게시판 리스트 가져오기
			List<InquiryDTO> rList = InquiryService.getInquiryList(pDTO);

			if (rList == null) {
				rList = new ArrayList<InquiryDTO>();
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

			return "/inquiry/inquirylist";
			}
			//관리자 끝------------------------------------
		} else {

			// 사용자 시작 --------------------------------

			log.info("no : " + i_user_no);
			InquiryDTO uDTO = new InquiryDTO();

			// 문의내역 현재 페이지 수 가져오기
			String number = request.getParameter("uno");
			// 게시글 번호를 DTO에 저장
			uDTO.setI_user_no(i_user_no);
			int cnt = InquiryService.InquiryUCounter(uDTO);

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
				uDTO.setSearch(search);
				uDTO.setI_user_no(i_user_no);
				cnt = InquiryService.SearchUCounter(uDTO);

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
						first = first + 10;
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

				uDTO.setCnt(cnt);
				uDTO.setOffsetnum(offsetnum);
				uDTO.setNowPage(nowPage);

				// 회원 수 게시판 가져오기
				List<InquiryDTO> uList = InquiryService.SearchUser(uDTO);
				if (uList == null) {
					uList = new ArrayList<InquiryDTO>();
				}

				// 조회된 리스트 결과값 넣어주기
				model.addAttribute("cnt", cnt);
				model.addAttribute("offsetnum", offsetnum);
				model.addAttribute("nowPage", nowPage);
				model.addAttribute("first", first);
				model.addAttribute("last", last);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("uList", uList);

				// 변수 초기화(메모리 효율화 시키기 위해 사용)
				uList = null;

				log.info(this.getClass().getName() + ".inquirylist end!");

				return "/inquiry/inquirylist";
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
				first = first + 10;
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

			uDTO.setCnt(cnt);
			uDTO.setOffsetnum(offsetnum);
			uDTO.setNowPage(nowPage);

			// 게시판 리스트 가져오기
			List<InquiryDTO> uList = InquiryService.getInquiryUser(uDTO);

			if (uList == null) {
				uList = new ArrayList<InquiryDTO>();
			}

			// 조회된 리스트 결과값 넣어주기
			model.addAttribute("cnt", cnt);
			model.addAttribute("offsetnum", offsetnum);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("first", first);
			model.addAttribute("last", last);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("uList", uList);

			// 변수 초기화(메모리 효율화 시키기 위해 사용)
			uList = null;

			log.info(this.getClass().getName() + ".inquirylist end!");

			return "/inquiry/inquirylist";
		} 
	  }
	}

	// 문의하기
	@RequestMapping(value = "inquiry/registinquiry")
	@ResponseBody
	public String registinquiry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// jsp에 기록된 내용을 전달
		String inquiry_title = CmmUtil.nvl(request.getParameter("inquiry_title"));
		String inquiry_content = CmmUtil.nvl(request.getParameter("inquiry_content"));
		String i_user_no = CmmUtil.nvl(request.getParameter("i_user_no"));
		String i_user_id = CmmUtil.nvl(request.getParameter("i_user_id"));
		String i_nick_name = CmmUtil.nvl(request.getParameter("i_nick_name"));
		String i_author = CmmUtil.nvl(request.getParameter("i_author"));

		// DTO에 그 값을 저장
		InquiryDTO pDTO = new InquiryDTO();
		pDTO.setInquiry_title(inquiry_title);
		pDTO.setInquiry_content(inquiry_content);
		pDTO.setI_user_no(i_user_no);
		pDTO.setI_user_id(i_user_id);
		pDTO.setI_nick_name(i_nick_name);
		pDTO.setI_author(i_author);

		// 등록
		int res = InquiryService.insertInquiry(pDTO);

		if (res == 1) {
			return "1";
		} else {
			return "0";
		}

	}

	// 문의사항 상세
	@RequestMapping(value = "inquiry/inquirydetail")
	public String inquirydetail(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".inquirydetail Start!");

		// 게시글 번호 받아오기
		String inquiry_no = request.getParameter("no");

		// 게시글 번호를 DTO에 저장
		InquiryDTO pDTO = new InquiryDTO();
		pDTO.setInquiry_no(inquiry_no);

		// 저장한 DTO를 서비스로 전달
		InquiryDTO rDTO = InquiryService.InquiryDetail(pDTO);

		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트함
		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/inquiry/inquiry.do");
			return "/redirect";
		}

		model.addAttribute("rDTO", rDTO);

		log.info(this.getClass().getName() + ".inquirydetail End!");

		return "/inquiry/inquirydetail";
	}

	// 문의사항 삭제
	@RequestMapping(value = "inquiry/InquiryDelete")
	public String InquiryDelete(HttpServletRequest request, ModelMap model) {
		log.info(this.getClass().getName() + ".InquiryDelete Start!");

		// 게시글 번호 받아오기
		String inquiry_no = request.getParameter("no");

		InquiryDTO pDTO = new InquiryDTO();
		pDTO.setInquiry_no(inquiry_no);

		int res = InquiryService.InquiryDelete(pDTO);

		url = "/inquiry/inquirylist.do";

		// 등록에 성공할 경우 res에 0보다 큰 수가 저장됨
		if (res > 0) {
			msg = "삭제되었습니다.";
		} else {
			msg = "삭제에 실패했습니다. 잠시 후 다시 시도하여 주십시오.";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass().getName() + ".InquiryDelete End!");
		return "/redirect";
	}

	// 문의사항 수정 사이트
	@RequestMapping(value = "inquiry/editinquiry")
	public String editinquiry(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// 게시글 번호 받아오기
		String inquiry_no = request.getParameter("no");

		// 게시글 번호를 DTO에 저장
		InquiryDTO pDTO = new InquiryDTO();
		pDTO.setInquiry_no(inquiry_no);

		// 저장한 DTO를 서비스로 전달
		InquiryDTO rDTO = InquiryService.InquiryDetail(pDTO);

		// 결과가 없을 경우 메시지와 함께 게시글 목록으로 리다이렉트함
		if (rDTO == null) {
			model.addAttribute("msg", "존재하지 않는 게시물입니다.");
			model.addAttribute("url", "/inquiry/inquirylist.do");
			return "/redirect";
		}
		model.addAttribute("rDTO", rDTO);

		return "/inquiry/editinquiry";

	}

	// 문의사항 수정 로직
	@RequestMapping(value = "inquiry/editIQ")
	public String editIQ(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ".editIQ Start!");

		// 게시글 관련 정보를 jsp에서 가져오기
		String inquiry_no = request.getParameter("no");
		String inquiry_us_content = request.getParameter("inquiry_us_content");
		String inquiry_content = request.getParameter("inquiry_content");
		
		String inquiry_update = inquiry_us_content + "\n---------------------------------------------------------------------------------------------------------------------------------------------------------\n안녕하세요 관리자 입니다.\n" 
		+ inquiry_content; 

		log.info("inquiry_no : " + inquiry_no);
		log.info("inquiry_update : " + inquiry_update);

		// 게시글 정보를 DTO에 저장
		InquiryDTO pDTO = new InquiryDTO();

		pDTO.setInquiry_no(inquiry_no);
		pDTO.setInquiry_content(inquiry_update);

		// 게시글 정보를 서비스로 전달하여 처리함
		int res = InquiryService.InquiryUpdate(pDTO);
		log.info("res : " + res);

		url = "/inquiry/inquirylist.do";

		// 수정
		if (res == 0) {
			msg = "답변에 실패했습니다.";
		} else {
			msg = "답변이 완료되었습니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info(this.getClass().getName() + ".editIQ End!");

		return "/redirect";
	}

}
