package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Base;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.ServerRequest;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.HttpTask;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class ClienteRequest extends ObjectRequest<Cliente> {

    public ClienteRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Cliente cliente) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("telefone", cliente.getCelular()));
        nameValuePairs.add(new BasicNameValuePair("cnpj", cliente.getCNPJ()));
        nameValuePairs.add(new BasicNameValuePair("email", cliente.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("nome", cliente.getNome()));
        nameValuePairs.add(new BasicNameValuePair("bairro", cliente.getBairro()));
        nameValuePairs.add(new BasicNameValuePair("cep", cliente.getCep()));
        nameValuePairs.add(new BasicNameValuePair("cidade", cliente.getCidade()));
        nameValuePairs.add(new BasicNameValuePair("endereco", cliente.getEndereco()));
        nameValuePairs.add(new BasicNameValuePair("estado", cliente.getEstado()));
        nameValuePairs.add(new BasicNameValuePair("pais", cliente.getPais()));

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
