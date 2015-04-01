package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Permissao;

import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public abstract class PermissaoRequest extends ObjectRequest<Permissao> {

    public PermissaoRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Permissao permissao) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(permissao.getFlag_alterar()!=null) {
            nameValuePairs.add(new BasicNameValuePair("flagalterar", permissao.getFlag_alterar().toString()));


        }

        if(permissao.getFlag_excluir()!=null) {
            nameValuePairs.add(new BasicNameValuePair("flagexcluir", permissao.getFlag_excluir().toString()));


        }

        if(permissao.getFlag_inserir()!=null) {
            nameValuePairs.add(new BasicNameValuePair("flaginserir", permissao.getFlag_inserir().toString()));


        }

        if(permissao.getGrupoUsuarioID()!=null) {
            nameValuePairs.add(new BasicNameValuePair("grupousuario.id", permissao.getGrupoUsuarioID() != null ? permissao.getGrupoUsuarioID().toString() : ""));


        }
        if( permissao.getMenuId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("menu.id", permissao.getMenuId() != null ? permissao.getMenuId().toString() : ""));
        }
        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

        if (serverResponse.isOK()) {

            Permissao menu;
            JSONObject jsonPermissao;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("menu")) {

                    jsonPermissao = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("menu");

                    menu = new Permissao();
                    menu.setId(jsonPermissao.has("id") ? jsonPermissao.getString("id") : null);
                    menu.setFlag_inserir(jsonPermissao.has("flaginserir") ? jsonPermissao.getBoolean("flaginserir") : null);
                    menu.setFlag_alterar(jsonPermissao.has("flagalterar") ? jsonPermissao.getBoolean("flagalterar") : null);
                    menu.setFlag_excluir(jsonPermissao.has("flagexcluir") ? jsonPermissao.getBoolean("flagexcluir") : null);
                    menu.setMenuId(jsonPermissao.has("menu.id") ? jsonPermissao.getLong("menu.id") : null);
                    menu.setGrupoUsuarioID(jsonPermissao.has("grupousuario.id") ? jsonPermissao.getLong("grupousuario.id") : null);
                    menu.setServiceName(jsonPermissao.has("servicename") ? jsonPermissao.getString("servicename") : "");


                    serverResponse.setReturnObject(menu);

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
