package login.com.huntvision.network;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Base;
import login.com.huntvision.models.ServerRequest;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.HttpTask;
import login.com.huntvision.network.http.InputStreamWrapper;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 19/01/2015.
 */
public abstract class ObjectRequest<T extends Base> extends HttpTask {

    protected String url;

    public ObjectRequest(String urlBase, ResponseListener listener) {
        this.url = urlBase;
        this.listener = listener;
    }

    public void post(T obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.POST, getUrlPost(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    public void post(T obj, List<InputStreamWrapper> files) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.POST, getUrlPostMultiFormData(obj), createParameters(obj), files);

        this.execute(serverRequest);
    }

    public void put(T obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.PUT, getUrlPut(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    public void delete(T obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.DELETE, getUrlDelete(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    public void get(T obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.GET, getUrlGet(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    public void getAll(T obj) {

        ServerRequest serverRequest = new ServerRequest(ServerRequest.GET, getUrlGetAll(obj), createParameters(obj));

        this.execute(serverRequest);
    }

    protected List<NameValuePair> createParameters(T object) {
        return new ArrayList<NameValuePair>();
    }

    protected abstract void handleResponse(ServerResponse serverResponse);

    protected String getUrlPost(T object) {

        return url + "/" + object.getServiceName();

    }

    protected String getUrlPostMultiFormData(T object) {

        return url + "/" + object.getServiceName();

    }

    protected String getUrlPut(T object) {

        return url + "/" + object.getServiceName() + "/" + (object.getId() == null ? "" : object.getId());

    }

    protected String getUrlDelete(T object) {

        return url + "/" + object.getServiceName() + "/" + (object.getId() == null ? "" : object.getId());

    }

    protected String  getUrlGet(T object) {

        return url + "/" + object.getServiceName() + "/" + (object.getId() == null ? "" : object.getId());

    }

    protected String  getUrlGetAll(T object) {

        return url + "/" + object.getServiceName();

    }

    @Override
    protected void onPostExecute(ServerResponse result) {
        try {
            handleResponse(result);
            listener.onResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
