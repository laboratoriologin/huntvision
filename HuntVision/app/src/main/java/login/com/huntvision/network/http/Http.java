package login.com.huntvision.network.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import login.com.huntvision.models.ServerRequest;
import login.com.huntvision.models.ServerResponse;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class Http {

    private int TIMEOUT = 30000;
    private HttpClient httpClient;
    private static Http http;

    public static Http getInstance() {

        if (http == null) {
            http = new Http();
        }

        return http;
    }

    private Http() {

        HttpParams httpParameters = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
        HttpProtocolParams.setContentCharset(httpParameters, HTTP.ISO_8859_1);
        HttpProtocolParams.setHttpElementCharset(httpParameters, HTTP.ISO_8859_1);

        this.httpClient = new DefaultHttpClient(httpParameters);

    }

    private ServerResponse execute(HttpUriRequest httpUriRequest) {
        try {

            HttpResponse response = this.httpClient.execute(httpUriRequest);

            if (response.getStatusLine().getStatusCode() != ServerResponse.SC_OK) {
                if (response.getStatusLine().getStatusCode() == ServerResponse.SC_PRECONDITION_FAILED) {
                    HttpEntity entity = response.getEntity();
                    ServerResponse serverResponse = new ServerResponse(response.getStatusLine().getStatusCode(), null);
                    serverResponse.setMsgPreConditionFail(EntityUtils.toString(entity, HTTP.UTF_8));
                    return serverResponse;
                }
                return new ServerResponse(response.getStatusLine().getStatusCode(), null);
            } else {
                return new ServerResponse(response.getStatusLine().getStatusCode(), read(response));
            }

        } catch (ClientProtocolException e) {
            return new ServerResponse(0, null);
        } catch (IOException e) {
            return new ServerResponse(0, null);
        }
    }

    private JSONObject read(HttpResponse response) {

        JSONObject jsonObject = null;

        try {

            StringBuilder builder = new StringBuilder();
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            jsonObject = readJson(builder);

            if (jsonObject == null) {
                return readJsonByArray(builder);
            }
            return jsonObject;

        } catch (Exception ex) {
            return null;
        }
    }

    private JSONObject readJson(StringBuilder builder) {
        try {
            return new JSONObject(builder.toString());
        } catch (Exception ex) {
            return null;
        }
    }

    private JSONObject readJsonByArray(StringBuilder builder) {
        JSONObject jsonObject = new JSONObject();
        try {
            return jsonObject.put("", new JSONArray(builder.toString()));

        } catch (Exception ex) {
            return null;
        }
    }

    public ServerResponse post(ServerRequest serverRequest) {

        if (serverRequest.getFiles() != null) {

            return postMultiFormData(serverRequest);

        }

        HttpPost httppost = new HttpPost(serverRequest.getUrlBase());

        try {

            if (serverRequest.getParams() != null || serverRequest.getParams().size() != 0) {

                httppost.setEntity(new UrlEncodedFormEntity(serverRequest.getParams(), HTTP.ISO_8859_1));

            }

        } catch (UnsupportedEncodingException e) {

            return new ServerResponse(0, null);

        }

        return execute(httppost);
    }

    public ServerResponse postMultiFormData(ServerRequest serverRequest) {

        HttpPost httppost = new HttpPost(serverRequest.getUrlBase());

        try {

            MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

            if (serverRequest.getFiles() != null) {

                for (InputStreamWrapper wrapper : serverRequest.getFiles()) {

                    entity.addPart(wrapper.getFilename(), new InputStreamBody(wrapper.getStream(), "image/jpeg", wrapper.getFilename()));

                }

            }

            for (NameValuePair param : serverRequest.getParams()) {

                if (param.getValue() != null) {

                    entity.addPart(param.getName(), new StringBody(param.getValue()));

                }

            }

            httppost.setEntity(entity);

        } catch (UnsupportedEncodingException e) {

            return new ServerResponse(0, null);

        }

        return execute(httppost);
    }

    @SuppressWarnings("null")
    public ServerResponse put(ServerRequest serverRequest) {

        HttpPut httpput = new HttpPut(serverRequest.getUrlBase());

        try {

            if (serverRequest.getParams() != null || serverRequest.getParams().size() != 0) {

                httpput.setEntity(new UrlEncodedFormEntity(serverRequest.getParams(), HTTP.ISO_8859_1));

            }

        } catch (UnsupportedEncodingException e) {

            return new ServerResponse(0, null);

        }

        return execute(httpput);

    }

    public ServerResponse get(String url) {

        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }
}
