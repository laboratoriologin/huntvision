package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.TipoQuestionario;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class TipoQuestionarioRequest extends ObjectRequest<TipoQuestionario> {

    public TipoQuestionarioRequest(String url,ResponseListener listener) {
        super( url , listener);
    }

    @Override
    protected List<NameValuePair> createParameters(TipoQuestionario TipoQuestionario) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if( TipoQuestionario.getDescricao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", TipoQuestionario.getDescricao().toString()));
        }


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
