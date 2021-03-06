package competition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import competition.domain.view.MailView;
import competition.mapper.UserMapper;
import competition.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {
	@Autowired(required=false)
	private MailSender mailSender;
	@Autowired(required=false)
	private UserMapper userMapper;
	@Autowired(required=false)
	private SimpleMailMessage preConfiguredMessage;

	public MailView getEmail(String userId, String question, String answer) {
		MailView mv = userMapper.getEmail(userId, question, answer);
		return mv;
	}

	/**
	 * This method will send compose and send the message
	 * */
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		
	//	System.out.println(to);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	/**
	 * This method will send a pre-configured message
	 * */
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(
				preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
}
