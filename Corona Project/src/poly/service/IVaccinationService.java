package poly.service;

import java.util.List;

import poly.dto.VaccinationDTO;

public interface IVaccinationService {
	
	/**
	 * 백신접종현황 수집하기
	 */
	public int Vaccination() throws Exception;
	
	/**
	 * 백신접종현황 가져오기
	 */
	public List<VaccinationDTO> getVaccination() throws Exception;

}
