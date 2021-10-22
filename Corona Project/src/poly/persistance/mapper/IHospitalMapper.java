package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.HospitalDTO;

@Mapper("HospitalMapper")
public interface IHospitalMapper {

	// 게시판 리스트
	List<HospitalDTO> getHospitalList(HospitalDTO pDTO) throws Exception;

	// 게시판 (게시물 수)
	int HospitalCounter(HospitalDTO pDTO) throws Exception;

	// 게시판 (검색)
	List<HospitalDTO> SearchList(HospitalDTO pDTO) throws Exception;

	// 게시판 (검색 수)
	int SearchCounter(HospitalDTO pDTO) throws Exception;

}
