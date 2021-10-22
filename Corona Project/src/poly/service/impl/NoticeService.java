package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.persistance.mapper.INoticeMapper;
import poly.service.INoticeService;

@Service("NoticeService")
public class NoticeService implements INoticeService {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "NoticeMapper")
	private INoticeMapper NoticeMapper;

	@Override
	public List<NoticeDTO> getNoticeList(NoticeDTO pDTO) throws Exception {
		return NoticeMapper.getNoticeList(pDTO);
	}

	@Override
	public int NoticeCounter(NoticeDTO pDTO) throws Exception {
		return NoticeMapper.NoticeCounter(pDTO);
	}

	@Override
	public List<NoticeDTO> SearchList(NoticeDTO pDTO) throws Exception {
		return NoticeMapper.SearchList(pDTO);
	}

	@Override
	public int SearchCounter(NoticeDTO pDTO) throws Exception {
		return NoticeMapper.SearchCounter(pDTO);
	}

	@Override
	public int insertNotice(NoticeDTO pDTO) {
		return NoticeMapper.insertNotice(pDTO);
	}

	@Override
	public NoticeDTO NoticeDetail(NoticeDTO pDTO) {
		return NoticeMapper.NoticeDetail(pDTO);
	}

	@Override
	public int insertReadCnt(NoticeDTO pDTO) {
		return NoticeMapper.insertReadCnt(pDTO);
	}

	@Override
	public int NoticeDelete(NoticeDTO pDTO) {
		return NoticeMapper.NoticeDelete(pDTO);
	}

	@Override
	public int Noticeupdate(NoticeDTO pDTO) {
		return NoticeMapper.Noticeupdate(pDTO);
	}

}
