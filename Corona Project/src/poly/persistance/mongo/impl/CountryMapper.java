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

import poly.dto.CountryDTO;
import poly.persistance.mongo.ICountryMapper;
import poly.util.CmmUtil;

@Component("CountryMapper")
public class CountryMapper implements ICountryMapper {
	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	// 서울
	@Override
	public boolean createSeoulCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createSeoulCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "seoul_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createSeoulCityCollection End!");

		return res;
	}

	@Override
	public int insertSeoulCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertSeoulCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertSeoulCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getSeoulCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getSeoulCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String city = CmmUtil.nvl((String) current.get("city")); // 도시명
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCity(city);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getSeoulCityInformation End!");
		return rList;
	}

	// 부산

	@Override
	public boolean createBusanCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createBusanCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "busan_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createBusanCityCollection End!");

		return res;
	}

	@Override
	public int insertBusanCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertBusanCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertBusanCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getBusanCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getBusanCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String city = CmmUtil.nvl((String) current.get("city")); // 도시명
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCity(city);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getBusanCityInformation End!");
		return rList;
	}

	// 대구(미완)

	@Override
	public boolean createDaeguCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createDaeguCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "daegu_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createDaeguCityCollection End!");

		return res;
	}

	@Override
	public int insertDaeguCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertDaeguCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertDaeguCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getDaeguCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getDaeguCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getDaeguCityInformation End!");
		return rList;
	}

	// 인천(미완)

	@Override
	public boolean createIncheonCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createIncheonCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "incheon_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createIncheonCityCollection End!");

		return res;
	}

	@Override
	public int insertIncheonCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertIncheonCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertIncheonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getIncheonCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getIncheonCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);

			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getIncheonCityInformation End!");
		return rList;
	}

	// 광주(미완)

	@Override
	public boolean createGwangjuCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createGwangjuCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "gwangju_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createGwangjuCityCollection End!");

		return res;
	}

	@Override
	public int insertGwangjuCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertGwangjuCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertGwangjuCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGwangjuCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getGwangjuCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수
			

			rDTO.setCollect_time(collect_time);

			rDTO.setCases(cases);


			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getGwangjuCityInformation End!");
		return rList;
	}

	// 대전

	@Override
	public boolean createDaejeonCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createDaejeonCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "daejeon_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createDaejeonCityCollection End!");

		return res;
	}

	@Override
	public int insertDaejeonCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertDaejeonCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertDaejeonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getDaejeonCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getDaejeonCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String city = CmmUtil.nvl((String) current.get("city")); // 도시
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCity(city);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getDaejeonCityInformation End!");
		return rList;
	}

	// 울산

	@Override
	public boolean createUlsanCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createUlsanCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "ulsan_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createUlsanCityCollection End!");

		return res;
	}

	@Override
	public int insertUlsanCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertUlsanCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertUlsanCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getUlsanCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getUlsanCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getUlsanCityInformation End!");
		return rList;
	}

	// 경기도

	@Override
	public boolean createGyeonggiCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createGyeonggiCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "gyeonggi_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createGyeonggiCityCollection End!");

		return res;
	}

	@Override
	public int insertGyeonggiCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertGyeonggiCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertGyeonggiCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGyeonggiCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getGyeonggiCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getGyeonggiCityInformation End!");
		return rList;
	}

	// 강원도

	@Override
	public boolean createGangwonCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createGangwonCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "gangwon_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createGangwonCityCollection End!");

		return res;
	}

	@Override
	public int insertGangwonCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertGangwonCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertGangwonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGangwonCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getGangwonCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getGangwonCityInformation End!");
		return rList;
	}

	// 충청북도

	@Override
	public boolean createNtChungcheongCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createNtChungcheongCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "ntcc_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createNtChungcheongCityCollection End!");

		return res;
	}

	@Override
	public int insertNtChungcheongCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertNtChungcheongCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertNtChungcheongCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtChungcheongCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getNtChungcheongCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getNtChungcheongCityInformation End!");
		return rList;
	}

	// 충청남도

	@Override
	public boolean createStChungcheongCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createStChungcheongCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "stcc_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createStChungcheongCityCollection End!");

		return res;
	}

	@Override
	public int insertStChungcheongCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertStChungcheongCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertStChungcheongCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStChungcheongCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getStChungcheongCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getStChungcheongCityInformation End!");
		return rList;
	}

	// 전라북도

	@Override
	public boolean createNtJeollaCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createNtJeollaCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "ntjl_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createNtJeollaCityCollection End!");

		return res;

	}

	@Override
	public int insertNtJeollaCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertNtJeollaCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertNtJeollaCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtJeollaCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getNtJeollaCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getNtJeollaCityInformation End!");
		return rList;
	}

	// 전라남도

	@Override
	public boolean createStJeollaCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createStJeollaCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "stjl_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createStJeollaCityCollection End!");

		return res;
	}

	@Override
	public int insertStJeollaCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertStJeollaCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertStJeollaCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStJeollaCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getStJeollaCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getStJeollaCityInformation End!");
		return rList;
	}

	// 경상북도

	@Override
	public boolean createNtGyeongsangCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createNtGyeongsangCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "ntgs_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createNtGyeongsangCityCollection End!");

		return res;
	}

	@Override
	public int insertNtGyeongsangCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertNtGyeongsangCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertNtGyeongsangCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtGyeongsangCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getNtGyeongsangCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getNtGyeongsangCityInformation End!");
		return rList;
	}

	@Override
	public boolean createStGyeongsangCityCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createStGyeongsangCityCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장 시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1), "stgs_Idx");

		res = true;

		log.info(this.getClass().getName() + ".createStGyeongsangCityCollection End!");

		return res;
	}

	@Override
	public int insertStGyeongsangCityCollection(List<CountryDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertStGyeongsangCityCollection Start!");

		int res = 0;

		if (pList == null) {
			pList = new ArrayList<CountryDTO>();
		}

		Iterator<CountryDTO> it = pList.iterator();

		while (it.hasNext()) {
			CountryDTO pDTO = (CountryDTO) it.next();

			if (pDTO == null) {
				pDTO = new CountryDTO();
			}

			mongodb.insert(pDTO, colNm);
		}

		res = 1;

		log.info(this.getClass().getName() + ".insertStGyeongsangCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStGyeongsangCityInformation(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getStGyeongsangCityInformation Start!");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);

		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();

		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<CountryDTO> rList = new ArrayList<CountryDTO>();

		// 퀴즈팩별 정답을 일차별 저장하기
		CountryDTO rDTO = null;

		while (cursor.hasNext()) {
			rDTO = new CountryDTO();

			final DBObject current = cursor.next();

			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); // 수집시간
			String cases = CmmUtil.nvl((String) current.get("cases")); // 도시별 확진자수

			rDTO.setCollect_time(collect_time);
			rDTO.setCases(cases);

			rList.add(rDTO); // List에 데이터 저장
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getStGyeongsangCityInformation End!");
		return rList;
	}

}
