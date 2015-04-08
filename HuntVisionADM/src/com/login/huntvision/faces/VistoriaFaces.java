/**
 * 
 */
package com.login.huntvision.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.GeradorQRCode;
import com.login.huntvision.model.Local;
import com.login.huntvision.model.Vistoria;
import com.login.huntvision.model.VistoriaResposta;
import com.login.huntvision.util.EmailUtil;
import com.login.huntvision.util.Utilitarios;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * @author Ricardo
 *
 */
@SessionScoped
@ManagedBean(name = "vistoriaFaces")
public class VistoriaFaces extends CrudFaces<Vistoria> {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboLocal;
	private String txtCode;
	private List<SelectItem> comboGeradorQRCode;
	private Cliente cliente;
	private GeradorQRCode itemSelecionado;
	private List<Vistoria> lstVistoria;
	private List<Vistoria> lstVistoriaTratada;
	private List<VistoriaResposta> lstVistoriaResposta;
	private List<VistoriaResposta> lstVistoriaRespostaTratada;

	@PostConstruct
	protected void init() {
	
		this.itemSelecionado = new GeradorQRCode();
		
		this.clearFields();

		setFieldOrdem("descricao");
		lstCliente = new ArrayList<Cliente>();
		lstCliente = (new Cliente().findAll("nome"));

		String clienteId = TSFacesUtil.getRequestParameter("cliente_id");

		if (clienteId != null && TSUtil.isNumeric(clienteId)) {

			Long id = Long.valueOf(clienteId);

			this.cliente = new Cliente();

			this.cliente.setId(id);

			this.cliente = this.cliente.getById();
			
		}

		this.getCrudModel().setCliente(cliente);
		// loadCliente();
		
		geraQrCodeRelatorio();

		

	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		this.txtCode = "";
		return retorno;
	}

	public void loadCliente() {

		lstCliente = new Cliente().findAll();

	}

	public void geraQrCodeRelatorio() {

		lstVistoria = new Vistoria().findAll();
		lstVistoriaRespostaTratada = new ArrayList<VistoriaResposta>();
		lstVistoriaResposta = new VistoriaResposta().findAll();
		lstVistoriaRespostaTratada = new ArrayList<VistoriaResposta>();
		lstVistoriaTratada = new ArrayList<Vistoria>();

		for (Vistoria vistoria : lstVistoria) {

			if (vistoria.getCliente().equals(cliente)) {

				lstVistoriaTratada.add(vistoria);

				for (VistoriaResposta objVistoriaResposta : lstVistoriaResposta) {

					if (objVistoriaResposta.getVistoria().equals(vistoria)) {

						lstVistoriaRespostaTratada.add(objVistoriaResposta);

					}

				}
			}
		}

	}
	
	public String enviarEmail() {
		
		String url = TSFacesUtil.getRequest().getRequestURL().toString();
		
		url = url.replaceAll("dashboard.xhtml", "relatorio/vistoriaImpressao.xhtml");
		
		url = url + "?cliente_id=" + cliente.getId();
		
		EmailUtil.enviar(cliente.getEmail(), Utilitarios.getVistoriaEmailMessage(cliente, url));
		
		this.addInfoMessage("E-mail enviado com sucesso!");
		
		return null;
		
	}

	/**
	 * @return the lstVistoriaTratada
	 */
	public List<Vistoria> getLstVistoriaTratada() {
		return lstVistoriaTratada;
	}

	/**
	 * @param lstVistoriaTratada
	 *            the lstVistoriaTratada to set
	 */
	public void setLstVistoriaTratada(List<Vistoria> lstVistoriaTratada) {
		this.lstVistoriaTratada = lstVistoriaTratada;
	}

	/**
	 * @return the lstVistoriaRespostaTratada
	 */
	public List<VistoriaResposta> getLstVistoriaRespostaTratada() {
		return lstVistoriaRespostaTratada;
	}

	/**
	 * @param lstVistoriaRespostaTratada
	 *            the lstVistoriaRespostaTratada to set
	 */
	public void setLstVistoriaRespostaTratada(List<VistoriaResposta> lstVistoriaRespostaTratada) {
		this.lstVistoriaRespostaTratada = lstVistoriaRespostaTratada;
	}

	/**
	 * @return the lstVistoriaResposta
	 */
	public List<VistoriaResposta> getLstVistoriaResposta() {
		return lstVistoriaResposta;
	}

	/**
	 * @param lstVistoriaResposta
	 *            the lstVistoriaResposta to set
	 */
	public void setLstVistoriaResposta(List<VistoriaResposta> lstVistoriaResposta) {
		this.lstVistoriaResposta = lstVistoriaResposta;
	}

	/**
	 * @return the lstVistoria
	 */
	public List<Vistoria> getLstVistoria() {
		return lstVistoria;
	}

	/**
	 * @param lstVistoria
	 *            the lstVistoria to set
	 */
	public void setLstVistoria(List<Vistoria> lstVistoria) {
		this.lstVistoria = lstVistoria;
	}

	private List<Cliente> lstCliente;

	/**
	 * @return the lstCliente
	 */
	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	/**
	 * @param lstCliente
	 *            the lstCliente to set
	 */
	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	private Vistoria vistoria;

	/**
	 * @return the itemSelecionado
	 */
	public GeradorQRCode getGeradorQRCodeSelecionado() {
		return itemSelecionado;
	}

	/**
	 * @param itemSelecionado
	 *            the itemSelecionado to set
	 */
	public void setGeradorQRCodeSelecionado(GeradorQRCode itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the comboLocal
	 */
	public List<SelectItem> getComboLocal() {
		return comboLocal;
	}

	/**
	 * @param comboLocal
	 *            the comboLocal to set
	 */
	public void setComboLocal(List<SelectItem> comboLocal) {
		this.comboLocal = comboLocal;
	}

	/**
	 * @return the txtCode
	 */
	public String getTxtCode() {
		return txtCode;
	}

	/**
	 * @param txtCode
	 *            the txtCode to set
	 */
	public void setTxtCode(String txtCode) {
		this.txtCode = txtCode;
	}

	/**
	 * @return the comboGeradorQRCode
	 */
	public List<SelectItem> getComboGeradorQRCode() {
		return comboGeradorQRCode;
	}

	/**
	 * @param comboGeradorQRCode
	 *            the comboGeradorQRCode to set
	 */
	public void setComboGeradorQRCode(List<SelectItem> comboGeradorQRCode) {
		this.comboGeradorQRCode = comboGeradorQRCode;
	}

}
