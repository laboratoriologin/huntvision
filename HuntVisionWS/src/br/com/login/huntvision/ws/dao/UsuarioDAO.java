package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Usuario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class UsuarioDAO  implements RestDAO<Usuario> {

	@Override
	public Usuario get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.get", id);

		return (Usuario) broker.getObjectBean(Usuario.class, "celular", "email", "flagAtivo", "grupoUsuario.id", "id", "login", "nome", "senha");

	}
	
	public Usuario getByLogin(String login) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.getbylogin", login);

		return (Usuario) broker.getObjectBean(Usuario.class, "celular", "email", "flagAtivo", "grupoUsuario.id", "id", "login", "nome", "senha");

	}

	@Override
	public List<Usuario> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.findall");

		return broker.getCollectionBean(Usuario.class, "celular", "email", "flagAtivo", "grupoUsuario.id", "id", "login", "nome", "senha");

	}

	@Override
	public Usuario insert(Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.usuarios "));

		broker.setPropertySQL("usuariodao.insert",model.getCelular(), model.getEmail(), model.getFlagAtivo(), model.getGrupoUsuario().getId(), model.getLogin(), model.getNome(), model.getSenha());

		broker.execute();

		return model;

	}

	@Override
	public Usuario update(final Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.update", model.getCelular(), model.getEmail(), model.getFlagAtivo(), model.getGrupoUsuario().getId(), model.getLogin(), model.getNome(), model.getSenha(), model.getId());

		broker.execute();

		return model;

	}
	
	public Usuario updateSenha(final Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.updatesenha",model.getSenha(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.delete", id);

		broker.execute();

	}

}
