package com.login.huntvision.network.http;

import com.login.huntvision.models.ServerResponse;

/**
 * Created by Ricardo on 19/01/2015.
 */
public interface ResponseListener {
    void onResult(ServerResponse serverResponse);
}
