package poly.persistance.mongo;

import java.util.List;

import poly.dto.GubunDTO;

public interface IGubunMapper {
	
	/**
	 * 구분별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createGubunCollection(String colNm) throws Exception;
	
	/**
	 * 구분별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createGenderCollection(String colNm) throws Exception;
	
	/**
	 * 구분별 컬렉션 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertGubun(List<GubunDTO> pList, String colNm) throws Exception;
	
	/**
	 * 구분별 컬렉션 데이터 가져오기
	 */
	public List<GubunDTO> getGubunInformation(String colNm) throws Exception;
	
	

}
