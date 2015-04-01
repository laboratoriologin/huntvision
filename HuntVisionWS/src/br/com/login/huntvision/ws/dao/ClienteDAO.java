package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Cliente;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ClienteDAO  implements RestDAO<Cliente> {

	@Override
	public Cliente get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.get", id);

		return (Cliente) broker.getObjectBean(Cliente.class, "bairro", "cep", "cidade", "cnpj", "email", "endereco", "estado", "id", "nome", "pais", "telefone");

	}

	@Override
	public List<Cliente> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.findall");

		return broker.getCollectionBean(Cliente.class, "bairro", "cep", "cidade", "cnpj", "email", "endereco", "estado", "id", "nome", "pais", "telefone");

	}

	@Override
	public Cliente insert(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.clientes "));

		broker.setPropertySQL("clientedao.insert",model.getBairro(), model.getCep(), model.getCidade(), model.getCnpj(), model.getEmail(), model.getEndereco(), model.getEstado(), model.getNome(), model.getPais(), model.getTelefone());

		broker.execute();

		return model;

	}

	@Override
	public Cliente update(final Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.update", model.getBairro(), model.getCep(), model.getCidade(), model.getCnpj(), model.getEmail(), model.getEndereco(), model.getEstado(), model.getNome(), model.getPais(), model.getTelefone(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.delete", id);

		broker.execute();

	}

}
