package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.GubunDTO;
import poly.persistance.mongo.IGubunMapper;
import poly.service.IGubunService;
import poly.util.DateUtil;

@Service("GubunService")
public class GubunService implements IGubunService {
	
	@Resource(name = "GubunMapper")
	private IGubunMapper GubunMapper; // MongoDB에 저장할 Mapper
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int gubunInformation() throws Exception {
		log.info(this.getClass().getName() + ".gubunInformation Start!");

		int res = 0;

		List<GubunDTO> pList = new ArrayList<GubunDTO>();

		// 연령별 현황
		String url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.data_table").eq(4);

		Iterator<Element> gubunList = element.select("tbody > tr").iterator();

		while (gubunList.hasNext()) {

			Element gubuninfo = gubunList.next();

			String gubun = gubuninfo.select("th").text();
			String confcase = gubuninfo.select("td > span").eq(0).text();
			String confcaserate = gubuninfo.select("td > span.txt_clr_nh").eq(0).text();
			String death = gubuninfo.select("td > span").eq(2).text();
			String deathrate = gubuninfo.select("td > span.txt_clr_nh").eq(1).text();
			String criticalrate = gubuninfo.select("td > span.txt_clr_nh").eq(2).text();

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			GubunDTO pDTO = new GubunDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setGubun(gubun);
			pDTO.setConfcase(confcase);
			pDTO.setConfcaserate(confcaserate);
			pDTO.setDeath(death);
			pDTO.setDeathrate(deathrate);
			pDTO.setCriticalrate(criticalrate);

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "GubunInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		GubunMapper.createGubunCollection(colNm);

		// MongoDB에 저장하기
		GubunMapper.insertGubun(pList, colNm);

		log.info(this.getClass().getName() + ".gubunInformation End!");
		return res;
	}

	@Override
	public List<GubunDTO> getGubunInformation() throws Exception {
		log.info(this.getClass().getName() + ".getGubunInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "GubunInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<GubunDTO> rList = GubunMapper.getGubunInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<GubunDTO>();
		}

		log.info(this.getClass().getName() + ".getGubunInformation End!");

		return rList;
	}
	
	// 성별

	@Override
	public int genderInformation() throws Exception {
		log.info(this.getClass().getName() + ".genderInformation Start!");

		int res = 0;

		List<GubunDTO> pList = new ArrayList<GubunDTO>();

		// 성별 현황
		String url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.data_table").eq(3);

		Iterator<Element> gubunList = element.select("tbody > tr").iterator();

		while (gubunList.hasNext()) {

			Element gubuninfo = gubunList.next();

			String gubun = gubuninfo.select("th").text();
			String confcase = gubuninfo.select("td > span").eq(0).text();
			String confcaserate = gubuninfo.select("td > span.txt_clr_nh").eq(0).text();
			String death = gubuninfo.select("td > span").eq(2).text();
			String deathrate = gubuninfo.select("td > span.txt_clr_nh").eq(1).text();
			String criticalrate = gubuninfo.select("td > span.txt_clr_nh").eq(2).text();

			
			

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			GubunDTO pDTO = new GubunDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setGubun(gubun);
			pDTO.setConfcase(confcase);
			pDTO.setConfcaserate(confcaserate);
			pDTO.setDeath(death);
			pDTO.setDeathrate(deathrate);
			pDTO.setCriticalrate(criticalrate);

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "GenderInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		GubunMapper.createGenderCollection(colNm);

		// MongoDB에 저장하기
		GubunMapper.insertGubun(pList, colNm);

		log.info(this.getClass().getName() + ".genderInformation End!");
		return res;
	}

	@Override
	public List<GubunDTO> getGenderInformation() throws Exception {
		log.info(this.getClass().getName() + ".getGenderInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "GenderInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<GubunDTO> rList = GubunMapper.getGubunInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<GubunDTO>();
		}

		log.info(this.getClass().getName() + ".getGenderInformation End!");

		return rList;
	}

	@Override
	public int SelectGubun(GubunDTO pDTO) throws Exception {
		// 연령별 : 1, 성별 : 2, 기타 에러 : 0
		int res = 0;
		
		if (pDTO == null) {
			pDTO = new GubunDTO();
		}
		
		if (pDTO.getReqgubun().equals("age")) {
			res = 1;
		} else if (pDTO.getReqgubun().equals("gender")) {
			res = 2;
		}
		
		return res;
	}

}
