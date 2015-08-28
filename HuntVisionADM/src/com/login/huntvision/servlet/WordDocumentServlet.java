/**
 * 
 */
package com.login.huntvision.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		Map<String, Object> parametros = new HashMap<String, Object>();

		JasperUtil jasperUtil = new JasperUtil();

		String dataInicial = request.getParameter("data_inicial");

		String dataFinal = request.getParameter("data_final");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		SimpleDateFormat formatterBR = new SimpleDateFormat("dd/MM/yyyy");

		String cliente = request.getParameter("cliente_id");

		parametros.put("CLIENTE", Long.valueOf(cliente));

		try {
			parametros.put("DATA_INICIO", formatter.format(formatterBR.parse(dataInicial)));
		} catch (ParseException e) {
			parametros.put("DATA_INICIO", formatter.format(new Date()));
		}

		try {
			parametros.put("DATA_FIM", formatter.format(formatterBR.parse(dataFinal)));
		} catch (ParseException e) {
			parametros.put("DATA_FIM", formatter.format(new Date()));
		}

		parametros.put("CAMINHO_IMAGEM", Constantes.CAMINHO_ARQUIVO);

		String jasper = request.getServletContext().getRealPath(WEB_INF + File.separator + RELATORIOS + File.separator + "visitas.jasper");

		String subreportDir = request.getServletContext().getRealPath(WEB_INF + File.separator + RELATORIOS) + File.separator;

		parametros.put("SUBREPORT_DIR", subreportDir);

		try {

			jasperUtil.gerarRelatorioDOC(response, cliente, jasper, parametros);

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

	private void executeJasper(Vistoria vistoria, HttpServletRequest request, HttpServletResponse response) {

	}

}