package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.TipoQuestionario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TipoQuestionariosDAO  implements RestDAO<TipoQuestionario> {

	@Override
	public TipoQuestionario get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoquestionariosdao.get", id);

		return (TipoQuestionario) broker.getObjectBean(TipoQuestionario.class, "descricao", "id");

	}

	@Override
	public List<TipoQuestionario> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoquestionariosdao.findall");

		return broker.getCollectionBean(TipoQuestionario.class, "descricao", "id");

	}

	@Override
	public TipoQuestionario insert(TipoQuestionario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.tipo_questionarios "));

		broker.setPropertySQL("tipoquestionariosdao.insert",model.getDescricao());

		broker.execute();

		return model;

	}

	@Override
	public TipoQuestionario update(final TipoQuestionario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoquestionariosdao.update", model.getDescricao(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoquestionariosdao.delete", id);

		broker.execute();

	}

}
