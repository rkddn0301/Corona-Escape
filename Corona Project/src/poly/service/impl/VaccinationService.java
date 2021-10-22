package poly.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import poly.dto.VaccinationDTO;
import poly.persistance.mongo.IVaccinationMapper;
import poly.service.IVaccinationService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

@Service("VaccinationService")
public class VaccinationService implements IVaccinationService {
	
	@Resource(name = "VaccinationMapper")
	private IVaccinationMapper VaccinationMapper; // MongoDB에 저장할 Mapper
	
	private Logger log = Logger.getLogger(this.getClass());

	// 셀레니움
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/javapractice/kang/Corona Project/WebContent/chromedriver/chromedriver.exe";

	public static WebDriver driver;

	@Override
	public int Vaccination() throws Exception {
		log.info(this.getClass().getName() + ".Vaccination Start!");
		
		WebElement element;
		Path path = Paths.get(System.getProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH));

		System.setProperty(WEB_DRIVER_ID, path.toString());
		
		List<VaccinationDTO> pList = new ArrayList<VaccinationDTO>();
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("headless");
		
		driver = new ChromeDriver(options);
		
		// 백신예방접종 사이트
		String url = "https://ncv.kdca.go.kr/mainStatus.es?mid=a11702000000";
		
		log.info("url : " + url);
		int res = 0;
		
		driver.get(url);;
		Thread.sleep(2000);
		
		// driver.getPageSource(); html소스 확인용
		element = driver.findElement(By.cssSelector("div.data_table.tbl_scrl_mini > table > tbody"));
		
		List<WebElement> city = element.findElements(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/th"));
		List<WebElement> TodayVaccine1 = element.findElements(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/td[1]"));
		List<WebElement> TodayVaccine2 = element.findElements(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/td[3]"));
		List<WebElement> TotalVaccine1 = element.findElements(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/td[2]"));
		List<WebElement> TotalVaccine2 = element.findElements(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/td[4]"));
		
		for (int i = 0; i < city.size(); i++) {	
			// MongoDB에 저장할 List 형태에 맞는 DTO 데이터 저장하기
			VaccinationDTO pDTO = new VaccinationDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMddhhmmss"));

			pDTO.setCity(CmmUtil.nvl(city.get(i).getText().trim()));
			pDTO.setTodayVaccine1(CmmUtil.nvl(TodayVaccine1.get(i).getText().trim()));
			pDTO.setTodayVaccine2(CmmUtil.nvl(TodayVaccine2.get(i).getText().trim()));
			pDTO.setTotalVaccine1(CmmUtil.nvl(TotalVaccine1.get(i).getText().trim()));
			pDTO.setTotalVaccine2(CmmUtil.nvl(TotalVaccine2.get(i).getText().trim()));

			// 한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
			pList.add(pDTO);

		}

		if (driver != null) {
			driver.close();

			driver.quit();
		}

		String colNm = "Vaccination_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션 명

		// MongoDB Collection 생성하기
		VaccinationMapper.createVaccine(colNm);

		// MongoDB에 저장하기
		VaccinationMapper.collectVaccination(pList, colNm);
		
		log.info(this.getClass().getName() + ".Vaccination End!");
		return res;
	}

	@Override
	public List<VaccinationDTO> getVaccination() throws Exception {
		log.info(this.getClass().getName() + ".getVaccination Start!");

		// 조회할 컬렉션 이름
		String colNm = "Vaccination_" + DateUtil.getDateTime("yyyyMMdd"); // 생성할 컬렉션명

		List<VaccinationDTO> rList = VaccinationMapper.getVaccination(colNm);

		if (rList == null) {
			rList = new ArrayList<VaccinationDTO>();
		}

		log.info(this.getClass().getName() + ".getVaccination End!");

		return rList;
	}

}
