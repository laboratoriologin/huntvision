package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Item;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public  class ItemRequest extends ObjectRequest<Item> {

    public ItemRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Item item) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(item.getDescricao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", item.getDescricao()));
        }

        if(item.getChave()!=null) {
            nameValuePairs.add(new BasicNameValuePair("chave", item.getChave()));
        }

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

       /* if (serverResponse.isOK()) {

            Item item;
            JSONObject jsonItem;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("item")) {

                    jsonItem = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("item");

                    item = new Item();
                    item.setId(jsonItem.has("id") ? jsonItem.getString("id") : null);
                    item.setDescricao((jsonItem.has("descricao") ? jsonItem.getString("descricao") : ""));
                    item.setChave(jsonItem.has("chave") ? jsonItem.getString("chave") : "");

                    serverResponse.setReturnObject(item);

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
