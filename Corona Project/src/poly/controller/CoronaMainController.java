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

import poly.dto.CoronaMainDTO;
import poly.service.ICoronaMainService;
import poly.service.ICountryService;
import poly.service.IForeignCountryService;
import poly.service.IGubunService;
import poly.service.IVaccinationService;

@Controller
public class CoronaMainController {

	private Logger log = Logger.getLogger(this.getClass());

	// 코로나 서비스
	@Resource(name = "CoronaMainService")
	private ICoronaMainService CoronaMainService;

	// 도시별 확진자수 서비스
	@Resource(name = "CountryService")
	private ICountryService CountryService;

	// 구분별 확진자수 서비스
	@Resource(name = "GubunService")
	private IGubunService GubunService;
		
	// 국외발생현황 확진자 수 서비스
	@Resource(name = "ForeignCountryService")
	private IForeignCountryService ForeignCountryService;
		
	// 백신접종현황 서비스
	@Resource(name = "VaccinationService")
	private IVaccinationService VaccinationService;

	@RequestMapping(value = "corona/coronaTest")
	@ResponseBody
	public String coronaTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".coronaMain start!");

		
		CoronaMainService.coronaInformation();

		/*
		 * // JSON으로부터 받은 결과를 자바에서 처리가능한 데이터 구조로 변경 Map<String, Object> rMap =
		 * getAccStatService.getAccStatForJSON(pDTO);
		 * 
		 * // JSON으로부터 받은 결과를 자바에서 처리 가능한 데이터 구조로 변경한 변수를 JSP에 전달
		 * model.addAttribute("rMap", rMap);
		 */
		log.info(this.getClass().getName() + ".getAccStatForJSON end!");

		return "success";
	}
	
/**
 * 코로나 데이터 테스트
 */
	@RequestMapping(value="corona/coronadataTest")
	public String coronadataTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".coronadataTest Start!");
		log.info(this.getClass().getName() + ".coronadataTest End!");
		
		return "/corona/coronadataTest";
	}
	
/**
 * 
 * 코로나 데이터 가져오기
 */

	@RequestMapping(value="corona/coronaMain")
	@ResponseBody
	public List<CoronaMainDTO> coronaMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".coronaMain Start!");
		List<CoronaMainDTO> rList = CoronaMainService.getInformation();
		
		
		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}
		log.info(this.getClass().getName() + ".coronaMain End!");
		
		return rList;
	}
	
	/**
	 * 코로나 시.도별 데이터 Mongo에 넣기
	 */
	@RequestMapping(value = "corona/countryTest")
	@ResponseBody
	public String countryTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".countryTest start!");

		
		CoronaMainService.CountryInformation();

		/*
		 * // JSON으로부터 받은 결과를 자바에서 처리가능한 데이터 구조로 변경 Map<String, Object> rMap =
		 * getAccStatService.getAccStatForJSON(pDTO);
		 * 
		 * // JSON으로부터 받은 결과를 자바에서 처리 가능한 데이터 구조로 변경한 변수를 JSP에 전달
		 * model.addAttribute("rMap", rMap);
		 */
		log.info(this.getClass().getName() + ".countryTest end!");

		return "success";
	}
	
	/**
	 * 
	 * 코로나 시.도별 가져오기
	 */
	@RequestMapping(value="corona/countryMain")
	@ResponseBody
	public List<CoronaMainDTO> countryMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".countryMain Start!");
		List<CoronaMainDTO> cList = CoronaMainService.getCountryInformation();
		
		
		if (cList == null) {
			cList = new ArrayList<CoronaMainDTO>();
		}
		log.info(this.getClass().getName() + ".countryMain End!");
		
		return cList;
	}
	
	/*
	 * 거리두기 데이터 수집하기
	 */
	@RequestMapping(value = "corona/socialDistancing")
	@ResponseBody
	public String socialDistancing(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		log.info(this.getClass().getName() + ".socialDistancing Start!");
		
		CoronaMainService.socialDistancing();
		
		log.info(this.getClass().getName() + ".socialDistancing End!");
		
		return "success";
	}
	/*
	 * 거리두기 데이터 가져오기
	 */
	@RequestMapping(value="corona/getSocialDistancing")
	@ResponseBody
	public List<CoronaMainDTO> getSocialDistancing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getSocialDistancing Start!");
		List<CoronaMainDTO> rList = CoronaMainService.getSocialDistancing();
	
		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}
		log.info(this.getClass().getName() + ".getSocialDistancing End!");
		
		return rList;
	}
	
	// 스케쥴러 대신 테스트 용
	@RequestMapping(value="corona/getCoronadata")
	@ResponseBody
	public String getCoronadata(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info(this.getClass().getName() + ".getCoronadata start!");
		CoronaMainService.coronaInformation();
		CoronaMainService.CountryInformation();
		CoronaMainService.socialDistancing();
		CountryService.SeoulCityCollection();
		CountryService.BusanCityCollection();
		CountryService.IncheonCityCollection();
		CountryService.GwangjuCityCollection();
		CountryService.DaejeonCityCollection();
		CountryService.DaeguCityCollection();
		CountryService.UlsanCityCollection();
		CountryService.GyeonggiCityCollection();
		CountryService.GangwonCityCollection();
		CountryService.NtChungcheongCityCollection();
		CountryService.StChungcheongCityCollection();
		CountryService.NtJeollaCityCollection();
		CountryService.StJeollaCityCollection();
		CountryService.NtGyeongsangCityCollection();
		CountryService.StGyeongsangCityCollection();
		
		GubunService.gubunInformation();
		GubunService.genderInformation();
		
		ForeignCountryService.ForeignCountry();
		
		VaccinationService.Vaccination();
		log.info(this.getClass().getName() + ".getCoronadata End!");
		
		return "success";
		
	}
	
}
