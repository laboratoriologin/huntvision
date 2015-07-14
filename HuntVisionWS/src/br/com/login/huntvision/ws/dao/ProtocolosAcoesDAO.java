package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.ItemLocal;
import br.com.login.huntvision.ws.model.ProtocoloAcao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ProtocolosAcoesDAO implements RestDAO<ProtocoloAcao> {

	@Override
	public ProtocoloAcao get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolosacoesdao.get", id);

		return (ProtocoloAcao) broker.getObjectBean(ProtocoloAcao.class, "id", "protocolo.id", "acao.id");

	}

	@Override
	public List<ProtocoloAcao> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolosacoesdao.findall");

		return broker.getCollectionBean(ProtocoloAcao.class, "id", "protocolo.id", "acao.id");

	}

	@Override
	public ProtocoloAcao insert(ProtocoloAcao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.protocolos_acoes"));

		broker.setPropertySQL("protocolosacoesdao.insert", model.getProtocolo().getId(), model.getAcao().getId());

		broker.execute();

		return model;

	}

	@Override
	public ProtocoloAcao update(final ProtocoloAcao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolosacoesdao.update" , model.getProtocolo().getId(), model.getAcao().getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("protocolosacoesdao.delete", id);

		broker.execute();

	}

}
