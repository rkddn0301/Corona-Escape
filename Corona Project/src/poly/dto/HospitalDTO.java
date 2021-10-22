package poly.dto;

public class HospitalDTO {
	
	
	// DB에 들어간 컬럼
	private String hospital_no; // 연번
	private String hospital_name; // 의료기관명
	private String hospital_addr; // 주소
	private String hospital_call; // 전화번호
	private String hospital_time; // 평일 영업시간
	private String hospital_time2; // 토요일 영업시간
	private String hospital_time3; // 공휴일 영업시간
	
	private String rnum; //순번
	
	
	// 페이지
	private int first; // 페이지 나누기 시작
	private int last; // 페이지 나누기 끝
	private int offsetnum; // OFFSET에 환산되는 페이지 수
	private int cnt; // 회원 수
	private int nowPage; // 현재 페이지의 장소를 나타냄
	private String search; // 검색
	
	public final String getRnum() {
		return rnum;
	}
	public final void setRnum(String rnum) {
		this.rnum = rnum;
	}
	
	
	public final int getFirst() {
		return first;
	}
	public final void setFirst(int first) {
		this.first = first;
	}
	public final int getLast() {
		return last;
	}
	public final void setLast(int last) {
		this.last = last;
	}
	public final int getOffsetnum() {
		return offsetnum;
	}
	public final void setOffsetnum(int offsetnum) {
		this.offsetnum = offsetnum;
	}
	public final int getCnt() {
		return cnt;
	}
	public final void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public final int getNowPage() {
		return nowPage;
	}
	public final void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public final String getSearch() {
		return search;
	}
	public final void setSearch(String search) {
		this.search = search;
	}
	public final String getHospital_no() {
		return hospital_no;
	}
	public final void setHospital_no(String hospital_no) {
		this.hospital_no = hospital_no;
	}
	public final String getHospital_name() {
		return hospital_name;
	}
	public final void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public final String getHospital_addr() {
		return hospital_addr;
	}
	public final void setHospital_addr(String hospital_addr) {
		this.hospital_addr = hospital_addr;
	}
	public final String getHospital_call() {
		return hospital_call;
	}
	public final void setHospital_call(String hospital_call) {
		this.hospital_call = hospital_call;
	}
	public final String getHospital_time() {
		return hospital_time;
	}
	public final void setHospital_time(String hospital_time) {
		this.hospital_time = hospital_time;
	}
	public final String getHospital_time2() {
		return hospital_time2;
	}
	public final void setHospital_time2(String hospital_time2) {
		this.hospital_time2 = hospital_time2;
	}
	public final String getHospital_time3() {
		return hospital_time3;
	}
	public final void setHospital_time3(String hospital_time3) {
		this.hospital_time3 = hospital_time3;
	}
	
	
	

}
