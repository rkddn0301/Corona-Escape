package poly.service.impl;

import org.springframework.stereotype.Service;

import poly.dto.RouteDTO;
import poly.service.IRouteService;

@Service("RouteService")
public class RouteService implements IRouteService {

	@Override
	public int SelectRoute(RouteDTO pDTO) throws Exception {
		/*
		 * 1 = seoul, 2 = busan, 3 = daegu, 4 = incheon, 5 = gwangju
		 * 6 = daejeon, 7 = ulsan, 8 = gyeonggi, 9 = gangwon, 10 = northcc
		 * 11 = southcc, 12 = northjl, 13 = southjl, 14 = northgs, 15 = southgs
		 * 16 = jeju, 17 = sejong, 0 = error
		 */
		
		int res = 0;
		
		if (pDTO == null) {
			pDTO = new RouteDTO();
		}
		
		if (pDTO.getReqroute().equals("seoul")) {
			res = 1;
		} else if (pDTO.getReqroute().equals("busan")) {
			res = 2;
		} else if (pDTO.getReqroute().equals("daegu")) {
			res = 3;
		} else if (pDTO.getReqroute().equals("incheon")) {
			res = 4;
		} else if (pDTO.getReqroute().equals("gwangju")) {
			res = 5;
		} else if (pDTO.getReqroute().equals("daejeon")) {
			res = 6;
		} else if (pDTO.getReqroute().equals("ulsan")) {
			res = 7;
		} else if (pDTO.getReqroute().equals("gyeonggi")) {
			res = 8;
		} else if (pDTO.getReqroute().equals("gangwon")) {
			res = 9;
		} else if (pDTO.getReqroute().equals("northcc")) {
			res = 10;
		} else if (pDTO.getReqroute().equals("southcc")) {
			res = 11;
		} else if (pDTO.getReqroute().equals("northjl")) {
			res = 12;
		} else if (pDTO.getReqroute().equals("southjl")) {
			res = 13;
		} else if (pDTO.getReqroute().equals("northgs")) {
			res = 14;
		} else if (pDTO.getReqroute().equals("southgs")) {
			res = 15;
		} else if (pDTO.getReqroute().equals("jeju")) {
			res = 16;
		} else if (pDTO.getReqroute().equals("sejong")) {
			res = 17;
		}
		
		return res;
	}

}
