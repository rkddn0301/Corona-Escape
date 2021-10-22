package poly.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import poly.dto.CoronaMainDTO;
import poly.persistance.mongo.ICoronaMainMapper;
import poly.service.ICoronaMainService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("CoronaMainService")
public class CoronaMainService implements ICoronaMainService {

	@Resource(name = "CoronaMainMapper")
	private ICoronaMainMapper CoronaMainMapper; // MongoDB에 저장할 mapper

	private Logger log = Logger.getLogger(this.getClass());
	
	// 셀레니움
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/javapractice/kang/Corona Project/WebContent/chromedriver/chromedriver.exe";

	public static WebDriver driver;

	/*
	 * JSON 형식을 파싱하는 구간
	 */
	private String getUrlForJSON(String callUrl) {
		log.info(this.getClass().getName() + ".getUrlForJSON start!");

		log.info("Requeted URL : " + callUrl);

		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;

		// json 결과값이 저장되는 변수
		String json = "";

		// SSL 적용된 사이트일 경우, 데이터 증명을 위해 사용
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		try {
			// 웹 사이트 접속을 위한 URL 파싱
			URL url = new URL(callUrl);

			// 접속
			urlConn = url.openConnection();

			// 접속하면, 응답을 60초(60 * 1000ms) 동안 기다림
			if (urlConn != null) {
				urlConn.setReadTimeout(60 * 1000);
			}

			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8"));

				BufferedReader bufferedReader = new BufferedReader(in);

				// 주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception URL : " + callUrl, e);
		}
		json = sb.toString(); // json 결과 저장
		log.info("JSON result : " + json);
		log.info(this.getClass().getName() + ".getUrlForJSON End!");

		return json;
	}

