package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.HospitalDTO;
import poly.persistance.mapper.IHospitalMapper;
import poly.service.IHospitalService;

@Service("HospitalService")
public class HospitalService implements IHospitalService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "HospitalMapper")
	private IHospitalMapper HospitalMapper;

	@Override
	public List<HospitalDTO> getHospitalList(HospitalDTO pDTO) throws Exception {
		
		return HospitalMapper.getHospitalList(pDTO);
	}

	@Override
	public int HospitalCounter(HospitalDTO pDTO) throws Exception {
		return HospitalMapper.HospitalCounter(pDTO);
	}

	@Override
	public List<HospitalDTO> SearchList(HospitalDTO pDTO) throws Exception {
		return HospitalMapper.SearchList(pDTO);
	}

	@Override
	public int SearchCounter(HospitalDTO pDTO) throws Exception {
		return HospitalMapper.SearchCounter(pDTO);
	}
	
	

}
