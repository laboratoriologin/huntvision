package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.GrupoUsuario;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public abstract class GrupoUsuarioRequest extends ObjectRequest<GrupoUsuario> {

    public GrupoUsuarioRequest(String url, ResponseListener listener) {
        super(url,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(GrupoUsuario grupoUsuario) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("descricao", grupoUsuario.getDescricao()));


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

        if (serverResponse.isOK()) {

            GrupoUsuario grupoUsuario;
            JSONObject jsonGrupoUsuario;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("grupoUsuario")) {

                    jsonGrupoUsuario = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("grupoUsuario");

                    grupoUsuario = new GrupoUsuario();
                    grupoUsuario.setId(jsonGrupoUsuario.has("id") ? jsonGrupoUsuario.getString("id") : null);
                    grupoUsuario.setDescricao((jsonGrupoUsuario.has("descricao") ? jsonGrupoUsuario.getString("descricao") : "").toString());

                    serverResponse.setReturnObject(grupoUsuario);

                } else {

                    serverResponse.setReturnObject(null);

                }


            } catch (JSONException e) {

                e.printStackTrace();
                serverResponse.setStatusCode(-1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
