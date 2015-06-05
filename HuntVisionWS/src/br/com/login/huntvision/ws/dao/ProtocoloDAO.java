package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Protocolo;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ProtocoloDAO  implements RestDAO<Protocolo> {

	@Override
	public Protocolo get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolodao.get", id);

		return (Protocolo) broker.getObjectBean(Protocolo.class,  "id" , "nome", "norma");

	}

	@Override
	public List<Protocolo> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolodao.findall");

		return broker.getCollectionBean(Protocolo.class, "id" , "nome", "norma");

	}

	@Override
	public Protocolo insert(Protocolo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.protocolos "));

		broker.setPropertySQL("protocolodao.insert",model.getNome(),model.getNorma());

		broker.execute();

		return model;

	}

	@Override
	public Protocolo update(final Protocolo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolodao.update", model.getNome(),model.getNorma(), model.getId());
 
		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolodao.delete", id);

		broker.execute();

	}

}
