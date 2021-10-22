package poly.persistance.redis;

public interface IContRedis {
	
	// 인터페이스 선언된 변수는 상수로 선언(final과 동일)되면, 한 번 메모리에 로딩된 후 값은 변경할 수 없음
	public String roomInfoKey = "myRoomKey";

}
