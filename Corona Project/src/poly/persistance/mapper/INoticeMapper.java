package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NoticeDTO;

@Mapper("NoticeMapper")
public interface INoticeMapper {

	// 게시판 리스트
	List<NoticeDTO> getNoticeList(NoticeDTO pDTO) throws Exception;
	
	// 게시판 (게시물 수)
	int NoticeCounter(NoticeDTO pDTO) throws Exception;
	
	// 게시판 (검색)
	List<NoticeDTO> SearchList(NoticeDTO pDTO) throws Exception;
	
	// 게시판 (검색 수)
	int SearchCounter(NoticeDTO pDTO) throws Exception;

	// 게시판 작성
	int insertNotice(NoticeDTO pDTO);

	// 게시판 상세
	NoticeDTO NoticeDetail(NoticeDTO pDTO);

	// 게시판 보기(조회수 추가)
	int insertReadCnt(NoticeDTO pDTO);

	// 게시글 삭제
	int NoticeDelete(NoticeDTO pDTO);
	
	// 게시글 수정
	int Noticeupdate(NoticeDTO pDTO);

}
