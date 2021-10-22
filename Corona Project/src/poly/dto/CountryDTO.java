package poly.dto;

public class CountryDTO {
	private String url;
	private String collect_time; // 수집시간
	
	private String city; // 도시 이름
	private String cases; // 도시별 확진자 수
	
	private int test; // 정수형 테스트
	
	
	
	
	public final int getTest() {
		return test;
	}
	public final void setTest(int test) {
		this.test = test;
	}
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
	public final String getCases() {
		return cases;
	}
	public final void setCases(String cases) {
		this.cases = cases;
	}
	
	

}
