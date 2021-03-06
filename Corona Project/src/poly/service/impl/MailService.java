package poly.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Service("MailService")
public class MailService implements IMailService {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	final String host = "smtp.gmail.com"; // 네이버에서 제공하는 smtp 서버 -> 전부 구글로 변경해야함
	final String user = "a22495407@gmail.com"; // 구글 아이디
	final String password = "b22495407"; // 구글 비번

	@Override
	public int doSendMail(MailDTO pDTO) {

		// 로그 찍기(후추 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSendMail start!");

		// 메일 발송 성공여부(발송 성공 : 1 / 실패 : 0 )
		int res = 1;

		// 전달 받은 DTO로부터 데이터 가져오기(DTO 객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 때문에 에러방지차원으로 if문
		// 사용함)
		if (pDTO == null) {
			pDTO = new MailDTO();
		}

		String toMail = CmmUtil.nvl(pDTO.getToMail()); // 받는 사람

		// Properties props = new Properties();
		// props.put("mail.smtp.host", host); // javax 외부 라이브러리에 메일 보내는 사람의 정보 설정
		// props.put("mail.smtp.auth", "true"); // javax 외부 라이브러리에 메일 보내는 사람 인증 여부 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		// 네이버 SMTP서버 인증 처리 로직
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

			// 메일 제목
			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

			// 메일 내용
			message.setText(CmmUtil.nvl(pDTO.getContents()));

			// 메일발송
			Transport.send(message);

		} catch (MessagingException e) { // 메일 전송 관련 에러 다 잡기
			res = 0; // 메일 발송이 실패하기 때문에 0으로 변경
			log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
		} catch (Exception e) {// 모든 에러 다 잡기
			res = 0; // 메일 발송이 실패하기 때문에 0으로 변경
			log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
		}

		// 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)

		log.info(this.getClass().getName() + ".doSendMail end!");
		return res;
	}

}
