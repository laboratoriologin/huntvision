/**
 * 
 */
package com.login.huntvision.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;

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

		String parameter = request.getParameter("cliente_id");

		if (TSUtil.isNumeric(parameter)) {

			Cliente cliente = new Cliente(parameter);

			cliente = cliente.getById();

			if (cliente != null) {

				generateDocument(cliente, response);

			}

		}

	}

	private void generateDocument(Cliente cliente, HttpServletResponse response) {

		try {

			XWPFDocument doc = new XWPFDocument();

			XWPFParagraph paragraph = doc.createParagraph();

			paragraph.setAlignment(ParagraphAlignment.CENTER);

			XWPFRun run = paragraph.createRun();

			run.addPicture(new FileInputStream(Constantes.CAMINHO_ARQUIVO + "logo.jpg"), XWPFDocument.PICTURE_TYPE_JPEG, "logo.jpg", Units.toEMU(200), Units.toEMU(200));

			run.addBreak();
			run.addBreak();
			
			run.setBold(true);

			run.setText(cliente.getNome());
			
			run.addBreak();
			run.addBreak();
			
			run.setText("Endereço: " + cliente.getEndereco());
			run.addBreak();
			run.setText("E-mail: " + cliente.getEmail());
			run.addBreak();
			run.setText("Telefone:" + cliente.getTelefone());
			run.addBreak();
			
			Vistoria vistoria = new Vistoria();

			vistoria.setCliente(cliente);

			List<Vistoria> vistorias = vistoria.findAllByCliente();

			VistoriaResposta vistoriaRespostaTmp = null;

			for (Vistoria objVistoria : vistorias) {
				
				paragraph = doc.createParagraph();

				run = paragraph.createRun();

				run.setBold(true);
				
				run.setText("Vistoria feita em " + objVistoria.getData());

				run.addBreak();
				run.addBreak();

				run = paragraph.createRun();
				
				vistoriaRespostaTmp = new VistoriaResposta();

				vistoriaRespostaTmp.setVistoria(objVistoria);

				objVistoria.setVistoriaRespostas(vistoriaRespostaTmp.findAllByVistoria());

				for (VistoriaResposta vistoriaResposta : objVistoria.getVistoriaRespostas()) {

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
				
			}

			response.setContentType("application/msword");

			response.setHeader("Content-Disposition", "attachment;filename=\"" + cliente.getNome().replaceAll("\\W", "_") + ".doc\"");

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