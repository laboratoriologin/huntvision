package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Acao;
import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.ServerRequest;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class AcaoRequest extends ObjectRequest<Acao> {

    public AcaoRequest(String url, ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Acao acao) {


        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(acao.getNome()!=null) {
            nameValuePairs.add(new BasicNameValuePair("nome",acao.getNome().toString()));
        }

        if(acao.getProcedimento() !=null) {
            nameValuePairs.add(new BasicNameValuePair("procedimento", acao.getProcedimento().toString()));
        }



        return nameValuePairs;

    }





    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
