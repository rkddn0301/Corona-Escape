package poly.service;

import java.util.List;

import poly.dto.UserDTO;

public interface IUserService {

	// 회원가입하기(회원정보등록)
	int InsertUser(UserDTO pDTO) throws Exception;

	// 아이디 중복 체크
	int idCheck(UserDTO pDTO) throws Exception;

	// 이메일 중복체크
	int mailCheck(UserDTO pDTO) throws Exception;

	// 닉네임 중복체크
	int checkNick(UserDTO pDTO) throws Exception;

	// 로그인
	UserDTO getLogin(UserDTO pDTO) throws Exception;

	// 아이디 찾기
	UserDTO idFind(UserDTO pDTO) throws Exception;

	// 아이디 찾기(이름, 이메일 일치하는지 확인)
	int idFindCheck(UserDTO pDTO) throws Exception;

	// 비밀번호 찾기(이름, 아이디, 이메일)
	int pwFindCheck(UserDTO pDTO) throws Exception;

	// 비밀번호 자동 변경
	int pwAutoUpdate(UserDTO pDTO) throws Exception;

	// 이메일 발송
	int pwmail(UserDTO pDTO) throws Exception;

	// 마이페이지(비밀번호 변경)
	int updatePw(UserDTO pDTO) throws Exception;

	// 마이페이지(주소 변경)
	int updateAddr(UserDTO pDTO) throws Exception;

	// 마이페이지(회원탈퇴)
	int deleteUser(UserDTO pDTO);

	// 회원관리(리스트)
	List<UserDTO> getUserList(UserDTO pDTO) throws Exception;

	// 회원관리(회원 수)
	int UserCounter(UserDTO pDTO) throws Exception;

	// 회원관리(검색)
	List<UserDTO> SearchList(UserDTO pDTO) throws Exception;

	// 회원관리(검색 수)
	int SearchCounter(UserDTO pDTO) throws Exception;

	// 회원관리(탈퇴)
	int dropUser(UserDTO pDTO);
}
