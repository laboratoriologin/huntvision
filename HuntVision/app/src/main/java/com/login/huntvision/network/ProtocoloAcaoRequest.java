package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.ProtocoloAcao;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class ProtocoloAcaoRequest extends ObjectRequest<ProtocoloAcao> {

    public ProtocoloAcaoRequest(String url, ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(ProtocoloAcao protocoloAcao) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(protocoloAcao.getAcao_id()!=null) {
            nameValuePairs.add(new BasicNameValuePair("acao_id", protocoloAcao.getAcao_id().toString()));
        }

        if(protocoloAcao.getProtocolo_id() !=null) {
            nameValuePairs.add(new BasicNameValuePair("protocolo_id", protocoloAcao.getProtocolo_id().toString()));
        }


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

       /* if (serverResponse.isOK()) {

            ItemLocal itemLocal;
            JSONObject jsonGrupoItem;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("grupoitem")) {

                    jsonGrupoItem = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("grupoitem");

                    itemLocal = new ItemLocal();
                    itemLocal.setId(jsonGrupoItem.has("id") ? jsonGrupoItem.getString("id") : null);
                    itemLocal.setItem_id(jsonGrupoItem.has("local_id") ? jsonGrupoItem.getInt("local_id") : null);
                    itemLocal.setItem_id(jsonGrupoItem.has("item_id") ? jsonGrupoItem.getInt("item_id") : null);

                    serverResponse.setReturnObject(itemLocal);

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
