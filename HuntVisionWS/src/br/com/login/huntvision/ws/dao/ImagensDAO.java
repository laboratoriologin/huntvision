package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Imagem;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ImagensDAO  implements RestDAO<Imagem> {

	@Override
	public Imagem get(Long id) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("imagensdao.get", id);

		return (Imagem) broker.getObjectBean(Imagem.class, "caminho", "id", "legenda");

	}

	@Override
	public List<Imagem> getAll() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("imagensdao.findall");

		return broker.getCollectionBean(Imagem.class, "caminho", "id", "legenda");

	}

	@Override
	public Imagem insert(Imagem model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.imagens "));

		broker.setPropertySQL("imagensdao.insert",model.getCaminho(), model.getLegenda());

		broker.execute();

		return model;

	}

	@Override
	public Imagem update(final Imagem model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("imagensdao.update", model.getCaminho(), model.getLegenda(), model.getId());

		broker.execute();

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("imagensdao.delete", id);

		broker.execute();

	}

}
