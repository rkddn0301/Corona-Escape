package poly.dto;

public class UserDTO {
	private String user_no; // 회원번호
	private String user_id; // 회원아이디
	private String user_name; // 회원명
	private String password; // 비밀번호
	private String year; // 출생년도
	private String month; // 출생월
	private String day; // 출생일
	private String email; // 이메일
	private String nick_name; // 닉네임
	private String gender; // 성별
	private String addr1; // 주소
	private String addr2; // 상세주소
	private String author; // 권한 여부 1=관리자, 0=사용자

	private String exists_yn; // 상호작용
	private String tpassword; // 임시비밀번호 생성
	private String rnum; // 순번
	
	private int first; // 페이지 나누기 시작
	private int last; // 페이지 나누기 끝
	private int offsetnum; // OFFSET에 환산되는 페이지 수
	private int cnt; // 회원 수
	private int nowPage; // 현재 페이지의 장소를 나타냄
	
	private String search; // 검색
	
	
	
	
	
	

	

	public final String getSearch() {
		return search;
	}

	public final void setSearch(String search) {
		this.search = search;
	}

	public final int getNowPage() {
		return nowPage;
	}

	public final void setNowPage(int nowPage) {
		this.nowPage = nowPage;
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

	public final String getRnum() {
		return rnum;
	}

	public final void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public final String getUser_no() {
		return user_no;
	}

	public final void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public final String getUser_id() {
		return user_id;
	}

	public final void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public final String getUser_name() {
		return user_name;
	}

	public final void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getYear() {
		return year;
	}

	public final void setYear(String year) {
		this.year = year;
	}

	public final String getMonth() {
		return month;
	}

	public final void setMonth(String month) {
		this.month = month;
	}

	public final String getDay() {
		return day;
	}

	public final void setDay(String day) {
		this.day = day;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getNick_name() {
		return nick_name;
	}

	public final void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final String getAddr1() {
		return addr1;
	}

	public final void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public final String getAddr2() {
		return addr2;
	}

	public final void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public final String getAuthor() {
		return author;
	}

	public final void setAuthor(String author) {
		this.author = author;
	}

	public final String getExists_yn() {
		return exists_yn;
	}

	public final void setExists_yn(String exists_yn) {
		this.exists_yn = exists_yn;
	}

	public final String getTpassword() {
		return tpassword;
	}

	public final void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

}
