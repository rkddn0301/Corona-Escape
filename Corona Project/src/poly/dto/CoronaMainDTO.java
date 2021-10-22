package poly.dto;

public class CoronaMainDTO {
	
	private String url;
	private String collect_time; // 수집시간
	
	// 코로나 api
	private String TotalCase; // 총 확진자
	private String TotalRecovered; // 총 완치자
	private String TotalDeath; // 총 사망자
	private String TodayRecovered; // 일일 완치자
	private String TodayDeath; // 일일 사망자
	private String SocialDistancing; // 거리두기 단계
	
	// 시.도별 코로나 api
	private String countryName; // 도시
	private String newCase; // 일일 확진자
	private String countrycase; // 확진자 수
	private String recovered; // 완치자 수
	private String death; // 사망자 수

	// 사회적 거리두기
	private String city; // 도시
	private String stage; // 단계
	
	
	
	
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final String getStage() {
		return stage;
	}
	public final void setStage(String stage) {
		this.stage = stage;
	}
	public final String getCountrycase() {
		return countrycase;
	}
	public final void setCountrycase(String countrycase) {
		this.countrycase = countrycase;
	}
	
	public final String getNewCase() {
		return newCase;
	}
	public final void setNewCase(String newCase) {
		this.newCase = newCase;
	}
	public final String getCountryName() {
		return countryName;
	}
	public final void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public final String getRecovered() {
		return recovered;
	}
	public final void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public final String getDeath() {
		return death;
	}
	public final void setDeath(String death) {
		this.death = death;
	}
	
	public final String getCollect_time() {
		return collect_time;
	}
	public final void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	public final String getTotalCase() {
		return TotalCase;
	}
	public final void setTotalCase(String totalCase) {
		TotalCase = totalCase;
	}
	public final String getTotalRecovered() {
		return TotalRecovered;
	}
	public final void setTotalRecovered(String totalRecovered) {
		TotalRecovered = totalRecovered;
	}
	public final String getTotalDeath() {
		return TotalDeath;
	}
	public final void setTotalDeath(String totalDeath) {
		TotalDeath = totalDeath;
	}
	public final String getTodayRecovered() {
		return TodayRecovered;
	}
	public final void setTodayRecovered(String todayRecovered) {
		TodayRecovered = todayRecovered;
	}
	public final String getTodayDeath() {
		return TodayDeath;
	}
	public final void setTodayDeath(String todayDeath) {
		TodayDeath = todayDeath;
	}
	public final String getSocialDistancing() {
		return SocialDistancing;
	}
	public final void setSocialDistancing(String socialDistancing) {
		SocialDistancing = socialDistancing;
	}

	
}
