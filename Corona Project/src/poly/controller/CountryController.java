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

import poly.dto.CountryDTO;
import poly.service.ICoronaMainService;
import poly.service.ICountryService;

@Controller
public class CountryController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="CoronaMainService")
	private ICoronaMainService CoronaMainService;
	
	@Resource(name="CountryService")
	private ICountryService CountryService;
	
	// 시도별 발생현황 사이트
	@RequestMapping(value="country/countrysite")
	public String contrysite(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		log.info(this.getClass().getName() + ".contrysite Start!");
		
		
		return "/country/countrysite";
	}
	
	/*
	 * 서울 확진자 가져오기
	 */
	@RequestMapping(value="country/getSeoulcity")
	@ResponseBody
	public List<CountryDTO> getSeoulcity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getSeoulcity Start!");
		List<CountryDTO> rList = CountryService.getSeoulCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getSeoulcity End!");
		
		return rList;
	}
	
	/*
	 * 부산 확진자 가져오기
	 */
	@RequestMapping(value="country/getBusancity")
	@ResponseBody
	public List<CountryDTO> getBusancity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getBusancity Start!");
		List<CountryDTO> rList = CountryService.getBusanCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getBusancity End!");
		
		return rList;
	}
	
	/*
	 * 대구 확진자 가져오기
	 */
	@RequestMapping(value="country/getDaegucity")
	@ResponseBody
	public List<CountryDTO> getDaegucity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getDaegucity Start!");
		List<CountryDTO> rList = CountryService.getDaeguCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getDaegucity End!");
		
		return rList;
	}
	
	/*
	 * 인천 확진자 가져오기
	 */
	@RequestMapping(value="country/getIncheoncity")
	@ResponseBody
	public List<CountryDTO> getIncheoncity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getIncheoncity Start!");
		List<CountryDTO> rList = CountryService.getIncheonCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getIncheoncity End!");
		
		return rList;
	}
	
	/*
	 * 광주 확진자 가져오기
	 */
	@RequestMapping(value="country/getGwangjucity")
	@ResponseBody
	public List<CountryDTO> getGwangjuncity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getGwangjuncity Start!");
		List<CountryDTO> rList = CountryService.getGwangjuCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getGwangjuncity End!");
		
		return rList;
	}
	
	/*
	 * 대전 확진자 가져오기
	 */
	@RequestMapping(value="country/getDaejeoncity")
	@ResponseBody
	public List<CountryDTO> getDaejeoncity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getDaejeoncity Start!");
		List<CountryDTO> rList = CountryService.getDaejeonCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getDaejeoncity End!");
		
		return rList;
	}
	
	/*
	 * 울산 확진자 가져오기
	 */
	@RequestMapping(value="country/getUlsancity")
	@ResponseBody
	public List<CountryDTO> getUlsancity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getUlsancity Start!");
		List<CountryDTO> rList = CountryService.getUlsanCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getUlsancity End!");
		
		return rList;
	}
	
	/*
	 * 경기도 확진자 가져오기
	 */
	@RequestMapping(value="country/getGyeonggicity")
	@ResponseBody
	public List<CountryDTO> getGyeonggicity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getGyeonggicity Start!");
		List<CountryDTO> rList = CountryService.getGyeonggiCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getGyeonggicity End!");
		
		return rList;
	}
	
	/*
	 * 강원도 확진자 가져오기
	 */
	@RequestMapping(value="country/getGangwoncity")
	@ResponseBody
	public List<CountryDTO> getGangwoncity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getGangwoncity Start!");
		List<CountryDTO> rList = CountryService.getGangwonCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getGangwoncity End!");
		
		return rList;
	}
	
	/*
	 * 충청북도 확진자 가져오기
	 */
	@RequestMapping(value="country/getNtChungcheongcity")
	@ResponseBody
	public List<CountryDTO> getNtChungcheongcity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getNtChungcheongcity Start!");
		List<CountryDTO> rList = CountryService.getNtChungcheongCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getNtChungcheongcity End!");
		
		return rList;
	}
	
	/*
	 * 충청남도 확진자 가져오기
	 */
	@RequestMapping(value="country/getStChungcheongcity")
	@ResponseBody
	public List<CountryDTO> getStChungcheongcity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getStChungcheongcity Start!");
		List<CountryDTO> rList = CountryService.getStChungcheongCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getStChungcheongcity End!");
		
		return rList;
	}
	
	/*
	 * 전라북도 확진자 가져오기
	 */
	@RequestMapping(value="country/getNtJeollacity")
	@ResponseBody
	public List<CountryDTO> getNtJeollacity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getNtJeollacity Start!");
		List<CountryDTO> rList = CountryService.getNtJeollaCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getNtJeollacity End!");
		
		return rList;
	}
	
	/*
	 * 전라남도 확진자 가져오기
	 */
	@RequestMapping(value="country/getStJeollacity")
	@ResponseBody
	public List<CountryDTO> getStJeollacity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getStJeollacity Start!");
		List<CountryDTO> rList = CountryService.getStJeollaCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getStJeollacity End!");
		
		return rList;
	}
	
	/*
	 * 경상북도 확진자 가져오기
	 */
	@RequestMapping(value="country/getNtGyeongsangcity")
	@ResponseBody
	public List<CountryDTO> getNtGyeongsangcity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getNtGyeongsangcity Start!");
		List<CountryDTO> rList = CountryService.getNtGyeongsangCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getNtGyeongsangcity End!");
		
		return rList;
	}
	
	/*
	 * 경상남도 확진자 가져오기
	 */
	@RequestMapping(value="country/getStGyeongsangcity")
	@ResponseBody
	public List<CountryDTO> getStGyeongsangcity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getStGyeongsangcity Start!");
		List<CountryDTO> rList = CountryService.getStGyeongsangCityInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}
		log.info(this.getClass().getName() + ".getStGyeongsangcity End!");
		
		return rList;
	}
	
	

}
