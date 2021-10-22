package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.GubunDTO;
import poly.service.ICoronaMainService;
import poly.service.ICountryService;
import poly.service.IGubunService;
import poly.util.CmmUtil;

@Controller
public class GubunController {
	
private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="CoronaMainService")
	private ICoronaMainService CoronaMainService;
	
	@Resource(name="CountryService")
	private ICountryService CountryService;
	
	@Resource(name="GubunService")
	private IGubunService GubunService;
	
	@RequestMapping(value = "gubun/gubunsite")
	public String gubunsite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".gubunsite Start!");
		
	
		return "/gubun/gubunsite";
	}
	
	/*
	 * 구분별 자료 가져오기
	 */
	@RequestMapping(value="gubun/getgubun")
	@ResponseBody
	public List<GubunDTO> getgubun(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getgubun Start!");
		List<GubunDTO> rList = GubunService.getGubunInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<GubunDTO>();
		}
		log.info(this.getClass().getName() + ".getgubun End!");
		
		return rList;
	}
	
	/*
	 * 성별 자료 가져오기
	 */
	@RequestMapping(value="gubun/getgender")
	@ResponseBody
	public List<GubunDTO> getgender(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getgender Start!");
		List<GubunDTO> rList = GubunService.getGenderInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<GubunDTO>();
		}
		log.info(this.getClass().getName() + ".getgender End!");
		
		return rList;
	}
	
	// 구분별로 선택지를 분리함
	@RequestMapping(value = "gubun/gubunsuccess")
	@ResponseBody
	public String gubunsuccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".gubunsuccess Start!");
		
		// 웹에서 받는 정보를 저장할 변수
		GubunDTO pDTO = null;
		
		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		
		String gubun = CmmUtil.nvl(request.getParameter("gubun"));
				
		log.info("gubun : " + gubun);
		
		// 받는 정보를 저장할 변수를 메모리에 올리기
		pDTO = new GubunDTO();
		
		pDTO.setReqgubun(gubun);
		
		// 서비스로 보냄
		int res = GubunService.SelectGubun(pDTO);
		
		if (res == 1) {
			log.info(this.getClass().getName() + ".gubunsuccess End!");
			return "1";
		} else if (res == 2) {
			log.info(this.getClass().getName() + ".gubunsuccess End!");
			return "2";
		} else {
			return "0";
		}	
		
	}

}
