/**
 * 
 */
package com.login.huntvision.faces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.GeradorQRCode;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.ItemLocal;

/**
 * @author Ricardo
 *
 */
@SessionScoped
@ManagedBean(name = "geradorQRCodesFaces")
public class GeradorQRCodesFaces extends CrudFaces<GeradorQRCode> {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboLocal;
	private String txtCode;
	private List<SelectItem> comboGeradorQRCode;

	private GeradorQRCode itemSelecionado;
	private List<Cliente> lstCliente;
	private List<ItemLocal> pLstItem;
	private Cliente cliente;

	@PostConstruct
	protected void init() {
		this.itemSelecionado = new GeradorQRCode();
		this.clearFields();

		setFieldOrdem("descricao");
		lstCliente = new ArrayList<Cliente>();
		lstCliente = (new Cliente().findAll("nome"));
		this.getCrudModel().setLstCliente(lstCliente);

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

	@Override
	protected String detail() {
		super.detail();
		this.txtCode = getCrudModel().getChave();
		return null;
	}

	@Override
	protected void prePersist() {
		getCrudModel().setChave("hv" + getCrudModel().hashCode());
	}

	@Override
	protected void posPersist() {
		this.txtCode = getCrudModel().getChave();
	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		this.txtCode = "";
		return retorno;
	}

	

	public void geraQrCodeRelatorio() {

		pLstItem = new ItemLocal().find(this.cliente);

	}

	public void geraQrCode() {
		System.out.print("Vivo");

		String qrcodetext = getCrudModel().getChave();

		String filepath = "C:\\temp\\" + getCrudModel().getDescricao() + ".png";

		int size = 125;

		String filetype = "png";

		File qrfile = new File(filepath);

		try {
			CreateQRImage(qrfile, qrcodetext, size, filetype);
		} catch (WriterException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("DONE");

	}

	private void CreateQRImage(File qrfile, String qrcodetext, int size, String filetype) throws WriterException, IOException {

		// Create the bytematrix for the QR-Code that encodes the given String

		Hashtable hintmap = new Hashtable();

		hintmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrcodewriter = new QRCodeWriter();

		BitMatrix bytematrix = qrcodewriter.encode(qrcodetext,

		BarcodeFormat.QR_CODE, size, size, hintmap);

		// Make the bufferedimage that are to hold the qrcode

		int matrixwidth = bytematrix.getWidth();

		BufferedImage image = new BufferedImage(matrixwidth, matrixwidth,

		BufferedImage.TYPE_INT_RGB);

		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();

		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixwidth, matrixwidth);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixwidth; i++) {

			for (int j = 0; j < matrixwidth; j++) {

				if (bytematrix.get(i, j)) {

					graphics.fillRect(i, j, 1, 1);
				}

			}

		}

		ImageIO.write(image, filetype, qrfile);

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
	 * @return the pLstItem
	 */
	public List<ItemLocal> getpLstItem() {
		return pLstItem;
	}

	/**
	 * @param pLstItem
	 *            the pLstItem to set
	 */
	public void setpLstItem(List<ItemLocal> pLstItem) {
		this.pLstItem = pLstItem;
	}

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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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
