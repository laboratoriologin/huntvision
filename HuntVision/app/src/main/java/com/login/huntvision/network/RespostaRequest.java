package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Resposta;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class RespostaRequest extends ObjectRequest<Resposta> {

    public RespostaRequest( String url , ResponseListener listener) {
        super(url, listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Resposta resposta) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if( resposta.getDescricao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", resposta.getDescricao().toString()));
        }
        if( resposta.getObservacao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("flagrespostacerta", resposta.getFlagRespostaCerta().toString()));
        }

        if( resposta.getObservacao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("observacao", resposta.getObservacao().toString()));
        }

        if( resposta.getQuestionarioId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("questionario.id", resposta.getQuestionarioId() != null ? resposta.getQuestionarioId().toString() : ""));
        }

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
