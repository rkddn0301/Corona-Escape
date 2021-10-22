package poly.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.ChatDTO;
import poly.persistance.redis.IChatMapper;
import poly.service.IChatService;
import poly.util.CmmUtil;

@Service("ChatService")
public class ChatService implements IChatService {
	
	@Resource(name = "ChatMapper")
	private IChatMapper ChatMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Set<String> getRoomList() throws Exception {
		log.info(this.getClass().getName() + ".getRoomList Start!");
		return ChatMapper.getRoomList();
	}

	@Override
	public List<ChatDTO> insertChat(ChatDTO pDTO) throws Exception {
		log.info(this.getClass().getName() + ".insertChat Start!");
		
		// 채팅 내용 저장
		if (ChatMapper.insertChat(pDTO) == 1) {
			log.info("ChatMapper.insertChat Success!");
			
			// 데이터 만료시간 정의(채팅 이후 5분까지만 데이터 저장)
			ChatMapper.setTimeOutMinute(CmmUtil.nvl(pDTO.getRoom_key()), 5);
			
		} else {
			log.info("ChatMapper.insertChat Fail!");
		}
		
		return getChat(pDTO);
	}

	@Override
	public List<ChatDTO> getChat(ChatDTO pDTO) throws Exception {
		log.info(this.getClass().getName() + ".getChat Start!");
		return ChatMapper.getChat(pDTO);
	}
	
	

}
