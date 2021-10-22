package poly.persistance.redis.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import poly.dto.ChatDTO;
import poly.persistance.redis.IChatMapper;
import poly.persistance.redis.IContRedis;
import poly.util.CmmUtil;

@Component("ChatMapper")
public class ChatMapper implements IChatMapper, IContRedis {
	
	@Autowired
	public RedisTemplate<String, Object> redisDB;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Set<String> getRoomList() throws Exception {
		
		log.info(this.getClass().getName() + ".getRoomList Start!");
		
		redisDB.setKeySerializer(new StringRedisSerializer());
		redisDB.setValueSerializer(new StringRedisSerializer());
		
		// 이름이 Chat으로 시작하는 Key만 가져오기
		Set<String> rSet = (Set) redisDB.keys("*");
		
		log.info(this.getClass().getName() + ".getRoomList End!");
		
		return rSet;
	}

	@Override
	public int insertChat(ChatDTO pDTO) throws Exception {
		log.info(this.getClass().getName() + ".ChatDTO Start!");
		
		int res = 0;
		
		// 대화방 키 정보
		String room_key = CmmUtil.nvl(pDTO.getRoom_key());
		
		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));
		
		redisDB.opsForList().rightPush(room_key, pDTO);
		
		res = 1;
		
		log.info(this.getClass().getName() + ".ChatDTO End!");
		return res;
	}

	@Override
	public List<ChatDTO> getChat(ChatDTO pDTO) throws Exception {
		log.info(this.getClass().getName() + ".getChat Start!");
		
		// Redis에서 가져온 결과 저장할 객체
		List<ChatDTO> rList = null;
		
		// 대화방 키 정보
		String room_key = CmmUtil.nvl(pDTO.getRoom_key());
		
		/*
		 * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
		 */
		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));
		
		if(redisDB.hasKey(room_key)) {
			
			// 저장한 전체 레코드 수
			rList = (List) redisDB.opsForList().range(room_key, 0, -1);
			
			if (rList == null) {
				rList = new LinkedList<ChatDTO>();
			}
		}
		
		log.info(this.getClass().getName() + ".getChat End!");
		return rList;
	}

	@Override
	public boolean setTimeOutHour(String room_key, int hours) throws Exception {
		log.info(this.getClass().getName() + ".setTimeOutHour Start!");
		return redisDB.expire(room_key, hours, TimeUnit.HOURS);
	}

	@Override
	public boolean setTimeOutMinute(String room_key, int minutes) throws Exception {
		log.info(this.getClass().getName() + ".setTimeOutMinute Start!");
		return redisDB.expire(room_key, minutes, TimeUnit.MINUTES);
	}
	

}
