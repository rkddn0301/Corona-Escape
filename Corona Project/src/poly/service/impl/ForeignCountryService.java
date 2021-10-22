package poly.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import poly.dto.ForeignCountryDTO;
import poly.persistance.mongo.IForeignCountryMapper;
import poly.service.IForeignCountryService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("ForeignCountryService")
public class ForeignCountryService implements IForeignCountryService {

	@Resource(name = "ForeignCountryMapper")
	private IForeignCountryMapper ForeignCountryMapper; // MongoDB에 저장할 Mapper

	private Logger log = Logger.getLogger(this.getClass());

	// 셀레니움
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/javapractice/kang/Corona Project/WebContent/chromedriver/chromedriver.exe";

	public static WebDriver driver;

	@Override
	public int ForeignCountry() throws Exception {
		log.info(this.getClass().getName() + ".ForeignCountry Start!");
		
		
		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());

		List<ForeignCountryDTO> pList = new ArrayList<ForeignCountryDTO>();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless");

		driver = new ChromeDriver(options);

		// 코로나 확진자 사이트(국외)
		String url = "https://www.worldometers.info/coronavirus/";

		log.info("url : " + url);
		int res = 0;

		driver.get(url);
		Thread.sleep(2000);
		
		// 국가명 정렬
		WebElement clicker = driver.findElement(By.xpath("//*[@id=\"main_table_countries_today\"]/thead/tr/th[2]"));
		clicker.sendKeys(Keys.ENTER);

		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("table.table.table-bordered.table-hover.main_table_countries.dataTable.no-footer > tbody"));
		
		List<WebElement> name = element.findElements(By.xpath("//*[@id=\"main_table_countries_today\"]/tbody[1]/tr/td[2]"));
		List<WebElement> TotalCases = element.findElements(By.xpath("//*[@id=\"main_table_countries_today\"]/tbody[1]/tr/td[3]"));
		List<WebElement> TotalDeaths = element.findElements(By.xpath("//*[@id=\"main_table_countries_today\"]/tbody[1]/tr/td[5]"));
		List<WebElement> TotalRecovered = element.findElements(By.xpath("//*[@id=\"main_table_countries_today\"]/tbody[1]/tr/td[7]"));
		
		for (int i = 0; i < name.size(); i++) {	
			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			ForeignCountryDTO pDTO = new ForeignCountryDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));

			pDTO.setName(CmmUtil.nvl(name.get(i).getText().trim()));
			pDTO.setTotalCases(CmmUtil.nvl(TotalCases.get(i).getText().trim()));
			pDTO.setTotalDeaths(CmmUtil.nvl(TotalDeaths.get(i).getText().trim()));
			pDTO.setTotalRecovered(CmmUtil.nvl(TotalRecovered.get(i).getText().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "ForeignCountry_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		ForeignCountryMapper.createForeign(colNm);

		// MongoDB에 저장하기
		ForeignCountryMapper.collectForeignCountry(pList, colNm);

		log.info(this.getClass().getName() + ".ForeignCountry End!");
		return res;
	}

	@Override
	public List<ForeignCountryDTO> getForeignCountry() throws Exception {
		log.info(this.getClass().getName() + ".getForeignCountry Start!");

		// 조회할 컬렉션 이름
		String colNm = "ForeignCountry_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<ForeignCountryDTO> rList = ForeignCountryMapper.getForeignCountry(colNm);

		if (rList == null) {
			rList = new ArrayList<ForeignCountryDTO>();
		}

		log.info(this.getClass().getName() + ".getForeignCountry End!");

		return rList;
	}

}
