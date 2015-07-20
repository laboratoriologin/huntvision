package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Questionario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class QuestionariosDAO  implements RestDAO<Questionario> {

	@Override
	public Questionario get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("questionariosdao.get", id);

		return (Questionario) broker.getObjectBean(Questionario.class, "data", "id",  "item.id", "tipoQuestionario.id", "usuario.id", "pergunta", "status" , "conformidade");
			
	}

	@Override
	public List<Questionario> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("questionariosdao.findall");

		return broker.getCollectionBean(Questionario.class, "data", "id",  "item.id", "tipoQuestionario.id", "usuario.id", "pergunta", "status" , "conformidade");

	}

	@Override
	public Questionario insert(Questionario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.questionarios"));
		
		broker.setPropertySQL("questionariosdao.insert",model.getTipoQuestionario().getId(), model.getItem().getId(), model.getStatus(), model.getPergunta(), model.getUsuario().getId(),  model.getData() ,  model.getConformidade());

		broker.execute();

		return model;

	}

	@Override
	public Questionario update(final Questionario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("questionariosdao.update", model.getTipoQuestionario().getId(), model.getItem().getId(),  model.getStatus(), model.getPergunta(),  model.getUsuario().getId(), model.getData(),model.getId() , model.getConformidade());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("questionariosdao.delete", id);

		broker.execute();

	}

}
