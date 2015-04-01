package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class QuestionarioRequest extends ObjectRequest<Questionario> {

    public QuestionarioRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Questionario questionario) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if( questionario.getPergunta()!=null) {
            nameValuePairs.add(new BasicNameValuePair("pergunta", questionario.getPergunta().toString()));
        }

        if( questionario.getStatus()!=null) {
            nameValuePairs.add(new BasicNameValuePair("status", questionario.getStatus().toString()));
        }
        if( questionario.getData()!=null) {
            nameValuePairs.add(new BasicNameValuePair("data", questionario.getData().toString()));
        }


        if( questionario.getItemId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("item.id", questionario.getItemId() != null ? questionario.getItemId().toString() : ""));
        }

        if( questionario.getItemId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("tipoquestionario.id", questionario.getTipoQuestionarioId() != null ? questionario.getTipoQuestionarioId().toString() : ""));
        }

        if( questionario.getUsuarioId()!=null) {
            nameValuePairs.add(new BasicNameValuePair("usuario.id", questionario.getUsuarioId() != null ? questionario.getUsuarioId().toString() : ""));
        }

        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {

      /*  if (serverResponse.isOK()) {

            Questionario questionario;
            JSONObject jsonQuestionario;

            try {

                if (((JSONObject) serverResponse.getReturnObject()).has("questionario")) {

                    jsonQuestionario = ((JSONObject) serverResponse.getReturnObject()).getJSONObject("questionario");

                    questionario = new Questionario();
                    questionario.setId(jsonQuestionario.has("id") ? jsonQuestionario.getString("id") : null);
                    questionario.setData(jsonQuestionario.has("data") ? jsonQuestionario.getString("data") : null);

                    questionario.setData( (jsonQuestionario.has("data") ? jsonQuestionario.getString("data") : "").toString());

                    questionario.setPergunta(jsonQuestionario.has("pergunta") ? jsonQuestionario.getString("pergunta") : null);
                    questionario.setStatus(jsonQuestionario.has("status") ? jsonQuestionario.getString("status") : null);
                    questionario.setItemId(jsonQuestionario.has("item.id") ? jsonQuestionario.getLong("item.id") : null);
                    questionario.setTipoQuestionarioId(jsonQuestionario.has("tipoquestionario.id") ? jsonQuestionario.getLong("tipoquestionario.id") : null);
                    questionario.setImagemId(jsonQuestionario.has("imagem.id") ? jsonQuestionario.getLong("imagem.id") : null);
                    questionario.setUsuarioId(jsonQuestionario.has("usuario.id") ? jsonQuestionario.getLong("usuario.id") : null);
                    questionario.setServiceName(jsonQuestionario.has("servicename") ? jsonQuestionario.getString("servicename") : "");


                    serverResponse.setReturnObject(questionario);

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
