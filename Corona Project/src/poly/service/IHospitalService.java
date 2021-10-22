package poly.service;

import java.util.List;

import poly.dto.HospitalDTO;

public interface IHospitalService {

	// 게시판 리스트
	List<HospitalDTO> getHospitalList(HospitalDTO pDTO) throws Exception;

	// 게시판(게시물 수)
	int HospitalCounter(HospitalDTO pDTO) throws Exception;

	// 게시판 (검색)
	List<HospitalDTO> SearchList(HospitalDTO pDTO) throws Exception;

	// 게시판 (검색 수)
	int SearchCounter(HospitalDTO pDTO) throws Exception;

}
