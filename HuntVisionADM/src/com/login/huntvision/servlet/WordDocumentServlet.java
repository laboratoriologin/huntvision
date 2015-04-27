/**
 * 
 */
package com.login.huntvision.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.topsys.util.TSUtil;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Vistoria;
import com.login.huntvision.model.VistoriaResposta;
import com.login.huntvision.util.Constantes;

/**
 * @author Ricardo
 *
 */
/**
 * Servlet implementation class QrCode
 */
@WebServlet("/word_document")
public class WordDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

				generateDocument(vistoria, response);

			}

		}

	}

	private void generateDocument(Vistoria vistoria, HttpServletResponse response) {

		try {

			XWPFDocument doc = new XWPFDocument();

			XWPFParagraph paragraph = doc.createParagraph();

			paragraph.setAlignment(ParagraphAlignment.CENTER);

			XWPFRun run = paragraph.createRun();
			
			try {

				run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + "logo.jpg"), XWPFDocument.PICTURE_TYPE_JPEG, "logo.jpg", Units.toEMU(200), Units.toEMU(200));
			
			}catch(FileNotFoundException ex) {
				//
			}

			run.addBreak();
			run.addBreak();

			run.setBold(true);

			run.setText(vistoria.getCliente().getNome());

			run.addBreak();
			run.addBreak();

			run.setText("Endereço: " + vistoria.getCliente().getEndereco());
			run.addBreak();
			run.setText("E-mail: " + vistoria.getCliente().getEmail());
			run.addBreak();
			run.setText("Telefone:" + vistoria.getCliente().getTelefone());
			run.addBreak();

			paragraph = doc.createParagraph();

			run = paragraph.createRun();

			run.setBold(true);

			run.setText("Vistoria feita em " + vistoria.getData());

			run.addBreak();
			run.addBreak();

			run = paragraph.createRun();

			VistoriaResposta vistoriaRespostaTmp = new VistoriaResposta();

			vistoriaRespostaTmp.setVistoria(vistoria);

			vistoria.setVistoriaRespostas(vistoriaRespostaTmp.findAllByVistoria());

			for (VistoriaResposta vistoriaResposta : vistoria.getVistoriaRespostas()) {
			
				paragraph = doc.createParagraph();

				run = paragraph.createRun();
				paragraph.setBorderBottom(Borders.DOUBLE);
				paragraph.setBorderTop(Borders.DOUBLE);
				paragraph.setBorderRight(Borders.DOUBLE);
				paragraph.setBorderLeft(Borders.DOUBLE);
				paragraph.setBorderBetween(Borders.SINGLE);
				
				run.setText(vistoriaResposta.getResposta().getQuestionario().getPergunta());
				run.addBreak();
				run.setText(vistoriaResposta.getResposta().getDescricao());
				run.addBreak();

				if (!TSUtil.isEmpty(vistoriaResposta.getImagem())) {

					try {

						run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + vistoriaResposta.getImagem()), XWPFDocument.PICTURE_TYPE_JPEG, vistoriaResposta.getImagem(), Units.toEMU(200), Units.toEMU(200));
						run.addBreak();

					} catch (Exception ex) {
						// n achou imagem
					}

				}

				run.addBreak(BreakType.TEXT_WRAPPING);

			}

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
}