/**
 * 
 */
package com.login.huntvision.faces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;













import br.com.topsys.exception.TSSystemException;
import br.com.topsys.util.TSUtil;

import com.login.huntvision.model.Imagem;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.Questionario;
import com.login.huntvision.model.Resposta;
import com.login.huntvision.model.TipoQuestionario;
import com.login.huntvision.model.Usuario;
import com.login.huntvision.util.Constantes;
import com.login.huntvision.util.HuntVisionUtil;
import com.login.huntvision.util.UsuarioUtil;
import com.login.huntvision.util.Utilitarios;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "questionarioFaces")
public class QuestionarioFaces extends CrudFaces<Questionario> {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboTipoQuestionario;

	/**
	 * @return the comboItem
	 */
	public List<SelectItem> getComboItem() {
		return comboItem;
	}

	/**
	 * @param comboItem the comboItem to set
	 */
	public void setComboItem(List<SelectItem> comboItem) {
		this.comboItem = comboItem;
	}

	/**
	 * @return the mAX_LARGURA_LOGO
	 */
	public int getMAX_LARGURA_LOGO() {
		return MAX_LARGURA_LOGO;
	}

	/**
	 * @return the mAX_ALTURA_LOGO
	 */
	public int getMAX_ALTURA_LOGO() {
		return MAX_ALTURA_LOGO;
	}

	private List<SelectItem> comboItem;
	private final int MAX_LARGURA_LOGO = 5000;
	private final int MAX_ALTURA_LOGO = 5000;

	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboTipoQuestionario = super.initCombo(
				new TipoQuestionario().findByModel("descricao"), "id",
				"descricao" );
		getCrudModel().setTipoQuestionario(new TipoQuestionario());
		
		this.comboItem = super.initCombo(
				new Item().findByModel("descricao"), "id",
				"descricao");
		getCrudModel().setItem(new Item());
		
	
		setFieldOrdem("pergunta");
	
	}

	@Override
	public String limpar() {

		super.limpar();
		this.getCrudModel().setRespostas(new ArrayList<Resposta>());
		this.getCrudModel().setImagens(new ArrayList<Imagem>());
		return null;

	}

	@Override
	protected String detail() {
		super.detail();

		return null;
	}

	@Override
	protected void prePersist() {
		
		for(Imagem objImagem: getCrudModel().getImagens())
		{
			objImagem.setQuestionario(getCrudModel());
		}
		
		for(Resposta objResposta: getCrudModel().getRespostas())
		{
			objResposta.setQuestionario(getCrudModel());
		}
		getCrudModel().setRespostas(getCrudModel().getRespostas());

	    getCrudModel().setUsuario(UsuarioUtil.obterUsuarioConectado());
	   
	      Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("E dd.MM.yyyy'at' hh:mm:ss a zzz");
	      getCrudModel().setData(ft.format(dNow));
	  	 
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
