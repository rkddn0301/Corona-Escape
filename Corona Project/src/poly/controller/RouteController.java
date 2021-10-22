package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.RouteDTO;
import poly.service.IRouteService;
import poly.service.impl.GubunService;
import poly.util.CmmUtil;

@Controller
public class RouteController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	String msg = "";
	String url = "";
	
	@Resource(name="RouteService")
	private IRouteService RouteService;
	
	@RequestMapping(value="route/routesite")
	public String routesite(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		log.info(this.getClass().getName() + ".routesite Start!");
		
		return "/route/routesite";
		
	}
	
	
	@RequestMapping(value = "route/routesuccess")
	@ResponseBody
	public String routesuccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(this.getClass().getName() + ".routesuccess Start!");
		
		// 웹에서 받는 정보를 저장할 변수
		RouteDTO pDTO = null;
		
		// 웹에서 받는 정보를 String 변수에 저장 시작. 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String에 저장
		
		String city = CmmUtil.nvl(request.getParameter("city"));
				
		log.info("city : " + city);
		
		// 받는 정보를 저장할 변수를 메모리에 올리기
		pDTO = new RouteDTO();
		
		pDTO.setReqroute(city);
		
		// 서비스로 보냄
		int res = RouteService.SelectRoute(pDTO);
		
		switch (res) {
		case 1: return "1";
		case 2: return "2";
		case 3: return "3";
		case 4: return "4";
		case 5: return "5";
		case 6: return "6";
		case 7: return "7";
		case 8: return "8";
		case 9: return "9";
		case 10: return "10";
		case 11: return "11";
		case 12: return "12";
		case 13: return "13";
		case 14: return "14";
		case 15: return "15";
		case 16: return "16";
		case 17: return "17";
		default : return "0";
		
		}
		
		
		
	}

}
