/**
 * 
 */
package com.login.huntvision.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.topsys.util.TSUtil;

import com.login.huntvision.model.Vistoria;
import com.login.huntvision.util.Constantes;
import com.login.huntvision.util.JasperUtil;

/**
 * @author Ricardo
 *
 */
/**
 * Servlet implementation class QrCode
 */
@WebServlet("/word_document")
public class WordDocumentServlet extends HttpServlet {
	private static final BigInteger CELL_WIDTH = BigInteger.valueOf(1200);
	private static final String LOGO_JPG = "logo.jpg";
	private static final String LOGO_HUNT_VISION_260PX = "logo_hunt_vision_260px.png";
	private static final String RELATORIOS = "relatorios";
	private static final String WEB_INF = "WEB-INF";
	private static final String APPLICATION_PDF = "APPLICATION/PDF";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WordDocumentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		exec(request, response);
	}

	private void exec(HttpServletRequest request, HttpServletResponse response) {

		String parameter = request.getParameter("vistoria_id");

		if (TSUtil.isNumeric(parameter)) {

			Vistoria vistoria = new Vistoria(parameter);

			vistoria = vistoria.getById();

			if (vistoria != null) {

				executeJasper(vistoria, request, response);

			}

		}

	}

	private void generateDocument(Vistoria vistoria, HttpServletResponse response) {

		try {

			// //
			XWPFDocument doc = new XWPFDocument();

			XWPFParagraph paragraph = doc.createParagraph();

			// Set bottom border to paragraph
			paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);

			// Set left border to paragraph
			paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);

			// Set right border to paragraph
			paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);

			// Set top border to paragraph
			paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

			XWPFRun run = paragraph.createRun();

			try {

				run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + vistoria.getCliente().getImagem()), XWPFDocument.PICTURE_TYPE_JPEG, vistoria.getCliente().getImagem(), Units.toEMU(130), Units.toEMU(130));

				run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + LOGO_JPG), XWPFDocument.PICTURE_TYPE_JPEG, LOGO_JPG, Units.toEMU(130), Units.toEMU(130));

				run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + LOGO_HUNT_VISION_260PX), XWPFDocument.PICTURE_TYPE_JPEG, LOGO_HUNT_VISION_260PX, Units.toEMU(130), Units.toEMU(130));

			} catch (FileNotFoundException ex) {
				//
			}

			// XWPFTable table2 = doc.createTable();
			//
			// row = table2.createRow();
			//
			// paragraph = row.createCell().addParagraph();
			//
			// run = paragraph.createRun();
			// run.setBold(true);
			//
			// run.setText(vistoria.getCliente().getNome());
			//
			// paragraph = row.createCell().addParagraph();
			// run = paragraph.createRun();
			// run.setBold(true);
			//
			// run.setText("Endereço: " + vistoria.getCliente().getEndereco());
			// run.addBreak();
			// run.setText("E-mail: " + vistoria.getCliente().getEmail());
			// run.addBreak();
			// run.setText("Telefone:" + vistoria.getCliente().getTelefone());
			//
			// run = paragraph.createRun();
			// run.setBold(true);
			//
			// run.addBreak();
			//
			// paragraph = row.createCell().addParagraph();
			// run = paragraph.createRun();
			// run.setBold(true);
			//
			// run.setText("Vistoria feita em " + vistoria.getData());
			// run.addBreak();
			//
			// run = paragraph.createRun();
			//
			// VistoriaResposta vistoriaRespostaTmp = new VistoriaResposta();
			//
			// vistoriaRespostaTmp.setVistoria(vistoria);
			//
			// vistoria.setVistoriaRespostas(vistoriaRespostaTmp.findAllByVistoria());

			// table = doc.createTable();
			//
			// row = null;
			//
			// for (VistoriaResposta vistoriaResposta :
			// vistoria.getVistoriaRespostas()) {
			//
			// table.setCellMargins(5, 5, 5, 5);
			//
			// row = table.createRow();
			//
			// cell = row.createCell();
			//
			// paragraph = cell.addParagraph();
			//
			// run = paragraph.createRun();
			//
			// run.setText(vistoriaResposta.getResposta().getQuestionario().getPergunta());
			//
			// paragraph = row.createCell().addParagraph();
			//
			// run = paragraph.createRun();
			//
			// run.setText(vistoriaResposta.getResposta().getDescricao());
			//
			// paragraph = row.createCell().addParagraph();
			//
			// run = paragraph.createRun();
			//
			// run.setText(vistoriaResposta.getObservacao());
			//
			// paragraph = row.createCell().addParagraph();
			//
			// run = paragraph.createRun();
			//
			// if (!TSUtil.isEmpty(vistoriaResposta.getImagem())) {
			//
			// try {
			//
			// run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO +
			// vistoriaResposta.getImagem()), XWPFDocument.PICTURE_TYPE_JPEG,
			// vistoriaResposta.getImagem(), Units.toEMU(100),
			// Units.toEMU(100));
			//
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// // n achou imagem
			// }
			//
			// }
			//
			// // run.addBreak(BreakType.TEXT_WRAPPING);
			//
			// }

			response.setContentType("application/msword");

			response.setHeader("Content-Disposition", "attachment;filename=\"" + vistoria.getCliente().getNome().replaceAll("\\W", "_") + ".doc\"");

			doc.write(response.getOutputStream());

			response.getOutputStream().flush();

			response.getOutputStream().close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		exec(request, response);
	}

	private void executeJasper(Vistoria vistoria,HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		JasperUtil jasperUtil = new JasperUtil();

		parametros.put("VISTORIA_ID", vistoria.getId());

		String nomeOrcamento = vistoria.getId() + "_" + vistoria.getCliente().getNome().replaceAll(" ", "_");

		nomeOrcamento = nomeOrcamento.replace(" ", "_");

		parametros.put("CAMINHO_IMAGEM", Constantes.CAMINHO_ARQUIVO);
		
		String jasper = request.getServletContext().getRealPath(WEB_INF + File.separator + RELATORIOS + File.separator + "visitas.jasper");

		try {
			
			jasperUtil.gerarRelatorioDOC(response, nomeOrcamento, jasper, parametros);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}