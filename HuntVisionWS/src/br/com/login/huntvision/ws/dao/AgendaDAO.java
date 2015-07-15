package br.com.login.huntvision.ws.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.login.huntvision.ws.model.Agenda;
import br.com.login.huntvision.ws.model.Agenda;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class AgendaDAO implements RestDAO<Agenda> {

	@Override
	public Agenda get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("agendasdao.get", id);

		return (Agenda) broker.getObjectBean(Agenda.class, "id", "cliente.id", "usuario.id", "descricao", "dataHora", "dataHoraChegada", "dataHoraSaida");

	}

	@Override
	public List<Agenda> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("agendasdao.findall");

		return broker.getCollectionBean(Agenda.class, "id", "cliente.id", "usuario.id", "descricao", "dataHora", "dataHoraChegada", "dataHoraSaida");

	}

	@Override
	public Agenda insert(Agenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.agendas "));

		broker.setPropertySQL("agendasdao.insert", model.getCliente().getId(), model.getUsuario().getId(), model.getDescricao(), model.getDataHora(), model.getDataHoraChegada(), model.getDataHoraSaida());

		broker.execute();

		return model;

	}

	public Agenda updateHoras(final Agenda model, Date dataHoraChegada, Date dataHoraSaida) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("agendasdao.updatehoras", dataHoraChegada == null?null : new Timestamp(dataHoraChegada.getTime()), dataHoraSaida == null?null: new Timestamp(dataHoraSaida.getTime()), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public Agenda update(final Agenda model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("agendasdao.update", model.getCliente().getId(), model.getUsuario().getId(), model.getDescricao(), model.getDataHora(), model.getDataHoraChegada(), model.getDataHoraSaida());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("agendasdao.delete", id);

		broker.execute();

	}

}
