package com.login.huntvision.faces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Agenda;
import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Usuario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "agendaFaces")
public class AgendaFaces extends TSMainFaces {

	private Agenda agenda;
	private ScheduleModel eventModel;
	private List<SelectItem> comboCliente;
	private List<SelectItem> comboUsuario;
	private Agenda agendaPesquisa;
	private MapModel mapa;
	private String centerMap;
	private Marker marker;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat dateFormatComplete  = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private List<String> coordenadas;

	@Override
	@PostConstruct
	protected void clearFields() {
		super.clearFields();
		initAgenda();
		eventModel = new DefaultScheduleModel();
		this.agendaPesquisa = new Agenda();
		this.agendaPesquisa.setUsuario(new Usuario());
		this.agendaPesquisa.setCliente(new Cliente());
		this.comboCliente = super.initCombo(new Cliente().findAll(), "id", "nome");
		this.comboUsuario = super.initCombo(new Usuario().findAll(), "id", "nome");
		find();
	}

	private void initAgenda() {
		this.agenda = new Agenda();
		this.agenda.setCliente(new Cliente());
		this.agenda.setUsuario(new Usuario());
	}

	protected String find() {

		eventModel.clear();

		DefaultScheduleEvent event = null;

		List<Agenda> agendas = this.agendaPesquisa.findByModel();

		mapa = new DefaultMapModel();
		
		coordenadas = new ArrayList<String>();

		for (Agenda item : agendas) {

			event = new DefaultScheduleEvent(item.toString(), item.getDataHora(), item.getDataHora(), item);

			event.setStyleClass(item.getStyleClass());

			eventModel.addEvent(event);

			addMapMarker(item);

		}

		return null;

	}

	private void addMapMarker(Agenda item) {

		if (dateFormat.format(Calendar.getInstance().getTime()).equals(dateFormat.format(item.getDataHora()))) {
			
			int qtd = 0;
			
			for(String coordenada : coordenadas) {
				
				if(coordenada.equals(item.getCliente().getLatitude() + "," + item.getCliente().getLongitude())) {
					qtd++;
				}
				
			}
			
			if(qtd == 0) {
				coordenadas.add(item.getCliente().getLatitude() + "," + item.getCliente().getLongitude());
			}
			
			double factor = Double.valueOf(qtd) / 10000;

			mapa.addOverlay(new Marker(new LatLng(item.getCliente().getLatitude() + factor, item.getCliente().getLongitude()), item.getCliente().getNome() + " | " + dateFormatComplete.format(item.getDataHora()) + " | " + item.getUsuario().getNome(), item.getCliente().getImagem(), item.getMapDot()));

			centerMap = "" + item.getCliente().getLatitude() + "," + item.getCliente().getLongitude();

		}

	}

	public void onEventSelect(SelectEvent selectEvent) {
		agenda = (Agenda) ((ScheduleEvent) selectEvent.getObject()).getData();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		initAgenda();
		agenda.setDataHora((Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) throws TSApplicationException {

		this.agenda = (Agenda) event.getScheduleEvent().getData();

		if (this.agenda.getDataHoraChegada() != null) {
			addInfoMessage("Esse agendamento não pode ser alterado pois o vistoriador já chegou no local");
			initAgenda();
			find();
			return;
		}

		Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));

		calendar.setTime(agenda.getDataHora());

		calendar.add(Calendar.DAY_OF_MONTH, -event.getDayDelta());

		calendar.add(Calendar.MINUTE, -event.getMinuteDelta());

		agenda.setDataHora(calendar.getTime());

		update();

	}

	protected String insert() throws TSApplicationException {

		agenda.save();

		initAgenda();

		find();

		return null;

	}

	protected String update() throws TSApplicationException {

		agenda.update();

		initAgenda();

		find();

		return null;

	}

	public String persist() throws TSApplicationException {

		agenda.setUsuario(agenda.getUsuario().getById());

		agenda.setCliente(agenda.getCliente().getById());

		if (TSUtil.isEmpty(TSUtil.tratarLong(agenda.getId()))) {
			return insert();
		}
		return update();

	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<SelectItem> getComboCliente() {
		return comboCliente;
	}

	public void setComboCliente(List<SelectItem> comboCliente) {
		this.comboCliente = comboCliente;
	}

	public List<SelectItem> getComboUsuario() {
		return comboUsuario;
	}

	public void setComboUsuario(List<SelectItem> comboUsuario) {
		this.comboUsuario = comboUsuario;
	}

	public Agenda getAgendaPesquisa() {
		return agendaPesquisa;
	}

	public void setAgendaPesquisa(Agenda agendaPesquisa) {
		this.agendaPesquisa = agendaPesquisa;
	}

	public MapModel getMapa() {
		return mapa;
	}

	public void setMapa(MapModel mapa) {
		this.mapa = mapa;
	}

	public String getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(String centerMap) {
		this.centerMap = centerMap;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}

}
