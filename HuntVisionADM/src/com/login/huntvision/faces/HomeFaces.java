/**
 * 
 */
package com.login.huntvision.faces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private LineChartModel animatedModel1;
	private BarChartModel animatedModel2;
	private List<SelectItem> comboClientes;
	private List<SelectItem> comboUsuarios;
	private Vistoria vistoria;
	private SimpleDateFormat dateFormatDiario = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat dateFormatMensal = new SimpleDateFormat("MM/yyyy");
	private HashMap<String, Integer> mapaQuantidadeDiaria;
	private HashMap<String, Integer> mapaQuantidadeMensal;
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

	public LineChartModel getAnimatedModel1() {
		return animatedModel1;
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	@Override
	protected String find() {
		this.vistorias = this.vistoria.findAllByCliente("data");
		createAnimatedModels();
		return null;
	}

	private void createAnimatedModels() {

		this.initCharts();
		animatedModel1.setTitle("Total de visitas");
		animatedModel1.setAnimate(true);
		animatedModel1.setLegendPosition("se");
		

		animatedModel2 = initBarModel();
		
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		
		int maximo = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("Visitas Realizadas por mês");
		
		for (String key :new TreeSet<String>(mapaQuantidadeMensal.keySet())) {

			series1.set(key, mapaQuantidadeMensal.get(key));
			
			if(mapaQuantidadeMensal.get(key) > maximo) {
				maximo  = mapaQuantidadeMensal.get(key);
			}
			
			

		}
		
		model.addSeries(series1);
		
		model.setTitle("Barra de visitas mensal");
		model.setAnimate(true);
		model.setLegendPosition("ne");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(maximo);
		

		return model;
	}

	private void initCharts() {

		this.animatedModel1 = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();

		series1.setLabel("Visitas por dia");

		mapaQuantidadeDiaria = new HashMap<String, Integer>();
		mapaQuantidadeMensal = new HashMap<String, Integer>();

		String mesAno = null;

		for (Vistoria vistoria : vistorias) {

			if (!mapaQuantidadeDiaria.containsKey(vistoria.getData())) {
				mapaQuantidadeDiaria.put(vistoria.getData(), 0);
			}

			mapaQuantidadeDiaria.put(vistoria.getData(), mapaQuantidadeDiaria.get(vistoria.getData()) + 1);

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

		for (String key : mapaQuantidadeDiaria.keySet()) {

			series1.set(key, mapaQuantidadeDiaria.get(key));

		}

		this.animatedModel1.addSeries(series1);

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

}