package poly.controller.cron;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import poly.service.ICoronaMainService;
import poly.service.ICountryService;
import poly.service.IForeignCountryService;
import poly.service.IGubunService;
import poly.service.IVaccinationService;

// cron 스케줄러 클래스임을 선언하는 어노테이션.
@Component
public class Scheduler {

	// 코로나 서비스
	@Resource(name = "CoronaMainService")
	private ICoronaMainService CoronaMainService;

	// 도시별 확진자수 서비스
	@Resource(name = "CountryService")
	private ICountryService CountryService;

	// 구분별 확진자수 서비스
	@Resource(name = "GubunService")
	private IGubunService GubunService;
	
	// 국외발생현황 확진자 수 서비스
	@Resource(name = "ForeignCountryService")
	private IForeignCountryService ForeignCountryService;
	
	// 백신접종현황 서비스
	@Resource(name = "VaccinationService")
	private IVaccinationService VaccinationService;

// cron 문법으로 스케줄러가 실행되는 주기를 설정.(아래의 cron 스케줄 문법에 자세한 설명)
	@Scheduled(cron = "* 10 10 * * *")
	public void run() throws Exception {
		System.out.println("cron test!!");
		// 코로나 컬렉션 갱신(매일 갱신되야 하므로 곧바로 적용한다. / 같은 컬렉션이 있으면 Mapper에서 삭제하기 때문에 일자별로 갱신.
		CoronaMainService.coronaInformation();
		CoronaMainService.CountryInformation();
		CoronaMainService.socialDistancing();
		CountryService.SeoulCityCollection();
		CountryService.BusanCityCollection();
		CountryService.IncheonCityCollection();
		CountryService.GwangjuCityCollection();
		CountryService.DaejeonCityCollection();
		CountryService.DaeguCityCollection();
		CountryService.UlsanCityCollection();
		CountryService.GyeonggiCityCollection();
		CountryService.GangwonCityCollection();
		CountryService.NtChungcheongCityCollection();
		CountryService.StChungcheongCityCollection();
		CountryService.NtJeollaCityCollection();
		CountryService.StJeollaCityCollection();
		CountryService.NtGyeongsangCityCollection();
		CountryService.StGyeongsangCityCollection();
		
		GubunService.gubunInformation();
		GubunService.genderInformation();
		
		ForeignCountryService.ForeignCountry();
		
		VaccinationService.Vaccination();
	

	}
	@Scheduled(cron = "* 1 * * * *")
	public void run1() throws Exception {
		System.out.println("cron test!!");
		
		// 코로나 컬렉션 갱신(매일 갱신되야 하므로 곧바로 적용한다. / 같은 컬렉션이 있으면 Mapper에서 삭제하기 때문에 일자별로 갱신.
		CountryService.DaejeonCityCollection();
		CoronaMainService.coronaInformation();
		CoronaMainService.CountryInformation();
		CoronaMainService.socialDistancing();
		CountryService.SeoulCityCollection();
		CountryService.BusanCityCollection();
		CountryService.IncheonCityCollection();
		CountryService.GwangjuCityCollection();
		
		CountryService.UlsanCityCollection();
		CountryService.GyeonggiCityCollection();
		CountryService.GangwonCityCollection();
		CountryService.NtChungcheongCityCollection();
		CountryService.StChungcheongCityCollection();
		CountryService.NtJeollaCityCollection();
		CountryService.StJeollaCityCollection();
		CountryService.NtGyeongsangCityCollection();
		CountryService.StGyeongsangCityCollection();
		
		
		GubunService.gubunInformation();
		GubunService.genderInformation();
		
		ForeignCountryService.ForeignCountry();
		
		VaccinationService.Vaccination();
		
		CountryService.DaeguCityCollection();
		
		
	}
}
