package poly.dto;

public class VaccinationDTO {
	
	private String url;
	private String collect_time; // 수집시간
	
	// 백신접종현황
	private String city; // 도시명
	private String TodayVaccine1; // 당일 1차 접종
	private String TodayVaccine2; // 당일 2차 접종
	private String TotalVaccine1; // 누계 1차 접종
	private String TotalVaccine2; // 누계 2차 접종
	
	
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	public final String getCollect_time() {
		return collect_time;
	}
	public final void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final String getTodayVaccine1() {
		return TodayVaccine1;
	}
	public final void setTodayVaccine1(String todayVaccine1) {
		TodayVaccine1 = todayVaccine1;
	}
	public final String getTodayVaccine2() {
		return TodayVaccine2;
	}
	public final void setTodayVaccine2(String todayVaccine2) {
		TodayVaccine2 = todayVaccine2;
	}
	public final String getTotalVaccine1() {
		return TotalVaccine1;
	}
	public final void setTotalVaccine1(String totalVaccine1) {
		TotalVaccine1 = totalVaccine1;
	}
	public final String getTotalVaccine2() {
		return TotalVaccine2;
	}
	public final void setTotalVaccine2(String totalVaccine2) {
		TotalVaccine2 = totalVaccine2;
	}
	
	

}
