package com.login.huntvision.faces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.topsys.exception.TSApplicationException;

import com.login.huntvision.model.Imagem;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.Questionario;
import com.login.huntvision.model.Resposta;
import com.login.huntvision.model.TipoQuestionario;
import com.login.huntvision.util.Constantes;
import com.login.huntvision.util.HuntVisionUtil;
import com.login.huntvision.util.UsuarioUtil;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "questionarioFaces")
public class QuestionarioFaces extends CrudFaces<Questionario> {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboTipoQuestionario;

	public List<SelectItem> getComboItem() {
		return comboItem;
	}

	public void setComboItem(List<SelectItem> comboItem) {
		this.comboItem = comboItem;
	}

	private List<SelectItem> comboItem;
	
	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboTipoQuestionario = super.initCombo(new TipoQuestionario().findByModel("descricao"), "id", "descricao");
		this.comboItem = super.initCombo(new Item().findByModel("descricao"), "id", "descricao");
		setFieldOrdem("pergunta");

	}

	@Override
	public String limpar() {

		super.limpar();
		this.getCrudModel().setRespostas(new ArrayList<Resposta>());
		this.getCrudModel().setImagens(new ArrayList<Imagem>());
		this.getCrudModel().setStatus(Boolean.TRUE);
		getCrudModel().setTipoQuestionario(new TipoQuestionario());
		getCrudModel().setItem(new Item());
		return null;

	}

	@Override
	protected String detail() {
		super.detail();

		return null;
	}

	@Override
	protected void prePersist() {

		for (Imagem objImagem : getCrudModel().getImagens()) {
			objImagem.setQuestionario(getCrudModel());
		}

		for (Resposta objResposta : getCrudModel().getRespostas()) {
			objResposta.setQuestionario(getCrudModel());
		}
		getCrudModel().setRespostas(getCrudModel().getRespostas());

		getCrudModel().setUsuario(UsuarioUtil.obterUsuarioConectado());

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy'at' hh:mm:ss a zzz");
		getCrudModel().setData(ft.format(dNow));

	}
	
	@Override
	protected String insert() throws TSApplicationException {
		
		super.setClearFields(false);
		
		Long item = getCrudModel().getItem().getId();
		
		Long tipoQuestionario = getCrudModel().getTipoQuestionario().getId();
		
		super.insert();
		
		getCrudModel().getItem().setId(item);
		
		getCrudModel().getTipoQuestionario().setId(tipoQuestionario);
		
		return null;
		
	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the comboTipoQuestionario
	 */
	public List<SelectItem> getComboTipoQuestionario() {
		return comboTipoQuestionario;
	}

	/**
	 * @param comboTipoQuestionario
	 *            the comboTipoQuestionario to set
	 */
	public void setComboTipoQuestionario(List<SelectItem> comboTipoQuestionario) {
		this.comboTipoQuestionario = comboTipoQuestionario;
	}

	public String adicionarResposta() {

		this.getCrudModel().getRespostas().add(new Resposta());

		return null;
	}

	public String removerResposta(Integer intPosition) {

		this.getCrudModel().getRespostas().remove(intPosition.intValue());

		return null;
	}

	public void uploadMidias(FileUploadEvent event) {

		Calendar now = Calendar.getInstance();
		String nomeArquivo = "";
		nomeArquivo += String.valueOf(now.get(Calendar.YEAR));
		nomeArquivo += String.valueOf(now.get(Calendar.MONTH));
		nomeArquivo += String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		nomeArquivo += String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		nomeArquivo += String.valueOf(now.get(Calendar.MINUTE));
		nomeArquivo += String.valueOf(now.get(Calendar.SECOND));
		nomeArquivo += String.valueOf(now.get(Calendar.MILLISECOND));
		nomeArquivo += "." + FilenameUtils.getExtension(event.getFile().getFileName());

		Imagem objImagem = new Imagem();

		objImagem.setLegenda("");

		objImagem.setCaminho(nomeArquivo);

		this.getCrudModel().getImagens().add(objImagem);

		HuntVisionUtil.criaArquivo(event.getFile(), Constantes.CAMINHO_ARQUIVO + nomeArquivo);

	}

}
