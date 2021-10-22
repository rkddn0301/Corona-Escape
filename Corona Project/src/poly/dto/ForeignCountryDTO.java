package poly.dto;

public class ForeignCountryDTO {

	private String url;
	private String collect_time; // 수집시간

	// 국외코로나 https://www.worldometers.info/coronavirus/
	private String name; // 국가명
	private String TotalCases; // 총 확진자 수
	private String TotalDeaths; // 총 사망자 수
	private String TotalRecovered; // 총 완치자 수
	
	
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
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getTotalCases() {
		return TotalCases;
	}
	public final void setTotalCases(String totalCases) {
		TotalCases = totalCases;
	}
	public final String getTotalDeaths() {
		return TotalDeaths;
	}
	public final void setTotalDeaths(String totalDeaths) {
		TotalDeaths = totalDeaths;
	}
	public final String getTotalRecovered() {
		return TotalRecovered;
	}
	public final void setTotalRecovered(String totalRecovered) {
		TotalRecovered = totalRecovered;
	}
	
	

}
