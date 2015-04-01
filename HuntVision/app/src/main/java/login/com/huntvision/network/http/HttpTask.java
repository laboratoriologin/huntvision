package login.com.huntvision.network.http;

import android.os.AsyncTask;

import login.com.huntvision.models.ServerRequest;
import login.com.huntvision.models.ServerResponse;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class HttpTask extends AsyncTask<ServerRequest, Void, ServerResponse> {

    protected ResponseListener listener;

    @Override
    protected ServerResponse doInBackground(ServerRequest... params) {

        ServerRequest serverRequest = params == null || params.length == 0 ? null : params[0];

        ServerResponse serverResponse;

        switch (serverRequest.getTypeMethod()) {

            case ServerRequest.GET:
                serverResponse = Http.getInstance().get(serverRequest.getUrlBase());
                break;
            case ServerRequest.POST:
                serverResponse = Http.getInstance().post(serverRequest);
                break;
            case ServerRequest.PUT:
                serverResponse = Http.getInstance().put(serverRequest);
                break;
            case ServerRequest.DELETE:
                serverResponse = new ServerResponse(500, null);
                break;
            default:
                serverResponse = new ServerResponse(500, null);
                break;
        }

        return serverResponse;
    }
}
