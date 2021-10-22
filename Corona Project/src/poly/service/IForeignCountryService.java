package poly.service;

import java.util.List;

import poly.dto.ForeignCountryDTO;

public interface IForeignCountryService {
	
	/**
	 * 국외발생현황 수집하기
	 */
	public int ForeignCountry() throws Exception;
	
	/**
	 * 국외발생현황 가져오기
	 */
	public List<ForeignCountryDTO> getForeignCountry() throws Exception;

}
