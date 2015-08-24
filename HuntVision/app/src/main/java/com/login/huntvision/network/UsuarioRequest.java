package com.login.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import com.login.huntvision.models.ServerRequest;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.models.Usuario;
import com.login.huntvision.network.http.ResponseListener;
import com.login.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class UsuarioRequest extends ObjectRequest<Usuario> {

    public UsuarioRequest(String url, ResponseListener listener) {
        super(url, listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Usuario usuario) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (usuario.getCelular() != null) {
            nameValuePairs.add(new BasicNameValuePair("celular", usuario.getCelular()));
        }

        if (usuario.getLogin() != null) {
            nameValuePairs.add(new BasicNameValuePair("login", usuario.getLogin()));
        }

        if (usuario.getEmail() != null) {
            nameValuePairs.add(new BasicNameValuePair("email", usuario.getEmail()));
        }

        if (usuario.getNome() != null) {
            nameValuePairs.add(new BasicNameValuePair("nome", usuario.getNome()));
        }

        if (usuario.getSenha() != null) {
            nameValuePairs.add(new BasicNameValuePair("senha", usuario.getSenha()));
        }


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

    }


    public void getByLogin(Usuario usuario) {

        String urlLogin = url + "/" + usuario.getServiceName() + "/login/" + usuario.getLogin();

        ServerRequest serverRequest = new ServerRequest(ServerRequest.GET, urlLogin, createParameters(usuario));

        this.execute(serverRequest);
    }


    public void getLembrarSenha(Usuario usuario) {

        String urlLembrarSenha = url + "/" + usuario.getServiceName() + "/lembrar_senha/" + usuario.getLogin();

        ServerRequest serverRequest = new ServerRequest(ServerRequest.GET, urlLembrarSenha, createParameters(usuario));

        this.execute(serverRequest);
    }

}
