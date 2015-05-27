package com.login.huntvision.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.login.huntvision.model.ConfiguracaoEmail;


public class EmailUtil {

	/*
	 * caso queira mudar o servidor e a porta, so enviar para o contrutor os
	 * valor como string
	 */
	/*
	 * public EnviarEmail(String mailSMTPServer, String mailSMTPServerPort) { //
	 * Para // Servidor this.mailSMTPServer = mailSMTPServer;
	 * this.mailSMTPServerPort = mailSMTPServerPort; }
	 */

	public static void enviar(String to, String message, final ConfiguracaoEmail configuracaoEmail) {


		Properties props = new Properties();
		
	    props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP  
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", configuracaoEmail.getSmtp());
		props.put("mail.smtp.port", configuracaoEmail.getPortaSmtp().toString());
		props.put("mail.smtp.user", configuracaoEmail.getRemetente()); 

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configuracaoEmail.getRemetente(), configuracaoEmail.getSenha());
			}
		});

		try {
			
			Message msg = new MimeMessage(session);

			// Setando o destinatário
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Setando a origem do email
		 	msg.setFrom(new InternetAddress(configuracaoEmail.getRemetente()));
			// Setando o assunto
			msg.setSubject(Constantes.ASSUNTO_EMAIL);
			// Setando o conteúdo/corpo do email
			msg.setContent(message, "text/html; charset=utf-8");

			Transport.send(msg);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}

// clase que retorna uma autenticacao para ser enviada e verificada pelo
// servidor smtp
class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;

	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}