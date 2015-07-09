package br.com.login.huntvision.ws.service;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.catalina.connector.Response;
import org.jboss.resteasy.annotations.Form;

import br.com.login.huntvision.ws.model.Agenda;
import br.com.login.huntvision.ws.model.ItemLocal;
import br.com.login.huntvision.ws.dao.AgendaDAO;
import br.com.login.huntvision.ws.dao.ItensLocaisDAO;
import br.com.login.huntvision.ws.exception.ApplicationException;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.exception.TSSystemException;
@Path("/agendas")
public class AgendaService extends RestService<Agenda> {

	@Override
	public void initDAO() {
		this.restDAO = new AgendaDAO();
	}
	
	@PUT
	@Path("/update_horas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Agenda update(@Form Agenda form, @PathParam("id") Long id)  throws ApplicationException {

		try {

			this.validate(form);

			form.setId(id);

			return new AgendaDAO().updateHoras(form);

		} catch (TSApplicationException ex) {

			throw new ApplicationException(ex.getMessage(), Response.SC_BAD_REQUEST);

		} catch (TSSystemException ex) {

			throw new ApplicationException(ex.getMessage(), Response.SC_INTERNAL_SERVER_ERROR);

		}

	}


}
