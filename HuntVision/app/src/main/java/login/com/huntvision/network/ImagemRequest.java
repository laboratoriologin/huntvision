package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class ImagemRequest extends ObjectRequest<Imagem> {

    public ImagemRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Imagem imagem) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("caminho", imagem.getCaminho()));
        nameValuePairs.add(new BasicNameValuePair("legenda", imagem.getLegenda()));

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

    }
}
