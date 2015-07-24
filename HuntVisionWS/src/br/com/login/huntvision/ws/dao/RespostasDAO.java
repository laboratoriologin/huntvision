package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Resposta;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class RespostasDAO  implements RestDAO<Resposta> {

	@Override
	public Resposta get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("respostasdao.get", id);

		return (Resposta) broker.getObjectBean(Resposta.class, "descricao", "flagNaoConformidade", "id",  "observacao", "questionario.id");

	}

	@Override
	public List<Resposta> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("respostasdao.findall");
		
		return broker.getCollectionBean(Resposta.class, "descricao", "flagNaoConformidade", "id", "observacao", "questionario.id");		

	}

	@Override
	public Resposta insert(Resposta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.respostas "));

		broker.setPropertySQL("respostasdao.insert",model.getDescricao(), model.getFlagNaoConformidade(), model.getObservacao(), model.getQuestionario());

		broker.execute();

		return model;

	}

	@Override
	public Resposta update(final Resposta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("respostasdao.update", model.getDescricao(), model.getFlagNaoConformidade(),  model.getObservacao(), model.getId(), model.getQuestionario());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("respostasdao.delete", id);

		broker.execute();

	}

}
