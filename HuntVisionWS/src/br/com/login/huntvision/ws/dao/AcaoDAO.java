package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Acao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class AcaoDAO  implements RestDAO<Acao> {

	@Override
	public Acao get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.get", id);

		return (Acao) broker.getObjectBean(Acao.class, "nome");
	}

	@Override
	public List<Acao> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.findall");

		return broker.getCollectionBean(Acao.class, "nome");

	}

	@Override
	public Acao insert(Acao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.acoes"));

		broker.setPropertySQL("acaodao.insert", model.getNome());

		broker.execute();

		return model;

	}

	@Override
	public Acao update(final Acao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.update", model.getNome());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.delete", id);

		broker.execute();

	}

}
