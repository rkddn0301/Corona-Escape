package poly.dto;

public class InquiryDTO {
	private String inquiry_no; // 문의사항 번호
	private String inquiry_title; // 문의사항 제목
	private String inquiry_content; // 문의사항 내용
	private String inquiry_date; // 문의 작성일
	private String inquiry_ch_date; // 문의 답변일
	private String inquiry_response; // 문의 답변여부

	// CORONA_USER 테이블과 연결

	private String i_user_no; // 회원 번호
	private String i_user_id; // 회원 아이디
	private String i_nick_name; // 닉네임
	private String i_author; // 회원 권한

	// 기타
	private String rnum; // 순번
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

	public final String getInquiry_no() {
		return inquiry_no;
	}

	public final void setInquiry_no(String inquiry_no) {
		this.inquiry_no = inquiry_no;
	}

	public final String getInquiry_title() {
		return inquiry_title;
	}

	public final void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}

	public final String getInquiry_content() {
		return inquiry_content;
	}

	public final void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}

	public final String getInquiry_date() {
		return inquiry_date;
	}

	public final void setInquiry_date(String inquiry_date) {
		this.inquiry_date = inquiry_date;
	}

	public final String getInquiry_ch_date() {
		return inquiry_ch_date;
	}

	public final void setInquiry_ch_date(String inquiry_ch_date) {
		this.inquiry_ch_date = inquiry_ch_date;
	}

	public final String getInquiry_response() {
		return inquiry_response;
	}

	public final void setInquiry_response(String inquiry_response) {
		this.inquiry_response = inquiry_response;
	}

	public final String getI_user_no() {
		return i_user_no;
	}

	public final void setI_user_no(String i_user_no) {
		this.i_user_no = i_user_no;
	}

	public final String getI_user_id() {
		return i_user_id;
	}

	public final void setI_user_id(String i_user_id) {
		this.i_user_id = i_user_id;
	}

	public final String getI_nick_name() {
		return i_nick_name;
	}

	public final void setI_nick_name(String i_nick_name) {
		this.i_nick_name = i_nick_name;
	}

	public final String getI_author() {
		return i_author;
	}

	public final void setI_author(String i_author) {
		this.i_author = i_author;
	}

	public final String getRnum() {
		return rnum;
	}

	public final void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public final String getRdate() {
		return rdate;
	}

	public final void setRdate(String rdate) {
		this.rdate = rdate;
	}

}
