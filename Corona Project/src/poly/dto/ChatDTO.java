package poly.dto;

public class ChatDTO {
	
	private String room_key; // 방번호
	private String user_no; // 사용자번호
	private String nick_name; // 닉네임
	private String msg; // 메시지
	private String datetime; // 전송시간
	private String author; // 권한(1 = 관리자, 0 = 사용자)
	
	public final String getRoom_key() {
		return room_key;
	}
	public final void setRoom_key(String room_key) {
		this.room_key = room_key;
	}
	public final String getUser_no() {
		return user_no;
	}
	public final void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public final String getNick_name() {
		return nick_name;
	}
	public final void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public final String getMsg() {
		return msg;
	}
	public final void setMsg(String msg) {
		this.msg = msg;
	}
	public final String getDatetime() {
		return datetime;
	}
	public final void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public final String getAuthor() {
		return author;
	}
	public final void setAuthor(String author) {
		this.author = author;
	}
	
	

}
