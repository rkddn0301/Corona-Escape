package poly.service;

import java.util.List;

import poly.dto.CountryDTO;

public interface ICountryService {

	// 서울

	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int SeoulCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getSeoulCityInformation() throws Exception;

	// 부산

	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int BusanCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getBusanCityInformation() throws Exception;

	// 대구
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int DaeguCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getDaeguCityInformation() throws Exception;

	// 인천
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int IncheonCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getIncheonCityInformation() throws Exception;

	// 광주
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int GwangjuCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getGwangjuCityInformation() throws Exception;

	// 대전
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int DaejeonCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getDaejeonCityInformation() throws Exception;

	// 울산
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int UlsanCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getUlsanCityInformation() throws Exception;

	// 경기도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int GyeonggiCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getGyeonggiCityInformation() throws Exception;

	// 강원도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int GangwonCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getGangwonCityInformation() throws Exception;

	// 충청북도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int NtChungcheongCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getNtChungcheongCityInformation() throws Exception;

	// 충청남도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int StChungcheongCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getStChungcheongCityInformation() throws Exception;

	// 전라북도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int NtJeollaCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getNtJeollaCityInformation() throws Exception;

	// 전라남도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int StJeollaCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getStJeollaCityInformation() throws Exception;

	// 경상북도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int NtGyeongsangCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getNtGyeongsangCityInformation() throws Exception;

	// 경상남도
	/**
	 * MongoDB 도시별 확진자수 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int StGyeongsangCityCollection() throws Exception;

	/**
	 * MongoDB 도시별 확진자수 가져오기
	 */
	public List<CountryDTO> getStGyeongsangCityInformation() throws Exception;

}
