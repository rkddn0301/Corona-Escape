package poly.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import poly.dto.CountryDTO;
import poly.persistance.mongo.ICountryMapper;
import poly.service.ICountryService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("CountryService")
public class CountryService implements ICountryService {

	@Resource(name = "CountryMapper")
	private ICountryMapper CountryMapper; // MongoDB에 저장할 mapper

	private Logger log = Logger.getLogger(this.getClass());

	// 셀레니움
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/javapractice/kang/Corona Project/WebContent/chromedriver/chromedriver.exe";

	public static WebDriver driver;

	// 서울
	@Override
	public int SeoulCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".SeoulCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(서울)
		String url = "https://www.seoul.go.kr/coronaV/coronaStatus.do";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.path-confirmed");

		Iterator<Element> cityList = element.select("div.autonomy").iterator();

		while (cityList.hasNext()) {

			Element cityinfo = cityList.next();

			String city = cityinfo.select("dl > dt > a > span").text();
			String cases = cityinfo.select("dl > dd").eq(0).text();

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCity(city);
			pDTO.setCases(cases);

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "SeoulCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createSeoulCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertSeoulCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".SeoulCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getSeoulCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getSeoulCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "SeoulCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getSeoulCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getSeoulCityInformation End!");

		return rList;
	}

	// 부산

	@Override
	public int BusanCityCollection() throws Exception {

		log.info(this.getClass().getName() + ".BusanCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(부산)
		String url = "https://www.busan.go.kr/covid19/Corona19.do";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.covid-state-table > table");

		Iterator<Element> city = element.select("tr > th").iterator();
		Iterator<Element> cases = element.select("tr > td").iterator();

		while (city.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCity(CmmUtil.nvl(city.next().text().trim()));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "BusanCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createBusanCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertBusanCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".BusanCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getBusanCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getBusanCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "BusanCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getBusanCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getBusanCityInformation End!");

		return rList;
	}

	// 대구

	@Override
	public int DaeguCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".DaeguCityCollection Start!");

		WebElement elementy;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless");

		driver = new ChromeDriver(options);

		CountryDTO pDTO = new CountryDTO();
		List<CountryDTO> pList = new ArrayList<CountryDTO>();
		int res = 0;

		// 중구
		String url = "https://www.jung.daegu.kr/new/pages/main/covid_19.html";
		
		log.info("url : " + url);

		driver.get(url);
		Thread.sleep(2000);
		
		elementy = driver.findElement(By.xpath("//*[@id=\"subCnt\"]/div/div[2]/div[1]/table/tbody"));
		
		List<WebElement> cases1 = elementy.findElements(By.xpath("//*[@id=\"subCnt\"]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/span"));
		
		for (int i = 0; i < cases1.size(); i++) {

		// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
		pDTO.setCases(CmmUtil.nvl(cases1.get(i).getText().trim()));

		// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
		pList.add(pDTO);
		}
		pDTO = null;

		// 동구
		url = "https://dong.daegu.kr/corona19/index.php";
		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.covid_box");
		Iterator<Element> cases = element.select("li > p.txt02").eq(0).iterator();
		pDTO = new CountryDTO();
		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
		pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

		pList.add(pDTO);
		pDTO = null;

		// 서구
		url = "https://www.dgs.go.kr/";
		doc = Jsoup.connect(url).get();

		element = doc.select("ul.clearfix");
		cases = element.select("li > span.num").eq(0).iterator();
		pDTO = new CountryDTO();
		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
		pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

		pList.add(pDTO);
		pDTO = null;

		// 북구
		url = "http://www.buk.daegu.kr/";
		doc = Jsoup.connect(url).get();

		element = doc.select("ul.clearfix");
		cases = element.select("li > span.num").eq(0).iterator();
		pDTO = new CountryDTO();
		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
		pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

		pList.add(pDTO);
		pDTO = null;

		// 수성구
		url = "https://www.suseong.kr/index.do";
		doc = Jsoup.connect(url).get();

		element = doc.select("table.tbl_cov.tac");
		cases = element.select("tr > td > strong").eq(1).iterator();
		pDTO = new CountryDTO();
		pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
		pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

		pList.add(pDTO);
		pDTO = null;

		// 남구
		
		
		url = "https://nam.daegu.kr/index.do";

		log.info("url : " + url);

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		elementy = driver.findElement(By.cssSelector("div.section1"));
		cases1 = elementy.findElements(By.tagName("strong"));
		for (int i = 0; i < cases1.size(); i++) {
			log.info("cases: " + cases1);


				// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
				pDTO = new CountryDTO();
				pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
				pDTO.setCases(CmmUtil.nvl(cases1.get(i).getText().trim()));

				// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
				pList.add(pDTO);
			

		}
		pDTO = null;

		// 달서구
		

		// 시.도 코로나 확진자 수 사이트
		url = "https://dalseo.daegu.kr/index.do";

		log.info("url : " + url);

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		elementy = driver.findElement(By.cssSelector("div.section1"));
		cases1 = elementy.findElements(By.tagName("td"));
		for (int i = 0; i < cases1.size(); i++) {
			log.info("cases: " + cases1);

			if (i == 2) {

				// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
				pDTO = new CountryDTO();
				pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
				pDTO.setCases(CmmUtil.nvl(cases1.get(i).getText().trim()));

				// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
				pList.add(pDTO);
			}

		}
		pDTO = null;

		// 달성군
		url = "https://www.dalseong.daegu.kr/index.do";
		log.info("url : " + url);

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		elementy = driver.findElement(By.cssSelector("div.section1"));
		cases1 = elementy.findElements(By.tagName("th"));
		for (int i = 0; i < cases1.size(); i++) {
			log.info("cases: " + cases1);

			if (i == 0) {

				// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
				pDTO = new CountryDTO();
				pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
				pDTO.setCases(CmmUtil.nvl(cases1.get(i).getText().trim()));

				// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
				pList.add(pDTO);
			}

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "DaeguCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createDaeguCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertDaeguCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".DaeguCityCollection End!");
		return res;

	}

	@Override
	public List<CountryDTO> getDaeguCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getDaeguCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "DaeguCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getDaeguCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getDaeguCityInformation End!");

		return rList;
	}

	// 인천 9.15일 기준 목록이 나오지 않음 -> 9/17 복구 완료

	@Override
	public int IncheonCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".IncheonCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "https://www.incheon.go.kr/covid19/index";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("table.covid-contents__situation");

		Iterator<Element> cases = element.select("tbody > tr > td").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "IncheonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createIncheonCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertIncheonCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".IncheonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getIncheonCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getIncheonCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "IncheonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getIncheonCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getIncheonCityInformation End!");

		return rList;
	}

	// 광주

	@Override
	public int GwangjuCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".GwangjuCityCollection Start!");
		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		List<CountryDTO> pList = new ArrayList<CountryDTO>();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless");

		driver = new ChromeDriver(options);

		// 시.도 코로나 확진자 수 사이트(광주)
		String url = "https://www.gwangju.go.kr/c19/";

		log.info("url : " + url);
		int res = 0;

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("table.tb_condition.tb_color3.mt10 > tbody"));
		List<WebElement> cases = element.findElements(By.tagName("td"));
		for (int i = 0; i < cases.size(); i++) {
			log.info("cases: " + cases);

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));

			pDTO.setCases(CmmUtil.nvl(cases.get(i).getText().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "GwangjuCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createGwangjuCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertGwangjuCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".GwangjuCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGwangjuCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getGwangjuCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "GwangjuCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getGwangjuCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getGwangjuCityInformation End!");

		return rList;
	}

	// 대전 9.15일 기준 PKIX 에러 뜸 셀레니움으로 교체해야 함 -> 9.17 처리 완료

	@Override
	public int DaejeonCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".DaejeonCityCollection Start!");
		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		List<CountryDTO> pList = new ArrayList<CountryDTO>();
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("headless");

		driver = new ChromeDriver(options);


		// 시.도 코로나 확진자 수 사이트(대전)
		String url = "https://www.daejeon.go.kr/corona19/index.do";

		log.info("url : " + url);
		int res = 0;
		
		
		driver.get(url);
		Thread.sleep(2000);
		
		// 대전 코로나 확진현황은 '자치구별 확진환자'버튼을 눌러야 하기 때문에 클릭기능을 작성함
		WebElement clicker = driver.findElement(By.xpath("//*[@id=\'modal_open\']"));
		clicker.click();
		
		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("div.wrap_map_status"));
		List<WebElement> city = element.findElements(By.tagName("span"));
		List<WebElement> cases = element.findElements(By.tagName("strong"));
			for (int i = 0; i < city.size(); i++) {
				log.info("cases: " + cases);

				// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
				CountryDTO pDTO = new CountryDTO();
				pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
				
				pDTO.setCity(CmmUtil.nvl(city.get(i).getText().trim()));
				pDTO.setCases(CmmUtil.nvl(cases.get(i).getText().trim()));

				// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
				pList.add(pDTO);

			}

		if (driver != null) {
			driver.close();

			driver.quit();
		}


		String colNm = "DaejeonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createDaejeonCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertDaejeonCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".DaejeonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getDaejeonCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getDaejeonCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "DaejeonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getDaejeonCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getDaejeonCityInformation End!");

		return rList;
	}

	// 울산

	@Override
	public int UlsanCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".UlsanCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "https://covid19.ulsan.go.kr/index.do";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.localarea_box");

		Iterator<Element> cases = element.select("tbody > tr > td").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "UlsanCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createUlsanCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertUlsanCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".UlsanCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getUlsanCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getUlsanCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "UlsanCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getUlsanCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getUlsanCityInformation End!");

		return rList;
	}

	// 경기도

	@Override
	public int GyeonggiCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".GyeonggiCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "https://www.gg.go.kr/contents/contents.do?ciIdx=1150&menuId=2909";
		

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.content_box");

		Iterator<Element> cases = element.select("dl > dd > strong").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "GyeonggiCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createGyeonggiCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertGyeonggiCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".GyeonggiCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGyeonggiCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getGyeonggiCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "GyeonggiCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getGyeonggiCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getGyeonggiCityInformation End!");

		return rList;
	}

	// 강원도

	@Override
	public int GangwonCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".GangwonCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "http://www.provin.gangwon.kr/covid-19.html";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("table.skinTb");

		Iterator<Element> cases = element.select("tbody > tr > td").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "GangwonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createGangwonCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertGangwonCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".GangwonCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getGangwonCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getGangwonCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "GangwonCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getGangwonCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getGangwonCityInformation End!");

		return rList;
	}

	// 충청북도

	@Override
	public int NtChungcheongCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".NtChungcheongCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "http://www1.chungbuk.go.kr/covid-19/index.do";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.inline_box2");

		Iterator<Element> cases = element.select("div > a").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "NtChungcheongCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createNtChungcheongCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertNtChungcheongCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".NtChungcheongCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtChungcheongCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getNtChungcheongCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "NtChungcheongCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getNtChungcheongCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getNtChungcheongCityInformation End!");

		return rList;
	}

	// 충청남도

	@Override
	public int StChungcheongCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".StChungcheongCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "http://www.chungnam.go.kr/coronaStatus.do?tab=1";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("table.new_tbl_board.mb20").eq(1);

		Iterator<Element> cases = element.select("tbody > tr > td").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "StChungcheongCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createStChungcheongCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertStChungcheongCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".StChungcheongCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStChungcheongCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getStChungcheongCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "StChungcheongCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getStChungcheongCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getStChungcheongCityInformation End!");

		return rList;
	}

	// 전라북도

	@Override
	public int NtJeollaCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".NtJeollaCityCollection Start!");

		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		List<CountryDTO> pList = new ArrayList<CountryDTO>();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless");

		driver = new ChromeDriver(options);

		// 시.도 코로나 확진자 수 사이트
		String url = "https://www.jeonbuk.go.kr/board/list.jeonbuk?boardId=BBS_0000105&menuCd=DOM_000000110001000000&contentsSid=1219&cpath=";

		log.info("url : " + url);
		int res = 0;

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("div.city > ul"));
		List<WebElement> cases = element.findElements(By.tagName("strong"));
		for (int i = 0; i < cases.size(); i++) {
			log.info("cases: " + cases);

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));

			pDTO.setCases(CmmUtil.nvl(cases.get(i).getText().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "NtJeollaCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createNtJeollaCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertNtJeollaCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".NtJeollaCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtJeollaCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getNtJeollaCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "NtJeollaCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getNtJeollaCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getNtJeollaCityInformation End!");

		return rList;
	}

	// 전라남도

	@Override
	public int StJeollaCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".StJeollaCityCollection Start!");

		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		List<CountryDTO> pList = new ArrayList<CountryDTO>();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless");

		driver = new ChromeDriver(options);

		// 시.도 코로나 확진자 수 사이트
		String url = "https://www.jeonnam.go.kr/coronaMainPage.do";

		log.info("url : " + url);
		int res = 0;

		driver.get(url);
		Thread.sleep(2000);

		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("table.tb_condition.tb_color2 > tbody.dataArr"));
		List<WebElement> cases = element.findElements(By.tagName("td"));
		for (int i = 0; i < cases.size(); i++) {
			log.info("cases: " + cases);

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));

			pDTO.setCases(CmmUtil.nvl(cases.get(i).getText().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "StJeollaCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createStJeollaCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertStJeollaCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".StJeollaCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStJeollaCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getStJeollaCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "StJeollaCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getStJeollaCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getStJeollaCityInformation End!");

		return rList;
	}

	// 경상북도

	@Override
	public int NtGyeongsangCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".NtGyeongsangCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "https://gb.go.kr/Main/open_contents/section/wel/page.do?mnu_uid=5856&LARGE_CODE=360&MEDIUM_CODE=90";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.city_corona");

		Iterator<Element> cases = element.select("dl > dd > strong").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "NtGyeongsangCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createNtGyeongsangCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertNtGyeongsangCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".NtGyeongsangCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getNtGyeongsangCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getNtGyeongsangCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "NtGyeongsangCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getNtGyeongsangCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getNtGyeongsangCityInformation End!");

		return rList;
	}

	// 경상남도

	@Override
	public int StGyeongsangCityCollection() throws Exception {
		log.info(this.getClass().getName() + ".StGyeongsangCityCollection Start!");

		int res = 0;

		List<CountryDTO> pList = new ArrayList<CountryDTO>();

		// 시.도 코로나 확진자 수 사이트(인천)
		String url = "http://xn--19-q81ii1knc140d892b.kr/main/main.do";

		log.info("url : " + url);

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스를 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		// 해당 태그 내에서 있는 HTML 소스만 element에 저장됨
		Elements element = doc.select("div.city_board");

		Iterator<Element> cases = element.select("tbody > tr > td.point").iterator();

		while (cases.hasNext()) {

			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			CountryDTO pDTO = new CountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));
			pDTO.setCases(CmmUtil.nvl(cases.next().text().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);
		}

		String colNm = "StGyeongsangCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		CountryMapper.createStGyeongsangCityCollection(colNm);

		// MongoDB에 저장하기
		CountryMapper.insertStGyeongsangCityCollection(pList, colNm);

		log.info(this.getClass().getName() + ".StGyeongsangCityCollection End!");
		return res;
	}

	@Override
	public List<CountryDTO> getStGyeongsangCityInformation() throws Exception {
		log.info(this.getClass().getName() + ".getStGyeongsangCityInformation Start!");

		// 조회할 컬렉션 이름
		String colNm = "StGyeongsangCityInformation_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<CountryDTO> rList = CountryMapper.getStGyeongsangCityInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<CountryDTO>();
		}

		log.info(this.getClass().getName() + ".getStGyeongsangCityInformation End!");

		return rList;
	}

}
