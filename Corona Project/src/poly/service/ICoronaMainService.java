package poly.service;

import java.util.List;

import poly.dto.CoronaMainDTO;

public interface ICoronaMainService {
	
	/**
	 * 코로나 정보 수집하기
	 */
	public int coronaInformation() throws Exception;
	
	/**
	 * 코로나 정보 가져오기
	 */
	public List<CoronaMainDTO> getInformation() throws Exception;
	
	
	/**
	 * MongoDB 전일대비 코로나 확진율 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int CountryInformation() throws Exception;
	
	/**
	 * MongoDB 전일대비 코로나 확진율 데이터 가져오기
	 */
	public List<CoronaMainDTO> getCountryInformation() throws Exception;
	
	/**
	 * 샤회적 거리두기 단계 수집하기
	 */
	public int socialDistancing() throws Exception;
	
	/**
	 * 사회적 거리두기 단계 가져오기
	 */
	public List<CoronaMainDTO> getSocialDistancing() throws Exception;
	
	// 메인페이지에 일일확진자 넣기
	public String newCaseCatch() throws Exception;
	
	
	
}
