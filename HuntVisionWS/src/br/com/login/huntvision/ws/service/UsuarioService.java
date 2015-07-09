package br.com.login.huntvision.ws.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.catalina.connector.Response;

import br.com.login.huntvision.ws.dao.UsuarioDAO;
import br.com.login.huntvision.ws.exception.ApplicationException;
import br.com.login.huntvision.ws.model.Usuario;
import br.com.login.huntvision.ws.util.Constantes;
import br.com.login.huntvision.ws.util.GeneratePassword;
import br.com.topsys.constant.TSConstant;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.exception.TSSystemException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;

@Path("/usuarios")
public class UsuarioService extends RestService<Usuario> {

	@Override
	public void initDAO() {
		this.restDAO = new UsuarioDAO();
	}

	@GET
	@Path("/login/{login}")
	@Produces("application/json; charset=UTF-8")
	public Usuario login(@PathParam("login") String login) {
		return new UsuarioDAO().getByLogin(login);
	}

	@POST
	@Path("/lembrar_senha/{login}")
	@Produces("application/json; charset=UTF-8")
	public void lembrarSenha(@PathParam("login") String login) throws ApplicationException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		Usuario usuario = usuarioDAO.getByLogin(login);

		if (TSUtil.isEmpty(usuario)) {

			throw new ApplicationException("Usuário inexistente", Response.SC_BAD_REQUEST);

		}

		String novaSenha = GeneratePassword.generatePassword();

		usuario.setSenha(TSCryptoUtil.gerarHash(novaSenha, TSConstant.CRIPTOGRAFIA_MD5));

		try {

			new UsuarioDAO().updateSenha(usuario);

			enviarEmailNovaSenha(usuario);

		} catch (TSApplicationException e) {

			throw new ApplicationException(e.getMessage(), Response.SC_BAD_REQUEST);

		}

	}

	public static void enviarEmailNovaSenha(final Usuario usuario) {

		StringBuilder email = new StringBuilder();

		email.append(usuario.getNome()).append(", <br/>");

		email.append("Sua nova senha é: ").append(usuario.getSenha());

		Properties props = System.getProperties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {

			@Override
			public PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(usuario.getEmail(), usuario.getSenha().toCharArray());

			}

		};

		try {

			Session session = Session.getInstance(props);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(Constantes.REMETENTE, Constantes.NOME_APP));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail(), ""));

			message.setSubject(Constantes.ASSUNTO_EMAIL, "UTF-8");

			message.setContent(email.toString(), "text/html");

			Transport.send(message);

		} catch (Exception ex) {
			throw new TSSystemException(ex);
		}

	}
}
