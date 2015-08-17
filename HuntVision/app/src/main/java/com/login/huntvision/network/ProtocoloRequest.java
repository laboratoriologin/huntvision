package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Acao;
import com.login.huntvision.models.Protocolo;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class ProtocoloRequest extends ObjectRequest<Protocolo> {

    public ProtocoloRequest(String url, ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Protocolo protocolo) {


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(protocolo.getNome()!=null) {
            nameValuePairs.add(new BasicNameValuePair("nome",protocolo.getNome().toString()));
        }

        if(protocolo.getNorma() !=null) {
            nameValuePairs.add(new BasicNameValuePair("norma", protocolo.getNorma().toString()));
        }

        return nameValuePairs;
    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }

}
