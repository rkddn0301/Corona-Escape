package poly.service;

import java.util.List;

import poly.dto.GubunDTO;

public interface IGubunService {
	/**
	 * 구분별 정보 수집하기
	 */
	public int gubunInformation() throws Exception;
	
	/**
	 * 구분별 정보 가져오기
	 */
	public List<GubunDTO> getGubunInformation() throws Exception;
	
	/**
	 * 성별 정보 수집하기
	 */
	public int genderInformation() throws Exception;
	
	/**
	 * 성별 정보 가져오기
	 */
	public List<GubunDTO> getGenderInformation() throws Exception;
	
	// 구분별 값 추가하기
	int SelectGubun(GubunDTO pDTO) throws Exception;
}
