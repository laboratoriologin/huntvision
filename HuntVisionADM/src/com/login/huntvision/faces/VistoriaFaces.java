package com.login.huntvision.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.GeradorQRCode;
import com.login.huntvision.model.Vistoria;
import com.login.huntvision.model.VistoriaResposta;
import com.login.huntvision.model.VistoriaRespostaImagem;
import com.login.huntvision.util.Constantes;
import com.login.huntvision.util.EmailUtil;
import com.login.huntvision.util.Utilitarios;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name = "vistoriaFaces")
public class VistoriaFaces extends CrudFaces<Vistoria> {

	private List<SelectItem> comboLocal;
	private String txtCode;
	private List<SelectItem> comboGeradorQRCode;
	private Cliente cliente;
	private GeradorQRCode itemSelecionado;
	private List<Vistoria> lstVistoria;
	private List<VistoriaResposta> lstVistoriaRespostaTratada;
	private MapModel mapModel;
	

	@Override
	@PostConstruct
	protected void clearFields() {

		super.clearFields();

		this.cliente = new Cliente();

		this.itemSelecionado = new GeradorQRCode();

		setFieldOrdem("descricao");
		lstCliente = new ArrayList<Cliente>();
		lstCliente = (new Cliente().findAll("nome"));

		String vistoriaId = TSFacesUtil.getRequestParameter("vistoria_id");

		if (vistoriaId != null && TSUtil.isNumeric(vistoriaId)) {

			this.setCrudModel(new Vistoria(vistoriaId));

			this.setCrudModel(this.getCrudModel().getById());

			geraQrCodeRelatorio();

		}

		this.mapModel = new DefaultMapModel();
	
	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		this.txtCode = "";
		return retorno;
	}

	@Override
	public String find() {

		Vistoria vistoria = new Vistoria();

		if (TSUtil.isEmpty(this.cliente.getNome())) {

			this.cliente.setNome("");

		}

		vistoria.setCliente(this.cliente);

		lstVistoria = vistoria.findAllByNomeCliente();

		TSFacesUtil.gerarResultadoLista(lstVistoria);

		return null;

	}
	


	public String marcarMapa() {

		LatLng coord = new LatLng(getCrudModel().getLatitude(), getCrudModel().getLongitude());

		mapModel.getMarkers().clear();

		mapModel.addOverlay(new Marker(coord, getCrudModel().getCliente().getNome()));

		return null;

	}

	public String geraQrCodeRelatorio() {

		this.setCrudModel(this.getCrudModel().getById());

		VistoriaResposta resposta = new VistoriaResposta();

		resposta.setVistoria(this.getCrudModel());

		this.lstVistoriaRespostaTratada = resposta.findAllByVistoria();
	
		
		return null;

	}

	public String enviarEmail() {

		String url = TSFacesUtil.getRequest().getRequestURL().toString();

		url = url.replaceAll("dashboard.xhtml", "relatorio/vistoriaImpressao.xhtml");

		url = url + "?vistoria_id=" + getCrudModel().getId();

		EmailUtil.enviar(getCrudModel().getCliente().getEmail(), Utilitarios.getVistoriaEmailMessage(getCrudModel().getCliente(), url , getCrudModel().getData().toString()));

		this.addInfoMessage("E-mail enviado com sucesso!");

		return null;

	}

	public List<VistoriaResposta> getLstVistoriaRespostaTratada() {
		return lstVistoriaRespostaTratada;
	}

	public void setLstVistoriaRespostaTratada(List<VistoriaResposta> lstVistoriaRespostaTratada) {
		this.lstVistoriaRespostaTratada = lstVistoriaRespostaTratada;
	}

	public List<Vistoria> getLstVistoria() {
		return lstVistoria;
	}

	public void setLstVistoria(List<Vistoria> lstVistoria) {
		this.lstVistoria = lstVistoria;
	}

	private List<Cliente> lstCliente;

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	public GeradorQRCode getGeradorQRCodeSelecionado() {
		return itemSelecionado;
	}

	public void setGeradorQRCodeSelecionado(GeradorQRCode itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

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

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

}

