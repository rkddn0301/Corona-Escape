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

import poly.dto.HospitalDTO;
import poly.service.IHospitalService;

@Controller
public class HospitalController {
	
	private Logger log = Logger.getLogger(this.getClass());

	String msg = "";
	String url = "";
	
	@Resource(name = "HospitalService")
	private IHospitalService HospitalService;
	
	
	// 선별진료소 사이트
	@RequestMapping(value = "hospital/hospital")
	public String hospital(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
		log.info(this.getClass().getName() + ".hospital start!");
		HospitalDTO pDTO = new HospitalDTO();

		// 공지사항 현재 페이지 수 가져오기
		String number = request.getParameter("no");
		int cnt = HospitalService.HospitalCounter(pDTO);

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
			cnt = HospitalService.SearchCounter(pDTO);

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
			List<HospitalDTO> rList = HospitalService.SearchList(pDTO);
			if (rList == null) {
				rList = new ArrayList<HospitalDTO>();
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

			log.info(this.getClass().getName() + ".hospital end!");

			return "/hospital/hospital";
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
			List<HospitalDTO> rList = HospitalService.getHospitalList(pDTO);

			if (rList == null) {
				rList = new ArrayList<HospitalDTO>();
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

			log.info(this.getClass().getName() + ".hospital end!");
			return "/hospital/hospital";
		}
	
	}
	
	
	

}
