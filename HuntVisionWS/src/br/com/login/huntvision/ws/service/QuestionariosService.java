package br.com.login.huntvision.ws.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;

import br.com.login.huntvision.ws.model.Questionario;
import br.com.login.huntvision.ws.model.Usuario;
import br.com.login.huntvision.ws.util.Constantes;
import br.com.login.huntvision.ws.dao.QuestionariosDAO;
import br.com.topsys.exception.TSSystemException;
@Path("/questionarios")
public class QuestionariosService extends RestService<Questionario> {

	@Override
	public void initDAO() {
		this.restDAO = new QuestionariosDAO();
	}
	

}
