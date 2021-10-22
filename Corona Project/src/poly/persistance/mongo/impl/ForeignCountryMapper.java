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

import poly.dto.ForeignCountryDTO;
import poly.persistance.mongo.IForeignCountryMapper;
import poly.util.CmmUtil;

@Component("ForeignCountryMapper")
public class ForeignCountryMapper implements IForeignCountryMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createForeign(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createForeign Start!");

		boolean res = false;

		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}

		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "FrIdx");
		res = true;

		log.info(this.getClass().getName() + ".createForeign End!");

		return res;
	}

	@Override
	public int collectForeignCountry(List<ForeignCountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".collectForeignCountry Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<ForeignCountryDTO>();
		}

		Iterator<ForeignCountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			ForeignCountryDTO pDTO = (ForeignCountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new ForeignCountryDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".collectForeignCountry End!");

		return res;
	}

	@Override
	public List<ForeignCountryDTO> getForeignCountry(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getForeignCountry Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<ForeignCountryDTO> rList = new ArrayList<ForeignCountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		ForeignCountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new ForeignCountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간

			String name = CmmUtil.nvl((String) current.get("name")); // 국가명
			String TotalCases = CmmUtil.nvl((String) current.get("TotalCases")); // 총 확진자 수
			String TotalDeaths = CmmUtil.nvl((String) current.get("TotalDeaths")); // 총 사망자 수
			String TotalRecovered = CmmUtil.nvl((String) current.get("TotalRecovered")); // 총 완치자 수
			
			if (TotalCases == "") {
				TotalCases = CmmUtil.nvl((String) current.get("0"));
				
			}
			if (TotalDeaths == "") {
				TotalDeaths = CmmUtil.nvl((String) current.get("0"));
			}
			if (TotalRecovered == "") {
				TotalRecovered = CmmUtil.nvl((String) current.get("0"));
			}
				

			rDTO.setCollect_time(collect_time);

			rDTO.setName(name);
			rDTO.setTotalCases(TotalCases);
			rDTO.setTotalDeaths(TotalDeaths);
			rDTO.setTotalRecovered(TotalRecovered);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getForeignCountry End!");
		return rList;
	}

}
