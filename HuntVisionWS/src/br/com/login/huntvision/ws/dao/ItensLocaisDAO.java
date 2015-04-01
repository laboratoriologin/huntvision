package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.ItemLocal;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ItensLocaisDAO implements RestDAO<ItemLocal> {

	@Override
	public ItemLocal get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itenslocaisdao.get", id);

		return (ItemLocal) broker.getObjectBean(ItemLocal.class, "id", "local.id", "item.id");

	}

	@Override
	public List<ItemLocal> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itenslocaisdao.findall");

		return broker.getCollectionBean(ItemLocal.class, "id", "local.id", "item.id");

	}

	@Override
	public ItemLocal insert(ItemLocal model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.grupos_itens "));

		broker.setPropertySQL("itenslocaisdao.insert", model.getLocal().getId(), model.getItem().getId());

		broker.execute();

		return model;

	}

	@Override
	public ItemLocal update(final ItemLocal model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itenslocaisdao.update" , model.getLocal().getId(), model.getItem().getId(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itenslocaisdao.delete", id);

		broker.execute();

	}

}
