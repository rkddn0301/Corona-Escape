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

import poly.dto.GubunDTO;
import poly.persistance.mongo.IGubunMapper;
import poly.util.CmmUtil;

@Component("GubunMapper")
public class GubunMapper implements IGubunMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createGubunCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createGubunCollection start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "cr_gubun_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createGubunCollection end!");
		return res;
	}

	@Override
	public int insertGubun(List<GubunDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertGubun Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<GubunDTO>();
		}

		Iterator<GubunDTO> it = pList.iterator();

		while (it.hasNext()) {
			GubunDTO pDTO = (GubunDTO) it.next();

			if (pDTO == null) {
				pDTO = new GubunDTO();
			}

			mongodb.insert(pDTO, colNm);

		}

		res = 1;

		log.info(this.getClass().getName() + ".insertGubun End!");

		return res;
	}

	@Override
	public List<GubunDTO> getGubunInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getGubunInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<GubunDTO> rList = new ArrayList<GubunDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		GubunDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new GubunDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String gubun = CmmUtil.nvl((String) current.get("gubun")); // 구분
			String confcase = CmmUtil.nvl((String) current.get("confcase")); // 확진자
			String confcaserate = CmmUtil.nvl((String) current.get("confcaserate")); // 확진자
			String death = CmmUtil.nvl((String) current.get("death")); // 확진자
			String deathrate = CmmUtil.nvl((String) current.get("deathrate")); // 확진자
			String criticalrate = CmmUtil.nvl((String) current.get("criticalrate")); // 확진자

			rDTO.setCollect_time(collect_time);
			rDTO.setGubun(gubun);
			rDTO.setConfcase(confcase);
			rDTO.setConfcaserate(confcaserate);
			rDTO.setDeath(death);
			rDTO.setDeathrate(deathrate);
			rDTO.setCriticalrate(criticalrate);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getGubunInformation End!");
		return rList;
	}

	@Override
	public boolean createGenderCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createGenderCollection start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "cr_gender_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createGenderCollection end!");
		return res;
	}

}
