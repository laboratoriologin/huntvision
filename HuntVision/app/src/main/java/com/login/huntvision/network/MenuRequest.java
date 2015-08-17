package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Menu;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public abstract class MenuRequest extends ObjectRequest<Menu> {

    public MenuRequest(String url ,ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Menu menu) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


        if(menu.getDescricao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", menu.getDescricao()));
        }

        if(menu.getuRL()!=null) {
            nameValuePairs.add(new BasicNameValuePair("url", menu.getuRL()));

        }

        if(menu.getFlag_ativo()!=null) {
            nameValuePairs.add(new BasicNameValuePair("flagativo", menu.getFlag_ativo().toString()));


        }

        if(menu.getOrdem()!=null) {
            nameValuePairs.add(new BasicNameValuePair("ordem", menu.getOrdem().toString()));


        }

        if(menu.getMenuId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("menu.id", menu.getMenuId() != null ? menu.getMenuId().toString() : ""));


        }

        nameValuePairs.add(new BasicNameValuePair("managedbeanreset", menu.getManagedBeanReset()));




        

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

       /* if (serverResponse.isOK()) {

            Menu menu;
            JSONObject jsonMenu;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("menu")) {

                    jsonMenu = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("menu");

                    menu = new Menu();
                    menu.setId(jsonMenu.has("id") ? jsonMenu.getString("id") : null);
                    menu.setDescricao(jsonMenu.has("descricao") ? jsonMenu.getString("descricao") : "");
                    menu.setuRL(jsonMenu.has("url") ? jsonMenu.getString("url") : "");
                    menu.setOrdem(jsonMenu.has("ordem") ? jsonMenu.getLong("ordem") : null);
                    menu.setFlag_ativo(jsonMenu.has("flagativo") ? jsonMenu.getBoolean("flagativo") : null);
                    menu.setMenuId(jsonMenu.has("menu") ? jsonMenu.getLong("menu") : null);
                    menu.setManagedBeanReset(jsonMenu.has("managedbeanreset") ? jsonMenu.getString("managedbeanreset") : "");
                    menu.setServiceName(jsonMenu.has("servicename") ? jsonMenu.getString("servicename") : "");


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

        }*/
    }


}
