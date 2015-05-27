/**
 * 
 */
package com.login.huntvision.faces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.MapUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Usuario;
import com.login.huntvision.model.Vistoria;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "homeFaces")
public class HomeFaces extends TSMainFaces {

	private LineChartModel chartUsuario;
	private BarChartModel chartMensal;
	private List<SelectItem> comboClientes;
	private List<SelectItem> comboUsuarios;
	private Vistoria vistoria;
	private SimpleDateFormat dateFormatDiario = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat dateFormatMensal = new SimpleDateFormat("MM/yyyy");
	private HashMap<String, Integer> mapaQuantidadeMensal;
	private HashMap<String, Integer> mapaQuantidadeUsuario;
	private List<Vistoria> vistorias;

	@PostConstruct
	public void init() {
		this.vistoria = new Vistoria();
		this.vistoria.setCliente(new Cliente());
		this.vistoria.setUsuario(new Usuario());
		comboClientes = super.initCombo(new Cliente().findAll("nome"), "id", "nome");
		comboUsuarios = super.initCombo(new Usuario().findAll("nome"), "id", "nome");
		find();
	}

	@Override
	protected String find() {

		this.vistorias = this.vistoria.findAllByCliente("data");

		createAnimatedModels();

		return null;

	}

	private void createAnimatedModels() {

		this.initMaps();

		this.initChartUsuario();

		this.initChartMensal();

	}

	private void initChartUsuario() {

		int maximo = 0;

		this.chartUsuario = new LineChartModel();

		Usuario currentUsuario = null;

		mapaQuantidadeUsuario = new HashMap<String, Integer>();

		String mesAno = null;

		Collections.sort(vistorias, new Comparator<Vistoria>() {

			@Override
			public int compare(Vistoria o1, Vistoria o2) {
				return o1.getUsuario().compareTo(o2.getUsuario());
			}
		});

		for (Vistoria vistoria : this.vistorias) {

			try {

				mesAno = dateFormatMensal.format(dateFormatDiario.parse(vistoria.getData()));

			} catch (ParseException e) {

				e.printStackTrace();

			}

			if (!vistoria.getUsuario().equals(currentUsuario)) {

				if (!mapaQuantidadeUsuario.isEmpty()) {

					ChartSeries usuarioChartSerie = new ChartSeries();

					usuarioChartSerie.setLabel(currentUsuario.getNome());

					for (String key : new TreeSet<String>(mapaQuantidadeUsuario.keySet())) {

						usuarioChartSerie.set(key, mapaQuantidadeUsuario.get(key));

						if (mapaQuantidadeUsuario.get(key) > maximo) {
							maximo = mapaQuantidadeUsuario.get(key);
						}

					}

					this.chartUsuario.addSeries(usuarioChartSerie);

				}

				currentUsuario = vistoria.getUsuario();

				mapaQuantidadeUsuario = new HashMap<String, Integer>();

			}

			if (!mapaQuantidadeUsuario.containsKey(mesAno)) {
				mapaQuantidadeUsuario.put(mesAno, 0);
			}

			mapaQuantidadeUsuario.put(mesAno, mapaQuantidadeUsuario.get(mesAno) + 1);

		}

		
		//adicionando ultimo usuario
		if (!mapaQuantidadeUsuario.isEmpty()) {

			ChartSeries usuarioChartSerie = new ChartSeries();

			usuarioChartSerie.setLabel(currentUsuario.getNome());

			for (String key : new TreeSet<String>(mapaQuantidadeUsuario.keySet())) {

				usuarioChartSerie.set(key, mapaQuantidadeUsuario.get(key));

				if (mapaQuantidadeUsuario.get(key) > maximo) {
					maximo = mapaQuantidadeUsuario.get(key);
				}

			}

			this.chartUsuario.addSeries(usuarioChartSerie);

		}

		chartUsuario.setTitle("Visitas por usuário");
		chartUsuario.setLegendPosition("e");
		chartUsuario.setShowPointLabels(true);
		chartUsuario.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis yAxis = chartUsuario.getAxis(AxisType.Y);
		yAxis.setLabel("Visitas");
		yAxis.setMin(0);
		yAxis.setMax(maximo + 10);

	}

	private void initChartMensal() {

		chartMensal = new BarChartModel();

		int maximo = 0;

		ChartSeries series1 = new ChartSeries();

		series1.setLabel("Visitas Realizadas por mês");

		for (String key : new TreeSet<String>(mapaQuantidadeMensal.keySet())) {

			series1.set(key, mapaQuantidadeMensal.get(key));

			if (mapaQuantidadeMensal.get(key) > maximo) {
				maximo = mapaQuantidadeMensal.get(key);
			}

		}

		chartMensal.addSeries(series1);

		chartMensal.setTitle("Barra de visitas mensal");
		chartMensal.setAnimate(true);
		chartMensal.setLegendPosition("ne");
		chartMensal.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis yAxis = chartMensal.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(maximo + 10);

	}

	private void initMaps() {

		mapaQuantidadeMensal = new HashMap<String, Integer>();

		String mesAno = null;

		for (Vistoria vistoria : vistorias) {

			try {

				mesAno = dateFormatMensal.format(dateFormatDiario.parse(vistoria.getData()));

				if (!mapaQuantidadeMensal.containsKey(mesAno)) {
					mapaQuantidadeMensal.put(mesAno, 0);
				}

				mapaQuantidadeMensal.put(mesAno, mapaQuantidadeMensal.get(mesAno) + 1);

			} catch (ParseException e) {

				e.printStackTrace();
			}

		}

	}

	/**
	 * @return the comboUsuarios
	 */
	public List<SelectItem> getComboUsuarios() {
		return comboUsuarios;
	}

	/**
	 * @param comboUsuarios
	 *            the comboUsuarios to set
	 */
	public void setComboUsuarios(List<SelectItem> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}

	/**
	 * @return the vistoria
	 */
	public Vistoria getVistoria() {
		return vistoria;
	}

	/**
	 * @param vistoria
	 *            the vistoria to set
	 */
	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	/**
	 * @return the comboClientes
	 */
	public List<SelectItem> getComboClientes() {
		return comboClientes;
	}

	/**
	 * @param comboClientes
	 *            the comboClientes to set
	 */
	public void setComboClientes(List<SelectItem> comboClientes) {
		this.comboClientes = comboClientes;
	}

	public LineChartModel getChartUsuario() {
		return chartUsuario;
	}

	public void setChartUsuario(LineChartModel chartUsuario) {
		this.chartUsuario = chartUsuario;
	}

	public BarChartModel getChartMensal() {
		return chartMensal;
	}

	public void setChartMensal(BarChartModel chartMensal) {
		this.chartMensal = chartMensal;
	}

}