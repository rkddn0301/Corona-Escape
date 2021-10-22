package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.InquiryDTO;
import poly.persistance.mapper.IInquiryMapper;
import poly.service.IInquiryService;

@Service("InquiryService")
public class InquiryService implements IInquiryService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="InquiryMapper")
	private IInquiryMapper InquiryMapper;
	

	@Override
	public List<InquiryDTO> getInquiryList(InquiryDTO pDTO) throws Exception {
		return InquiryMapper.getInquiryList(pDTO);
	}
	
	@Override
	public List<InquiryDTO> getInquiryUser(InquiryDTO uDTO) throws Exception {
		return InquiryMapper.getInquiryUser(uDTO);
	}
	
	@Override
	public int InquiryMCounter(InquiryDTO pDTO) throws Exception {
		return InquiryMapper.InquiryMCounter(pDTO);
	}

	@Override
	public int InquiryUCounter(InquiryDTO uDTO) throws Exception {
		return InquiryMapper.InquiryUCounter(uDTO);
	}
	
	@Override
	public List<InquiryDTO> SearchList(InquiryDTO pDTO) throws Exception {
		return InquiryMapper.SearchList(pDTO);
	}

	@Override
	public List<InquiryDTO> SearchUser(InquiryDTO uDTO) throws Exception {
		return InquiryMapper.SearchUser(uDTO);
	}

	@Override
	public int SearchMCounter(InquiryDTO pDTO) throws Exception {
		return InquiryMapper.SearchMCounter(pDTO);
	}

	@Override
	public int SearchUCounter(InquiryDTO uDTO) throws Exception {
		return InquiryMapper.SearchUCounter(uDTO);
	}

	@Override
	public int insertInquiry(InquiryDTO pDTO) {
		return InquiryMapper.insertInquiry(pDTO);
	}

	@Override
	public InquiryDTO InquiryDetail(InquiryDTO pDTO) {
		return InquiryMapper.InquiryDetail(pDTO);
	}

	@Override
	public int InquiryDelete(InquiryDTO pDTO) {
		return InquiryMapper.InquiryDelete(pDTO);
	}

	@Override
	public int InquiryUpdate(InquiryDTO pDTO) {
		return InquiryMapper.InquiryUpdate(pDTO);
	}
	

}
