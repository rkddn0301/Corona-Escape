package poly.dto;

public class NoticeDTO {
	
	private String notice_no; //공지사항 번호
	private String notice_title; //공지사항 제목
	private String notice_content; //공지사항 내용
	private String notice_date; //공지사항 작성일
	private String notice_ch_date; //공지사항 수정일
	private String notice_cnt; //조회수
	
	// CORONA_USER 테이블과 연결
	
	private String n_user_no; //회원번호
	private String n_user_id; //회원아이디
	private String n_nick_name; //닉네임
	private String n_author; //회원권환
	
	// 기타
	
	private String rnum; //순번
	private String rdate; // 데이트 포맷
	
	// 페이지
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
	public final String getRdate() {
		return rdate;
	}
	public final void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public final String getRnum() {
		return rnum;
	}
	public final void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public final String getNotice_no() {
		return notice_no;
	}
	public final void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}
	public final String getNotice_title() {
		return notice_title;
	}
	public final void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public final String getNotice_content() {
		return notice_content;
	}
	public final void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public final String getNotice_date() {
		return notice_date;
	}
	public final void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public final String getNotice_ch_date() {
		return notice_ch_date;
	}
	public final void setNotice_ch_date(String notice_ch_date) {
		this.notice_ch_date = notice_ch_date;
	}
	public final String getNotice_cnt() {
		return notice_cnt;
	}
	public final void setNotice_cnt(String notice_cnt) {
		this.notice_cnt = notice_cnt;
	}
	public final String getN_user_no() {
		return n_user_no;
	}
	public final void setN_user_no(String n_user_no) {
		this.n_user_no = n_user_no;
	}
	public final String getN_user_id() {
		return n_user_id;
	}
	public final void setN_user_id(String n_user_id) {
		this.n_user_id = n_user_id;
	}
	public final String getN_nick_name() {
		return n_nick_name;
	}
	public final void setN_nick_name(String n_nick_name) {
		this.n_nick_name = n_nick_name;
	}
	public final String getN_author() {
		return n_author;
	}
	public final void setN_author(String n_author) {
		this.n_author = n_author;
	}
	
	
}
