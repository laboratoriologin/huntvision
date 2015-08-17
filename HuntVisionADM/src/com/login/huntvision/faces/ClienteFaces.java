/**
 * 
 */
package com.login.huntvision.faces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.file.TSFile;
import br.com.topsys.util.TSUtil;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.ItemLocal;
import com.login.huntvision.model.Local;
import com.login.huntvision.util.Constantes;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "clienteFaces")
public class ClienteFaces extends CrudFaces<Cliente> {

	private static final long serialVersionUID = 1L;

	private Local localSelecionado;
	private List<SelectItem> comboItem;

	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboItem = super.initCombo(new Item().findByModel("descricao"), "id", "descricao");
		setFieldOrdem("nome");
	}

	public String addLocal() {

		Local local = new Local();

		local.setCliente(getCrudModel());

		if (TSUtil.isEmpty(getCrudModel().getLocais())) {
			getCrudModel().setLocais(new ArrayList<Local>());
		}

		getCrudModel().getLocais().add(local);

		return null;
	}

	public String addItem() {

		if (TSUtil.isEmpty(this.localSelecionado.getItensLocais())) {
			this.localSelecionado.setItensLocais(new ArrayList<ItemLocal>());
		}

		ItemLocal itemLocal = new ItemLocal();

		itemLocal.setItem(new Item());

		itemLocal.setLocal(this.localSelecionado);
		
		this.localSelecionado.getItensLocais().add(itemLocal);

		return null;

	}

	public String delLocal() {
		getCrudModel().getLocais().remove(this.localSelecionado);
		return null;
	}
	
	public String delItem(Integer position) {
		this.localSelecionado.getItensLocais().remove(position.intValue());
		return null;
	}

	@Override
	protected String detail() {
		super.detail();

		return null;
	}

	@Override
	protected void prePersist() {

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

		this.getCrudModel().setImagem(nomeArquivo);

		try {

			TSFile.inputStreamToFile(event.getFile().getInputstream(), Constantes.CAMINHO_ARQUIVO + this.getCrudModel().getImagem());

		} catch (TSApplicationException | IOException e) {
			this.addErrorMessage("Ocorreu um erro ao enviar imagem: " + e.getMessage());
			e.printStackTrace();

		}

	}

	public Local getLocalSelecionado() {
		return localSelecionado;
	}

	public void setLocalSelecionado(Local localSelecionado) {
		this.localSelecionado = localSelecionado;
	}

	public List<SelectItem> getComboItem() {
		return comboItem;
	}

	public void setComboItem(List<SelectItem> comboItem) {
		this.comboItem = comboItem;
	}

}
