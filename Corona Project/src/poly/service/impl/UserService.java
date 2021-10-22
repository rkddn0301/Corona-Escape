package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.ICoronaMainService;
import poly.service.ICountryService;
import poly.service.IGubunService;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service("UserService")
public class UserService implements IUserService {

	private Logger log = Logger.getLogger(this.getClass());

	// 회원 DB
	@Resource(name = "UserMapper")
	private IUserMapper UserMapper;

	// 메일 서비스
	@Resource(name = "MailService")
	private IMailService MailService;

	// 코로나 서비스
	@Resource(name = "CoronaMainService")
	private ICoronaMainService CoronaMainService;

	// 도시별 확진자수 서비스
	@Resource(name = "CountryService")
	private ICountryService CountryService;
	
	// 구분별 확진자수 서비스
	@Resource(name = "GubunService")
	private IGubunService GubunService;

	// 회원가입 서비스
	@Override
	public int InsertUser(UserDTO pDTO) throws Exception {
		// 가입성공 : 1, 중복으로 인한 취소 : 2, 기타 에러 : 0
		int res = 0;

		// Controller에서 값이 넘어오지 않을 경우를 대비
		if (pDTO == null) {
			pDTO = new UserDTO();
		}

		log.info(pDTO.getUser_id());
		log.info(pDTO.getUser_name());
		log.info(pDTO.getPassword());
		log.info(pDTO.getYear());
		log.info(pDTO.getMonth());
		log.info(pDTO.getDay());
		log.info(pDTO.getEmail());
		log.info(pDTO.getNick_name());
		log.info(pDTO.getGender());
		log.info(pDTO.getAddr1());
		log.info(pDTO.getAddr2());

		// 중복 방지를 위해 데이터 조회
		log.info("idCheck 시작 ----------");

		int check = UserMapper.idCheck(pDTO);

		log.info("idCheck 값 : " + check);

		log.info("idCheck 끝 ----------");

		// 중복된 회원정보가 있는 경우, 결과값을 2회 변경하고 작업을 진행하지 않음
		if (check > 0) {
			res = 2;
		}

		// 중복된 정보가 없는 경우 작업을 진행함
		else {
			// 회원가입
			log.info("InsertUser 시작 ----------");

			int success = UserMapper.InsertUser(pDTO);

			log.info("success 값 : " + success);

			log.info("InsertUser 끝 ----------");

			// db에 등록됐는지 확인
			if (success > 0) {
				res = 1;
			} else {
				res = 0;
			}
		}

		return res;
	}

	// 아이디 중복체크
	@Override
	public int idCheck(UserDTO pDTO) throws Exception {
		return UserMapper.idCheck(pDTO);
	}

	// 닉네임 중복체크
	@Override
	public int checkNick(UserDTO pDTO) throws Exception {
		return UserMapper.checkNick(pDTO);
	}

	// 이메일 중복체크
	@Override
	public int mailCheck(UserDTO pDTO) throws Exception {
		return UserMapper.mailCheck(pDTO);
	}

	// 로그인 구현
	@Override
	public UserDTO getLogin(UserDTO pDTO) throws Exception {
		
		

		return UserMapper.getLogin(pDTO);
	}

	// 아이디 찾기 구현

	@Override
	public UserDTO idFind(UserDTO pDTO) throws Exception {
		return UserMapper.idFind(pDTO);
	}

	// 아이디 찾기(이름, 이메일 일치하는지 확인)

	@Override
	public int idFindCheck(UserDTO pDTO) throws Exception {
		int res = 0;
		UserDTO rDTO = UserMapper.idFindCheck(pDTO);

		if (rDTO == null) {
			rDTO = new UserDTO();
		}

		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res = 1;
		} else if (CmmUtil.nvl(rDTO.getExists_yn()).equals("N")) {
			res = 0;
		} else {
			res = 2;
		}

		return res;
	}

	// 비밀번호 찾기(이름, 아이디, 이메일)

	@Override
	public int pwFindCheck(UserDTO pDTO) throws Exception {
		int res = 0;
		UserDTO rDTO = UserMapper.pwFindCheck(pDTO);

		if (rDTO == null) {
			rDTO = new UserDTO();
		}

		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res = 1;
		} else if (CmmUtil.nvl(rDTO.getExists_yn()).equals("N")) {
			res = 0;
		} else {
			res = 2;
		}

		return res;
	}

	// 비밀번호 자동 변경
	@Override
	public int pwAutoUpdate(UserDTO pDTO) throws Exception {

		return UserMapper.pwAutoUpdate(pDTO);
	}

	// 이메일 발송
	@Override
	public int pwmail(UserDTO pDTO) throws Exception {
		int mres = 0;
		mres = UserMapper.pwAutoUpdate(pDTO);

		// 메일발송 로직 추가
		MailDTO mDTO = new MailDTO();

		mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
		mDTO.setTitle("[Corona Escape] 임시비밀번호 발송해드립니다.");
		mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 비밀번호는 " + pDTO.getTpassword() + " 입니다.");
		log.info(mDTO.getContents().substring(11, 17));

		// 메일발송
		MailService.doSendMail(mDTO);

		return mres;
	}

	@Override
	public int updatePw(UserDTO pDTO) throws Exception {
		return UserMapper.updatePw(pDTO);
	}

	@Override
	public int updateAddr(UserDTO pDTO) throws Exception {
		return UserMapper.updateAddr(pDTO);
	}

	@Override
	public int deleteUser(UserDTO pDTO) {
		return UserMapper.deleteUser(pDTO);
	}

	@Override
	public List<UserDTO> getUserList(UserDTO pDTO) throws Exception {
		return UserMapper.getUserList(pDTO);
	}
	
	@Override
	public int UserCounter(UserDTO pDTO) throws Exception {
		return UserMapper.UserCounter(pDTO);
	}
	
	@Override
	public List<UserDTO> SearchList(UserDTO pDTO) throws Exception {
		return UserMapper.SearchList(pDTO);
	}

	@Override
	public int SearchCounter(UserDTO pDTO) throws Exception {
		return UserMapper.SearchCounter(pDTO);
	}

	@Override
	public int dropUser(UserDTO pDTO) {
		return UserMapper.dropUser(pDTO);
	}

	

	

}
