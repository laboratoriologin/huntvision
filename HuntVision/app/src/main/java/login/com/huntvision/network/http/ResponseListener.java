package login.com.huntvision.network.http;

import login.com.huntvision.models.ServerResponse;

/**
 * Created by Ricardo on 19/01/2015.
 */
public interface ResponseListener {
    void onResult(ServerResponse serverResponse);
}
