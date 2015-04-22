package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Vistoria;
import br.com.login.huntvision.ws.model.VistoriaResposta;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class VistoriaDAO  implements RestDAO<Vistoria> {

	@Override
	public Vistoria get(Long id) {
		
		return null;
	}

	@Override
	public List<Vistoria> getAll() {

		return null;
		
	}

	@Override
	public Vistoria insert(Vistoria model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("dbo.vistorias"));

		broker.setPropertySQL("vistoriadao.insert", model.getUsuario().getId(), model.getCliente().getId(), model.getData(), model.getLatitude(), model.getLongitude());

		broker.execute();
		
		for(VistoriaResposta resposta : model.getRespostas()) {
			
			broker.setPropertySQL("vistoriarespostadao.insert", resposta.getResposta().getId(), model.getId(), resposta.getImagem(), resposta.getObservacao());

			broker.execute();
			
		}
		
		broker.endTransaction();

		return model;

	}

	@Override
	public Vistoria update(final Vistoria model) throws TSApplicationException {

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {

	}

}
