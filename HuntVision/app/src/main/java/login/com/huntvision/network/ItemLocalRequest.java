package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class ItemLocalRequest extends ObjectRequest<ItemLocal> {

    public ItemLocalRequest(String url , ResponseListener listener) {
        super(url ,listener);
    }

    @Override
    protected List<NameValuePair> createParameters(ItemLocal itemLocal) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if(itemLocal.getItem_id()!=null) {
            nameValuePairs.add(new BasicNameValuePair("item_id", itemLocal.getItem_id().toString()));
        }

        if(itemLocal.getLocal_id() !=null) {
            nameValuePairs.add(new BasicNameValuePair("local_id", itemLocal.getLocal_id().toString()));
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
