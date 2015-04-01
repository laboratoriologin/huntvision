package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Item;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ItemDAO  implements RestDAO<Item> {

	@Override
	public Item get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itemdao.get", id);

		return (Item) broker.getObjectBean(Item.class, "chave", "id" , "descricao");

	}

	@Override
	public List<Item> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itemdao.findall");

		return broker.getCollectionBean(Item.class, "chave", "id" , "descricao");

	}

	@Override
	public Item insert(Item model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.itens "));

		broker.setPropertySQL("itemdao.insert",model.getChave(),model.getDescricao());

		broker.execute();

		return model;

	}

	@Override
	public Item update(final Item model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itemdao.update", model.getChave(),model.getDescricao(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("itemdao.delete", id);

		broker.execute();

	}

}
