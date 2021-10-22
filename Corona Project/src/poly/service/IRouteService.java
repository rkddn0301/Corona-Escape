package poly.service;

import poly.dto.RouteDTO;

public interface IRouteService {
	
	// 루트별 값 추가하기
	int SelectRoute(RouteDTO pDTO) throws Exception;

}
