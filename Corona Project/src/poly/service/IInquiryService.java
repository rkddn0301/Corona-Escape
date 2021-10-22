package poly.service;

import java.util.List;

import poly.dto.InquiryDTO;

public interface IInquiryService {

	// 문의내역 리스트(관리자)
	List<InquiryDTO> getInquiryList(InquiryDTO pDTO) throws Exception;

	// 문의내역 리스트(사용자)
	List<InquiryDTO> getInquiryUser(InquiryDTO uDTO) throws Exception;

	// 문의내역 리스트(관리자 게시물 수)
	int InquiryMCounter(InquiryDTO pDTO) throws Exception;

	// 문의내역 리스트(사용자 게시물 수)
	int InquiryUCounter(InquiryDTO uDTO) throws Exception;
	
	// 문의내역 검색(관리자)
	List<InquiryDTO> SearchList(InquiryDTO pDTO) throws Exception;
	
	// 문의내역 검색(사용자)
	List<InquiryDTO> SearchUser(InquiryDTO uDTO) throws Exception;
	
	// 문의내역 검색 수(관리자)
	int SearchMCounter(InquiryDTO pDTO) throws Exception;
	
	// 문의내역 검색 수(사용자)
	int SearchUCounter(InquiryDTO uDTO) throws Exception;

	// 문의하기
	int insertInquiry(InquiryDTO pDTO);

	// 문의내역 상세
	InquiryDTO InquiryDetail(InquiryDTO pDTO);

	// 문의내역 삭제
	int InquiryDelete(InquiryDTO pDTO);

	// 문의내역 수정
	int InquiryUpdate(InquiryDTO pDTO);

}
