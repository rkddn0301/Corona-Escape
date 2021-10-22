package poly.persistance.mongo;

import java.util.List;

import poly.dto.CoronaMainDTO;

public interface ICoronaMainMapper {
	
	/**
	 * MongoDB 코로나 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createCollection(String colNm) throws Exception;
	
	/**
	 * MongoDB 코로나 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertInformation(List<CoronaMainDTO> pList, String colNm) throws Exception;
	
	/**
	 * MongoDB 코로나 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */
	public List<CoronaMainDTO> getInformation(String colNm) throws Exception;
	
	/**
	 * MongoDB 전일대비 코로나 확진율 데이터 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createCountryCollection(String colNm) throws Exception;
	
	/**
	 * MongoDB 전일대비 코로나 확진율 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertCountryInformation(List<CoronaMainDTO> pList, String colNm) throws Exception;
	
	/**
	 * MongoDB 전일대비 코로나 확진율 데이터  가져오기
	 */
	public List<CoronaMainDTO> getCountryInformation(String colNm) throws Exception;
	
	/**
	 * 거리두기 컬렉션 생성하기
	 */
	public boolean createDistancing(String colNm) throws Exception;
	
	/**
	 * 거리두기 데이터 저장하기
	 */
	public int collectsocialDistancing(List<CoronaMainDTO> pList, String colNm) throws Exception;
	
	/**
	 * 거리두기 데이터 가져오기
	 */
	public List<CoronaMainDTO> getSocialDistancing(String colNm) throws Exception;
 	

}
