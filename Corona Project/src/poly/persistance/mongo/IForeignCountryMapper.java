package poly.persistance.mongo;

import java.util.List;

import poly.dto.ForeignCountryDTO;

public interface IForeignCountryMapper {
	
	/**
	 * 국외발생현황 컬렉션 생성하기
	 */
	public boolean createForeign(String colNm) throws Exception;
	
	/**
	 * 국외발생현황 데이터 저장하기
	 */
	public int collectForeignCountry(List<ForeignCountryDTO> pList, String colNm) throws Exception;
	
	/**
	 * 국외발생현황 데이터 가져오기
	 */
	public List<ForeignCountryDTO> getForeignCountry(String colNm) throws Exception;

}
