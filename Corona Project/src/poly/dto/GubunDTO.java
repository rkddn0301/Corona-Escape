package poly.dto;

public class GubunDTO {
	
	private String url;
	private String collect_time; // 수집시간
	
	private String gubun; // 구분
	private String confcase; // 확진자
	private String confcaserate; //확진율
	
	private String death; // 사망자
	private String deathrate; // 사망률
	
	private String criticalrate; // 치명률
	
	// 구분별 파라미터
	private String reqgubun; // 구분 파라미터
	private String reqchoice; // 선택 파라미터
	
	
	public final String getReqgubun() {
		return reqgubun;
	}

	public final void setReqgubun(String reqgubun) {
		this.reqgubun = reqgubun;
	}

	public final String getReqchoice() {
		return reqchoice;
	}

	public final void setReqchoice(String reqchoice) {
		this.reqchoice = reqchoice;
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

	public final String getGubun() {
		return gubun;
	}

	public final void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public final String getConfcase() {
		return confcase;
	}

	public final void setConfcase(String confcase) {
		this.confcase = confcase;
	}

	public final String getConfcaserate() {
		return confcaserate;
	}

	public final void setConfcaserate(String confcaserate) {
		this.confcaserate = confcaserate;
	}

	public final String getDeath() {
		return death;
	}

	public final void setDeath(String death) {
		this.death = death;
	}

	public final String getDeathrate() {
		return deathrate;
	}

	public final void setDeathrate(String deathrate) {
		this.deathrate = deathrate;
	}

	public final String getCriticalrate() {
		return criticalrate;
	}

	public final void setCriticalrate(String criticalrate) {
		this.criticalrate = criticalrate;
	}
	
	
	

}
