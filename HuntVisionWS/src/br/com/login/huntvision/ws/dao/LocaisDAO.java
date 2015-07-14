package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Local;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class LocaisDAO  implements RestDAO<Local> {

	@Override
	public Local get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("locaisdao.get", id);

		return (Local) broker.getObjectBean(Local.class, "cliente.id",  "id", "nomeLocal");

	}

	@Override
	public List<Local> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("locaisdao.findall");

		return broker.getCollectionBean(Local.class, "cliente.id",  "id", "nomeLocal");

	}

	@Override
	public Local insert(Local model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.locais "));

		broker.setPropertySQL("locaisdao.insert",model.getCliente().getId(), model.getNomeLocal());

		broker.execute();

		return model;

	}

	@Override
	public Local update(final Local model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("locaisdao.update", model.getCliente().getId(), model.getNomeLocal(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("locaisdao.delete", id);

		broker.execute();

	}

}