	/*
	 * 코로나 JSON 형식을 받아서 컬렉션을 생성하는 구간
	 */
	@Override
	public int coronaInformation() throws Exception {
		log.info(this.getClass().getName() + ".coronaInformation Start!");

		int res = 0;

		List<CoronaMainDTO> pList = new ArrayList<CoronaMainDTO>();
		CoronaMainDTO pDTO = new CoronaMainDTO();

		String url = "https://api.corona-19.kr/korea/?serviceKey=4HIKCanG5lftsiW19AEkdQ26RBmwjrvSc";

		log.info("url : " + url);
		// OpenAPI 호출을 위한 파라미터 저장하기
		pDTO.setUrl(url);

		// JSON 결과 받아오기
		String json = getUrlForJSON(CmmUtil.nvl(pDTO.getUrl()));

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위한 객체를 메모리에 올림
		JSONParser parser = new JSONParser();

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위해 자바 최상위 Object 변환
		Object obj = parser.parse(json);

		// 변환된 Object 객체를 json 데이터 구조로 변경
		JSONObject jsonObject = (JSONObject) obj;

		// 요청한 파라미터 가져오기
		String TotalCase = CmmUtil.nvl((String) jsonObject.get("TotalCase"));
		log.info("TotalCase :: " + TotalCase);

		pDTO.setTotalCase(TotalCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		String TotalRecovered = CmmUtil.nvl((String) jsonObject.get("TotalRecovered"));
		log.info("TotalRecovered :: " + TotalRecovered);

		pDTO.setTotalRecovered(TotalRecovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		String TotalDeath = CmmUtil.nvl((String) jsonObject.get("TotalDeath"));
		log.info("TotalDeath :: " + TotalDeath);

		pDTO.setTotalDeath(TotalDeath); // 데이터 저장

		// 요청한 파라미터 가져오기
		String TodayRecovered = CmmUtil.nvl((String) jsonObject.get("TodayRecovered"));
		log.info("TodayRecovered :: " + TodayRecovered);

		pDTO.setTodayRecovered(TodayRecovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		String TodayDeath = CmmUtil.nvl((String) jsonObject.get("TodayDeath"));
		log.info("TodayDeath :: " + TodayDeath);

		pDTO.setTodayDeath(TodayDeath); // 데이터 저장

		// 요청한 파라미터 가져오기(다른 API에 있어서 따로 만든 Service에서 가져옴)
		String newCase = CmmUtil.nvl(newCaseCatch());
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);

		String colNm = "CoronaInformation " + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		CoronaMainMapper.createCollection(colNm);

		// MongoDB에 데이터저장하기
		CoronaMainMapper.insertInformation(pList, colNm);

		log.info(this.getClass().getName() + ".coronaInformation End!");
		return res;
	}
	/*
	 * 생성한 컬렉션을 조회하는 구간
	 */

	@Override
	public List<CoronaMainDTO> getInformation() throws Exception {
		log.info(this.getClass().getName() + ".getInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "CoronaInformation " + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CoronaMainDTO> rList = CoronaMainMapper.getInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}

		log.info(this.getClass().getName() + ".getInformation End!");

		return rList;
	}

	@Override
	public int CountryInformation() throws Exception {
		log.info(this.getClass().getName() + ".CountryInformation Start!");

		int res = 0;

		List<CoronaMainDTO> pList = new ArrayList<CoronaMainDTO>();
		CoronaMainDTO pDTO = new CoronaMainDTO();

		String url = "https://api.corona-19.kr/korea/country/new/?serviceKey=4HIKCanG5lftsiW19AEkdQ26RBmwjrvSc";

		log.info("url : " + url);
		// OpenAPI 호출을 위한 파라미터 저장하기
		pDTO.setUrl(url);

		// JSON 결과 받아오기
		String json = getUrlForJSON(CmmUtil.nvl(pDTO.getUrl()));

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위한 객체를 메모리에 올림
		JSONParser parser = new JSONParser();

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위해 자바 최상위 Object 변환
		Object obj = parser.parse(json);

		// 변환된 Object 객체를 json 데이터 구조로 변경
		JSONObject jsonObject = (JSONObject) obj;

		/*
		 * 상위 json을 가져온다. 하위 json들과 같이 있는 상위 json은 한 번 더 경로를 지정해줘야 한다.
		 */
		JSONObject koreaObject = ((JSONObject) jsonObject.get("korea"));
		JSONObject seoulObject = ((JSONObject) jsonObject.get("seoul"));
		JSONObject busanObject = ((JSONObject) jsonObject.get("busan"));
		JSONObject daeguObject = ((JSONObject) jsonObject.get("daegu"));
		JSONObject incheonObject = ((JSONObject) jsonObject.get("incheon"));
		JSONObject gwangjuObject = ((JSONObject) jsonObject.get("gwangju"));
		JSONObject daejeonObject = ((JSONObject) jsonObject.get("daejeon"));
		JSONObject ulsanObject = ((JSONObject) jsonObject.get("ulsan"));
		JSONObject sejongObject = ((JSONObject) jsonObject.get("sejong"));
		JSONObject gyeonggiObject = ((JSONObject) jsonObject.get("gyeonggi"));
		JSONObject gangwonObject = ((JSONObject) jsonObject.get("gangwon"));
		JSONObject chungbukObject = ((JSONObject) jsonObject.get("chungbuk"));
		JSONObject chungnamObject = ((JSONObject) jsonObject.get("chungnam"));
		JSONObject jeonbukObject = ((JSONObject) jsonObject.get("jeonbuk"));
		JSONObject jeonnamObject = ((JSONObject) jsonObject.get("jeonnam"));
		JSONObject gyeongbukObject = ((JSONObject) jsonObject.get("gyeongbuk"));
		JSONObject gyeongnamObject = ((JSONObject) jsonObject.get("gyeongnam"));
		JSONObject jejuObject = ((JSONObject) jsonObject.get("jeju"));
		JSONObject quarantineObject = ((JSONObject) jsonObject.get("quarantine"));

		/**
		 * 한국
		 */
		// 요청한 파라미터 가져오기
		String countryName = CmmUtil.nvl((String) koreaObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		String newCase = CmmUtil.nvl((String) koreaObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		String countrycase = CmmUtil.nvl((String) koreaObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		String recovered = CmmUtil.nvl((String) koreaObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		String death = CmmUtil.nvl((String) koreaObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);

		pDTO = null;

		/**
		 * 서울
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) seoulObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) seoulObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) seoulObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) seoulObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) seoulObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 부산
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) busanObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) busanObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) busanObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) busanObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) busanObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;
		/**
		 * 대구
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) daeguObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) daeguObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) daeguObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) daeguObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) daeguObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;
		/**
		 * 인천
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) incheonObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) incheonObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) incheonObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) incheonObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) incheonObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 광주
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) gwangjuObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) gwangjuObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) gwangjuObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) gwangjuObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) gwangjuObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 대전
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) daejeonObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) daejeonObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) daejeonObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) daejeonObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) daejeonObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 울산
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) ulsanObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) ulsanObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) ulsanObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) ulsanObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) ulsanObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 세종
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) sejongObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) sejongObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) sejongObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) sejongObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) sejongObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 경기
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) gyeonggiObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) gyeonggiObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) gyeonggiObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) gyeonggiObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) gyeonggiObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 강원
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) gangwonObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) gangwonObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) gangwonObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) gangwonObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) gangwonObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 충북
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) chungbukObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) chungbukObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) chungbukObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) chungbukObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) chungbukObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 충남
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) chungnamObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) chungnamObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) chungnamObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) chungnamObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) chungnamObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 전북
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) jeonbukObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) jeonbukObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) jeonbukObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) jeonbukObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) jeonbukObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 전남
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) jeonnamObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) jeonnamObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) jeonnamObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) jeonnamObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) jeonnamObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 경북
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) gyeongbukObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) gyeongbukObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) gyeongbukObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) gyeongbukObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) gyeongbukObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 경남
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) gyeongnamObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) gyeongnamObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) gyeongnamObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) gyeongnamObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) gyeongnamObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 제주
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) jejuObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) jejuObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) jejuObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) jejuObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) jejuObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		/**
		 * 검역
		 */
		pDTO = new CoronaMainDTO();
		// 요청한 파라미터 가져오기
		countryName = CmmUtil.nvl((String) quarantineObject.get("countryName"));
		log.info("countryName :: " + countryName);

		pDTO.setCountryName(countryName); // 데이터 저장

		// 요청한 파라미터 가져오기
		newCase = CmmUtil.nvl((String) quarantineObject.get("newCase"));
		log.info("newCase :: " + newCase);

		pDTO.setNewCase(newCase); // 데이터 저장

		// 요청한 파라미터 가져오기
		countrycase = CmmUtil.nvl((String) quarantineObject.get("totalCase"));
		log.info("contrycase :: " + countrycase);

		pDTO.setCountrycase(countrycase); // 데이터 저장

		// 요청한 파라미터 가져오기
		recovered = CmmUtil.nvl((String) quarantineObject.get("recovered"));
		log.info("recovered :: " + recovered);

		pDTO.setRecovered(recovered); // 데이터 저장

		// 요청한 파라미터 가져오기
		death = CmmUtil.nvl((String) quarantineObject.get("death"));
		log.info("death :: " + death);

		pDTO.setDeath(death); // 데이터 저장

		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss")); // 수집 날짜 저장

		// 한꺼번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		pDTO = null;

		String colNm = "CountryInformation " + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		CoronaMainMapper.createCountryCollection(colNm);

		// MongoDB에 데이터저장하기
		CoronaMainMapper.insertCountryInformation(pList, colNm);

		log.info(this.getClass().getName() + ".CountryInformation End!");
		return res;
	}

	@Override
	public List<CoronaMainDTO> getCountryInformation() throws Exception {
		log.info(this.getClass().getName() + ".getCountryInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "CountryInformation " + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CoronaMainDTO> rList = CoronaMainMapper.getCountryInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}

		log.info(this.getClass().getName() + ".getCountryInformation End!");

		return rList;
	}

	// 일일확진자 가져오기 -> main 화면에 필요함
	@Override
	public String newCaseCatch() throws Exception {
		CoronaMainDTO pDTO = new CoronaMainDTO();

		String url = "https://api.corona-19.kr/korea/country/new/?serviceKey=4HIKCanG5lftsiW19AEkdQ26RBmwjrvSc";

		log.info("url : " + url);
		// OpenAPI 호출을 위한 파라미터 저장하기
		pDTO.setUrl(url);

		// JSON 결과 받아오기
		String json = getUrlForJSON(CmmUtil.nvl(pDTO.getUrl()));

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위한 객체를 메모리에 올림
		JSONParser parser = new JSONParser();

		// String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위해 자바 최상위 Object 변환
		Object obj = parser.parse(json);

		// 변환된 Object 객체를 json 데이터 구조로 변경
		JSONObject jsonObject = (JSONObject) obj;

		/*
		 * 상위 json을 가져온다. 하위 json들과 같이 있는 상위 json은 한 번 더 경로를 지정해줘야 한다.
		 */
		JSONObject koreaObject = ((JSONObject) jsonObject.get("korea"));

		// 요청한 파라미터 가져오기
		String newCase = CmmUtil.nvl((String) koreaObject.get("newCase"));
		log.info("newCase :: " + newCase);

		return newCase;
	}

	// 거리두기 단계 넣기
	@Override
	public int socialDistancing() throws Exception {
		log.info(this.getClass().getName() + ".socialDistancing start!");
		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));
		System.setProperty(WEB_DRIVER_ID, path.toString());
		
		int res = 0;

		List<CoronaMainDTO> pList = new ArrayList<CoronaMainDTO>();
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("headless");
		
		driver = new ChromeDriver(options);
	
		// 코로나19 사이트
		String url = "http://ncov.mohw.go.kr/";

		log.info("url : " + url);
		
		driver.get(url);
		Thread.sleep(2000);
		
		// driver.getPageSource(); html 소스 확인용
		
		element = driver.findElement(By.cssSelector("div.rssm_graph"));
		List<WebElement> city = element.findElements(By.cssSelector("span.name"));
		List<WebElement> stage = element.findElements(By.cssSelector("span.num"));
		
		for (int i = 0; i < city.size(); i++) {
			log.info("city : " + city);
			log.info("stage : " + stage);

			// MongoDB에 저장할 List 형태의 맞는 DTO 데이터 저장하기
			CoronaMainDTO pDTO = new CoronaMainDTO();

			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCity(CmmUtil.nvl(city.get(i).getText().trim()));
			pDTO.setStage(CmmUtil.nvl(stage.get(i).getText().trim()));

			// 여러 개를 List로 저장
			pList.add(pDTO);
		
		}
		
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		
		String colNm = "socialDistancing_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		CoronaMainMapper.createDistancing(colNm);

		// MongoDB에 데이터 저장하기
		CoronaMainMapper.collectsocialDistancing(pList, colNm);

		log.info(this.getClass().getName() + ".socialDistancing End!");
		return res;
	}

	@Override
	public List<CoronaMainDTO> getSocialDistancing() throws Exception {
		log.info(this.getClass().getName() + ".getSocialDistancing Start!");

		// 조회할 컬렉션 이름
		String colNm = "socialDistancing_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CoronaMainDTO> rList = CoronaMainMapper.getSocialDistancing(colNm);

		if (rList == null) {
			rList = new ArrayList<CoronaMainDTO>();
		}

		log.info(this.getClass().getName() + ".getSocialDistancing End!");

		return rList;
	}

}
