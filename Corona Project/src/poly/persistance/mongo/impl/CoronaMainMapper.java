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

import poly.dto.CoronaMainDTO;
import poly.persistance.mongo.ICoronaMainMapper;
import poly.util.CmmUtil;

@Component("CoronaMainMapper")
public class CoronaMainMapper implements ICoronaMainMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "cr_main_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createCollection End!");

		return res;
	}

	@Override
	public int insertInformation(List<CoronaMainDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformation Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CoronaMainDTO>();
		}

		Iterator<CoronaMainDTO> it = pList.iterator();

		while (it.hasNext()) {
			CoronaMainDTO pDTO = (CoronaMainDTO) it.next();

			if (pDTO == null) {
				pDTO = new CoronaMainDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".insertInformation End!");

		return res;
	}

	@Override
	public List<CoronaMainDTO> getInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CoronaMainDTO> rList = new ArrayList<CoronaMainDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CoronaMainDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CoronaMainDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String TotalCase = CmmUtil.nvl((String) current.get("TotalCase")); // 총 확진자
			String TotalRecovered = CmmUtil.nvl((String) current.get("TotalRecovered")); // 총 완치자
			String TotalDeath = CmmUtil.nvl((String) current.get("TotalDeath")); // 총 사망자
			String TodayRecovered = CmmUtil.nvl((String) current.get("TodayRecovered")); // 일일 완치자
			String TodayDeath = CmmUtil.nvl((String) current.get("TodayDeath")); // 일일 사망자
			String newCase = CmmUtil.nvl((String) current.get("newCase")); // 일일 확진자

			rDTO.setCollect_time(collect_time);
			rDTO.setTotalCase(TotalCase);
			rDTO.setTotalRecovered(TotalRecovered);
			rDTO.setTotalDeath(TotalDeath);
			rDTO.setTodayRecovered(TodayRecovered);
			rDTO.setTodayDeath(TodayDeath);
			rDTO.setNewCase(newCase);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}

		log.info(this.getClass().getName() + ".getInformation End!");

		return rList;
	}

	@Override
	public boolean createCountryCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createCountryCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "cr_country_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createCountryCollection End!");

		return res;
	}

	@Override
	public int insertCountryInformation(List<CoronaMainDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertCountryInformation Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CoronaMainDTO>();
		}

		Iterator<CoronaMainDTO> it = pList.iterator();

		while (it.hasNext()) {
			CoronaMainDTO pDTO = (CoronaMainDTO) it.next();

			if (pDTO == null) {
				pDTO = new CoronaMainDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".insertCountryInformation End!");

		return res;
	}

	@Override
	public List<CoronaMainDTO> getCountryInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getCountryInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CoronaMainDTO> rList = new ArrayList<CoronaMainDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CoronaMainDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CoronaMainDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String countryName = CmmUtil.nvl((String) current.get("countryName")); // 도시 이름
			String newCase = CmmUtil.nvl((String) current.get("newCase")); // 일일 확진자
			String countrycase = CmmUtil.nvl((String) current.get("countrycase")); // 총 확진자
			String recovered = CmmUtil.nvl((String) current.get("recovered")); // 총 완치자
			String death = CmmUtil.nvl((String) current.get("death")); // 총 사망자

			rDTO.setCollect_time(collect_time);
			rDTO.setCountryName(countryName);
			rDTO.setNewCase(newCase);
			rDTO.setCountrycase(countrycase);
			rDTO.setRecovered(recovered);
			rDTO.setDeath(death);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}

		log.info(this.getClass().getName() + ".getCountryInformation End!");

		return rList;
	}
	
	// 거리두기 단계 가져오기

	@Override
	public boolean createDistancing(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createDistancing Start!");

		boolean res = false;

		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}

		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "dtIdx");
		res = true;

		log.info(this.getClass().getName() + ".createDistancing End!");

		return res;
	}

	@Override
	public int collectsocialDistancing(List<CoronaMainDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".collectsocialDistancing Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CoronaMainDTO>();
		}

		Iterator<CoronaMainDTO> it = pList.iterator();

		while (it.hasNext()) {
			CoronaMainDTO pDTO = (CoronaMainDTO) it.next();

			if (pDTO == null) {
				pDTO = new CoronaMainDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".collectsocialDistancing End!");

		return res;

	}

	@Override
	public List<CoronaMainDTO> getSocialDistancing(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getSocialDistancing Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CoronaMainDTO> rList = new ArrayList<CoronaMainDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CoronaMainDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CoronaMainDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			
			
			String city = CmmUtil.nvl((String) current.get("city")); // 도시명
			String stage = CmmUtil.nvl((String) current.get("stage")); // 거리두기 단계
			

			rDTO.setCollect_time(collect_time);

			rDTO.setCity(city);
			rDTO.setStage(stage);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getSocialDistancing End!");
		return rList;
	}
}
