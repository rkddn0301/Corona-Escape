package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.VaccinationDTO;
import poly.persistance.mongo.IVaccinationMapper;
import poly.util.CmmUtil;

@Component("VaccinationMapper")
public class VaccinationMapper implements IVaccinationMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createVaccine(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createVaccine Start!");

		boolean res = false;

		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}

		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "VaIdx");
		res = true;

		log.info(this.getClass().getName() + ".createVaccine End!");

		return res;
	}

	@Override
	public int collectVaccination(List<VaccinationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".collectVaccination Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<VaccinationDTO>();
		}

		Iterator<VaccinationDTO> it = pList.iterator();

		while (it.hasNext()) {
			VaccinationDTO pDTO = (VaccinationDTO) it.next();

			if (pDTO == null) {
				pDTO = new VaccinationDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".collectVaccination End!");

		return res;
	}

	@Override
	public List<VaccinationDTO> getVaccination(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getVaccination Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<VaccinationDTO> rList = new ArrayList<VaccinationDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		VaccinationDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new VaccinationDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간

			String city = CmmUtil.nvl((String) current.get("city")); // 국가명
			String TodayVaccine1 = CmmUtil.nvl((String) current.get("TodayVaccine1")); // 당일 1차 접종
			String TodayVaccine2 = CmmUtil.nvl((String) current.get("TodayVaccine2")); // 당일 2차 접종
			String TotalVaccine1 = CmmUtil.nvl((String) current.get("TotalVaccine1")); // 누계 1차 접종
			String TotalVaccine2 = CmmUtil.nvl((String) current.get("TotalVaccine2")); // 누계 2차 접종
				

			rDTO.setCollect_time(collect_time);

			rDTO.setCity(city);
			rDTO.setTodayVaccine1(TodayVaccine1);
			rDTO.setTodayVaccine2(TodayVaccine2);
			rDTO.setTotalVaccine1(TotalVaccine1);
			rDTO.setTotalVaccine2(TotalVaccine2);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getVaccination End!");
		return rList;
	}
	
	
	
	
	

}
