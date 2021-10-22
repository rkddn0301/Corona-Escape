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

import poly.dto.ForeignCountryDTO;
import poly.service.IForeignCountryService;

@Controller
public class ForeignCountryController {
	
private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="ForeignCountryService")
	private IForeignCountryService ForeignCountryService;
	
	// 국외발생현황 사이트
	@RequestMapping(value = "foreign/foreignsite")
	public String Foreignsite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName()+ "Foreignsite Start!");
	
		
		return "/foreign/foreignsite";
	}
	
	/*
	 * 국외 확진자 수집하기
	 */
	@RequestMapping(value = "foreign/ForeignCountry")
	@ResponseBody
	public String ForeignCountry(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		log.info(this.getClass().getName() + ".ForeignCountry Start!");
		
		ForeignCountryService.ForeignCountry();
		
		log.info(this.getClass().getName() + ".ForeignCountry End!");
		
		return "success";
	}
	
	/*
	 * 국외 확진자 가져오기
	 */
	@RequestMapping(value="foreign/getForeignCountry")
	@ResponseBody
	public List<ForeignCountryDTO> getForeignCountry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getForeignCountry Start!");
		List<ForeignCountryDTO> rList = ForeignCountryService.getForeignCountry();
		
		
		if (rList == null) {
			rList = new ArrayList<ForeignCountryDTO>();
		}
		log.info(this.getClass().getName() + ".getForeignCountry End!");
		
		return rList;
	}

}
