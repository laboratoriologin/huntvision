package login.com.huntvision.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class VistoriaRequest extends ObjectRequest<Vistoria> {

    public VistoriaRequest(String url, ResponseListener listener) {
        super(url, listener);
    }

    @Override
    protected List<NameValuePair> createParameters(Vistoria vistoria) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        if (vistoria.getClienteId() != null) {
            nameValuePairs.add(new BasicNameValuePair("cliente", vistoria.getClienteId()));
        }
        if (vistoria.getUsuarioId() != null) {
            nameValuePairs.add(new BasicNameValuePair("usuario", vistoria.getUsuarioId()));
        }

        if (vistoria.getData() != null) {
            nameValuePairs.add(new BasicNameValuePair("data", vistoria.getData()));
        }

        nameValuePairs.add(new BasicNameValuePair("latitude", vistoria.getLatitude().toString()));

        nameValuePairs.add(new BasicNameValuePair("longitude", vistoria.getLongitude().toString()));


        if (vistoria.getRespostas() != null) {

            for (int i = 0; i < vistoria.getRespostas().size(); i++) {

                nameValuePairs.add(new BasicNameValuePair("respostas[" + i + "].resposta", vistoria.getRespostas().get(i).getRespostaId()));

                nameValuePairs.add(new BasicNameValuePair("respostas[" + i + "].vistoria", vistoria.getId().toString()));

                nameValuePairs.add(new BasicNameValuePair("respostas[" + i + "].observacao", vistoria.getRespostas().get(i).getObservacao()));

                if (vistoria.getRespostas().get(i).getImagens() != null && !vistoria.getRespostas().get(i).getImagens().isEmpty()) {

                    nameValuePairs.add(new BasicNameValuePair("respostas[" + i + "].imagem", vistoria.getRespostas().get(i).getImagens().get(0).getCaminho()));

                    for (int j = 0; j < vistoria.getRespostas().get(i).getImagens().size(); j++) {

                        nameValuePairs.add(new BasicNameValuePair("respostas[" + i + "].imagens[" + j + "].caminho", vistoria.getRespostas().get(i).getImagens().get(j).getCaminho()));

                    }

                }

            }

        }

        return nameValuePairs;

    }

    @Override
    protected String getUrlPostMultiFormData(Vistoria object) {

        return url + "/" + object.getServiceName() + "/imagens";
    }

    @Override
    protected void handleResponse(ServerResponse serverResponse) {


    }


}
