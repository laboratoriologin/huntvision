package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Agenda;
import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.ServerRequest;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class AgendaRequest extends ObjectRequest<Agenda> {

    public AgendaRequest(String url, ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Agenda agenda) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(agenda.getClienteId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("cliente.id", agenda.getClienteId() != null ? agenda.getClienteId().toString() : ""));
        }

        if(agenda.getDescricao() !=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", agenda.getDescricao().toString()));
        }

        if(agenda.getDataHora() !=null) {
            nameValuePairs.add(new BasicNameValuePair("data_hora", dateFormat.format(agenda.getDataHora())));
        }
        if(agenda.getDataHoraChegada() !=null) {
            nameValuePairs.add(new BasicNameValuePair("data_hora_chegada", dateFormat.format(agenda.getDataHoraChegada())));
        }

        if(agenda.getDataHoraSaida() !=null) {
            nameValuePairs.add(new BasicNameValuePair("data_hora_saida", dateFormat.format(agenda.getDataHoraSaida())));
        }


        return nameValuePairs;

    }

    public void updateHoras(Agenda obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.PUT, getURLUpdateHoras(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    protected String getURLUpdateHoras(Agenda object) {

        return url + "/" + object.getServiceName() + "/update_horas/" + (object.getId() == null ? "" : object.getId());

    }



    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
