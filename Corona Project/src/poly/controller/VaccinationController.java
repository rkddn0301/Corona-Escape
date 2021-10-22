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

import poly.dto.VaccinationDTO;
import poly.service.IVaccinationService;

@Controller
public class VaccinationController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name ="VaccinationService")
	private IVaccinationService VaccinationService;
	
	/*
	 * 백신접종현황 수집하기
	 */
	@RequestMapping(value = "vaccine/Vaccination")
	@ResponseBody
	public String Vaccination(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		log.info(this.getClass().getName() + ".Vaccination Start!");
		
		VaccinationService.Vaccination();
		
		log.info(this.getClass().getName() + ".Vaccination End!");
		
		return "success";
	}
	
	/*
	 * 백신접종현황 가져오기
	 */
	@RequestMapping(value="vaccine/getVaccination")
	@ResponseBody
	public List<VaccinationDTO> getVaccination(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".getVaccination Start!");
		List<VaccinationDTO> rList = VaccinationService.getVaccination();
		
		
		if (rList == null) {
			rList = new ArrayList<VaccinationDTO>();
		}
		log.info(this.getClass().getName() + ".getVaccination End!");
		
		return rList;
	}

}
