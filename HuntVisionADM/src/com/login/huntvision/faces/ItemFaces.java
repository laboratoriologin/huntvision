/**
 * 
 */
package com.login.huntvision.faces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.login.huntvision.model.Item;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "itemFaces")
public class ItemFaces extends CrudFaces<Item> {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> comboLocal;
	private String txtCode;
	private List<SelectItem> comboItem;
	private Item itemSelecionado;
	/**
	 * @return the itemSelecionado
	 */
	public Item getItemSelecionado() {
		return itemSelecionado;
	}

	/**
	 * @param itemSelecionado the itemSelecionado to set
	 */
	public void setItemSelecionado(Item itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	@PostConstruct
	protected void init() {
		this.itemSelecionado = new Item();
		this.clearFields();

		setFieldOrdem("descricao");
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
	 * @return the comboItem
	 */
	public List<SelectItem> getComboItem() {
		return comboItem;
	}

	/**
	 * @param comboItem
	 *            the comboItem to set
	 */
	public void setComboItem(List<SelectItem> comboItem) {
		this.comboItem = comboItem;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("DONE");

	}

	private void CreateQRImage(File qrfile, String qrcodetext, int size,

	String filetype) throws WriterException, IOException {

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
	
	


}
