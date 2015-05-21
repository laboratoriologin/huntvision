package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Local;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class LocalRequest extends ObjectRequest<Local> {

    public LocalRequest(String url , ResponseListener listener) {
        super(url , listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Local local) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(local.getNomeLocal()!=null) {
            nameValuePairs.add(new BasicNameValuePair("nomelocal", local.getNomeLocal()));
        }

        if(local.getClienteId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("cliente.id", local.getClienteId() != null ? local.getClienteId().toString() : ""));
        }

        if(local.getGrupoItemId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("grupoitem.id", local.getGrupoItemId() != null ? local.getGrupoItemId().toString() : ""));
        }

        if(local.getClientes()!=null) {

            Cliente cliente;

            for (int i = 0; i < local.getClientes().size(); i++) {

                cliente = local.getClientes().get(i);

                nameValuePairs.add(new BasicNameValuePair("cliente[" + i + "]", cliente.getNome().toString()));

            }

        }

        if(local.getItemLocals()!=null) {

            ItemLocal itemLocal;

            for (int i = 0; i < local.getItemLocals().size(); i++) {

                itemLocal = local.getItemLocals().get(i);

                nameValuePairs.add(new BasicNameValuePair("grupoItens[" + i + "].quantidade", local.getItemLocals().toString()));


            }

        }


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

       /* if (serverResponse.isOK()) {

            Local local;
            JSONObject jsonLocal;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("local")) {

                    jsonLocal = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("local");

                    local = new Local();
                    local.setId(jsonLocal.has("id") ? jsonLocal.getString("id") : null);
                    local.setNomeLocal(jsonLocal.has("nomelocal") ? jsonLocal.getString("nomelocal") : "");



                    serverResponse.setReturnObject(local);

                } else {

                    serverResponse.setReturnObject(null);

                }


            } catch (JSONException e) {

                e.printStackTrace();
                serverResponse.setStatusCode(-1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
    }


}
