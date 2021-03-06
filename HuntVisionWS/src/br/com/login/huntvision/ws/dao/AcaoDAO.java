package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Acao;
import br.com.login.huntvision.ws.model.Questionario;
import br.com.login.huntvision.ws.model.Resposta;
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

		return broker.getCollectionBean(Acao.class, "nome" , "procedimento");

	}

	@Override
	public Acao insert(Acao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.acoes"));

		broker.setPropertySQL("acaodao.insert", model.getNome()  , model.getProcedimento());

		broker.execute();

		return model;

	}

	@Override
	public Acao update(final Acao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.update", model.getNome()  , model.getProcedimento());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.delete", id);

		broker.execute();

	}
	
	public List<Acao> getAll(Questionario questionario) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("acaodao.findallbyquestionario", questionario.getId());

		return broker.getCollectionBean(Acao.class, "nome" , "procedimento");

	}

}
