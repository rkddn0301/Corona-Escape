package poly.persistance.mongo;

import java.util.List;

import poly.dto.CountryDTO;

public interface ICountryMapper {

	// 서울

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createSeoulCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertSeoulCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getSeoulCityInformation(String colNm) throws Exception;

	// 부산

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createBusanCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertBusanCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getBusanCityInformation(String colNm) throws Exception;

	// 대구

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createDaeguCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertDaeguCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getDaeguCityInformation(String colNm) throws Exception;

	// 인천

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createIncheonCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertIncheonCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getIncheonCityInformation(String colNm) throws Exception;

	// 광주

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createGwangjuCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertGwangjuCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getGwangjuCityInformation(String colNm) throws Exception;

	// 대전

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createDaejeonCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertDaejeonCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getDaejeonCityInformation(String colNm) throws Exception;

	// 울산

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createUlsanCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertUlsanCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getUlsanCityInformation(String colNm) throws Exception;

	// 경기도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createGyeonggiCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertGyeonggiCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getGyeonggiCityInformation(String colNm) throws Exception;

	// 강원도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createGangwonCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertGangwonCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getGangwonCityInformation(String colNm) throws Exception;

	// 충청북도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createNtChungcheongCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertNtChungcheongCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getNtChungcheongCityInformation(String colNm) throws Exception;

	// 충청남도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createStChungcheongCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertStChungcheongCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getStChungcheongCityInformation(String colNm) throws Exception;

	// 전라북도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createNtJeollaCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertNtJeollaCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getNtJeollaCityInformation(String colNm) throws Exception;

	// 전라남도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createStJeollaCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertStJeollaCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getStJeollaCityInformation(String colNm) throws Exception;

	// 경상북도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createNtGyeongsangCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertNtGyeongsangCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getNtGyeongsangCityInformation(String colNm) throws Exception;

	// 경상남도

	/**
	 * MongoDB 도시별 컬렉션 생성하기
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */
	public boolean createStGyeongsangCityCollection(String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertStGyeongsangCityCollection(List<CountryDTO> pList, String colNm) throws Exception;

	/**
	 * MongoDB 도시별 컬렉션 데이터 가져오기
	 */
	public List<CountryDTO> getStGyeongsangCityInformation(String colNm) throws Exception;

}
