package br.com.login.huntvision.ws.dao;

import java.util.List;

import br.com.login.huntvision.ws.model.Resposta;
import br.com.login.huntvision.ws.model.VistoriaResposta;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class VistoriaRespostaDAO  implements RestDAO<VistoriaResposta> {

	@Override
	public VistoriaResposta get(Long id) {

		return null;

	}

	@Override
	public List<VistoriaResposta> getAll() {

		return  null;

	}

	@Override
	public VistoriaResposta insert(VistoriaResposta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("dbo.vistorias_respostas "));

		broker.setPropertySQL("vistoriarespostadao.insert", model.getResposta().getId(), model.getVistoria().getId(), model.getImagem());

		broker.execute();

		return model;

	}

	@Override
	public VistoriaResposta update(final VistoriaResposta model) throws TSApplicationException {

		return model;

	}

	@Override
	public void delete(Long id) throws TSApplicationException {
	

	}

}
