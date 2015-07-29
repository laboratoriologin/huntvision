package br.com.login.huntvision.ws.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;
 



import br.com.login.huntvision.ws.dao.DestinariosDAO;
import br.com.login.huntvision.ws.model.Destinatario;
import br.com.login.huntvision.ws.model.Questionario;
import br.com.login.huntvision.ws.model.Resposta;
import br.com.login.huntvision.ws.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSSystemException;

@Path("/destinatarios")
public class DestinatariosService extends RestService<Destinatario> {

	@Override
	public void initDAO() {
		this.restDAO = new DestinariosDAO();
	}
	

	
	
	public static void enviarRetaguarda(final Destinatario destinatario) {

		StringBuilder email = new StringBuilder();

		email.append(" Sr(a) " + destinatario.getNome()).append(", <br/>");

		email.append("Relatório de não conformidade enviado pelo HuntVision ");

		Properties props = System.getProperties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		

		try {

			Session session = Session.getInstance(props);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(Constantes.REMETENTE, Constantes.NOME_APP));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getEmail(), ""));

			message.setSubject(Constantes.ASSUNTO_EMAIL, "UTF-8");

			message.setContent(email.toString(), "text/html");

			Transport.send(message);

		} catch (Exception ex) {
			throw new TSSystemException(ex);
		}

	}


}
