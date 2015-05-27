package com.login.huntvision.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

/**
 * 
 * @author Marcelo Silva
 */
public class JasperUtil {

	private static final String RELATORIOS = "relatorios";
	private static final String WEB_INF = "WEB-INF";
	private static final String APPLICATION_PDF = "APPLICATION/PDF";
	private static final String MSWORD = "application/msword";

	public void gerarRelatorio(String nome, String jasper, Map<String, Object> parametros) throws ClassNotFoundException, JRException, SQLException {

		Connection con = TSDataBaseBrokerFactory.getDataBaseBrokerIf().getConnection();

		jasper = TSFacesUtil.getServletContext().getRealPath(WEB_INF + File.separator + RELATORIOS + File.separator + jasper);

		JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);

		if (!TSUtil.isEmpty(impressao)) {

			ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			response.setContentType(APPLICATION_PDF);

			response.setHeader("Content-Disposition", "attachment;filename=\"" + nome + ".pdf\"");

			try {

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

			} catch (JRException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			} catch (IOException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			} finally {

				con.close();

			}

			TSFacesUtil.getFacesContext().responseComplete();

		}

	}

	public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, String nome, String jasper, Map<String, Object> parametros) throws ClassNotFoundException, JRException, SQLException {

		Connection con = TSDataBaseBrokerFactory.getDataBaseBrokerIf().getConnection();

		jasper = request.getServletContext().getRealPath(WEB_INF + File.separator + RELATORIOS + File.separator + jasper);

		JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);
		
		if (!TSUtil.isEmpty(impressao)) {

			response.setContentType(APPLICATION_PDF);

			response.setHeader("Content-Disposition", "attachment;filename=\"" + nome + ".pdf\"");

			try {

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

			} catch (JRException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				con.close();

				try {
					response.flushBuffer();
				} catch (IOException e) {
				}

			}

		}

	}
	
	public void gerarRelatorioDOC(HttpServletResponse response, String nome, String jasper, Map<String, Object> parametros) throws ClassNotFoundException, JRException, SQLException {

		Connection con = TSDataBaseBrokerFactory.getDataBaseBrokerIf().getConnection();

		JasperPrint impressao = JasperFillManager.fillReport(jasper, parametros, con);
		
		if (!TSUtil.isEmpty(impressao)) {

			response.setContentType(MSWORD);

			response.setHeader("Content-Disposition", "attachment;filename=\"" + nome + ".doc\"");

			try {
				
				JRDocxExporter exporter = new JRDocxExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exporter.exportReport();
				

			} catch (JRException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				con.close();

				try {
					response.flushBuffer();
				} catch (IOException e) {
				}

			}

		}

	}
}