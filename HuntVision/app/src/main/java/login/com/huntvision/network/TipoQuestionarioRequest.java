package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.TipoQuestionario;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.http.ResponseListener;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class TipoQuestionarioRequest extends ObjectRequest<TipoQuestionario> {

    public TipoQuestionarioRequest(ResponseListener listener) {
        super(listener);
    }

    @Override
    protected List<NameValuePair> createParameters(TipoQuestionario TipoQuestionario) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if( TipoQuestionario.getDescricao()!=null) {
            nameValuePairs.add(new BasicNameValuePair("descricao", TipoQuestionario.getDescricao().toString()));
        }


        return nameValuePairs;

    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
