package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Destinatario;
import br.com.login.huntvision.ws.model.Resposta;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class DestinariosDAO implements RestDAO<Destinatario> {

	@Override
	public Destinatario get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("destinariodao.get", id);

		return (Destinatario) broker.getObjectBean(Destinatario.class, "id", "nome" , "email", "itemLocal.id");

	}

	@Override
	public List<Destinatario> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("destinariodao.findall");

		return broker.getCollectionBean(Destinatario.class,  "id", "nome" , "email", "itemLocal.id");

	}

	@Override
	public Destinatario insert(Destinatario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.destinatarios"));

		broker.setPropertySQL("destinariodao.insert", model.getNome() , model.getEmail() , model.getItemLocal().getId());

		broker.execute();

		return model;

	}

	@Override
	public Destinatario update(final Destinatario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("destinariodao.update" , model.getNome() ,  model.getEmail() ,model.getItemLocal().getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("destinariodao.delete", id);

		broker.execute();

	}
	
	public List<Destinatario> getByDestinatarios() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("destinariosdAO.getbyDestinatarios");
		
		return broker.getCollectionBean(Destinatario.class, "nome", "email", "itemLocal");		

	}

}
