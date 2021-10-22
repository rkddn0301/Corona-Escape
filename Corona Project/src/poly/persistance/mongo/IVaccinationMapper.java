package poly.persistance.mongo;

import java.util.List;

import poly.dto.VaccinationDTO;

public interface IVaccinationMapper {
	
	/**
	 * 백신접종현황 컬렉션 생성하기
	 */
	public boolean createVaccine(String colNm) throws Exception;
	
	/**
	 * 백신접종현황 데이터 저장하기
	 */
	public int collectVaccination(List<VaccinationDTO> pList, String colNm) throws Exception;
	
	/**
	 * 백신접종현황 데이터 가져오기
	 */
	public List<VaccinationDTO> getVaccination(String colNm) throws Exception;

}
